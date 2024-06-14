package models;

/**
 * The {@code StockOrder} interface represents an order to buy or sell a specific amount of stock
 * on a particular date.
 * <p>
 * It provides methods to retrieve information about the order type (buy or sell),
 * the stock ticker symbol, the number of shares, and the date of the order.
 */
public interface StockOrder {

  /**
   * Retrieves the type of the stock order (BUY or SELL).
   *
   * @return the order type (BUY or SELL)
   */
  OrderType getOrderType();

  /**
   * Retrieves the ticker symbol of the stock associated with the order.
   *
   * @return the ticker symbol of the stock
   */
  String getTicker();

  /**
   * Retrieves the number of shares involved in the stock order.
   *
   * @return the number of shares in the order
   */
  double getShares();

  /**
   * Retrieves the date when the stock order was executed.
   *
   * @return the date of the stock order
   */
  Date getDate();
}
