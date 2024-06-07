package views;

/**
 * Implements {@link GameView}.
 * Represents a View that adds the desired outputs to a StringBuilder.
 */
public class MockStockGameView implements GameView {
  private final StringBuilder out;

  /**
   * Constructs a {@code MockStockGameView} object with the given {@code StringBuilder}.
   *
   * @param out the {@code StringBuilder} to which messages will be appended
   */
  public MockStockGameView(StringBuilder out) {
    this.out = out;
  }

  /**
   * Displays the given message to the user.
   *
   * @param message the message to display
   */
  public void displayMessage(String message) {
    out.append(message);
  }
}
