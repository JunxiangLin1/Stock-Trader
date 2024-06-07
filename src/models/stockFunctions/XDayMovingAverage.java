package models.stockFunctions;

import models.Date;
import models.Stock;
import models.impl.DateImpl;

/**
 * A Function that returns the average closing values of the previous x days of the specified date.
 * Contains a date and an integer days to specify the days for the calculation.
 */
public class XDayMovingAverage implements StockFunction<Stock, Double> {
  Date date;
  int days;

  /**
   * The constructor for the {@code XDayMovingAverage} class.
   * Takes in a date and an integer days to specify the days for the calculation
   *
   * @param date the starting date for the calculation
   * @param days the number of previous days for the calculation
   */
  public XDayMovingAverage(Date date, int days) {
    this.date = date;
    this.days = days;
  }

  /**
   * Returns the average closing values of the previous x days of the specified date.
   *
   * @param stock the stock to apply the Function onto
   * @return the resulting average
   */
  @Override
  public Double apply(Stock stock) {
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
