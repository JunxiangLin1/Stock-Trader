package controllers;


import java.io.IOException;
import java.util.Scanner;

import controllers.commands.AddStockToPortfolio;
import controllers.commands.AnalyzeGainOrLoss;
import controllers.commands.AnalyzeXDayCrossover;
import controllers.commands.AnalyzeXDayMovingAverage;
import controllers.commands.BuyStock;
import controllers.commands.Composition;
import controllers.commands.CreatePortfolio;
import controllers.commands.Distribution;
import controllers.commands.GetValue;
import controllers.commands.Performance;
import controllers.commands.Rebalance;
import controllers.commands.SellStock;
import models.User;
import views.GameView;

/**
 * The {@code StockGameController} class handles user input and commands for the stock application.
 */
public class StockGameController implements Controller {

  private final Readable readable;

  private final User user;

  private final GameView view;

  /**
   * Constructs a {@code StockGameController} object.
   *
   * @param readable the source of user input
   * @param user the user interacting with the application
   * @param view the view for displaying messages to the user
   * @throws IllegalArgumentException if any of the parameters are null
   */
  public StockGameController(Readable readable, User user, GameView view) {
    if ((user == null) || (readable == null) || (view == null)) {
      throw new IllegalArgumentException("User, readable or appendable is null");
    }
    this.user = user;
    this.readable = readable;
    this.view = view;
  }

  /**
   * Starts the control loop, processing user commands until the user exits.
   *
   * @throws IOException if an I/O error occurs
   */
  public void control() throws IOException {
    Scanner scanner = new Scanner(readable);
    welcomeMessage();
    while (true) {
      printMenu();
      writeMessage("Enter command: ");
      String command = scanner.next();
      if (command.equalsIgnoreCase("exit")) {
        farewellMessage();
        break;
      }
      processCommands(scanner, user, command);
    }
    scanner.close();
  }

  /**
   * Processes user commands and executes corresponding actions.
   *
   * @param sc the scanner for reading user input
   * @param user the user interacting with the application
   * @param userInstruction the user command to process
   * @throws IOException if an I/O error occurs
   */
  protected void processCommands(Scanner sc, User user, String userInstruction) throws IOException {
    switch (userInstruction.toLowerCase()) {
      case "create-portfolio":
        new CreatePortfolio(sc, user, this.view).execute();
        break;
      case "add-stock-to-portfolio":
        new AddStockToPortfolio(sc, user, this.view).execute();
        break;
      case "analyze-gain-or-loss":
        new AnalyzeGainOrLoss(sc, user, this.view).execute();
        break;
      case "analyze-x-day-moving-average":
        new AnalyzeXDayMovingAverage(sc, user, this.view).execute();
        break;
      case "analyze-x-day-crossover":
        new AnalyzeXDayCrossover(sc, user, this.view).execute();
        break;
      case "get-value":
        new GetValue(sc, user, this.view).execute();
        break;
      case "buy-shares":
        new BuyStock(sc, user, this.view).execute();
        break;
      case "sell-shares":
        new SellStock(sc, user, this.view).execute();
        break;
      case "composition":
        new Composition(sc, user, this.view).execute();
        break;
      case "distribution-of-value":
        new Distribution(sc, user, this.view).execute();
        break;
      case "re-balance":
        new Rebalance(sc, user, this.view).execute();
        break;
      case "performance":
        new Performance(sc, user, this.view).execute();
        break;
      case "menu":
        welcomeMessage();
        printMenu();
        break;
      default:
        writeMessage("Invalid command. Try again.");
    }
  }

  /**
   * Writes a message to the view.
   *
   * @param message the message to display
   * @throws IllegalStateException if an I/O error occurs
   */
  public void writeMessage(String message) throws IllegalStateException {
    view.displayMessage(message + System.lineSeparator());
  }

  /**
   * Prints the menu of supported user instructions.
   *
   * @throws IllegalStateException if an I/O error occurs
   */
  public void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: ");
    writeMessage("create-portfolio portfolio-name "
            + "(create a portfolio of the given name)");
    writeMessage("add-stock-to-portfolio portfolio-name stock-ticker share amount"
            + "(adds a stock of the given ticker to the designated portfolio)");
    writeMessage("buy-shares portfolio-name stock-ticker share amount"
            + "(adds a stock of the given ticker and share amount to the designated portfolio)");
    writeMessage("sell-shares portfolio-name stock-ticker share amount"
            + "(sells a stock of the given ticker and share amount from the designated portfolio)");
    writeMessage("analyze-gain-or-loss portfolio-name stock-ticker start-date(YYYY-MM-DD) "
            + "end-date(YYYY-MM-DD) "
            + "(analyses whether or not the given stock increased in price within the date range)");
    writeMessage("analyze-x-day-moving-average portfolio-name stock-ticker date(YYYY-MM-DD) "
            + "num-days "
            + "(returns the average closing value of the previous x days) ");
    writeMessage("analyze-x-day-crossover portfolio-name stock-ticker start-date(YYYY-MM-DD) "
            + "end-date(YYYY-MM-DD) num-days "
            + "(returns all the days within the range where the closing price is greater than the "
            + "x-day moving average)");
    writeMessage("get-value portfolio-name date(YYYY-MM-DD) "
            + "(returns the value of the inputted portfolio on that day)");
    writeMessage("distribution-of-value portfolio-name date(YYYY-MM-DD) "
            + "(returns the value of each individual stock of the inputted portfolio on that day)");
    writeMessage("composition portfolio-name "
            + "(returns all stocks and share amount for each stock for the inputted portfolio)");
    writeMessage("re-balance portfolio-name date(YYYY-MM-DD) weight1, weight2, etc 'end' "
            + "re-balances the portfolio to have the correct values and the " +
            "shares that match those "
            + "values");
    writeMessage("menu (Print supported instruction list)");
    writeMessage("exit (quit the program) ");
  }

  /**
   * Displays a welcome message and the menu of supported instructions.
   *
   * @throws IllegalStateException if an I/O error occurs
   */
  public void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the Stock Game!");
  }

  /**
   * Displays a farewell message when the user exits the program.
   *
   * @throws IllegalStateException if an I/O error occurs
   */
  public void farewellMessage() throws IllegalStateException {
    writeMessage("Thank you for using this program!");
  }
}
