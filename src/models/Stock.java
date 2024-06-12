package models;

import java.util.HashMap;
import java.util.List;

/**
 * This interface represents a stock with methods to add and retrieve stock data,
 * and to get various stock attributes such as ticker, shares, and daily price values.
 */
public interface Stock {

  /**
   * Adds data for a given date.
   *
   * @param date   the date of the stock data
   * @param open   the opening price
   * @param high   the highest price
   * @param low    the lowest price
   * @param close  the closing price
   * @param volume the volume of the stock traded
   */
  void addData(Date date, double open, double high, double low, double close,
               double volume);

  /**
   * Retrieves all the stock data.
   *
   * @return a map of dates to their corresponding stock data
   */
  HashMap<Date, List<Double>> getData();

  /**
   * Gets the number of shares.
   *
   * @return the number of shares
   */
  int getShares();

  /**
   * Gets the ticker symbol of the stock.
   *
   * @return the ticker symbol
   */
  String getTicker();

  /**
   * Gets the opening price for a given day.
   *
   * @param day the date of the stock data
   * @return the opening price
   */
  double getOpen(Date day);

  /**
   * Gets the highest price for a given day.
   *
   * @param day the date of the stock data
   * @return the highest price
   */
  double getHigh(Date day);

  /**
   * Gets the lowest price for a given day.
   *
   * @param day the date of the stock data
   * @return the lowest price
   */
  double getLow(Date day);

  /**
   * Gets the volume of stock traded for a given day.
   *
   * @param day the date of the stock data
   * @return the volume of stock traded
   */
  double getVolume(Date day);

  /**
   * Gets the closing price for a given day.
   *
   * @param day the date of the stock data
   * @return the closing price
   */
  double getClose(Date day);

  void setShares(int shareAmount);
}
