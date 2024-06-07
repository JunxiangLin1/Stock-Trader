package controllers.commands;

import java.util.Scanner;

import models.User;
import models.impl.DateImpl;
import views.GameView;
import views.StockGameView;

/**
 * A command for the controller to calculate the value of the desired portfolio at a given date.
 * Uses a scanner, user model, and view for execution.
 */
public class GetValue extends AbstractGameCommand {

  /**
   * The constructor for the {@code GetValue} class.
   * The following scanner, user model, and view are used for the command's execution.
   *
   * @param sc   the Scanner to be used
   * @param user the User model to be used
   * @param view the {@code StockGameView} view to be used
   */
  public GetValue(Scanner sc, User user, GameView view) {
    super(sc, user, view);
  }

  /**
   * Displays the value of the desired portfolio at a given date.
   *
   * <p>Takes in 2 input from the scanner:
   * <li>portfolio name
   * <li>date
   *
   * <p>Instructs the view to display the calculated portfolio value.
   */
  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();
    String date = sc.next();

    double value = this.getUser().getPortfolio(portfolioName).getValue(new DateImpl(date));
    writeMessage("The value is: " + value);
  }
}
