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

  private PrintStream outputStream;

  public TextBasedView(PrintStream outputStream) {
    this.outputStream = outputStream;
  }

  // Method to display a message to the user
  public void showMessage(String message) {
    outputStream.println(message);
  }

}
