package models.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Date;
import models.Stock;

public class StockImpl implements Stock {
  String ticker;
  Map<Date, List<Double>> days;

  public StockImpl(String ticker) {
    this.ticker = ticker;
    this.days = new HashMap<Date, List<Double>>();
  }

  @Override
  public void addDate(Date date, double open, double close) {
    this.days.put(date, new ArrayList<Double>(List.of(open, close)));
  }

  private double getClose(Date day) {
    return this.days.get(day).get(1);
  }

  private double getOpen(Date day) {
    return this.days.get(day).get(0);
  }

  private boolean enoughDaysBefore(Date date, int days) {
    int numDays = 0;
    for (Date day : this.days.keySet()) {
      if (day.compareTo(date) <= 0) {
        numDays++;
      }
      if (numDays >= days) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean increase(Date start, Date end) throws IllegalArgumentException {
    if (start.compareTo(end) >= 0) {
      throw new IllegalArgumentException("Start date must come before End date.");
    }
    return this.getClose(end) > this.getClose(start);
  }

  @Override
  public double xDayMovingAverage(Date date, int days) {
    if (!this.enoughDaysBefore(date, days)) {
      throw new IllegalArgumentException("Not enough days before " + date.toString());
    }
    double average = 0;
    Date currentDate = new DateImpl(date.toString());
    for (int i = 0; i < days; i++) {
      if (this.days.containsKey(currentDate)) {
        average += this.getClose(currentDate);
      } else {
        i -= 1;
      }
      currentDate.advance(-1);
    }
    return average / days;
  }

  @Override
  public List<Date> xDayCrossovers(Date start, Date end, int day) {
    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException("Start date cannot be greater than end date.");
    }

    // Make a copy of the current date and initialize returning dates
    List<Date> dates = new ArrayList<Date>();
    Date currentDate = new DateImpl(start.toString());

    //While the current date has not reached the end
    while (currentDate.compareTo(end) <= 0) {
      // If the current day's close is greater than the moving average, add it to the dates.
      if (this.days.containsKey(currentDate)
              && (this.getClose(currentDate) > this.xDayMovingAverage(currentDate, day))){
        dates.add(new DateImpl(currentDate.toString()));
      }
      currentDate.advance(1);
    }
    return dates;
  }
}
