package pl.rsokol;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CircleTest {

    @Test(expected = IllegalArgumentException.class)
    public void negativeRadius() {
        Circle.withRadius(-0.1);
    }

    @Test
    public void zeroRadius() {
        final Circle circle = Circle.withRadius(0.0);
        assertThat(circle.getRadius()).isZero();
        assertThat(circle.getCircumference()).isZero();
        assertThat(circle.getArea()).isZero();
    }

    @Test
    public void positiveRadius() {
        final Circle circle = Circle.withRadius(1.0);
        assertThat(circle.getRadius()).isEqualTo(1.0);
        assertThat(circle.getCircumference()).isEqualTo(2.0 * Math.PI);
        assertThat(circle.getArea()).isEqualTo(Math.PI);
    }

}
