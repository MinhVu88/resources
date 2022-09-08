package uk.co.monotonic.testing.junit5.after.m6;


import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class SalesReportSystemTest
{
    @Test
    public void findsExampleSalesInOutput()
    {
        ApplicationRunner runner = new ApplicationRunner();

        String output = runner.run("src/main/resources/example-sales.csv");

        assertThat(output, containsString("- London          -    235 -"));
    }
}
