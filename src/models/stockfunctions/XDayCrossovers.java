package models.stockfunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import models.Date;
import models.Stock;
import models.impl.DateImpl;

/**
 * A Function that returns the dates within the date range that has a current
 * closing value greater than its x day moving average.
 * Contains a start and end date for the range and a number to specify the number of days
 * for the x day moving average.
 */
public class XDayCrossovers implements StockFunction<Stock, List<Date>> {
  Date start;
  Date end;
  int day;

  /**
   * The constructor for the {@code XDayCrossovers} class.
   * Takes in a start and end date for the range and a number to specify the number of days
   * for the x day moving average.
   *
   * @param start the start date
   * @param end the end date
   * @param day the number of days for the x day moving average
   */
  public XDayCrossovers(Date start, Date end, int day) {
    this.start = start;
    this.end = end;
    this.day = day;
  }

  /**
   * Returns the dates within the date range that has a current
   * closing value greater than its x day moving average.
   *
   * @param stock the stock to apply the Function onto
   * @return the list of {@link Date} specified above
   */
  @Override
  public List<Date> apply(Stock stock) {
    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException("Start date cannot be greater than end date.");
    }

    // Make a copy of the current date and initialize returning dates
    List<Date> dates = new ArrayList<Date>();
    Date currentDate = new DateImpl(start.toString());

    //While the current date has not reached the end
    while (currentDate.compareTo(end) <= 0) {
      // If the current day's close is greater than the moving average, add it to the dates.
      Function<Stock, Double> movingAverageMacro = new XDayMovingAverage(currentDate, day);
      if (stock.getData().containsKey(currentDate)
              && (stock.getClose(currentDate) > movingAverageMacro.apply(stock))) {
        dates.add(new DateImpl(currentDate.toString()));
      }
      currentDate.advance(1);
    }
    return dates;
  }
}
