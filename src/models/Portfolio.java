package models;

/**
 * This interface represents a Portfolio which can hold multiple stocks.
 * It provides methods to add stocks, retrieve a specific stock by its ticker,
 * and calculate the total value of the portfolio on a given date.
 */
public interface Portfolio {

  /**
   * Adds a stock to the portfolio.
   *
   * @param stock1 the stock to be added to the portfolio
   */
  void addStock(Stock stock1);

  /**
   * Retrieves a stock from the portfolio based on its ticker symbol.
   *
   * @param ticker the ticker symbol of the stock to be retrieved
   * @return the stock associated with the given ticker symbol
   * @throws IllegalArgumentException if the stock with the given ticker is not in the portfolio
   */
  Stock getStock(String ticker);

  /**
   * Calculates the total value of the portfolio on a given date.
   *
   * @param date the date on which the portfolio value is to be calculated
   * @return the total value of the portfolio on the given date
   */
  double getValue(Date date);

  void sellStock(String ticker, int shares);

  void buyShares(String ticker, int shares);

  String getComposition();

  String getValueDistribution(Date date);
}
