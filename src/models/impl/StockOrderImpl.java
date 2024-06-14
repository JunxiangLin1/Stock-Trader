package models.impl;

import models.Date;
import models.OrderType;
import models.StockOrder;

/**
 * Implementation of the {@link StockOrder} interface.
 * <p>
 * This class represents an order for a specific stock, including details such as order type (BUY or SELL),
 * ticker symbol, number of shares, and order date.
 */
public class StockOrderImpl implements StockOrder {
  OrderType orderType;
  String ticker;
  double shares;
  Date date;

  /**
   * Constructs a {@code StockOrderImpl} object with the specified order details.
   *
   * @param orderType the type of the order (BUY or SELL)
   * @param ticker    the ticker symbol of the stock associated with the order
   * @param shares    the number of shares involved in the order
   * @param date      the date when the order was executed
   */
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
