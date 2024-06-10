package models.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import models.Date;
import models.Portfolio;
import models.Stock;

/**
 * Implementation of the {@link Portfolio} interface. This class represents
 * a portfolio of stocks, providing methods to add stocks, retrieve stocks by
 * their ticker symbols, and calculate the total value of the portfolio on a given date.
 */
public class PortfolioImpl implements Portfolio {

  private Map<String, Stock> stocks;

  /**
   * Constructs a {@code PortfolioImpl} object with an empty collection of stocks.
   */
  public PortfolioImpl() {
    this.stocks = new HashMap<>();
  }

  @Override
  public void addStock(Stock stock1) {
    this.stocks.put(stock1.getTicker(), stock1);
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
    if (other instanceof PortfolioImpl) {
      PortfolioImpl otherPortfolioImpl = (PortfolioImpl) other;
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

}
