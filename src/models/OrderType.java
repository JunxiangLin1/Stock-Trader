package models;

/**
 * The {@code OrderType} enum represents the types of stock orders that can be placed:
 * BUY and SELL.
 * Each enum constant represents a specific type of order:
 * - {@code BUY}: Indicates a purchase order to buy stocks.
 * - {@code SELL}: Indicates a sale order to sell stocks.
 * This enum provides a type-safe way to differentiate between different types of stock orders,
 * enhancing code readability and reducing potential errors related to order types.
 */
public enum OrderType {
  /**
   * Represents a purchase order to buy stocks.
   */
  BUY,

  /**
   * Represents a sale order to sell stocks.
   */
  SELL;
}
