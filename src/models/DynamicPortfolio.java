package models;

public interface DynamicPortfolio extends Portfolio {

  void rebalance(Date date, int... weight);

  void sellShares(String ticker, double shares, Date date);

  void buyShares(String ticker, int shares, Date date);

}
