package controllers;


import java.io.IOException;
import java.util.Scanner;

import controllers.commands.AddStockToPortfolio;
import controllers.commands.AnalyzeGainOrLoss;
import controllers.commands.AnalyzeXDayCrossover;
import controllers.commands.CreatePortfolio;
import controllers.commands.GetValue;
import models.User;
import views.StockGameView;


public class StockGameController {
  private final Readable readable;
  private final User user;
  private final StockGameView view;

  public StockGameController(Readable readable, User user, StockGameView view) {
    if ((user == null) || (readable == null) || (view == null)) {
      throw new IllegalArgumentException("User, readable or appendable is null");
    }
    this.user = user;
    this.readable = readable;
    this.view = view;
  }

  public void control() throws IOException {
    Scanner scanner = new Scanner(readable);
    welcomeMessage();
    while (true) {
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
      case "analyze-x-day-crossover":
        new AnalyzeXDayCrossover(sc, user, this.view).execute();
        break;
      case "get-value":
        new GetValue(sc, user, this.view).execute();
        break;
      case "menu":
        welcomeMessage();
        printMenu();
      default:
        writeMessage("Invalid command. Try again.");
    }
  }

  protected void writeMessage(String message) throws IllegalStateException {
    view.displayMessage(message + System.lineSeparator());
  }

  protected void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: ");
    writeMessage("create-portfolio portfolio-name " +
            "(create a portfolio of the given name)");
    writeMessage("add-stock-to-portfolio portfolio-name stock-ticker share amount" +
            "(adds a stock of the given ticker to the designated portfolio)");
    writeMessage("analyze-gain-or-loss portfolio-name stock-ticker start-date(YYYY-MM-DD) " +
            "end-date(YYYY-MM-DD) " +
            "(analyses whether or not the given stock increased in price within the date range)");
    writeMessage("analyze-x-day-crossover portfolio-name stock-ticker start-date(YYYY-MM-DD) " +
            "end-date(YYYY-MM-DD) num-days " +
            "(returns all the days within the range where the closing price is greater than the " +
            "x-day moving average)");
    writeMessage("get-value portfolio-name date(YYYY-MM-DD) " +
            "(returns the value of the inputted portfolio on that day)");
    writeMessage("menu (Print supported instruction list)");
    writeMessage("exit (quit the program) ");
  }

  protected void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the Stock Game!");
    printMenu();
  }

  protected void farewellMessage() throws IllegalStateException {
    writeMessage("Thank you for using this program!");
  }
}
