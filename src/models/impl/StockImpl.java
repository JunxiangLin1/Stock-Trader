package models.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Date;
import models.Stock;
import models.macros.PopulateDataMacro;

public class StockImpl implements Stock {
  String ticker;
  Map<Date, List<Double>> data;
  int shares;

  public StockImpl(String ticker, int shares) {
    this.ticker = ticker;
    this.data = new HashMap<Date, List<Double>>();
    this.shares = shares;
    new PopulateDataMacro().execute(this);
  }

  @Override
  public void addData(Date date, double open, double high, double low, double close,
                      double volume) {
    this.data.put(date, new ArrayList<Double>(List.of(open, high, low, close, volume)));
  }

  @Override
  public HashMap<Date, List<Double>> getData() {
    return new HashMap<>(this.data);
  }

  @Override
  public int getShares() {
    return this.shares;
  }

  @Override
  public String getTicker() {
    return this.ticker;
  }

  @Override
  public double getOpen(Date day) {
    return this.data.get(day).get(0);
  }

  @Override
  public double getHigh(Date day) {
    return this.data.get(day).get(1);
  }

  @Override
  public double getLow(Date day) {
    return this.data.get(day).get(2);
  }

  @Override
  public double getClose(Date day) {
    return this.data.get(day).get(3);
  }

  @Override
  public double getVolume(Date day) {
    return this.data.get(day).get(3);
  }
}
