package models.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import models.Date;
import models.StaticPortfolio;
import models.Stock;

/**
 * Implementation of the {@link StaticPortfolio} interface. This class represents
 * a portfolio of stocks, providing methods to add stocks, retrieve stocks by
 * their ticker symbols, and calculate the total value of the portfolio on a given date.
 */
public class StaticPortfolioImpl implements StaticPortfolio {

  private Map<String, Stock> stocks;

  /**
   * Constructs a {@code PortfolioImpl} object with an empty collection of stocks.
   */
  public StaticPortfolioImpl() {
    this.stocks = new HashMap<>();
  }

  @Override
  public void addStock(Stock stock1) {
    this.stocks.put(stock1.getTicker(), stock1);
  }

  @Override
  public void buyShares(String ticker, int shares) {
    if (shares <= 0) {
      throw new IllegalArgumentException("Shares to buy must be greater than zero.");
    }
    if (this.stocks.containsKey(ticker)) {
      Stock stock = this.stocks.get(ticker);
      stock.setShares(stock.getShares() + shares);
    } else {
      Stock newStock = new StockImpl(ticker, shares); // stockImpl or simpleStock?
      this.stocks.put(ticker, newStock);
    }
  }

  @Override
  public void sellShares(String ticker, double shares) {
    if (!this.stocks.containsKey(ticker)) {
      throw new IllegalArgumentException("Stock not in portfolio.");
    }
    Stock stock = this.stocks.get(ticker);
    double currentShares = stock.getShares();
    if (shares > currentShares) {
      throw new IllegalArgumentException("Not enough shares to sell.");
    }
    stock.setShares(currentShares - shares);
    if (stock.getShares() == 0) {
      this.stocks.remove(ticker);
    }
  }

  @Override
  public Stock getStock(String ticker) {
    if (!this.stocks.containsKey(ticker)) {
      throw new IllegalArgumentException("Not in list bitch");
    } else {
      return this.stocks.get(ticker);
    }
  }

  @Override
  public double getValue(Date date) {
    double value = 0.0;
    for (Stock stock : stocks.values()) {
      value += (stock.getClose(date) * stock.getShares());
    }
    return value;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof StaticPortfolioImpl) {
      StaticPortfolioImpl otherPortfolioImpl = (StaticPortfolioImpl) other;
      if (this == otherPortfolioImpl) {
        return true;
      }
      return this.stocks.equals(otherPortfolioImpl.stocks);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(stocks);
  }

  @Override
  public String getComposition() {
    StringBuilder composition = new StringBuilder();
    for (Stock stock : stocks.values()) {
      composition.append("Stock: ")
              .append(stock.getTicker())
              .append(", Shares: ")
              .append(stock.getShares())
              .append("\n");
    }
    return composition.toString();
  }

  @Override
  public String getValueDistribution(Date date) {
    StringBuilder distribution = new StringBuilder();
    distribution.append("Individual Stock Values:\n");
    for (Stock stock : stocks.values()) {
      double stockValue = stock.getClose(date) * stock.getShares();
      distribution.append("Stock: ").append(stock.getTicker()).append(", Value: $").append(stockValue).append("\n");
    }
    return distribution.toString();
  }
}
