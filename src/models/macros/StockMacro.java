package models.macros;

import models.Stock;

public interface StockMacro<T> {

  /**
   * Applies this macro onto the given {@link Stock}.
   *
   * @param stock the stock to apply the macro onto
   */
  T execute(Stock stock);
}
