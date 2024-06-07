import controllers.StockGameController;
import models.Date;
import models.Portfolio;
import models.Stock;
import models.User;

import org.junit.Before;
import org.junit.Test;

import views.StockGameView;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ControllerTest {

  private TestUser testUser;
  private TestView testView;
  private StockGameController controller;

  @Before
  public void setUp() {
    testUser = new TestUser();
    testView = new TestView();
  }

  @Test
  public void testControlCreatePortfolio() throws IOException {
    // Simulate user input
    String input = "create-portfolio myPortfolio\nexit\n";
    Readable readable = new StringReader(input);

    // Initialize controller with test objects
    controller = new StockGameController(readable, testUser, testView);

    // Run the control method
    controller.control();

    // Verify interactions
    assertTrue(testView.getMessages().get(0).contains("Welcome to the Stock Game!"));
    assertTrue(testView.getMessages().get(1).contains("Enter command: "));
    assertTrue(testView.getMessages().get(2).contains("create-portfolio portfolio-name"));
    assertTrue(testUser.getPortfolios().containsKey("myPortfolio"));
    assertTrue(testView.getMessages().get(testView.getMessages().size() - 1).contains("Thank you for using this program!"));
  }

  private static class TestUser implements User {
    private final Map<String, Portfolio> portfolios = new HashMap<>();

    @Override
    public void createPortfolio(String portfolioName) {
      portfolios.put(portfolioName, new TestPortfolio());
    }

    @Override
    public Portfolio getPortfolio(String portfolioName) {
      return portfolios.get(portfolioName);
    }

    @Override
    public void addStockToPortfolio(String portfolioName, Stock stock) {
      getPortfolio(portfolioName).addStock(stock);
    }

    public Map<String, Portfolio> getPortfolios() {
      return portfolios;
    }
  }

  private static class TestView extends StockGameView {
    private final List<String> messages = new ArrayList<>();

    public TestView() {
      super(System.out);
    }

    @Override
    public void displayMessage(String message) {
      messages.add(message);
    }

    public List<String> getMessages() {
      return messages;
    }
  }

  private static class TestPortfolio implements Portfolio {
    private final Map<String, Stock> stocks = new HashMap<>();

    @Override
    public void addStock(Stock stock) {
      stocks.put(stock.getTicker(), stock);
    }

    @Override
    public Stock getStock(String ticker) {
      return stocks.get(ticker);
    }

    @Override
    public double getValue(Date date) {
      return 0;
    }
  }
}
