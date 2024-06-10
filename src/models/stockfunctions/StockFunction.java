package models.stockfunctions;

import java.util.function.Function;

/**
 * An interface representing a function that can be applied to a stock to produce a result.
 *
 * @param <Stock> the input stock
 * @param <R> the type of the result
 */
public interface StockFunction<Stock, R> extends Function<Stock, R> {

  /**
   * Applies this Function onto the given {@link Stock}.
   *
   * @param stock the stock to apply the Function onto
   * @return the desired return value specified by the implementation
   **/
  R apply(Stock stock);
}
