package uk.co.monotonic.testing.junit5.before.m3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CafeTest {

    @Test
    public void canBrewEspresso() {
        // given
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        // when
        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        // then
        assertEquals(7, coffee.getBeans());
        assertEquals(0, coffee.getMilk());
        assertEquals(CoffeeType.Espresso, coffee.getType());
    }

    @Test
    public void brewingEspressoConsumesBeans() {
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        // when
        cafe.brew(CoffeeType.Espresso);

        // then
        assertEquals(0, cafe.getBeansInStock());
    }

    @Test
    public void canBrewLatte() {
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);
        cafe.restockMilk(227);

        // when
        Coffee coffee = cafe.brew(CoffeeType.Latte);

        // then
        assertEquals(CoffeeType.Latte, coffee.getType());
    }

    @Test
    public void mustRestockMilk() {
        Cafe cafe = new Cafe();

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            cafe.restockMilk(0);
        });
    }

    @Test
    public void mustRestockBeans() {
        Cafe cafe = new Cafe();

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            cafe.restockBeans(0);
        });
    }

    @Test
    public void lattesRequireMilk() {
        Cafe cafe = new Cafe();

        // given
        cafe.restockBeans(7);

        // then
        assertThrows(IllegalStateException.class, () -> {
            // when
            cafe.brew(CoffeeType.Latte);
        }, "incorrectly brewed Latte");
    }

}
