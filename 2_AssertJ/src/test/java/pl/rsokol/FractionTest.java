package pl.rsokol;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FractionTest {

    @Test
    public void createValid() {
        final Fraction fraction = Fraction.of(3, 4);
        assertThat(fraction).isNotNull();
        assertThat(fraction.getNominator()).isEqualTo(3);
        assertThat(fraction.getDenominator()).isEqualTo(4);
    }

    @Test
    public void createFull() {
        assertThat(Fraction.of(1, 1)).isSameAs(Fraction.FULL);
    }

    @Test
    public void createHalf() {
        assertThat(Fraction.of(1, 2)).isSameAs(Fraction.HALF);
    }

    @Test
    public void createOneThird() {
        assertThat(Fraction.of(1, 3)).isSameAs(Fraction.ONE_THIRD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void dividePositiveByZero() {
        Fraction.of(5, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void divideNegativeByZero() {
        Fraction.of(-5, 0);
    }

    @Test
    public void divideZeroByZero() {
        assertThat(Fraction.of(0, 0)).isSameAs(Fraction.INDETERMINATE);
    }

    @Test
    public void equalHash() {
        assertThat(Fraction.of(3, 4).hashCode()).isEqualTo(Fraction.of(3, 4).hashCode());
    }
    
    @Test
    public void verifyToString() {
        assertThat(Fraction.of(3, 4).toString()).isEqualTo("3/4");
    }
    
    /*@Test
    public void equalsPositive() {
        assertThat(Fraction.of(7, 8)).isEqualTo(Fraction.of(7, 8));
    }

    @Test
    public void equalsDifferentNominator() {
        assertThat(Fraction.of(6, 8)).isNotEqualTo(Fraction.of(7, 8));
    }

    @Test
    public void equalsDifferentDenominator() {
        assertThat(Fraction.of(7, 8)).isNotEqualTo(Fraction.of(7, 9));
    }*/

    @Test
    public void equalsAndHashCode() {
        EqualsVerifier.forClass(Fraction.class)
                      .verify();
    }

}
