package pl.rsokol;

public interface Inventory extends Iterable<InventoryItem> {

    double getWeightLimit();

    void setWeightLimit(double limit);

    int getCountLimit();

    void setCountLimit(int limit);

    void add(InventoryItem item);

    void remove(InventoryItem item);

    InventoryItem get(int index);

    int size();

    double getTotalWeight();

}
