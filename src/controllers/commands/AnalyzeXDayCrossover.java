package controllers.commands;

import java.util.List;
import java.util.Scanner;

import models.Date;
import models.User;
import models.impl.DateImpl;
import models.stockFunctions.XDayCrossovers;
import views.GameView;
import views.StockGameView;

/**
 * A command for the controller to determine the dates within the date range that has a current
 * closing value greater than its x day moving average.
 * Uses a scanner, user model, and view for execution.
 */
public class AnalyzeXDayCrossover extends AbstractGameCommand {

  /**
   * The constructor for the {@code AnalyzeXDayCrossover} class.
   * The following scanner, user model, and view are used for the command's execution.
   *
   * @param sc   the Scanner to be used
   * @param user the User model to be used
   * @param view the {@code StockGameView} view to be used
   */
  public AnalyzeXDayCrossover(Scanner sc, User user, GameView view) {
    super(sc, user, view);
  }

  /**
   * Displays the dates within the date range that has a current closing value greater than its
   * x day moving average.
   *
   * <p>Takes in 5 inputs from the scanner:
   * <li>portfolio name
   * <li>stock ticker
   * <li>startDate
   * <li>endDate
   * <li>numDays
   *
   * <p>Instructs the view to display the desired dates.
   */
  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();
    String stockTicker = sc.next();
    String startDate = sc.next();
    String endDate = sc.next();
    int numDays = sc.nextInt();

    List<Date> crosses = new XDayCrossovers(new DateImpl(startDate), new DateImpl(endDate),
            numDays).apply(this.getUser().getPortfolio(portfolioName).getStock(stockTicker));
    for (int i = 0; i < crosses.size(); i++) {
      writeMessage(crosses.get(i).toString());
    }
  }
}
