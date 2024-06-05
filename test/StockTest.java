import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class StockTest {
  DateImpl testDate1;
  DateImpl testDate2;
  DateImpl testDate3;
  DateImpl testDate4;
  DateImpl testDate5;
  Stock stock1;
  Stock stock2;

  @Before
  public void setup() {
    testDate1 = new DateImpl("11/13/2014");
    testDate2 = new DateImpl("11/14/2014");
    testDate3 = new DateImpl("11/15/2014");
    testDate4 = new DateImpl("12/13/2014");
    testDate5 = new DateImpl("12/14/2014");
    stock1 = new StockImpl("META");
    stock2 = new StockImpl("MSFT");
    stock1.addDate(testDate1, 2, 3);
    stock1.addDate(testDate2, 3, 3);
    stock1.addDate(testDate3, 3, 4);
    stock1.addDate(testDate4, 4, 0);
    stock1.addDate(testDate5, 0, 104);
  }

  @Test
  public void testIncrease() {
    assertTrue(stock1.increase(testDate1, testDate5));
    assertFalse(stock1.increase(testDate1, testDate4));
    Exception exception = assertThrows(IllegalArgumentException.class,
            () -> stock1.increase(testDate3, testDate3));
    assertEquals("Start date must come before End date.", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class,
            () ->stock1.increase(testDate2, testDate2));
    assertEquals("Start date must come before End date.", exception.getMessage());
  }

  @Test
  public void testXDayMovingAverage() {
    assertEquals(2.5, stock1.xDayMovingAverage(testDate4, 4), 0.01);
    assertEquals((104.0+3+4)/4, stock1.xDayMovingAverage(testDate5, 4), 0.01);
    Exception exception = assertThrows(IllegalArgumentException.class,
            () -> stock1.xDayMovingAverage(testDate2, 3));
    assertEquals("Not enough days before 11/14/2014", exception.getMessage());
  }

  @Test
  public void testXDayCrossovers() {
    List<Date> expectedDates = new ArrayList<Date>(List.of(testDate3, testDate5));
    assertEquals(expectedDates, stock1.xDayCrossovers(testDate2, testDate5, 2));

    stock2.addDate(testDate1, 6, 5);
    stock2.addDate(testDate2, 5, 4);
    stock2.addDate(testDate3, 4, 3);
    stock2.addDate(new DateImpl("11/16/2014"), 3, 4);
    stock2.addDate(new DateImpl("11/17/2014"), 4, 5);

    expectedDates = new ArrayList<Date>(List.of(
            new DateImpl("11/16/2014"),
            new DateImpl("11/17/2014")));

    assertEquals(expectedDates, stock2.xDayCrossovers(testDate2, new DateImpl("11/17/2014"), 2));
  }
}