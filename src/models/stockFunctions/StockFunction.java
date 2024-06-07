package models.stockFunctions;

import java.util.function.Function;

public interface StockFunction<Stock, R> extends Function<Stock, R> {

  /**
   * Applies this Function onto the given {@link Stock}.
   *
   * @param stock the stock to apply the Function onto
   * @return the desired return value specified by the implementation
   **/
  R apply(Stock stock);
}
