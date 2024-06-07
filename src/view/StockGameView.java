package view;

public class StockGameView {

  private PrintStream outputStream;

  public TextBasedView(PrintStream outputStream) {
    this.outputStream = outputStream;
  }

  // Method to display a message to the user
  public void showMessage(String message) {
    outputStream.println(message);
  }

}
