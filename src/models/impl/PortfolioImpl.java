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
  public Portfolio addStock(Stock stock1) {
    this.stocks.put(stock1.getTicker(), stock1);
    return this;
  }

  @Override
  public Stock getStock(String ticker) {
    return this.stocks.get(ticker);
  }

  @Override
  public double getValue(Date date) {
    double value = 0.0;
    for (Stock stock : stocks.values()) {
      value += stock.getClose(date);
    }
    return value;
  }

}
