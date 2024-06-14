package models;

/**
 * The {@code Portfolio} interface represents a collection of stocks,
 * providing methods to manage and retrieve information about stocks within the portfolio.
 * This interface allows manipulation of stocks in a portfolio, including adding stocks,
 * retrieving stocks by their ticker symbols, and calculating portfolio values on specific dates.
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

  /**
   * Retrieves the composition of the portfolio, listing all stocks and their respective shares.
   *
   * @return a string representation of the portfolio composition
   */
  String getComposition();


  /**
   * Retrieves the value distribution of the portfolio on a specific date,
   * listing individual values of each stock held in the portfolio.
   *
   * @param date the date on which the value distribution is to be retrieved
   * @return a string representation of the value distribution of the portfolio
   */
  String getValueDistribution(Date date);


}
