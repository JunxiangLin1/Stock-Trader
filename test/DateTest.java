import org.junit.Before;
import org.junit.Test;

import models.impl.DateImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The {@code DateTest} class contains unit tests for all the methods of the {@link DateImpl} class.
 */
public class DateTest {

  DateImpl testDate1;
  DateImpl testDate2;
  DateImpl testDate3;

  @Before
  public void setup() {
    testDate1 = new DateImpl("12/13/2014");
    testDate2 = new DateImpl("12/13/2014");
    testDate3 = new DateImpl("11/13/2014");
  }

  @Test
  public void getDayWorks() {
    assertEquals(13, testDate1.getDay());
  }

  @Test
  public void getMonthWorks() {
    assertEquals(12, testDate1.getMonth());
  }

  @Test
  public void getYearWorks() {
    assertEquals(2014, testDate1.getYear());
  }

  @Test
  public void equalsReturnsTrueCorrectly() {
    assertTrue(testDate1.equals(testDate1));
    assertTrue(testDate1.equals(testDate2));
  }

  @Test
  public void equalsReturnsFalseCorrectly() {
    assertFalse(testDate1.equals(testDate3));
  }

  @Test
  public void compareToReturnsNegativeCorrectly() {
    assertTrue(testDate3.compareTo(testDate1) < 0);
  }

  @Test
  public void compareToReturnsPositiveCorrectly() {
    assertTrue(testDate1.compareTo(testDate3) > 0);
  }

  @Test
  public void compareToReturnsZeroCorrectly() {
    assertEquals(0, testDate1.compareTo(testDate2));
  }
}
