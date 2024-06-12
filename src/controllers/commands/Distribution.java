package controllers.commands;

import java.util.Scanner;

import models.Date;
import models.User;
import models.impl.DateImpl;
import views.GameView;

/**
 * The {@code GetValueDistribution} class retrieves and displays the value distribution of a portfolio on a specific date.
 */
public class Distribution extends AbstractGameCommand {

  /**
   * Constructs a {@code GetValueDistribution} object.
   *
   * @param sc the scanner for reading user input
   * @param user the user interacting with the application
   * @param view the view for displaying messages to the user
   */
  public Distribution(Scanner sc, User user, GameView view) {
    super(sc, user, view);
  }

  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();
    String dateStr = sc.next();

    try {
      Date date = new DateImpl(dateStr);
      String distribution = this.getUser().getPortfolio(portfolioName).getValueDistribution(date);
      writeMessage("Value distribution of portfolio '" + portfolioName + "' on " + dateStr + ":\n" + distribution);
    } catch (IllegalArgumentException e) {
      writeMessage("Error: " + e.getMessage());
    }
  }
}