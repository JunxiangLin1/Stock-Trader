Used Adapter design for this updated version.
Created the StockOrderImpl class to represent a stock order, including the order type (buy or sell), ticker symbol, number of shares, and date of the order.
This class provides a structured way to store and retrieve stock order information, which is essential for managing dynamic portfolios and tracking transactions over time.
Created the DynamicPortfolioImpl class to represent a dynamic portfolio that supports stock orders and rebalancing based on specified weights.
This class extends the functionality of a static portfolio by adding the ability to rebalance based on target weights, which is important for maintaining desired portfolio allocations over time.