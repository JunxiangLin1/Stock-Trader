package controllers.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Date;
import models.DynamicPortfolio;
import models.User;
import models.impl.DateImpl;
import views.GameView;

/**
 * The {@code RebalanceCommand} class handles the rebalancing of a dynamic portfolio
 * on a specific date with given weights.
 */
public class Rebalance extends AbstractGameCommand {

  /**
   * Constructs a {@code RebalanceCommand} object.
   *
   * @param sc   the scanner for reading user input
   * @param user the user interacting with the application
   * @param view the view for displaying messages to the user
   */
  public Rebalance(Scanner sc, User user, GameView view) {
    super(sc, user, view);
  }

  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();
    String dateStr = sc.next();
    List<Integer> weights = new ArrayList<>();

    // Read weights from input until "end" is encountered
    while (sc.hasNext()) {
      String input = sc.next();
      if (input.equalsIgnoreCase("end")) {
        break;
      }
      try {
        weights.add(Integer.parseInt(input));
      } catch (NumberFormatException e) {
        writeMessage("Invalid weight input. Please enter integer weights followed by 'end'.");
        return;
      }
    }

    try {
      Date date = new DateImpl(dateStr);
      DynamicPortfolio portfolio = (DynamicPortfolio) this.getUser().getPortfolio(portfolioName);


      int[] weightArray = weights.stream().mapToInt(i -> i).toArray();

      portfolio.rebalance(date, weightArray);
      writeMessage("Portfolio '" + portfolioName + "' has been rebalanced on " + dateStr);
    } catch (IllegalArgumentException e) {
      writeMessage("Error: " + e.getMessage());
    } catch (ClassCastException e) {
      writeMessage("Error: The specified portfolio is not a dynamic portfolio.");
    }
  }

}