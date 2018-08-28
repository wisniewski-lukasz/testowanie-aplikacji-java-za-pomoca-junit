package pl.rsokol;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.io.Serializable;
import javax.annotation.Nonnull;

public final class InventoryItemImpl implements InventoryItem, Serializable {

    public static InventoryItem of(final String name, final double weight) {
        return new InventoryItemImpl(name, weight);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(final Object rightSide) {
        if (!(rightSide instanceof InventoryItem)) {
            return false;
        }
        final InventoryItem that = (InventoryItem) rightSide;
        return (this == that) || (
            name.equals(that.getName()) &&
            Double.compare(weight, that.getWeight()) == 0
        );
    }

    @Override
    public int hashCode() {
        return name.hashCode() + Double.hashCode(weight);
    }

    @Override
    public String toString() {
        return new StringBuilder(name)
                .append(", ")
                .append(weight)
                .toString();
    }

    private InventoryItemImpl(final String name, final double weight) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "No item name specified");
        Preconditions.checkArgument(weight >= 0.0, "Item weight has to be non-negative");
        this.name = name;
        this.weight = weight;
    }

    private final @Nonnull String name;
    private final double weight;

}
