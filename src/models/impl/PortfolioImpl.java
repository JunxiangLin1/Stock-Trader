package models.impl;

import java.util.HashMap;
import java.util.Map;

import models.Date;
import models.Portfolio;
import models.Stock;

public class PortfolioImpl implements Portfolio {
  private Map<String, Stock> stocks;

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
      value += stock.getClose(date);
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

}
