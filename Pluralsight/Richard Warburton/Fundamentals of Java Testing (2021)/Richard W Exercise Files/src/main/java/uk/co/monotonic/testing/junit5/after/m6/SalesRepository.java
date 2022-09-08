package uk.co.monotonic.testing.junit5.after.m6;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalesRepository {

    private final String fileLocation;
    private List<Sale> sales;

    public SalesRepository(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    private int parseInt(String value) {
        return Integer.parseInt(value.trim());
    }

    public List<Sale> loadSales() {
        if (sales == null) {
            sales = new ArrayList<>();
            final File file = new File(fileLocation);
            if (!file.exists() || !file.canRead() || !file.isFile())
            {
                System.err.println("Unable to find readable file: " + file.getAbsolutePath());
            }
            try (CSVReader reader = new CSVReader(new FileReader(fileLocation))) {
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    String store = nextLine[1].trim();
                    int number = parseInt(nextLine[2]);
                    int pricePerItem = parseInt(nextLine[3]);
                    sales.add(new Sale(store, number, pricePerItem));
                }
                return sales;
            } catch (CsvValidationException | IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return sales;
    }

}
