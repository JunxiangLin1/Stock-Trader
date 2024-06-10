package models.stockfunctions;

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

import models.Stock;
import models.impl.DateImpl;

/**
 * A Function that populates the data for a given stock using data from the Alpha Vantage API.
 */
public class PopulateData implements StockFunction<Stock, Void> {
  static final String apiKey = "BYAZRVQYKZU9H4X6";

  /**
   * Populates the data for a given stock using data from the Alpha Vantage API.
   *
   * @param stock the function argument
   * @return null
   */
  @Override
  public Void apply(Stock stock) {
    this.makeStockInfoCSV(stock, this.getAPIInputStream(stock));
    this.readStockInfoCSV(stock);
    return null;
  }

  private InputStream getAPIInputStream(Stock stock) {
    URL url;
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stock.getTicker() + "&apikey=" + PopulateData.apiKey + "&datatype=csv");
    }
    catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }
    try {
      return url.openStream();
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stock.getTicker());
    }
  }

  private void makeStockInfoCSV(Stock stock, InputStream in) {
    StringBuilder output = new StringBuilder();
    int b;
    try {
      while ((b = in.read()) != -1) {
        char character = (char) b;
        output.append(character);
      }
      // Write to CSV
      try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {
        writer.write(output.toString());
      } catch (FileNotFoundException e) {
        throw new FileNotFoundException("CSV file not found.");
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stock.getTicker());
    }
  }

  private void readStockInfoCSV(Stock stock) {
    List<List<String>> records = new ArrayList<>();
    try (Scanner scanner = new Scanner(new File("test.csv"))) {
      // Skip first line of labels
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        this.readStockInfoRow(stock, scanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      throw new InternalError("Read and write files are mismatched.");
    }
  }

  private void readStockInfoRow(Stock stock, String line) {
    String[] stockInfo = line.split(",");
    stock.addData(
            new DateImpl(stockInfo[0]),
            Double.parseDouble(stockInfo[1]),
            Double.parseDouble(stockInfo[2]),
            Double.parseDouble(stockInfo[3]),
            Double.parseDouble(stockInfo[4]),
            Double.parseDouble(stockInfo[5]));
  }

}
