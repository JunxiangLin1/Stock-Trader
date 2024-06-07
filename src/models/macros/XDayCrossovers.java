package models.macros;

import java.util.ArrayList;
import java.util.List;

import models.Date;
import models.Stock;
import models.impl.DateImpl;

public class XDayCrossovers implements StockMacro<List<Date>> {
  Date start;
  Date end;
  int day;

  public XDayCrossovers(Date start, Date end, int day) {
      this.start = start;
      this.end = end;
      this.day = day;
  }

  @Override
  public List<Date> execute(Stock stock) {
    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException("Start date cannot be greater than end date.");
    }

    // Make a copy of the current date and initialize returning dates
    List<Date> dates = new ArrayList<Date>();
    Date currentDate = new DateImpl(start.toString());

    //While the current date has not reached the end
    while (currentDate.compareTo(end) <= 0) {
      // If the current day's close is greater than the moving average, add it to the dates.
      StockMacro<Double> movingAverageMacro = new XDayMovingAverage(currentDate, day);
      if (stock.getData().containsKey(currentDate)
              && (stock.getClose(currentDate) > movingAverageMacro.execute(stock))){
        dates.add(new DateImpl(currentDate.toString()));
      }
      currentDate.advance(1);
    }
    return dates;
  }
}
