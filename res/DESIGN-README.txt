Package Structure:

1. controllers:
    Contains the controller classes responsible for handling user commands.
    Also contains the package commands which contain command classes that encapsulate user actions.

2. main:
    Contains the main class where the program starts.

3. models:
    Contains the core domain interfaces.
    Also contains the implementations of the interfaces in the impl package.
    Also contains the stockfunctions package where our stock objects functions are located.

4. views:
    Contains the view classes and interface for user interaction.

models package:

1. Portfolio interface + PortfolioImpl class:
    addStock(Stock stock): Adds a stock to the portfolio.
    buyShares(String ticker, int shares, double price, Date date): Buys a specified number of shares of a stock at a given price on a specific date.
    sellStock(String ticker, int shares, double price, Date date): Sells a specified number of shares of a stock at a given price on a specific date.
    getStock(String ticker): Retrieves a stock by its ticker symbol.
    getValue(Date date): Calculates the total value of the portfolio on a given date.
    getComposition(): Returns a string representation of the portfolio composition.
    getValueDistribution(Date date): Returns a string representation of the individual stock values in the portfolio on a given date.

2. Stock interface + StockImpl class:
    addData(Date date, double open, double high, double low, double close, double volume).
    getData(): Adds daily price data for the specified date.
    getShares(): Returns the number of shares of the stock.
    getTicker(): Returns the ticker symbol of the stock.
    getOpen(Date day): Returns the opening price of the stock on the specified date.
    getHigh(Date day): Returns the highest price of the stock on the specified date.
    getLow(Date day): Returns the lowest price of the stock on the specified date.
    getClose(Date day): Returns the closing price of the stock on the specified date.
    getVolume(Date day): Returns the trading volume of the stock on the specified date.
    setShares(int shareAmount): Sets the number of shares for the stock.

3. stock function:
    increase(Date start, Date end): Returns whether the stock's closing price has increased in the specified time span.
    populateData(): A Function that populates the data for a given stock using data from the Alpha Vantage API.
    xDayCrossovers(Date start, Date end, int day): Returns the dates within the date range that has a current closing value greater than its x day moving average.
    xDayMovingAverage(Date date, int days): Returns the average closing values of the previous x days of the specified date.

4. Date interface + DateImpl class:
    private boolean isLeapYear(): Determines if the year is a leap year.
    private int maxDaysOfMonth(): Returns the maximum number of days in the current month.
    private boolean isValid(): Validates the current date.
    private void shiftYear(int shift): Adjusts the year by a specified number of years.
    private void shiftMonth(int shift): Adjusts the month by a specified number of months.
    private void shiftDay(int shift): Adjusts the day by a specified number of days.
    public void advance(int days): Advances the date by a specified number of days.
    public int getDay(): Returns the day component of the date.
    public int getMonth(): Returns the month component of the date.
    public int getYear(): Returns the year component of the date.
    public boolean equals(Object other): Compares this date to another object for equality.
    public int compareTo(Date other): Compares this date to another Date object.

5. User interface + UserImpl class:
    public void createPortfolio(String portfolioName): Creates a new portfolio with the specified name.
    public Portfolio getPortfolio(String portfolioName): Retrieves the portfolio associated with the specified name.
    public void addStockToPortfolio(String portfolioName, Stock stock): Adds the specified stock to the portfolio associated with the given name.


controllers package:

1. StockGameController:
    Responsible for handling user input and coordinating the execution of commands.
    Uses a Scanner to read user input and delegates actions to appropriate command classes based on the input.

controllers.commands:

1. AbstractGameCommand + GameCommand:
    Sets up scanner, user, and game view.
    execute(): executes the implemented method in the children classes.
    writeMessage(String message): prints out the status message after the method is run.
2. AddStockToPortfolio:
    Adds the specified quantity of a desired stock to the chosen portfolio.
3. AnalyzeGainOrLoss:
    Displays whether the desired stock of the chosen portfolio has increased in price over the desired period of time.
4. AnalyzeXDayCrossover:
    Displays the dates within the date range that has a current closing value greater than its x day moving average.
5. AnalyzeXDayMovingAverage:
    Determines the average of the closing values in the last x days of a specified date.
6. BuyStock:
    Executes the command to buy stocks and add them to the user's portfolio.
7. Composition:
    Returns the composition of the portfolio, what stocks and how many shares for each stock.
8. CreatePortfolio:
    Creates a portfolio with the given name.
9. Distribution:
    Returns the distribution of the portfolio, and how much money is in each individual stock.
10. GetValue:
    Gets the value of the portfolio at that date.
11. SellStock:
    Sells a certain number of shares of a stock.

views:

1. GameView + StockGameView:
    Represents the view component of the application. It provides methods for displaying messages to the user.
    displayMessage(String message): Displays the necessary message.

Design patterns used:
Command design
Adapter design