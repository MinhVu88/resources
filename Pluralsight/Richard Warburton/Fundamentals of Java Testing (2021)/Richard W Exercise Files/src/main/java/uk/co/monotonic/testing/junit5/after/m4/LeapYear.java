package uk.co.monotonic.testing.junit5.after.m4;

public class LeapYear {

    public static boolean isLeapYear(final int year) {
        return isDivisible(year, 4)
            && (!isDivisible(year, 100) || isDivisible(year, 400));
    }

    private static boolean isDivisible(final int year, final int denominator) {
        return year % denominator == 0;
    }

}
