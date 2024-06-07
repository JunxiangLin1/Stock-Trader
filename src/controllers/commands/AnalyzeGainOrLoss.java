package controllers.commands;

import java.util.Scanner;

import models.User;
import models.impl.DateImpl;
import models.macros.IncreaseMacro;
import views.StockGameView;

public class AnalyzeGainOrLoss extends AbstractGameCommand {
  public AnalyzeGainOrLoss(Scanner sc, User user, StockGameView view) {
    super(sc, user, view);
  }

  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();
    String stockTicker = sc.next();
    String startDate = sc.next();
    String endDate = sc.next();

    boolean inc = new IncreaseMacro(new DateImpl(startDate), new DateImpl(endDate))
            .execute(this.getUser().getPortfolio(portfolioName).getStock(stockTicker));

    if (inc) {
      writeMessage("Increased.");
    } else {
      writeMessage("Did not increase.");
    }
  }
}
