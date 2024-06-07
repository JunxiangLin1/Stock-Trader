package controllers;

import java.util.Scanner;

import javax.swing.text.View;

import models.User;
import view.StockGameView;

public interface GameCommand {
  void execute();
}
