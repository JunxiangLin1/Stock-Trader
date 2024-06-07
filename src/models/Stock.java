package models;

import java.util.HashMap;
import java.util.List;

public interface Stock {

  void addData(Date date, double open, double high, double low, double close,
               double volume);

  HashMap<Date, List<Double>> getData();

  int getShares();

  String getTicker();

  double getOpen(Date day);

  double getHigh(Date day);

  double getLow(Date day);

  double getVolume(Date day);

  double getClose(Date day);
}
