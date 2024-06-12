package controllers.commands;

import java.util.Scanner;
import models.User;
import views.GameView;

/**
 * The {@code GetComposition} class retrieves and displays the composition of a portfolio.
 */
public class Composition extends AbstractGameCommand {

  /**
   * Constructs a {@code GetComposition} object.
   *
   * @param sc the scanner for reading user input
   * @param user the user interacting with the application
   * @param view the view for displaying messages to the user
   */
  public Composition(Scanner sc, User user, GameView view) {
    super(sc, user, view);
  }

  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();

    try {
      String composition = this.getUser().getPortfolio(portfolioName).getComposition();
      writeMessage("Composition of portfolio '" + portfolioName + "':\n" + composition);
    } catch (IllegalArgumentException e) {
      writeMessage("Error: " + e.getMessage());
    }
  }
}
