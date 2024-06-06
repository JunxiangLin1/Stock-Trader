package controllers;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import models.Date;
import models.Portfolio;
import models.Stock;
import models.User;
import models.impl.DateImpl;
import models.impl.StockImpl;


public class StockGameController {
  private Readable readable;
  private Appendable appendable;
  private User user;
  private static final String apiKey = "OMS6CJPTPC6RP2PW";


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
        append("Created Portfolio.");
        break;
      case "add-stock-to-portfolio":
        user.addStockToPortfolio(sc.next(), new StockImpl(sc.next()));
        append("Added Stock.");
        break;
      case "analyze-gain-or-loss":
        boolean inc = user.getPortfolio(sc.next()).getStock(sc.next())
                .increase(new DateImpl(sc.next()), new DateImpl(sc.next()));
        if (inc) {
          append("Increased.");
        } else {
          append("Did not increase.");
        }
        break;
      case "analyze-x-day-crossover":
        List<Date> crosses = user.getPortfolio(sc.next()).getStock(sc.next()).xDayCrossovers(new DateImpl(sc.next()), new DateImpl(sc.next()), Integer.parseInt(sc.next()));
        for (int i  = 0; i <= crosses.size(); i++) {
          append(crosses.get(i).toString());
        }
        break;
      default:
        append("Invalid command. Try again.");
    }
  }

  private void append(String message) throws IOException {
      appendable.append(message).append("\n");
  }
}
