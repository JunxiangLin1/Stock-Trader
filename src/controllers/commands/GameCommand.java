package controllers.commands;

/**
 * The macro commands for the controller.
 * Executes when called upon by the controller
 */
public interface GameCommand {

  /**
   * The instructions of the command's execution to be specified by specific implementation.
   * Executes using the scanner, model, and view provided in the constructor.
   */
  void execute();
}
