package uk.co.monotonic.testing.junit5.after.m4;

import static java.lang.Math.min;

public class WordWrap {

    public static String wrap(final String input, final int maxLineLength) {
        final StringBuilder accumulator = new StringBuilder();
        final int length = input.length();

        accumulator.append(input, 0, min(length, maxLineLength));

        int split = maxLineLength;
        while (length > split) {
            accumulator.append('\n');
            accumulator.append(input, split, min(length, split + maxLineLength));
            split += maxLineLength;
        }

        return accumulator.toString();
    }
}
