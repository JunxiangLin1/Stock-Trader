import java.util.List;

public interface Stock {

  boolean increase (Date start, Date end);

  double xDayMovingAverage(Date date, int days);

  List<Date> xDayCrossovers(Date start, Date end, int day);

}
