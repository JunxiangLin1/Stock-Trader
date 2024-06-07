package controllers.commands;

import java.util.Scanner;

import models.User;
import views.StockGameView;

public class CreatePortfolio extends AbstractGameCommand {
  public CreatePortfolio(Scanner sc, User user, StockGameView view) {
    super(sc, user, view);
  }

  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();

    this.getUser().createPortfolio(portfolioName);
    writeMessage("Created Portfolio.");
  }
}
