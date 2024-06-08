import org.junit.Before;
import org.junit.Test;

import models.Portfolio;
import models.Stock;
import models.impl.DateImpl;
import models.impl.PortfolioImpl;
import models.impl.SimpleStock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * The {@code PortfolioTest} class contains unit tests for all the methods of {@link PortfolioImpl} class.
 */
public class PortfolioTest {
  private Portfolio portfolio;
  private Stock stock1;
  private Stock stock2;

  @Before
  public void setUp() {
    portfolio = new PortfolioImpl();
    stock1 = new SimpleStock("AAPL", 10);
    stock2 = new SimpleStock("GOOG", 5);
  }

  @Test
  public void testAddStock() {
    portfolio.addStock(stock1);
    assertEquals(stock1, portfolio.getStock("AAPL"));
  }

  @Test
  public void testGetStock() {
    portfolio.addStock(stock1);
    portfolio.addStock(stock2);
    assertEquals(stock1, portfolio.getStock("AAPL"));
    assertEquals(stock2, portfolio.getStock("GOOG"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetStockThrowsExceptionForNonExistentStock() {
    portfolio.getStock("AMZN");
  }

  @Test
  public void testGetValue() {
    stock1.addData(new DateImpl("2023-01-01"), 100.0, 110.0, 90.0, 100.0, 1000.0);
    stock2.addData(new DateImpl("2023-01-01"), 200.0, 220.0, 180.0, 200.0, 2000.0);
    portfolio.addStock(stock1);
    portfolio.addStock(stock2);
    assertEquals(2000.0, portfolio.getValue(new DateImpl("2023-01-01")), 0.01);
  }

  @Test
  public void testEquals() {
    Portfolio portfolio1 = new PortfolioImpl();
    Portfolio portfolio2 = new PortfolioImpl();
    assertEquals(portfolio1, portfolio2);
    portfolio1.addStock(stock1);
    assertNotEquals(portfolio1, portfolio2);
    portfolio2.addStock(stock1);
    assertEquals(portfolio1, portfolio2);
  }
}
