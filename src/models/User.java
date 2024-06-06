package models;

public interface User {
  void createPortfolio(String portfolioName);

  Portfolio getPortfolio(String portfolioName);

  void addStockToPortfolio(String portfolioName, Stock stock);
}
