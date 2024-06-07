package controllers.commands;

import java.util.Scanner;

import models.User;
import models.impl.DateImpl;
import views.StockGameView;

public class GetValue extends AbstractGameCommand {
  public GetValue(Scanner sc, User user, StockGameView view) {
    super(sc, user, view);
  }

  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();
    String date = sc.next();

    double value = this.getUser().getPortfolio(portfolioName).getValue(new DateImpl(date));
    writeMessage("The value is: " + value);
  }
}
