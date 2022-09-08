package uk.co.monotonic.testing.junit5.after.m2;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static uk.co.monotonic.testing.junit5.after.m2.CoffeeType.Espresso;
import static uk.co.monotonic.testing.junit5.after.m2.CoffeeType.Latte;

public class CafeTest {

    @Test
    public void canBrewEspresso() {
        // given
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        // when
        Coffee coffee = cafe.brew(Espresso);

        // then
        assertEquals(7, coffee.getBeans());
        assertEquals(0, coffee.getMilk());
        assertEquals(Espresso, coffee.getType());
    }

    @Test
    public void brewingEspressoConsumesBeans() {
        // given
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        // when
        cafe.brew(Espresso);

        // then
        assertEquals(0, cafe.getBeansInStock());
    }

    @Test
    public void canBrewLatte() {
        // given
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);
        cafe.restockMilk(227);

        // when
        Coffee coffee = cafe.brew(Latte);

        // then
        assertEquals(Latte, coffee.getType());
    }

    @Test
    public void mustRestockMilk() {
        // given
        Cafe cafe = new Cafe();

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            cafe.restockMilk(0);
        });
    }

    @Test
    public void mustRestockBeans() {
        // given
        Cafe cafe = new Cafe();

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            cafe.restockBeans(0);
        });
    }

    @Test
    public void lattesRequireMilk() {
        // given
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        // then
        assertThrows(IllegalStateException.class, () -> {
            // when
            cafe.brew(Latte);
        }, "incorrectly brewed Latte");
    }

}
