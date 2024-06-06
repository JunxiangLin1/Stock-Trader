package models;

public interface Portfolio {

  Portfolio addStock(Stock stock1);

  Stock getStock(String ticker);

  double getValue(Date date);

}
