package uk.co.monotonic.testing.junit5.before.m5;

import java.util.List;

public interface SalesRepository {
    List<Sale> loadSales();
}
