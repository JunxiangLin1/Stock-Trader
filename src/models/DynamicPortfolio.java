package models;

/**
 * The {@code DynamicPortfolio} interface extends the {@code Portfolio} interface
 * to include dynamic management of stocks, such as rebalancing the portfolio
 * and handling stock orders with specified dates.
 */
public interface DynamicPortfolio extends Portfolio {

  /**
   * Rebalances the portfolio on a given date based on the specified weights for each stock.
   * The weights are specified as percentages and should sum up to 100.
   * The method adjusts the number of shares of each stock to match the desired weights.
   *
   * @param date the date on which the rebalancing should be performed
   * @param weight the target weights for each stock as percentages
   * @throws IllegalArgumentException if the weights do not sum up to 100 or if the date is invalid
   */
  void rebalance(Date date, int... weight);

  /**
   * Sells a specified number of shares of a stock on a given date.
   * The method records the transaction in the portfolio's history.
   *
   * @param ticker the ticker symbol of the stock to be sold
   * @param shares the number of shares to be sold
   * @param date the date on which the shares are sold
   * @throws IllegalArgumentException if the ticker is not in the portfolio,
   *                                  if the number of shares is greater than the current holdings,
   *                                  or if the date is invalid
   */
  void sellShares(String ticker, double shares, Date date);

  /**
   * Buys a specified number of shares of a stock on a given date.
   * The method records the transaction in the portfolio's history.
   *
   * @param ticker the ticker symbol of the stock to be bought
   * @param shares the number of shares to be bought
   * @param date the date on which the shares are bought
   * @throws IllegalArgumentException if the ticker is invalid,
   *                                  if the number of shares is less than or equal to zero,
   *                                  or if the date is invalid
   */
  void buyShares(String ticker, int shares, Date date);

}
