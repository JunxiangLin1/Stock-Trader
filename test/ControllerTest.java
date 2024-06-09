import controllers.StockGameController;


import org.junit.Before;
import org.junit.Test;

import models.impl.UserImpl;
import views.MockStockGameView;


import java.io.IOException;
import java.io.StringReader;

import java.util.Scanner;


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

    String[] outputParts = output.toString().split(System.lineSeparator());
    // Verify interactions
    assertTrue(outputParts[0].contains("Welcome to the Stock Game!"));
    assertTrue(outputParts[1].contains("Supported user instructions are:"));
    assertTrue(outputParts[2].contains("create-portfolio portfolio-name " +
            "(create a portfolio of the given name)"));
    assertTrue(outputParts[3].contains("add-stock-to-portfolio portfolio-name stock-ticker share " +
            "amount(adds a stock of the given ticker to the designated portfolio)"));
    assertTrue(outputParts[4].contains("analyze-gain-or-loss portfolio-name stock-ticker " +
            "start-date(YYYY-MM-DD) end-date(YYYY-MM-DD) (analyses whether or not the given" +
            " stock " +
            "increased in price within the date range)"));
    assertTrue(outputParts[5].contains("analyze-x-day-moving-average portfolio-name stock-ticker " +
            "date(YYYY-MM-DD) num-days (returns the average closing value of the " +
            "previous x days)"));
    assertTrue(outputParts[6].contains("analyze-x-day-crossover portfolio-name stock-ticker " +
            "start-date(YYYY-MM-DD) end-date(YYYY-MM-DD) num-days (returns all the days within " +
            "the " +
            "range where the closing price is greater than the x-day moving average)"));
    assertTrue(outputParts[7].contains("get-value portfolio-name date(YYYY-MM-DD) (returns the " +
            "value of the inputted portfolio on that day)"));
    assertTrue(outputParts[8].contains("menu (Print supported instruction list)"));
    assertTrue(outputParts[9].contains("exit (quit the program)"));
    assertTrue(outputParts[10].contains("Enter command:"));
    assertTrue(outputParts[11].contains("Created Portfolio."));
    assertTrue(outputParts[12].contains("Enter command:"));
    assertTrue(outputParts[13].contains("Added Stock."));
    assertTrue(outputParts[14].contains("Enter command:"));
    assertTrue(outputParts[15].contains("Did not increase."));
    assertTrue(outputParts[16].contains("Enter command:"));
    assertTrue(outputParts[17].contains("3 Day Moving Average of META: 26.33"));
    assertTrue(outputParts[18].contains("Enter command:"));
    assertTrue(outputParts[19].contains("2013-05-30"));
    assertTrue(outputParts[20].contains("2013-05-31"));
    assertTrue(outputParts[21].contains("2013-06-07"));
    assertTrue(outputParts[22].contains("2013-06-10"));
    assertTrue(outputParts[23].contains("2013-06-11"));
    assertTrue(outputParts[24].contains("2013-06-17"));
    assertTrue(outputParts[25].contains("2013-06-18"));
    assertTrue(outputParts[26].contains("2013-06-19"));
    assertTrue(outputParts[27].contains("Enter command:"));
    assertTrue(outputParts[28].contains("The value is: 78.75"));
    assertTrue(outputParts[29].contains("Enter command:"));
    assertTrue(outputParts[30].contains("Added Stock."));
    assertTrue(outputParts[31].contains("Enter command:"));
    assertTrue(outputParts[32].contains("The value is: 113.62"));
    assertTrue(outputParts[34].contains("Enter command:"));
    assertTrue(outputParts[35].contains("Added Stock."));
    assertTrue(outputParts[36].contains("The value is: 34.87"));
    assertTrue(outputParts[37].contains("Thank you for using this program!"));
  }
}
