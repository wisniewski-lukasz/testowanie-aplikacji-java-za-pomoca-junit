package pl.rsokol;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InventoryItemImplTest {

    @Test
    public void create() {
        final InventoryItem item = InventoryItemImpl.of("Przedmiot", 12.34);
        assertThat(item.getName()).isEqualTo("Przedmiot");
        assertThat(item.getWeight()).isEqualTo(12.34);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNullName() {
        InventoryItemImpl.of(null, 12.34);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithEmptyName() {
        InventoryItemImpl.of("", 12.34);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNegativeWeight() {
        InventoryItemImpl.of("Przedmiot", -0.01);
    }

    @Test
    public void equalsAndHashCode() {
        EqualsVerifier.forClass(InventoryItemImpl.class)
                      .verify();
    }

    @Test
    public void stringification() {
        final InventoryItem item = InventoryItemImpl.of("Przedmiot", 12.34);
        assertThat(item.toString()).isEqualTo("Przedmiot, 12.34");
    }

}
