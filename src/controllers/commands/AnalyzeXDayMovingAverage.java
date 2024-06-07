package controllers.commands;

import java.util.Scanner;

import models.User;
import models.impl.DateImpl;
import models.stockFunctions.XDayMovingAverage;
import views.GameView;

/**
 * A command for the controller to determine the average of the closing values in the last x days
 * of a specified date.
 */
public class AnalyzeXDayMovingAverage extends AbstractGameCommand{

  /**
   * Constructor for the {@code AnalyzeXDayMovingAverage}.
   * Takes in a scanner, model, and view to execute the desired command.
   *
   * @param sc   the scanner the Command uses for the execution
   * @param user the user model the Command uses for the execution.
   * @param view the view the Command uses for the execution.
   */
  public AnalyzeXDayMovingAverage(Scanner sc, User user, GameView view) {
    super(sc, user, view);
  }

  /**
   * Determines the average of the closing values in the last x days of a specified date.
   *
   * <p>Takes in 4 inputs from the scanner:
   * <li>portfolio name
   * <li>stock ticker
   * <li>date
   * <li>numDays
   *
   * <p>Instructs the view to display the calculated average.
   */
  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();
    String stockTicker = sc.next();
    String date = sc.next();
    int numDays = sc.nextInt();

    double average = new XDayMovingAverage(new DateImpl(date), numDays)
            .apply(this.getUser().getPortfolio(portfolioName).getStock(stockTicker));

    writeMessage(String.format("%d Day Moving Average of %s: %.2f", numDays, stockTicker, average));
  }
}
