package view;

import java.io.PrintStream;

public class StockGameView {
  private final PrintStream out;

  public StockGameView(PrintStream out) {
    this.out = out;
  }

  public void displayMessage(String message) {
    out.println(message);
  }

}
