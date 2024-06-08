import org.junit.Before;
import org.junit.Test;

import models.Portfolio;
import models.Stock;
import models.impl.PortfolioImpl;
import models.impl.SimpleStock;
import models.impl.UserImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * The {@code UserTest} class contains unit tests for the {@link UserImpl} class.
 */
public class UserTest {
  private UserImpl user;

  @Before
  public void setUp() {
    user = new UserImpl();
  }

  @Test
  public void testCreatePortfolio() {
    user.createPortfolio("Portfolio1");
    Portfolio portfolio = user.getPortfolio("Portfolio1");
    assertEquals(new PortfolioImpl(), portfolio);
  }

  @Test
  public void testGetPortfolio() {
    user.createPortfolio("Portfolio1");
    Portfolio portfolio = user.getPortfolio("Portfolio1");
    assertEquals(new PortfolioImpl(), portfolio);
  }

  @Test
  public void testGetPortfolioForNonExistentPortfolio() {
    Portfolio portfolio = user.getPortfolio("NonExistentPortfolio");
    assertNull(portfolio);
  }

  @Test
  public void testAddStockToPortfolio() {
    user.createPortfolio("Portfolio1");
    Portfolio portfolio = user.getPortfolio("Portfolio1");
    Stock stock = new SimpleStock("AAPL", 10);
    user.addStockToPortfolio("Portfolio1", stock);
    assertEquals(stock, portfolio.getStock("AAPL"));
  }
}
