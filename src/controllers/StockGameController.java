package controllers;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import models.Date;
import models.User;
import models.impl.DateImpl;
import models.impl.StockImpl;
import view.StockGameView;


public class StockGameController {
  private Readable readable;
  private User user;
  private StockGameView view;

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
      String command = scanner.nextLine();
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
        user.createPortfolio(sc.next());
        writeMessage("Created Portfolio.");
        break;
      case "add-stock-to-portfolio":
        user.addStockToPortfolio(sc.next(), new StockImpl(sc.next()));
        writeMessage("Added Stock.");
        break;
      case "analyze-gain-or-loss":
        boolean inc = user.getPortfolio(sc.next()).getStock(sc.next())
                .increase(new DateImpl(sc.next()), new DateImpl(sc.next()));
        if (inc) {
          writeMessage("Increased.");
        } else {
          writeMessage("Did not increase.");
        }
        break;
      case "analyze-x-day-crossover":
        List<Date> crosses = user.getPortfolio(sc.next()).getStock(sc.next()).xDayCrossovers(new DateImpl(sc.next()), new DateImpl(sc.next()), Integer.parseInt(sc.next()));
        for (int i  = 0; i <= crosses.size(); i++) {
          writeMessage(crosses.get(i).toString());
        }
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
    writeMessage("add-stock-to-portfolio portfolio-name stock-ticker " +
            "(adds a stock of the given ticker to the designated portfolio)");
    writeMessage("analyze-gain-or-loss portfolio-name stock-ticker start-date(YYYY-MM-DD) " +
            "end-date(YYYY-MM-DD) " +
            "(analyses whether or not the given stock increased in price within the date range)");
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
