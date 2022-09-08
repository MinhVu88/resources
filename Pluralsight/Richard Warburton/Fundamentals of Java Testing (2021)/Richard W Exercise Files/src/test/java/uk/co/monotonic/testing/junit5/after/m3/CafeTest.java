package uk.co.monotonic.testing.junit5.after.m3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static uk.co.monotonic.testing.junit5.after.m3.CoffeeType.Espresso;
import static uk.co.monotonic.testing.junit5.after.m3.CoffeeType.Latte;

public class  CafeTest {

    public static final int ESPRESSO_BEANS_WEIGHT = 7;
    public static final int LATTE_MILK_WEIGHT = 227;

    private Cafe cafe;

    @BeforeEach
    public void before() {
        cafe = new Cafe();
    }

    @Test
    public void canBrewEspresso() {
        // given
        withBeans();

        // when
        Coffee coffee = cafe.brew(Espresso);

        // then
        assertThat(coffee, hasProperty("beans", equalTo(ESPRESSO_BEANS_WEIGHT)));
        assertEquals(0, coffee.getMilk(), "Wrong amount of milk");
        assertEquals(Espresso, coffee.getType(), "Wrong coffee type");
    }

    @Test
    public void brewingEspressoConsumesBeans() {
        // given
        withBeans();

        // when
        cafe.brew(Espresso);

        // then
        assertEquals(0, cafe.getBeansInStock());
    }

    @Test
    public void canBrewLatte() {
        // given
        withBeans();
        cafe.restockMilk(LATTE_MILK_WEIGHT);

        // when
        Coffee coffee = cafe.brew(Latte);

        // then
        assertEquals(Latte, coffee.getType(), "Wrong coffee type");
    }

    @Test
    public void mustRestockMilk() {
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            cafe.restockMilk(0);
        });
    }

    @Test
    public void mustRestockBeans() {
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            cafe.restockBeans(0);
        });
    }

    @Test
    public void lattesRequireMilk() {
        // given
        withBeans();

        // then
        assertThrows(IllegalStateException.class, () -> {
            // when
            cafe.brew(CoffeeType.Latte);
        }, "incorrectly brewed Latte");
    }

    private void withBeans()
    {
        cafe.restockBeans(ESPRESSO_BEANS_WEIGHT);
    }

}
