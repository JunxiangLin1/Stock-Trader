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
    testDate1 = new DateImpl("11/13/2014");
    testDate2 = new DateImpl("11/14/2014");
    testDate3 = new DateImpl("11/15/2014");
    testDate4 = new DateImpl("12/13/2014");
    testDate5 = new DateImpl("12/14/2014");
    stock1 = new StockImpl("META");
    stock2 = new StockImpl("MSFT");
    emptyStock = new StockImpl("EMPTY");
    stock1.addData(testDate1, 2, 3);
    stock1.addData(testDate2, 3, 3);
    stock1.addData(testDate3, 3, 4);
    stock1.addData(testDate4, 4, 0);
    stock1.addData(testDate5, 0, 104);
    stock2.addData(testDate1, 6, 5);
    stock2.addData(testDate2, 5, 4);
    stock2.addData(testDate3, 4, 3);
    stock2.addData(new DateImpl("11/16/2014"), 3, 4);
    stock2.addData(new DateImpl("11/17/2014"), 4, 5);
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
    assertEquals(8, portfolio1.getValue(testDate1), 0.00001);
  }
}
