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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The tests for the {@code StockGameController} class.
 */
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
  public void testControlCommands() throws IOException {
    // Simulate user input
    String input = "create-portfolio myPortfolio "
            + "add-stock-to-portfolio myPortfolio META 3 "
            + "analyze-gain-or-loss myPortfolio META 2013-05-17 2013-06-19 "
            + "analyze-x-day-moving-average myPortfolio META 2013-05-17 3 "
            + "analyze-x-day-crossover myPortfolio META 2013-05-17 2013-06-19 3 "
            + "get-value myPortfolio 2013-05-17 "
            + "add-stock-to-portfolio myPortfolio MSFT 1 "
            + "get-value myPortfolio 2013-05-17 "
            + "add-stock-to-portfolio myPortfolio META 0 "
            + "get-value myPortfolio 2013-05-17 "
            + "\nexit\n";
    Readable readable = new StringReader(input);

    // Initialize controller with test objects
    controller = new StockGameController(readable, testUser, testView);

    // Run the control method
    controller.control();

    String[] outputParts = output.toString().split("\n");
    System.out.println(output);
    System.out.println(outputParts[1]);
    // Verify interactions
    assertTrue(outputParts[0].contains("Welcome to the Stock Game!"));
    assertEquals(outputParts[1], "Supported user instructions are: "
            + System.lineSeparator());
    assertEquals(outputParts[2], "create-portfolio portfolio-name " +
            "(create a portfolio of the given name)" + System.lineSeparator());
    assertEquals(outputParts[3], "myPortfolio");
    assertEquals(outputParts[4], "myPortfolio");
    assertEquals(outputParts[5], "myPortfolio");
    assertEquals(outputParts[6], "myPortfolio");
    assertEquals(outputParts[7], "myPortfolio");
  }
}
