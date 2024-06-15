package models.impl;

import java.util.ArrayList;
import java.util.List;


import models.Date;
import models.DynamicPortfolio;
import models.OrderType;
import models.Stock;
import models.StockOrder;

/**
 * Implementation of the {@link DynamicPortfolio} interface.
 * This class represents a dynamic portfolio that allows buying and selling stocks
 * based on predefined orders and rebalancing the portfolio based on specified weights.
 */
public class DynamicPortfolioImpl implements DynamicPortfolio {

  StaticPortfolioImpl portfolio;

  List<StockOrder> stockOrders;

  /**
   * Constructs a {@code DynamicPortfolioImpl} object with an initial static portfolio.
   *
   * @param portfolio the static portfolio to initialize this dynamic portfolio
   */
  public DynamicPortfolioImpl(StaticPortfolioImpl portfolio) {
    this.portfolio = portfolio;
    this.stockOrders = new ArrayList<>();
  }

  @Override
  public void addStock(Stock stock1) {
    portfolio.addStock(stock1);
  }

  @Override
  public Stock getStock(String ticker) {
    return portfolio.getStock(ticker);
  }

  @Override
  public double getValue(Date date) {
    double totalValue = 0;
    for (String stock : this.getOrderedStocksAtDate(date)) {
      totalValue += getStockValueAtDate(stock, date);
    }
    return totalValue;
  }

  private List<String> getOrderedStocksAtDate(Date date) {
    List<String> stockList = new ArrayList<>();
    for (StockOrder stockOrder : stockOrders) {
      if (date.compareTo(stockOrder.getDate()) <= 0
              && !stockList.contains(stockOrder.getTicker())) {
        stockList.add(stockOrder.getTicker());
      }
    }
    return stockList;
  }

  private double getStockValueAtDate(String stock, Date date) {
    double totalValue = 0;
    for (StockOrder stockOrder : stockOrders) {
      if (date.compareTo(stockOrder.getDate()) <= 0
              && stock.equals(stockOrder.getTicker())) {
        totalValue += (stockOrder.getOrderType() == OrderType.BUY ? 1 : -1)
                * (new StockImpl(stockOrder.getTicker(), 0).getClose(date)
                * stockOrder.getShares());
      }
    }
    return totalValue;
  }

  @Override
  public void rebalance(Date date, int... weight) {
    double totalValue = this.getValue(date);
    List<String> orderedStocks = getOrderedStocksAtDate(date);
    for (int i = 0; i < orderedStocks.size(); i++) {
      String stock = orderedStocks.get(i);
      double difference = this.getStockValueAtDate(stock, date) - totalValue * weight[i] / 100;
      double singleStockValue = new StockImpl(stock, 0).getClose(date);
      if (difference < 0) {
        this.buyShares(stock, (int) Math.ceil(-1 * difference / singleStockValue), date);
        difference += singleStockValue * (int) Math.ceil(-1 * difference / singleStockValue);
      }
      this.sellShares(stock, difference / singleStockValue, date);
    }
  }

  @Override
  public String getComposition() {
    return portfolio.getComposition();
  }

  @Override
  public String getValueDistribution(Date date) {
    return portfolio.getValueDistribution(date);
  }

  @Override
  public void sellShares(String ticker, double shares, Date date) {
    this.portfolio.sellShares(ticker, shares);
    this.stockOrders.add(new StockOrderImpl(OrderType.SELL, ticker, shares, date));
  }

  @Override
  public void buyShares(String ticker, int shares, Date date) {
    this.portfolio.buyShares(ticker, shares);
    this.stockOrders.add(new StockOrderImpl(OrderType.BUY, ticker, shares, date));
  }

  private String performanceDuringMonth(Date date) {
    Date lastDate = new DateImpl(date.toString());
    while (lastDate.getMonth() == date.getMonth()) {
      lastDate.advance(1);
    }
    Stock stock = new StockImpl("META", 0);
    boolean dateNotFound = true;
    while (dateNotFound) {
      System.out.println("finding date");
      lastDate.advance(-1);
      try {
        stock.getClose(lastDate);
        dateNotFound = false;
      } catch (Exception ignored) {
        // Ignore as stock has been found
      }
    }
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < this.getValue(lastDate) / 1000; i++) {
      result.append('*');
    }
    return result.toString();
  }

  private String performanceDuringYear(Date date) {
    Date lastDate = new DateImpl(date.toString());
    while (lastDate.getYear() == date.getYear()) {
      lastDate.advance(1);
    }
    Stock stock = new StockImpl("META", 0);
    boolean dateNotFound = true;
    while (dateNotFound) {
      lastDate.advance(-1);
      System.out.println("finding date");
      try {
        stock.getClose(lastDate);
        dateNotFound = false;
      } catch (Exception ignored) {
        // Ignore as stock has been found
      }
    }
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < this.getValue(lastDate) / 1000; i++) {
      result.append('*');
    }
    return result.toString();
  }

  private String performanceDuringDay(Date date) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < this.getValue(date) / 1000; i++) {
      result.append('*');
    }
    return result.toString();
  }


  @Override
  public String performanceOverTime(Date start, Date end) {
    StringBuilder result = new StringBuilder();
    int totalDays = start.daysUntil(end);
    Date tempStart = new DateImpl(start.toString());
    System.out.println("starts here");
    if (totalDays / 30 >= 12 * 5) {
      // Go by year
      System.out.println("while 1 starts here");
      while (tempStart.compareTo(end) < 0) {
        System.out.println("starts here");
        result.append(tempStart.toString()).append(": ")
                .append(this.performanceDuringYear(tempStart))
                .append(System.lineSeparator());
        tempStart.advance(365);
      }
    } else if (totalDays / 30 >= 5) {
      int monthSkip = 1;
      if (totalDays / 30 > 30) {
        monthSkip = 2;
      }
      System.out.println("while 2 starts here");
      while (tempStart.compareTo(end) < 0) {
        System.out.println("starts here");
        result.append(tempStart.toString()).append(": ")
                .append(this.performanceDuringMonth(tempStart))
                .append(System.lineSeparator());
        tempStart.advance(monthSkip * 30);
      }
      // Go by month
    } else if (totalDays / 30 >= 0) {
      int daySkip = (int) Math.ceil((double)totalDays / 30);
      System.out.println("while 3 starts here");
      while (tempStart.compareTo(end) < 0) {
        System.out.println("starts here");
        result.append(tempStart.toString()).append(": ")
                .append(this.performanceDuringDay(tempStart))
                .append(System.lineSeparator());
        tempStart.advance(daySkip);
      }
    }
    result.append("Scale: * = $1000");
    return result.toString();
  }

}
