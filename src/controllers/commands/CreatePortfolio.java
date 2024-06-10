package controllers.commands;

import java.util.Scanner;

import models.User;
import views.GameView;

/**
 * A command for the controller to create a new portfolio.
 * Uses a scanner, user model, and view for execution.
 */
public class CreatePortfolio extends AbstractGameCommand {

  /**
   * The constructor for the {@code CreatePortfolio} class.
   * The following scanner, user model, and view are used for the command's execution.
   *
   * @param sc   the Scanner to be used
   * @param user the User model to be used
   * @param view the {@code StockGameView} view to be used
   */
  public CreatePortfolio(Scanner sc, User user, GameView view) {
    super(sc, user, view);
  }

  /**
   * Creates a portfolio for the user.
   *
   * <p>Takes in 1 input from the scanner:
   * <li>portfolio name
   *
   * <p>Instructs the view to display that the portfolio has been successfully created.
   */
  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();

    this.getUser().createPortfolio(portfolioName);
    writeMessage("Created Portfolio.");
  }
}
