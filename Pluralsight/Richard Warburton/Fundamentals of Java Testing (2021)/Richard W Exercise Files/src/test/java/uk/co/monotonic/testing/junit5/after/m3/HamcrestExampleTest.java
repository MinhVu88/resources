package uk.co.monotonic.testing.junit5.after.m3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestExampleTest
{

    @Test
    public void mapShouldContainValue()
    {
        var values = Map.of("A", 1, "B", 2);

        assertThat(values, hasEntry("B", 2));
    }

    @Test
    public void listOrderingIsIrrelevant()
    {
        List<Integer> numbers = getNumbers();

        assertThat(numbers, containsInAnyOrder(1, 2, 3, 4, 5));
    }

    private List<Integer> getNumbers()
    {
        return Arrays.asList(5, 4, 1, 2, 3);
    }


    @Test
    public void properties()
    {
        Coffee coffee = new Coffee(CoffeeType.Espresso, 7, 0);

        assertThat(coffee, hasProperty("beans", is(7)));
    }

    @Test
    public void composition()
    {
        var coffees = List.of(
            new Coffee(CoffeeType.Espresso, 7, 0),
            new Coffee(CoffeeType.FilterCoffee, 10, 0));

        assertThat(coffees, hasItem(hasProperty("beans", is(7))));

        assertThat(coffees, contains(
            hasProperty("beans", is(7)),
            hasProperty("beans", is(10))));
    }

}
