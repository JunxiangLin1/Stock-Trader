package controllers;


import java.io.IOException;
import java.util.Scanner;

import models.User;



public class StockGameController {
  private Readable readable;
  private Appendable appendable;
  private User user;


  public StockGameController(Readable readable, Appendable appendable, User user) {
    if ((user == null) || (readable == null) || (appendable == null)) {
      throw new IllegalArgumentException("User, readable or appendable is null");
    }
    this.user = user;
    this.appendable = appendable;
    this.readable = readable;
  }

  public void control() throws IOException {
    Scanner scanner = new Scanner(readable);
    while (true) {
      append("Enter command: ");
      String command = scanner.nextLine();
      if (command.equalsIgnoreCase("exit")) {
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
        break;
      case "add-stock-to-portfolio":
        user.addStockToPortfolio(sc.next(), sc.next());
        break;
      case "analyze-gain-or-loss":
        break;
      case "analyze-x-day-crossover":
        break;
      default:
        append("Invalid command. Please try again.");
    }
  }

  private void append(String message) throws IOException {
      appendable.append(message).append("\n");
  }

}
