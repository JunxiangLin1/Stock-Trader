package controllers.commands;

import models.User;
import models.impl.DateImpl;

import views.GameView;


import java.util.Scanner;

/**
 * The {@code BuyStock} class represents a command to buy stocks and add them to a user's portfolio.
 */
public class BuyStock extends AbstractGameCommand {

  /**
   * Constructs a {@code BuyStock} object.
   *
   * @param sc   the Scanner to be used
   * @param user the User model to be used
   * @param view the {@code GameView} view to be used
   */
  public BuyStock(Scanner sc, User user, GameView view) {
    super(sc, user, view);
  }

  /**
   * Executes the command to buy stocks and add them to the user's portfolio.
   *
   * <p>The user is prompted to enter the stock details: ticker symbol and number of shares to buy.
   * The stock is then added to the user's portfolio.
   */
  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();
    String stockTicker = sc.next();
    int shares = sc.nextInt();
    String date = sc.next();

    this.getUser().getPortfolio(portfolioName).buyShares(stockTicker, shares, new DateImpl(date));
    writeMessage("Added Shares.");
  }
}