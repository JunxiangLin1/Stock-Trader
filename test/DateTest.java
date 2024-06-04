import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateTest {

  NormalStockDate testDate1;

  @Before
  public void setup() {
    testDate1 = new NormalStockDate("12/13/2014");
  }

  @Test
  public void getDayWorks() {
    assertEquals(13, testDate1.getDay());
  }

  @Test
  public void getMonthWorks() {
    assertEquals(12, testDate1.getMonth());
  }

  @Test
  public void getYearWorks() {
    assertEquals(2014, testDate1.getYear());
  }
}
