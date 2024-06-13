package controllers.commands;

import models.User;
import views.GameView;

import java.util.Scanner;

/**
 * The {@code SellStock} class represents a command to sell stocks from a user's portfolio.
 */
public class SellStock extends AbstractGameCommand {

  /**
   * Constructs a {@code SellStock} object.
   *
   * @param sc   the Scanner to be used
   * @param user the User model to be used
   * @param view the {@code GameView} view to be used
   */
  public SellStock(Scanner sc, User user, GameView view) {
    super(sc, user, view);
  }

  /**
   * Executes the command to sell stocks from the user's portfolio.
   *
   * <p>The user is prompted to enter the stock details: ticker symbol and number of shares to sell.
   * The specified number of shares are then removed from the user's portfolio.
   */
  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();
    String stockTicker = sc.next();
    int shares = sc.nextInt();

    this.getUser().getPortfolio(portfolioName).sellShares(stockTicker, shares);

    writeMessage("Successfully sold " + shares + " shares of " + stockTicker);
  }
}
