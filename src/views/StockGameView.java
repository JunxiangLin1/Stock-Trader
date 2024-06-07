package views;

import java.io.PrintStream;

/**
 * The {@code StockGameView} class provides methods to display messages
 * to the user in a text-based interface for the stock game application.
 */
public class StockGameView {
  private final PrintStream out;

  /**
   * Constructs a {@code StockGameView} object with the given {@code PrintStream}.
   *
   * @param out the {@code PrintStream} to which messages will be printed
   */
  public StockGameView(PrintStream out) {
    this.out = out;
  }

  /**
   * Displays the given message to the user.
   *
   * @param message the message to display
   */
  public void displayMessage(String message) {
    out.println(message);
  }
}
