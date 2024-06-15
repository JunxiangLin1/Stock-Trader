package controllers.commands;

import java.util.Scanner;
import models.User;
import models.impl.DateImpl;
import views.GameView;

/**
 * The {@code GetComposition} class retrieves and displays the composition of a portfolio.
 */
public class Performance extends AbstractGameCommand {

  /**
   * Constructs a {@code GetComposition} object.
   *
   * @param sc the scanner for reading user input
   * @param user the user interacting with the application
   * @param view the view for displaying messages to the user
   */
  public Performance(Scanner sc, User user, GameView view) {
    super(sc, user, view);
  }

  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();
    String date1 = sc.next();
    String date2 = sc.next();

    try {
      writeMessage("Executation");
      String performace = this.getUser().getPortfolio(portfolioName)
              .performanceOverTime(new DateImpl(date1), new DateImpl(date2));
      writeMessage("Performance of '" + portfolioName + "':\n" + performace);
    } catch (IllegalArgumentException e) {
      writeMessage("Error: " + e.getMessage());
    }
  }
}
