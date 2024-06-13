package models;

public interface StockOrder {
  OrderType getOrderType();
  String getTicker();
  double getShares();
  Date getDate();
}
