package models;

public interface Portfolio {

  void addStock(Stock stock1);

  Stock getStock(String ticker);

  double getValue(Date date);

}
