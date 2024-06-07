package controllers.commands;

import java.util.Scanner;

import controllers.StockGameController;
import models.User;
import views.StockGameView;

/**
 * Abstract class for the commands of the {@link StockGameController}.
 * Takes in a scanner, model, and view to execute the command onto.
 */
public abstract class AbstractGameCommand implements GameCommand {
  private final Scanner sc;
  private final User user;
  private final StockGameView view;

  /**
   * Constructor for the {@code AbstractGameCommand}.
   * Takes in a scanner, model, and view to execute the desired command.
   *
   * @param sc   the scanner the Command uses for the execution
   * @param user the user model the Command uses for the execution.
   * @param view the view the Command uses for the execution.
   */
  public AbstractGameCommand(Scanner sc, User user, StockGameView view) {
    this.sc = sc;
    this.user = user;
    this.view = view;
  }

  public abstract void execute();

  /**
   * Calls the view to display the desired message followed by a line separator.
   *
   * @param message the message to be displayed by the view
   * @throws IllegalStateException if an I/O error occurs
   */
  protected void writeMessage(String message) throws IllegalStateException {
    view.displayMessage(message + System.lineSeparator());
  }

  /**
   * Returns the object's Scanner.
   *
   * @return the scanner to be returned
   */
  protected Scanner getScanner() {
    return this.sc;
  }

  /**
   * Returns the object's User.
   *
   * @return the user to be returned
   */
  protected User getUser() {
    return this.user;
  }

  /**
   * Returns the object's View.
   *
   * @return the view to be returned
   */
  protected StockGameView getView() {
    return this.view;
  }
}
