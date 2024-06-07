package models.stockFunctions;

import models.Date;
import models.Stock;

/**
 * A Function that returns whether the stock's closing price has increased in the
 * specified time span.
 * Contains a start and end date for the date range.
 */
public class Increase implements StockFunction<Stock, Boolean> {
  Date start;
  Date end;

  /**
   * The constructor for the {@code Increase} class.
   * Takes in a start and end date for the date range.
   *
   * @param start the start date
   * @param end   the end date
   */
  public Increase(Date start, Date end) {
    this.start = start;
    this.end = end;
  }

  /**
   * Returns whether the stock's closing price has increased in the specified time span.
   *
   * @param stock the function argument
   * @return whether the stock's closing price has increased in the specified time span
   */
  @Override
  public Boolean apply(Stock stock) {
    if (this.start.compareTo(this.end) >= 0) {
      throw new IllegalArgumentException("Start date must come before End date.");
    }
    return stock.getClose(end) > stock.getClose(start);
  }
}
