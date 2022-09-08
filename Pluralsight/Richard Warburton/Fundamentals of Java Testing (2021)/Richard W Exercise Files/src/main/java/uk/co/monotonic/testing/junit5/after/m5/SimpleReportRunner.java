package uk.co.monotonic.testing.junit5.after.m5;

public class SimpleReportRunner {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("You must provide a commandline argument specifying the file to analyse");
            System.exit(-1);
        }

        CsvSalesRepository repo = new CsvSalesRepository(args[0]);
        repo.setError(System.err);

        SalesAnalysisService analysisService = new SalesAnalysisService(repo);
        SalesReport report = new SalesReport(System.out, analysisService);
        report.report();
    }

}
