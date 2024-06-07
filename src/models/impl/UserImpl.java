package models.impl;

import java.util.HashMap;
import java.util.Map;

import models.Portfolio;
import models.Stock;
import models.User;

/**
 * Implementation of the {@link User} interface. This class represents a user
 * in the stock game application who can manage multiple portfolios and add stocks to them.
 */
public class UserImpl implements User {

  private Map<String, Portfolio> portfolios;

  /**
   * Constructs a {@code UserImpl} object with an empty portfolio map.
   */
  public UserImpl() {
    this.portfolios = new HashMap<>();
  }

  @Override
  public void createPortfolio(String portfolioName) {
    this.portfolios.put(portfolioName, new PortfolioImpl());
  }

  @Override
  public Portfolio getPortfolio(String portfolioName) {
    return this.portfolios.get(portfolioName);
  }

  @Override
  public void addStockToPortfolio(String portfolioName, Stock stock) {
    this.getPortfolio(portfolioName).addStock(stock);
  }
}
