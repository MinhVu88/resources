package uk.co.monotonic.testing.junit5.before.m3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HamcrestExampleTest
{

    @Test
    public void mapShouldContainValue()
    {
        var values = Map.of("A", 1, "B", 2);
    }

    @Test
    public void listOrderingIsIrrelevant()
    {
        var numbers = getNumbers();
    }

    private List<Integer> getNumbers()
    {
        return Arrays.asList(5, 4, 1, 2, 3);
    }

    @Test
    public void properties()
    {
        Coffee coffee = new Coffee(CoffeeType.Espresso, 7, 0);
        assertEquals(7, coffee.getBeans());
    }

    @Test
    public void composition()
    {
        var coffees  = List.of(
            new Coffee(CoffeeType.Espresso, 7, 0),
            new Coffee(CoffeeType.FilterCoffee, 10, 0));
    }

}
