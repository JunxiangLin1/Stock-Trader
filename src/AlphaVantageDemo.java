import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import models.InputDataType;
import models.Stock;
import models.impl.DateImpl;
import models.impl.StockImpl;

public class AlphaVantageDemo {
  public static void main(String []args) {

    Stock stock = new StockImpl("GOOG");
    //the API key needed to use this web service.
    //Please get your own free API key here: https://www.alphavantage.co/
    //Please look at documentation here: https://www.alphavantage.co/documentation/
    String apiKey = "DR9Y7T4OGF18HDFA";
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

      Appendable date = new StringBuilder();
      Appendable open = new StringBuilder();
      Appendable close = new StringBuilder();
      boolean isFirstLine = true;

      while ((b=in.read())!=-1) {
        char character = (char)b;
        System.out.print(character);
        if (character == ',' || character == '\n' || character == '\r') {
          inputDataType = inputDataType.next();
          if (inputDataType == InputDataType.TIMESTAMP) {
            if (!isFirstLine) {
              stock.addDate(
                      new DateImpl(date.toString()),
                      Double.parseDouble(open.toString()),
                      Double.parseDouble(close.toString()));
              date = new StringBuilder();
              open = new StringBuilder();
              close = new StringBuilder();
            }
            isFirstLine = false;
          }
        } else if (!isFirstLine) {
          System.out.print(date.toString());
          if (inputDataType == InputDataType.TIMESTAMP) {
            date.append(character);
          } else if (inputDataType == InputDataType.CLOSE) {
            close.append(character);
          } else if (inputDataType == InputDataType.OPEN) {
            open.append(character);
          }
        }
      }
    }
    catch (IOException e) {
      throw new IllegalArgumentException("No price data found for "+ stockSymbol);
    }
    System.out.println("Return value: ");
  }
}
