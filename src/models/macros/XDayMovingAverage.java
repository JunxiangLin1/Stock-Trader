package models.macros;

import models.Date;
import models.Stock;
import models.impl.DateImpl;

public class XDayMovingAverage implements StockMacro<Double> {
  Date date;
  int days;

  public XDayMovingAverage(Date date, int days) {
    this.date = date;
    this.days = days;
  }

  @Override
  public Double execute(Stock stock) {
    if (!this.enoughDaysBefore(stock, date, days)) {
      throw new IllegalArgumentException("Not enough days before " + date.toString());
    }
    double average = 0;
    Date currentDate = new DateImpl(date.toString());
    for (int i = 0; i < days; i++) {
      if (stock.getData().containsKey(currentDate)) {
        average += stock.getClose(currentDate);
      } else {
        i -= 1;
      }
      currentDate.advance(-1);
    }
    return average / days;
  }

  private boolean enoughDaysBefore(Stock stock, Date date, int days) {
    int numDays = 0;
    for (Date day : stock.getData().keySet()) {
      if (day.compareTo(date) <= 0) {
        numDays++;
      }
      if (numDays >= days) {
        return true;
      }
    }
    return false;
  }
}
