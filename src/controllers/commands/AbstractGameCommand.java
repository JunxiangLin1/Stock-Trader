package controllers.commands;

import java.util.Scanner;

import models.User;
import views.StockGameView;

public abstract class AbstractGameCommand implements GameCommand {
  private Scanner sc;
  private User user;
  private StockGameView view;

  public AbstractGameCommand(Scanner sc, User user, StockGameView view) {
    this.sc = sc;
    this.user = user;
    this.view = view;
  }

  protected void writeMessage(String message) throws IllegalStateException {
    view.displayMessage(message + System.lineSeparator());
  }

  protected Scanner getScanner() {
    return this.sc;
  }

  protected User getUser() {
    return this.user;
  }

  protected StockGameView getView() {
    return this.view;
  }
}
