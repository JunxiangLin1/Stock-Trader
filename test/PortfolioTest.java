import org.junit.Before;
import org.junit.Test;

import models.Portfolio;
import models.Stock;
import models.impl.DateImpl;
import models.impl.PortfolioImpl;
import models.impl.StockImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PortfolioTest {
  DateImpl testDate1;
  DateImpl testDate2;
  DateImpl testDate3;
  DateImpl testDate4;
  DateImpl testDate5;
  Stock stock1;
  Stock stock2;
  Stock emptyStock;
  Portfolio portfolio1;

  @Before
  public void setup() {
    testDate1 = new DateImpl("2014-11-13");
    testDate2 = new DateImpl("2014-11-14");
    testDate3 = new DateImpl("2014-11-15");
    testDate4 = new DateImpl("2014-12-13");
    testDate5 = new DateImpl("2014-12-14");
    stock1 = new StockImpl("META", 10);
    stock2 = new StockImpl("MSFT", 10);
    emptyStock = new StockImpl("EMPTY", 10);
    stock1.addDate(testDate1, 2, 3);
    stock1.addDate(testDate2, 3, 3);
    stock1.addDate(testDate3, 3, 4);
    stock1.addDate(testDate4, 4, 0);
    stock1.addDate(testDate5, 0, 104);
    stock2.addDate(testDate1, 6, 5);
    stock2.addDate(testDate2, 5, 4);
    stock2.addDate(testDate3, 4, 3);
    stock2.addDate(new DateImpl("2014-11-16"), 3, 4);
    stock2.addDate(new DateImpl("2014-11-17"), 4, 5);
    portfolio1 = new PortfolioImpl();
    portfolio1.addStock(stock1);
    portfolio1.addStock(stock2);
  }

  @Test
  public void addStockAddedCorrectlyAndGotStockCorrectly() {
    assertEquals(stock1, portfolio1.getStock(stock1.getTicker()));
    assertEquals(stock2, portfolio1.getStock(stock2.getTicker()));
    Exception exception = assertThrows(IllegalArgumentException.class,
            () -> portfolio1.getStock(emptyStock.getTicker()));
    assertEquals("Not in list bitch", exception.getMessage());
  }

  @Test
  public void getValueReturnstheRightNumber() {
    assertEquals(80, portfolio1.getValue(testDate1), 0.00001);
  }
}
