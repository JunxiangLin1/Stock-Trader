package models;

/**
 * This interface represents a Date with basic functionalities such as
 * getting the day, month, and year, advancing the date by a certain number
 * of days, and comparing dates.
 */
public interface Date {

  /**
   * Returns the hash code value for this Date.
   *
   * @return the hash code value for this Date
   */
  int hashCode();

  /**
   * Compares this Date with the specified other Date.
   * First compares year, month, and finally day.
   *
   * @param otherDate the Date to be compared
   * @return a negative integer, zero, or a positive integer as this Date
   *         is earlier than, equal to, or later than the specified Date
   */
  int compareTo(Date otherDate);

  /**
   * Indicates whether some other date object is "equal to" this Date.
   *
   * @param other the reference object with which to compare
   * @return {@code true} if this object is the same as the object argument;
   *         {@code false} otherwise
   */
  boolean equals(Object other);

  /**
   * Returns the month of this Date.
   *
   * @return the month of this Date
   */
  int getMonth();

  /**
   * Returns the year of this Date.
   *
   * @return the year of this Date
   */
  int getYear();

  /**
   * Returns the day of this Date.
   *
   * @return the day of this Date
   */
  int getDay();

  /**
   * Advances the Date by the given days and stops if advances before 1 Jan 0000.
   *
   * @param days the amount of days the models.Date will shift by
   */
  void advance(int days);

}
