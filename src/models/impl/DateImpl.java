package models.impl;

import java.util.List;

import models.Date;

public class DateImpl implements Date {

  private int day;
  private int month;
  private int year;
  private final String date;

  /**
   * Construct a {@code models.impl.DateImpl} object.
   *
   * @param date the date in question as a formatted string
   * @throws IllegalArgumentException if the models.Date is not valid
   */
  public DateImpl(String date) {
    this.date = date;
    String[] parts = date.split("-");
    this.day = Integer.parseInt(parts[2]);
    this.month = Integer.parseInt(parts[1]);
    this.year = Integer.parseInt(parts[0]);
    if (!this.isValid()) {
      throw new IllegalArgumentException("SMH, Skill issue bruh, your mom is INVALID!!!");
    }
  }

  // Returns if the year is a leap year
  private boolean isLeapYear() {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
  }

  // Returns the maximum days of the current month
  private int maxDaysOfMonth() {
    if (List.of(1, 3, 5, 7, 8, 10, 12).contains(this.month)) {
      return 31;
    }
    if (this.month == 2) {
      return this.isLeapYear() ? 29 : 28;
    } else {
      return 30;
    }
  }

  // Returns if the current MyDate is valid
  private boolean isValid() {
    if (this.year < 0 || this.month < 1 || this.day < 1 || this.month > 12) {
      return false;
    } else {
      return this.day <= this.maxDaysOfMonth();
    }
  }

  // Shifts the year of MyDate by shift
  private void shiftYear(int shift) {
    this.year += shift;
  }

  // Shifts the month of MyDate by shift
  private void shiftMonth(int shift) {
    this.month += shift;
    this.shiftYear(Math.floorDiv(this.month, 12));
    if (this.month == 0 || this.month == 12) {
      this.month = 12;
      this.shiftYear(-1);
    } else {
      this.month = this.month % 12;
    }
  }

  // Shifts the day of MyDate by shift
  private void shiftDay(int shift) {
    this.day += shift;
    while (this.day > this.maxDaysOfMonth()) {
      this.day -= this.maxDaysOfMonth();
      this.shiftMonth(1);
    }
    while (this.day < 1) {
      this.shiftMonth(-1);
      this.day += this.maxDaysOfMonth();
    }
  }

  /**
   * Advances the MyDate by the given days and stops if advances before 1 Jan 0000.
   *
   * @param days the amount of days the models.Date will shift by
   */
  public void advance(int days) {
    this.shiftDay(days);
    if (this.year < 0) {
      this.year = 0;
      this.month = 1;
      this.day = 1;
    }
  }

  @Override
  public int getDay() {
    return this.day;
  }

  @Override
  public int getMonth() {
    return this.month;
  }

  @Override
  public int getYear() {
    return this.year;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof DateImpl) {
      DateImpl otherDateImpl = (DateImpl) other;
      return this.getYear() == otherDateImpl.getYear()
              && this.getMonth() == otherDateImpl.getMonth()
              && this.getDay() == otherDateImpl.getDay();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.getYear())
            + Integer.hashCode(this.getMonth())
            + Integer.hashCode(this.getDay());
  }

  @Override
  public int compareTo(Date other) {
    int yearComparison = Integer.compare(this.getYear(), other.getYear());
    if (yearComparison != 0) {
      return yearComparison;
    }

    int monthComparison = Integer.compare(this.getMonth(), other.getMonth());
    if (monthComparison != 0) {
      return monthComparison;
    }

    return Integer.compare(this.getDay(), other.getDay());
  }

  @Override
  public String toString() {
    return String.format("%04d-%02d-%02d", this.year, this.month, this.day);
  }
}
