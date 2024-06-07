import org.junit.Before;
import org.junit.Test;

import java.util.List;

import models.Date;
import models.Stock;
import models.impl.DateImpl;
import models.impl.SimpleStock;
import models.impl.StockImpl;
import models.stockFunctions.Increase;
import models.stockFunctions.XDayCrossovers;
import models.stockFunctions.XDayMovingAverage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * The {@code StockTest} class contains unit tests for the {@link StockImpl} class.
 */
public class StockFunctionTest {

  private Date startDate;

  private Date endDate;

  private Stock stock;

  @Before
  public void setUp() {
    startDate = new DateImpl("2023-01-03");
    endDate = new DateImpl("2023-01-05");

    stock = new SimpleStock("AAPL", 100);
    stock.addData(new DateImpl("2022-12-30"), 100.0, 110.0, 90.0, 100.0, 1000.0);
    stock.addData(new DateImpl("2022-12-31"), 101.0, 111.0, 91.0, 101.0, 1000.0);
    stock.addData(new DateImpl("2023-01-01"), 102.0, 112.0, 92.0, 102.0, 1000.0);
    stock.addData(new DateImpl("2023-01-02"), 103.0, 113.0, 93.0, 103.0, 1000.0);
    stock.addData(new DateImpl("2023-01-03"), 104.0, 114.0, 94.0, 104.0, 1000.0);
    stock.addData(new DateImpl("2023-01-04"), 105.0, 115.0, 95.0, 105.0, 1000.0);
    stock.addData(new DateImpl("2023-01-05"), 106.0, 116.0, 96.0, 106.0, 1000.0);
  }

  // Tests for Increase

  @Test
  public void testIncreaseTrue() {
    Increase increase = new Increase(startDate, endDate);
    assertTrue(increase.apply(stock));
  }

  @Test
  public void testIncreaseFalse() {
    stock.addData(new DateImpl("2023-01-05"), 106.0, 116.0, 96.0, 99.0, 1000.0); // Change the end date close to be less
    Increase increase = new Increase(startDate, endDate);
    assertFalse(increase.apply(stock));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncreaseInvalidDateRange() {
    Date invalidEndDate = new DateImpl("2022-12-31");
    Increase increase = new Increase(startDate, invalidEndDate);
    increase.apply(stock);
  }

  // Tests for XDayCrossovers

  @Test
  public void testXDayCrossoversReturnsCorrectDates() {
    XDayCrossovers xDayCrossovers = new XDayCrossovers(startDate, endDate, 2);
    List<Date> result = xDayCrossovers.apply(stock);

    assertEquals(3, result.size());
    assertTrue(result.contains(new DateImpl("2023-01-03")));
    assertTrue(result.contains(new DateImpl("2023-01-04")));
    assertTrue(result.contains(new DateImpl("2023-01-05")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testXDayCrossoversThrowsExceptionForInvalidDateRange() {
    Date invalidEndDate = new DateImpl("2022-12-31");
    XDayCrossovers xDayCrossovers = new XDayCrossovers(startDate, invalidEndDate, 2);
    xDayCrossovers.apply(stock);
  }

  @Test
  public void testXDayCrossoversReturnsEmptyListWhenNoCrossovers() {
    // Change stock data so no crossovers happen
    stock.addData(new DateImpl("2023-01-03"), 100.0, 110.0, 90.0, 100.0, 1000.0);
    stock.addData(new DateImpl("2023-01-04"), 100.0, 110.0, 90.0, 100.0, 1000.0);
    stock.addData(new DateImpl("2023-01-05"), 100.0, 110.0, 90.0, 100.0, 1000.0);

    XDayCrossovers xDayCrossovers = new XDayCrossovers(startDate, endDate, 2);
    List<Date> result = xDayCrossovers.apply(stock);

    assertTrue(result.isEmpty());
  }

  @Test
  public void testXDayMovingAverageReturnsCorrectValue() {
    XDayMovingAverage xDayMovingAverage = new XDayMovingAverage(new DateImpl("2023-01-06"), 3);
    assertEquals(105.0, xDayMovingAverage.apply(stock), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testXDayMovingAverageThrowsExceptionWhenNotEnoughPreviousDays() {
    XDayMovingAverage xDayMovingAverage = new XDayMovingAverage(new DateImpl("2023-01-01"), 7);
    xDayMovingAverage.apply(stock);
  }

}
