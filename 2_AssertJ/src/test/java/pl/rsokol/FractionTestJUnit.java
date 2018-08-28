package pl.rsokol;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FractionTestJUnit {

    @Test
    public void JUNIT_createValid() {
        final Fraction fraction = Fraction.of(3, 4);
        assertNotNull(fraction);
        assertEquals(3, fraction.getNominator());
        assertEquals(4, fraction.getDenominator());
    }

    @Test
    public void createValid() {
        final Fraction fraction = Fraction.of(3, 4);
        assertThat(fraction).isNotNull();
        assertThat(fraction.getNominator()).isEqualTo(3);
        assertThat(fraction.getDenominator()).isEqualTo(4);
    }

}
