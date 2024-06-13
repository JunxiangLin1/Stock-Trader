package models;

/**
 * This interface represents a Portfolio which can hold multiple stocks.
 * It provides methods to add stocks, retrieve a specific stock by its ticker,
 * and calculate the total value of the portfolio on a given date.
 */
public interface StaticPortfolio extends Portfolio {

  void sellShares(String ticker, double shares);

  void buyShares(String ticker, int shares);

}
