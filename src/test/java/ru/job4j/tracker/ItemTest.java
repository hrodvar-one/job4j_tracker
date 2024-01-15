package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {
    @Test
    void WhenItemAscByName() {
        List<Item> items = Arrays.asList(
                new Item(1, "Item 1"),
                new Item(3, "Item 3"),
                new Item(2, "Item 2")
        );
        items.sort(new ItemAscByName());
        List<Item> expected = Arrays.asList(
                new Item(1, "Item 1"),
                new Item(2, "Item 2"),
                new Item(3, "Item 3")
        );
        assertThat(items.toString()).isEqualTo(expected.toString());
    }

    @Test
    void WhenItemDescByName() {
        List<Item> items = Arrays.asList(
                new Item(1, "Item 1"),
                new Item(3, "Item 3"),
                new Item(2, "Item 2")
        );
        items.sort(new ItemDescByName());
        List<Item> expected = Arrays.asList(
                new Item(3, "Item 3"),
                new Item(2, "Item 2"),
                new Item(1, "Item 1")
        );
        assertThat(items.toString()).isEqualTo(expected.toString());
    }
}