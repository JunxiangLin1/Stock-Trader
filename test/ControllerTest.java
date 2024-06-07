import controllers.StockGameController;
import models.Date;
import models.Portfolio;
import models.Stock;
import models.User;

import org.junit.Before;
import org.junit.Test;

import models.impl.UserImpl;
import views.MockStockGameView;
import views.StockGameView;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class ControllerTest {

  private Scanner scanner;
  private UserImpl testUser;
  private MockStockGameView testView;
  private StockGameController controller;
  StringBuilder output;

  @Before
  public void setUp() {
    output = new StringBuilder();
    testUser = new UserImpl();
    testView = new MockStockGameView(output);
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

    String[] outputParts = output.toString().split("\n");
    // Verify interactions
    assertTrue(outputParts[0].contains("Welcome to the Stock Game!"));
    assertTrue(outputParts[1].contains("Enter command: "));
    assertTrue(outputParts[2].contains("create-portfolio portfolio-name"));
    assertTrue(outputParts[3].contains("myPortfolio"));
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
