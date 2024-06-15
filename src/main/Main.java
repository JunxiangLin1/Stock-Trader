package main;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


import controllers.StockGameController;


import models.User;
import models.impl.UserImpl;
import views.StockGameView;

/**
 * The Main class serves as the entry point for the Stock Game application.
 * It initializes the necessary components (model, view, and controller)
 * and starts the application.
 */
public class Main {

  /**
   * The main method initializes the User model, the view, and the controller
   * for the Stock Game application, and then starts the control loop.
   *
   * @param args args command line arguments (not used in this application)
   * @throws IOException IOException if an I/O error occurs
   */
  public static void main(String[] args) throws IOException {
    User user = new UserImpl();

    StockGameView view = new StockGameView(new PrintStream(System.out));

    StockGameController controller = new StockGameController(
            new InputStreamReader(System.in),
            user,
            view);

    controller.control();
  }
}
