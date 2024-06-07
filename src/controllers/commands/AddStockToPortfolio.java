package controllers.commands;

import java.util.Scanner;

import models.User;
import models.impl.StockImpl;
import views.StockGameView;

public class AddStockToPortfolio extends AbstractGameCommand {

  public AddStockToPortfolio(Scanner sc, User user, StockGameView view) {
    super(sc, user, view);
  }

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
