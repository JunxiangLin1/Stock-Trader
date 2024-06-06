package models;

import java.util.NoSuchElementException;

public enum InputDataType {
  TIMESTAMP, OPEN, HIGH, LOW, CLOSE, VOLUME;

  public InputDataType next() {
    switch (this) {
      case TIMESTAMP:
        return OPEN;
      case OPEN:
        return HIGH;
      case HIGH:
        return LOW;
      case LOW:
        return CLOSE;
      case CLOSE:
        return VOLUME;
      case VOLUME:
        return TIMESTAMP;
      default:
        throw new NoSuchElementException();
    }
  }
}
