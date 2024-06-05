public class NormalStockDate implements Date{

  private final String date;

  public NormalStockDate(String date) {
    this.date = date;
  }

  @Override
  public int getDay() {
    String[] components = date.split("/");
    return Integer.parseInt(components[1]);
  }

  @Override
  public int getMonth() {
    String[] components = date.split("/");
    return Integer.parseInt(components[0]);
  }

  @Override
  public int getYear() {
    String[] components = date.split("/");
    return Integer.parseInt(components[2]);
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof NormalStockDate) {
      NormalStockDate otherNormalStockDate = (NormalStockDate) other;
      return this.getYear() == otherNormalStockDate.getYear()
              && this.getMonth() == otherNormalStockDate.getMonth()
              && this.getDay() == otherNormalStockDate.getDay();
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = Integer.hashCode(getYear());
    result = 31 * result + Integer.hashCode(getMonth());
    result = 31 * result + Integer.hashCode(getDay());
    return result;
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
    return date;
  }
}
