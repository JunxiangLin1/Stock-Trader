package models;

public interface Date {
  int hashCode();

  int compareTo(Date otherDate);

  boolean equals(Object other);

  int getMonth();

  int getYear();

  int getDay();

  void advance(int days);

}
