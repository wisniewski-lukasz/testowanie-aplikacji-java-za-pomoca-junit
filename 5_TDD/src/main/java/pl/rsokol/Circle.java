package pl.rsokol;

import com.google.common.base.Preconditions;
import java.io.Serializable;

public final class Circle implements Serializable {

    public static Circle withRadius(final double r) {
        return new Circle(r);
    }

    public double getRadius() {
        return r;
    }

    public double getCircumference() {
        return 2.0 * Math.PI * r;
    }

    public double getArea() {
        final double area = Math.PI * r * r;
        if (area < 0.0) {
            throw new IllegalStateException("Postcondition failure: area cannot be negative");
        }
        return area;
    }

    private Circle(final double r) {
        Preconditions.checkArgument(r >= 0.0, "Invalid radius: %s", r);
        this.r = r;
    }

    private final double r;

}
