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
 * <p>
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
}
