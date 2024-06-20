package models;

import java.util.List;

/**
 * This interface represents a user in the stock game application.
 * A user can create portfolios, retrieve existing portfolios, and add stocks to portfolios.
 */
public interface User {

  /**
   * Creates a portfolio with the given name.
   *
   * @param portfolioName the name of the portfolio to create
   */
  void createPortfolio(String portfolioName);

  /**
   * Retrieves the portfolio with the given name.
   *
   * @param portfolioName the name of the portfolio to retrieve
   * @return the portfolio with the given name
   */
  DynamicPortfolio getPortfolio(String portfolioName);

  /**
   * Adds a stock to the specified portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @param stock the stock to add to the portfolio
   */
  void addStockToPortfolio(String portfolioName, Stock stock);


  List<String> getAllPortfolioNames();

}
