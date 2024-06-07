package controllers.commands;

import java.util.Scanner;

import models.User;
import models.impl.StockImpl;
import views.GameView;
import views.StockGameView;

/**
 * A command for the controller to add a stock to the portfolio.
 * Uses a scanner, user model, and view for execution.
 */
public class AddStockToPortfolio extends AbstractGameCommand {

  /**
   * The constructor for the {@code AddStockToPortfolio} class.
   * The following scanner, user model, and view are used for the command's execution.
   *
   * @param sc   the Scanner to be used
   * @param user the User model to be used
   * @param view the {@code StockGameView} view to be used
   */
  public AddStockToPortfolio(Scanner sc, User user, GameView view) {
    super(sc, user, view);
  }

  /**
   * Adds the specified quantity of a desired stock to the chosen portfolio.
   *
   * <p>Takes in 3 inputs from the scanner:
   * <li>portfolio name
   * <li>stock ticker
   * <li>number of shares
   *
   * <p>Instructs the view to display that the stock was added successfully.
   */
  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();
    String stockTicker = sc.next();
    int shares = sc.nextInt();

    this.getUser().addStockToPortfolio(portfolioName, new StockImpl(stockTicker, shares));
    writeMessage("Added Stock.");
  }
}
