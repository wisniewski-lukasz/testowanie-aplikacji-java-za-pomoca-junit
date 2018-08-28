package pl.rsokol;

import java.io.Serializable;

public final class QuadraticFunction implements Serializable {

    public static QuadraticFunction of(final double a, final double b, final double c) {
        return new QuadraticFunction(a, b, c);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    private QuadraticFunction(final double a, final double b, final double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        final double delta = b * b - 4.0 * a * c;
        if (delta > 0.0) {
            x1 = (-b - Math.sqrt(delta)) / 2.0 / a;
            x2 = (-b + Math.sqrt(delta)) / 2.0 / a;
        } else if (Math.abs(delta) < 1e-15) {
            x1 = x2 = -b / 2.0 / a;
        } else {
            throw new IllegalStateException("No real solution for these parameters");
        }
    }

    private final double a;
    private final double b;
    private final double c;
    private final double x1;
    private final double x2;

}
