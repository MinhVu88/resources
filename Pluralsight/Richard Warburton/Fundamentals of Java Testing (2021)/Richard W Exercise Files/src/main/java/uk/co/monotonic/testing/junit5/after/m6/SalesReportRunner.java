package uk.co.monotonic.testing.junit5.after.m6;

import java.io.PrintStream;

public class SalesReportRunner
{
    public static void main(String[] args)
    {
        run(args, System.out);
    }

    public static void run(String[] args, PrintStream output)
    {
        String fileLocation = args[0];
        SalesRepository repo = new SalesRepository(fileLocation);
        SalesAnalyser analyser = new SalesAnalyser(repo);
        SalesReport salesReport = new SalesReport(output, analyser);
        salesReport.run();
    }
}
