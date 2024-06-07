package controllers.commands;

import java.util.Scanner;

import models.User;
import models.impl.DateImpl;
import models.stockFunctions.Increase;
import views.StockGameView;

/**
 * A command for the controller to analyze whether there was a gain or loss over a
 * specified period of time.
 * Uses a scanner, user model, and view for execution.
 */
public class AnalyzeGainOrLoss extends AbstractGameCommand {

  /**
   * The constructor for the {@code AnalyzeGainOrLoss} class.
   * The following scanner, user model, and view are used for the command's execution.
   *
   * @param sc   the Scanner to be used
   * @param user the User model to be used
   * @param view the {@code StockGameView} view to be used
   */
  public AnalyzeGainOrLoss(Scanner sc, User user, StockGameView view) {
    super(sc, user, view);
  }


  /**
   * Displays whether the desired stock of the chosen portfolio has increased in price over the
   * desired period of time.
   *
   * <p>Takes in 4 inputs from the scanner:
   * <li>portfolio name
   * <li>stock ticker
   * <li>start date
   * <li>end date
   *
   * <p>Instructs the view to display whether there was an increase or not.
   */
  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();
    String stockTicker = sc.next();
    String startDate = sc.next();
    String endDate = sc.next();

    boolean inc = new Increase(new DateImpl(startDate), new DateImpl(endDate))
            .apply(this.getUser().getPortfolio(portfolioName).getStock(stockTicker));

    if (inc) {
      writeMessage("Increased.");
    } else {
      writeMessage("Did not increase.");
    }
  }
}
