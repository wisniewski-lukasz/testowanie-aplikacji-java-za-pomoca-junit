package pl.rsokol;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class InventoryImpl implements Serializable, Inventory {

    public static Inventory create() {
        return new InventoryImpl();
    }

    @Override
    public double getWeightLimit() {
        return weightLimit;
    }

    @Override
    public void setWeightLimit(final double limit) {
        Preconditions.checkArgument(limit >= 0.0, "Weight limit cannot be negative");
        Preconditions.checkState(limit >= getTotalWeight());
        this.weightLimit = limit;
    }

    @Override
    public int getCountLimit() {
        return countLimit;
    }

    @Override
    public void setCountLimit(final int limit) {
        Preconditions.checkArgument(limit >= 0, "Count limit cannot be negative");
        Preconditions.checkState(limit >= items.size(), "Limit too low");
        this.countLimit = limit;
    }

    @Override
    public void add(final InventoryItem item) {
        Preconditions.checkArgument(item != null, "New item cannot be null");
        Preconditions.checkState(size() < getCountLimit(), "Cannot exceed item count limit");
        Preconditions.checkState(getTotalWeight() + item.getWeight() <= getWeightLimit(),
                                 "Cannot exceed total item weight limit");
        items.add(item);
    }

    @Override
    public void remove(final InventoryItem item) {
        Preconditions.checkArgument(items.contains(item), "No such element in the inventory");
        items.remove(item);
    }

    @Override
    public InventoryItem get(final int index) {
        return items.get(index);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public double getTotalWeight() {
        double totalWeight = 0.0;
        for (final InventoryItem item : items) totalWeight += item.getWeight();
        return totalWeight;
    }

    @Override
    public Iterator<InventoryItem> iterator() {
        return items.iterator();
    }

    private InventoryImpl() {
    }

    private final List<InventoryItem> items = new ArrayList<>();
    private int countLimit;
    private double weightLimit;

}
