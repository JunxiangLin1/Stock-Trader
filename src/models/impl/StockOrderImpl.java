package models.impl;

import models.Date;
import models.OrderType;
import models.Stock;
import models.StockOrder;

public class StockOrderImpl implements StockOrder {
  OrderType orderType;
  String ticker;
  double shares;
  Date date;

  public StockOrderImpl(OrderType orderType, String ticker, double shares, Date date) {
    this.orderType = orderType;
    this.ticker = ticker;
    this.shares = shares;
    this.date = date;
  }

  @Override
  public OrderType getOrderType() {
    return this.orderType;
  }

  @Override
  public String getTicker() {
    return this.ticker;
  }

  @Override
  public double getShares() {
    return this.shares;
  }

  @Override
  public Date getDate() {
    return this.date;
  }
}
