package controllers;

import java.io.IOException;

/**
 * Interface for handling user input and commands for the stock application.
 */
public interface Controller {

  /**
   * Starts the control loop, processing user commands until the user exits.
   *
   * @throws IOException if an I/O error occurs
   */
  void control() throws IOException;

  /**
   * Writes a message to the view.
   *
   * @param message the message to display
   * @throws IllegalStateException if an I/O error occurs
   */
  void writeMessage(String message) throws IllegalStateException;

  /**
   * Prints the menu of supported user instructions.
   *
   * @throws IllegalStateException if an I/O error occurs
   */
  void printMenu() throws IllegalStateException;

  /**
   * Displays a welcome message and the menu of supported instructions.
   *
   * @throws IllegalStateException if an I/O error occurs
   */
  void welcomeMessage() throws IllegalStateException;

  /**
   * Displays a farewell message when the user exits the program.
   *
   * @throws IllegalStateException if an I/O error occurs
   */
  void farewellMessage() throws IllegalStateException;
}