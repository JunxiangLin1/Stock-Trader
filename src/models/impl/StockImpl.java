package models.impl;

import models.stockfunctions.PopulateData;

/**
 * Extends the {@link SimpleStock} class.
 * This class represents a stock with its ticker symbol, number of shares, and daily price data.
 * Represents a stock with data from Vantage API.
 */
public class StockImpl extends SimpleStock {

  /**
   * Constructs a {@code StockImpl} object with the specified ticker and number of shares.
   * Then populates the stock using data from Alpha Vantage API
   *
   * @param ticker the ticker symbol of the stock
   * @param shares the number of shares
   */
  public StockImpl(String ticker, int shares) {
    super(ticker, shares);
    new PopulateData().apply(this);
  }
}
