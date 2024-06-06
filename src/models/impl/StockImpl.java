package models.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import models.Date;
import models.InputDataType;
import models.Stock;

public class StockImpl implements Stock {
  String ticker;
  Map<Date, List<Double>> days;

  public StockImpl(String ticker) {
    this.ticker = ticker;
    this.days = new HashMap<Date, List<Double>>();
  }

  @Override
  public void addDate(Date date, double open, double close) {
    this.days.put(date, new ArrayList<Double>(List.of(open, close)));
  }

  @Override
  public String getTicker() {
    return this.ticker;
  }

  @Override
  public double getClose(Date day) {
    return this.days.get(day).get(1);
  }

  @Override
  public double getOpen(Date day) {
    return this.days.get(day).get(0);
  }

  private boolean enoughDaysBefore(Date date, int days) {
    int numDays = 0;
    for (Date day : this.days.keySet()) {
      if (day.compareTo(date) <= 0) {
        numDays++;
      }
      if (numDays >= days) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean increase(Date start, Date end) throws IllegalArgumentException {
    if (start.compareTo(end) >= 0) {
      throw new IllegalArgumentException("Start date must come before End date.");
    }
    return this.getClose(end) > this.getClose(start);
  }

  @Override
  public double xDayMovingAverage(Date date, int days) {
    if (!this.enoughDaysBefore(date, days)) {
      throw new IllegalArgumentException("Not enough days before " + date.toString());
    }
    double average = 0;
    Date currentDate = new DateImpl(date.toString());
    for (int i = 0; i < days; i++) {
      if (this.days.containsKey(currentDate)) {
        average += this.getClose(currentDate);
      } else {
        i -= 1;
      }
      currentDate.advance(-1);
    }
    return average / days;
  }

  @Override
  public List<Date> xDayCrossovers(Date start, Date end, int day) {
    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException("Start date cannot be greater than end date.");
    }

    // Make a copy of the current date and initialize returning dates
    List<Date> dates = new ArrayList<Date>();
    Date currentDate = new DateImpl(start.toString());

    //While the current date has not reached the end
    while (currentDate.compareTo(end) <= 0) {
      // If the current day's close is greater than the moving average, add it to the dates.
      if (this.days.containsKey(currentDate)
              && (this.getClose(currentDate) > this.xDayMovingAverage(currentDate, day))){
        dates.add(new DateImpl(currentDate.toString()));
      }
      currentDate.advance(1);
    }
    return dates;
  }

  private void makeStockInfoCSV(InputStream in) {
    StringBuilder output = new StringBuilder();
    int b;
    try {
      while ((b=in.read())!=-1) {
        char character = (char)b;
        output.append(character);
      }
      // Write to CSV
      try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {
        writer.write(output.toString());
      } catch (FileNotFoundException e) {
        throw new FileNotFoundException("CSV file not found.");
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + this.getTicker());
    }
  }

  private void readStockInfoCSV() throws FileNotFoundException {
    List<List<String>> records = new ArrayList<>();
    Scanner scanner = new Scanner(new File("test.csv"));
      while (scanner.hasNextLine()) {
        this.readStockInfoRow(scanner.nextLine());
      }
  }

  private void readStockInfoRow(String line) {
    String[] stockInfo = line.split(",");
    this.addDate(
            new DateImpl(stockInfo[0]),
            Double.parseDouble(stockInfo[1]),
            Double.parseDouble(stockInfo[4]));
  }

  private InputStream getAPIInputStream() {
    String apiKey = "OMS6CJPTPC6RP2PW";
    URL url;
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + this.getTicker() + "&apikey=" + apiKey + "&datatype=csv");
    }
    catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }
    try {
      return url.openStream();
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + this.getTicker());
    }
  }

  private void populateStockData() throws FileNotFoundException {
    this.makeStockInfoCSV(this.getAPIInputStream());
    this.readStockInfoCSV();
  }


}
