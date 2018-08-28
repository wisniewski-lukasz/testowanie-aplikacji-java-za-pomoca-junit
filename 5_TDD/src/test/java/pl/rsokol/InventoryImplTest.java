package pl.rsokol;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.data.Offset;

public class InventoryImplTest {

    @Before
    public void before() {
        inventory = InventoryImpl.create();
    }

    @Test
    public void setPositiveCountLimit() {
        inventory.setCountLimit(5);
        assertThat(inventory.getCountLimit()).isEqualTo(5);
    }

    @Test
    public void setZeroCountLimit() {
        inventory.setCountLimit(0);
        assertThat(inventory.getCountLimit()).isZero();
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNegativeCountLimit() {
        inventory.setCountLimit(-1);
    }

    @Test
    public void lowerCountLimit() {
        inventory.setWeightLimit(4.0);
        inventory.setCountLimit(3);
        inventory.add(InventoryItemImpl.of("pierwszy", 1.0));
        inventory.add(InventoryItemImpl.of("drugi", 2.0));
        inventory.setCountLimit(2);
    }

    @Test(expected = IllegalStateException.class)
    public void countLimitTooLow() {
        inventory.setCountLimit(3);
        inventory.add(InventoryItemImpl.of("pierwszy", 1.0));
        inventory.add(InventoryItemImpl.of("drugi", 2.0));
        inventory.setCountLimit(1);
    }

    @Test
    public void setPositiveWeightLimit() {
        inventory.setWeightLimit(2.5);
        assertThat(inventory.getWeightLimit()).isEqualTo(2.5);
    }

    @Test
    public void setZeroWeightLimit() {
        inventory.setWeightLimit(0.0);
        assertThat(inventory.getWeightLimit()).isZero();
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNegativeWeightLimit() {
        inventory.setWeightLimit(-0.1);
    }

    @Test
    public void lowerWeightLimit() {
        inventory.setCountLimit(2);
        inventory.setWeightLimit(5.0);
        inventory.add(InventoryItemImpl.of("pierwszy", 1.0));
        inventory.add(InventoryItemImpl.of("drugi", 2.0));
        inventory.setWeightLimit(4.0);
    }

    @Test(expected = IllegalStateException.class)
    public void weightLimitTooLow() {
        inventory.setWeightLimit(4.0);
        inventory.add(InventoryItemImpl.of("pierwszy", 1.0));
        inventory.add(InventoryItemImpl.of("drugi", 2.0));
        inventory.setWeightLimit(2.0);
    }

    @Test
    public void emptyInventoryHasToWeight() {
        assertThat(inventory.getTotalWeight()).isZero();
    }

    @Test
    public void getTotalWeight() {
        inventory.setCountLimit(2);
        inventory.setWeightLimit(4.0);
        inventory.add(InventoryItemImpl.of("pierwszy", 1.1));
        inventory.add(InventoryItemImpl.of("drugi", 2.2));
        assertThat(inventory.getTotalWeight()).isEqualTo(3.3, Offset.offset(0.01));
    }

    //

    @Test(expected = IllegalArgumentException.class)
    public void addNull() {
        inventory.add(null);
    }

    @Test(expected = IllegalStateException.class)
    public void addExceedingCountLimit() {
        inventory.add(InventoryItemImpl.of("pierwszy", 1.1));
    }

    @Test(expected = IllegalStateException.class)
    public void addExceedingWeightLimit() {
        inventory.setWeightLimit(0.5);
        inventory.add(InventoryItemImpl.of("pierwszy", 0.6));
    }

    @Test
    public void add() {
        inventory.setCountLimit(10);
        inventory.setWeightLimit(10.0);
        inventory.add(InventoryItemImpl.of("pierwszy", 1.1));
        inventory.add(InventoryItemImpl.of("drugi", 2.2));
        assertThat(inventory.size()).isEqualTo(2);
    }

    //

    @Test(expected = IndexOutOfBoundsException.class)
    public void getNegative() {
        inventory.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getFromEmpty() {
        inventory.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getExceedingEnd() {
        inventory.setCountLimit(2);
        inventory.setWeightLimit(10.0);
        inventory.add(InventoryItemImpl.of("pierwszy", 1.1));
        inventory.get(1);
    }

    @Test
    public void get() {
        inventory.setCountLimit(2);
        inventory.setWeightLimit(10.0);
        final InventoryItem item = InventoryItemImpl.of("pierwszy", 1.1);
        inventory.add(item);
        assertThat(inventory.get(0)).isSameAs(item);
    }

    // iterator

    @Test
    public void iterator() {
        inventory.setCountLimit(10);
        inventory.setWeightLimit(10.0);
        final InventoryItem item1 = InventoryItemImpl.of("pierwszy", 1.1);
        final InventoryItem item2 = InventoryItemImpl.of("drugi", 2.2);
        inventory.add(item1);
        inventory.add(item2);
        assertThat(inventory).containsOnly(item1, item2);
    }

    //

    @Test(expected = IllegalArgumentException.class)
    public void removeNull() {
        inventory.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNonexistent() {
        inventory.setCountLimit(10);
        inventory.setWeightLimit(10.0);
        final InventoryItem item1 = InventoryItemImpl.of("pierwszy", 1.1);
        final InventoryItem item2 = InventoryItemImpl.of("drugi", 2.2);
        inventory.add(item1);
        inventory.remove(item2);
    }

    @Test
    public void remove() {
        inventory.setCountLimit(10);
        inventory.setWeightLimit(10.0);
        final InventoryItem item1 = InventoryItemImpl.of("pierwszy", 1.1);
        final InventoryItem item2 = InventoryItemImpl.of("drugi", 2.2);
        inventory.add(item1);
        inventory.add(item2);
        inventory.remove(item2);
        assertThat(inventory).containsOnly(item1);
    }

    private Inventory inventory;

}
