package main;

import java.io.IOException;


import javax.swing.*;

import models.User;
import models.impl.UserImpl;

public class Main2 {
  public static void main(String[] args) throws IOException {
    User user = new UserImpl();
    JFrame frame = new JFrameView(user);
    frame.setVisible(true);
  }
}
