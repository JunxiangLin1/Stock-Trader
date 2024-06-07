package controllers;

import java.util.Scanner;

import models.User;

public interface GameCommand {
  void execute(Scanner sc, User user, );
}
