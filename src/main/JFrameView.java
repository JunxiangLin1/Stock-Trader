package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import models.Date;
import models.DynamicPortfolio;
import models.User;
import models.impl.DateImpl;

public class JFrameView extends JFrame {

  private JTextField portfolioInput;
  private JTextField stockInput;
  private JTextField dateInput;
  private JTextField sharesInput;
  private User user;
  private JTextArea portfolioDisplayArea;
  private JTextArea stockDisplayArea;
  private JTextArea valueDisplayArea;

  public JFrameView(User user) {
    this.user = user;

    setSize(600, 900);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.getContentPane().setBackground(Color.MAGENTA);
    this.setLayout(new FlowLayout());

    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(new ExitButtonListener());
    this.add(exitButton);

    JLabel portfolioLabel = new JLabel("Portfolio Name: ");
    this.add(portfolioLabel);
    portfolioInput = new JTextField(10);
    this.add(portfolioInput);

    JButton createPortfolioButton = new JButton("Create Portfolio");
    createPortfolioButton.addActionListener(new CreatePortfolioListener());
    this.add(createPortfolioButton);

    JLabel stockLabel = new JLabel("Stock Ticker: ");
    this.add(stockLabel);
    stockInput = new JTextField(10);
    this.add(stockInput);

    JLabel dateLabel = new JLabel("Date (YYYY-MM-DD): ");
    this.add(dateLabel);
    dateInput = new JTextField(10);
    this.add(dateInput);

    JLabel sharesLabel = new JLabel("Shares: ");
    this.add(sharesLabel);
    sharesInput = new JTextField(10);
    this.add(sharesInput);

    JButton buyStockButton = new JButton("Buy Stock");
    buyStockButton.addActionListener(new BuyStockListener());
    this.add(buyStockButton);

    JButton sellStockButton = new JButton("Sell Stock");
    sellStockButton.addActionListener(new SellStockListener());
    this.add(sellStockButton);

    JButton displayPortfoliosButton = new JButton("Display Portfolios");
    displayPortfoliosButton.addActionListener(new DisplayPortfoliosListener());
    this.add(displayPortfoliosButton);


    JPanel portfolioPanel = new JPanel();
    portfolioPanel.setPreferredSize(new Dimension(550, 200));
    portfolioPanel.setBackground(Color.LIGHT_GRAY);
    portfolioPanel.setLayout(new BorderLayout());

    portfolioDisplayArea = new JTextArea();
    portfolioDisplayArea.setEditable(false);
    portfolioDisplayArea.setLineWrap(true);
    portfolioDisplayArea.setWrapStyleWord(true);

    JScrollPane scrollPane = new JScrollPane(portfolioDisplayArea);
    portfolioPanel.add(scrollPane, BorderLayout.CENTER);

    this.add(portfolioPanel);


    JButton displayStocksButton = new JButton("Display Stocks");
    displayStocksButton.addActionListener(new DisplayStocksListener());
    this.add(displayStocksButton);


    JPanel stockPanel = new JPanel();
    stockPanel.setPreferredSize(new Dimension(550, 200));
    stockPanel.setBackground(Color.LIGHT_GRAY);
    stockPanel.setLayout(new BorderLayout());

    stockDisplayArea = new JTextArea();
    stockDisplayArea.setEditable(false);
    stockDisplayArea.setLineWrap(true);
    stockDisplayArea.setWrapStyleWord(true);

    JScrollPane stockScrollPane = new JScrollPane(stockDisplayArea);
    stockPanel.add(stockScrollPane, BorderLayout.CENTER);

    this.add(stockPanel);




    JButton displayValueButton = new JButton("Display Portfolio Value");
    displayValueButton.addActionListener(new DisplayValueListener());
    this.add(displayValueButton);


    JPanel valuePanel = new JPanel();
    valuePanel.setPreferredSize(new Dimension(550, 200));
    valuePanel.setBackground(Color.LIGHT_GRAY);
    valuePanel.setLayout(new BorderLayout());

    valueDisplayArea = new JTextArea();
    valueDisplayArea.setEditable(false);
    valueDisplayArea.setLineWrap(true);
    valueDisplayArea.setWrapStyleWord(true);

    JScrollPane valueScrollPane = new JScrollPane(valueDisplayArea);
    valuePanel.add(valueScrollPane, BorderLayout.CENTER);

    this.add(valuePanel);


  }

  private class ExitButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      System.exit(0);
    }
  }

  private class CreatePortfolioListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String portfolioName = portfolioInput.getText();
      if (!portfolioName.isEmpty()) {
        user.createPortfolio(portfolioName);
        JOptionPane.showMessageDialog(null, "Portfolio '" + portfolioName + "' created successfully!");
      } else {
        JOptionPane.showMessageDialog(null, "Portfolio name cannot be empty!");
      }
    }
  }

  private class BuyStockListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String portfolioName = portfolioInput.getText();
      String stockTicker = stockInput.getText();
      String dateStr = dateInput.getText();
      String sharesStr = sharesInput.getText();

      if (!portfolioName.isEmpty() && !stockTicker.isEmpty() && !dateStr.isEmpty() && !sharesStr.isEmpty()) {
        try {
          int shares = Integer.parseInt(sharesStr);
          Date date = new DateImpl(dateStr);
          DynamicPortfolio portfolio = user.getPortfolio(portfolioName);
          portfolio.buyShares(stockTicker, shares, date);
          JOptionPane.showMessageDialog(null, "Bought " + shares + " shares of '" + stockTicker + "' on " + dateStr + " successfully!");
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
      } else {
        JOptionPane.showMessageDialog(null, "All fields must be filled out!");
      }
    }
  }

  private class SellStockListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String portfolioName = portfolioInput.getText();
      String stockTicker = stockInput.getText();
      String dateStr = dateInput.getText();
      String sharesStr = sharesInput.getText();

      if (!portfolioName.isEmpty() && !stockTicker.isEmpty() && !dateStr.isEmpty() && !sharesStr.isEmpty()) {
        try {
          int shares = Integer.parseInt(sharesStr);
          Date date = new DateImpl(dateStr);
          DynamicPortfolio portfolio = user.getPortfolio(portfolioName);
          portfolio.sellShares(stockTicker, shares, date);
          JOptionPane.showMessageDialog(null, "Sold " + shares + " shares of '" + stockTicker + "' on " + dateStr + " successfully!");
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
      } else {
        JOptionPane.showMessageDialog(null, "All fields must be filled out!");
      }
    }
  }

  private class DisplayPortfoliosListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      java.util.List<String> portfolioNames = user.getAllPortfolioNames();
      if (portfolioNames.isEmpty()) {
        portfolioDisplayArea.setText("No portfolios available!");
      } else {
        StringBuilder portfoliosList = new StringBuilder("Portfolios:\n");
        for (String name : portfolioNames) {
          portfoliosList.append(name).append("\n");
        }
        portfolioDisplayArea.setText(portfoliosList.toString());
      }
    }
  }

  private class DisplayStocksListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String portfolioName = portfolioInput.getText();
      if (!portfolioName.isEmpty()) {
        try {
          DynamicPortfolio portfolio = user.getPortfolio(portfolioName);
          String composition = portfolio.getComposition();
          stockDisplayArea.setText(composition);
        } catch (Exception ex) {
          stockDisplayArea.setText("Error: " + ex.getMessage());
        }
      } else {
        stockDisplayArea.setText("Portfolio name cannot be empty!");
      }
    }
  }

  private class DisplayValueListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String portfolioName = portfolioInput.getText();
      String dateStr = dateInput.getText();
      if (!portfolioName.isEmpty() && !dateStr.isEmpty()) {
        try {
          Date date = new DateImpl(dateStr); // Assuming a Date class with valueOf method
          DynamicPortfolio portfolio = user.getPortfolio(portfolioName);
          double value = portfolio.getValue(date);
          valueDisplayArea.setText("Value of portfolio '" + portfolioName + "' on " + dateStr + ": " + value);
        } catch (Exception ex) {
          valueDisplayArea.setText("Error: " + ex.getMessage());
        }
      } else {
        valueDisplayArea.setText("Portfolio name and date cannot be empty!");
      }
    }
  }

}
