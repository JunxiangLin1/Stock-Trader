package models;

import java.io.FileNotFoundException;
import java.util.List;

public interface Stock {

  boolean increase (Date start, Date end);

  double xDayMovingAverage(Date date, int days);

  List<Date> xDayCrossovers(Date start, Date end, int day);

  void addDate(Date date, double open, double close);

  String getTicker();

  double getClose(Date day);

  double getOpen(Date day);

  public void populateStockData();
}
