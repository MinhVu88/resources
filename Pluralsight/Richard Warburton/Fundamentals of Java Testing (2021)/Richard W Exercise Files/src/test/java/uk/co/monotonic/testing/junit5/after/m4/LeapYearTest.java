package uk.co.monotonic.testing.junit5.after.m4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeapYearTest {

    // a year is a leap year if it is divisible by four
    // but, years divisible by 100, are not leap years,
    // except years divisible by 400

    @Test
    public void leapYarsAreDivisibleByFour() {
        assertTrue(LeapYear.isLeapYear(2020));
    }

    @Test
    public void normalYearIsNotDivisibleByFour() {
        assertFalse(LeapYear.isLeapYear(2019));
    }

    @Test
    public void yearsDivisibleBy100AreNotLeapYears() {
        assertFalse(LeapYear.isLeapYear(1900));
    }

    @Test
    public void yearsDivisbleBy400AreLeapYears() {
        assertTrue(LeapYear.isLeapYear(2000));
    }

}
