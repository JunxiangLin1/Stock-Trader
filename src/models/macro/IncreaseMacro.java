package models.macro;

import models.Date;
import models.Stock;

public class IncreaseMacro implements StockMacro<Boolean> {
  Date start;
  Date end;

  public IncreaseMacro(Date start, Date end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public Boolean execute(Stock stock) {
    if (this.start.compareTo(this.end) >= 0) {
      throw new IllegalArgumentException("Start date must come before End date.");
    }
    return stock.getClose(end) > stock.getClose(start);
  }
}
