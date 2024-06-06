package StockInformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.InputDataType;
import models.Stock;
import models.impl.StockImpl;

public class AlphaVantageDemo {
  public static void main(String []args) {

    Stock stock = new StockImpl("GOOG");
    //the API key needed to use this web service.
    //Please get your own free API key here: https://www.alphavantage.co/
    //Please look at documentation here: https://www.alphavantage.co/documentation/
    String apiKey = "OMS6CJPTPC6RP2PW";
    String stockSymbol = stock.getTicker(); //ticker symbol for Google
    InputDataType inputDataType = InputDataType.TIMESTAMP;
    URL url;

    try {
      /*
      create the URL. This is the query to the web service. The query string
      includes the type of query (DAILY stock prices), stock symbol to be
      looked up, the API key and the format of the returned
      data (comma-separated values:csv). This service also supports JSON
      which you are welcome to use.
       */
      url = new URL("https://www.alphavantage"
                    + ".co/query?function=TIME_SERIES_DAILY"
                    + "&outputsize=full"
                    + "&symbol"
                    + "=" + stockSymbol + "&apikey="+apiKey+"&datatype=csv");
    }
    catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
                                 + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      /*
      Execute this query. This returns an InputStream object.
      In the csv format, it returns several lines, each line being separated
      by commas. Each line contains the date, price at opening time, highest
      price for that date, lowest price for that date, price at closing time
      and the volume of trade (no. of shares bought/sold) on that date.

      This is printed below.
       */
      in = url.openStream();

      int b;

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

      List<String> values = new ArrayList<String>();
      // Read Data from CSV into Stock
      try (Scanner scanner = new Scanner(new File("test.csv"));) {
        while (scanner.hasNextLine()) {
          Scanner rowScanner = new Scanner(scanner.nextLine());
            rowScanner.useDelimiter(",?\\r?");
            while (rowScanner.hasNext()) {
              values.add(rowScanner.next());
            }
          }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      for (int i = 7; i < values.size(); i++) {
        System.out.println(values.get(i));
      }
    }
    catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
  }
}
