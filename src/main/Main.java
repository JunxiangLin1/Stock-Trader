package main;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


import controllers.StockGameController;

import models.User;
import models.impl.UserImpl;
import views.StockGameView;

public class Main {
  public static void main(String[] args) throws IOException {
    User user = new UserImpl();

    StockGameView view = new StockGameView(new PrintStream(System.out));

    StockGameController controller = new StockGameController(new InputStreamReader(System.in), user, view);

    controller.control();
  }
}
