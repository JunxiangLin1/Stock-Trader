package models.impl;

import java.util.HashMap;
import java.util.Map;

import models.Portfolio;
import models.Stock;
import models.User;

public class UserImpl implements User {
  private Map<String, Portfolio> portfolios;

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
