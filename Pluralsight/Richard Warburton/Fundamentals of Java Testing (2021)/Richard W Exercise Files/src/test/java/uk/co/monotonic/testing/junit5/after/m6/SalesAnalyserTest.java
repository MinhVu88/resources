package uk.co.monotonic.testing.junit5.after.m6;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SalesAnalyserTest {

    private static final List<Sale> exampleSales = Arrays.asList(
        new Sale("Cardiff", 10, 2),
        new Sale("Cardiff", 3, 5),
        new Sale("Cardiff", 6, 20),
        new Sale("London", 5, 7)
    );

    private static final Map<String, Integer> expectedStoreSales = new HashMap<>();
    static {
        expectedStoreSales.put("Cardiff", 155);
        expectedStoreSales.put("London", 35);
    }

    @Test
    public void shouldAggregateStoreSales() {
        // given
        SalesRepository mockRepository = mock(SalesRepository.class);
        when(mockRepository.loadSales()).thenReturn(exampleSales);

        SalesAnalyser analyser = new SalesAnalyser(mockRepository);

        // when
        Map<String, Integer> storeSales = analyser.tallyStoreSales();

        // then
        assertEquals(expectedStoreSales, storeSales, "Calculated wrong store sales");
        verify(mockRepository, times(1)).loadSales();
    }

}
