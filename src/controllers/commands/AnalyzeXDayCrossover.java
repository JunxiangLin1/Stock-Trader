package controllers.commands;

import java.util.List;
import java.util.Scanner;

import models.Date;
import models.User;
import models.impl.DateImpl;
import models.macros.XDayCrossoversMacro;
import views.StockGameView;

public class AnalyzeXDayCrossover extends AbstractGameCommand {
  public AnalyzeXDayCrossover(Scanner sc, User user, StockGameView view) {
    super(sc, user, view);
  }

  @Override
  public void execute() {
    Scanner sc = this.getScanner();
    String portfolioName = sc.next();
    String stockTicker = sc.next();
    String startDate = sc.next();
    String endDate = sc.next();
    int numDays = sc.nextInt();


    List<Date> crosses = new XDayCrossoversMacro(new DateImpl(startDate), new DateImpl(endDate),
            numDays).execute(this.getUser().getPortfolio(portfolioName).getStock(stockTicker));
    for (int i  = 0; i <= crosses.size(); i++) {
      writeMessage(crosses.get(i).toString());
    }
  }
}
