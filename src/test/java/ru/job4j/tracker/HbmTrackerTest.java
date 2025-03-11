package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class HbmTrackerTest {

    /**
     * Позитивный тест добавления элемента в хранилище.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName()).isEqualTo(item.getName());
        }
    }

    /**
     * Негативный тест добавления элемента в хранилище.
     */
    @Test
    public void whenAddNullItemThenThrowException() {
        try (var tracker = new HbmTracker()) {
            assertThatThrownBy(() -> tracker.add(null))
                    .isInstanceOf(Exception.class);
        }
    }

    /**
     * Позитивный тест успешной замены существующего элемента.
     */
    @Test
    public void whenReplaceExistingItemThenReturnTrue() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("Old Name");
            tracker.add(item);

            Item newItem = new Item();
            newItem.setName("New Name");

            boolean result = tracker.replace(item.getId(), newItem);
            Item replacedItem = tracker.findById(item.getId());

            assertThat(result).isTrue();
            assertThat(replacedItem.getName()).isEqualTo("New Name");
        }
    }

    /**
     * Негативный тест замены несуществующего элемента.
     */
    @Test
    public void whenReplaceNonExistingItemThenReturnFalse() {
        try (var tracker = new HbmTracker()) {
            Item newItem = new Item();
            newItem.setName("New Name");

            boolean result = tracker.replace(9999, newItem);
            assertThat(result).isFalse();
        }
    }

    /**
     * Позитивный тест успешного удаления существующего элемента.
     */
    @Test
    public void whenDeleteExistingItemThenReturnTrue() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("To Delete");
            tracker.add(item);

            boolean result = tracker.delete(item.getId());
            Item deletedItem = tracker.findById(item.getId());

            assertThat(result).isTrue();
            assertThat(deletedItem).isNull();
        }
    }

    /**
     * Негативный тест удаления несуществующего элемента.
     */
    @Test
    public void whenDeleteNonExistingItemThenReturnFalse() {
        try (var tracker = new HbmTracker()) {
            boolean result = tracker.delete(9999);
            assertThat(result).isFalse();
        }
    }

    /**
     * Позитивный тест поиска всех элементов в хранилище.
     */
    @Test
    public void whenFindAllThenReturnAllItems() {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item();
            item1.setName("Item 1");
            tracker.add(item1);

            Item item2 = new Item();
            item2.setName("Item 2");
            tracker.add(item2);

            List<Item> items = tracker.findAll();

            assertThat(items).hasSizeGreaterThanOrEqualTo(2);
            assertThat(items).extracting(Item::getName).contains("Item 1", "Item 2");
        }
    }

    /**
     * Негативный тест поиска всех элементов в пустом хранилище.
     */
    @Test
    public void whenFindAllInEmptyTrackerThenReturnEmptyList() {
        try (var tracker = new HbmTracker()) {
            List<Item> items = tracker.findAll();
            assertThat(items).isEmpty();
        }
    }

    /**
     * Позитивный тест поиска элемента по имени.
     */
    @Test
    public void whenFindByNameThenReturnMatchingItems() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("FindMe");
            tracker.add(item);

            List<Item> result = tracker.findByName("FindMe");

            assertThat(result).isNotEmpty();
            assertThat(result.get(0).getName()).isEqualTo("FindMe");
        }
    }

    /**
     * Негативный тест поиска элемента по имени, которого нет в хранилище.
     */
    @Test
    public void whenFindByNameNotExistingThenReturnEmptyList() {
        try (var tracker = new HbmTracker()) {
            List<Item> result = tracker.findByName("NonExistentName");
            assertThat(result).isEmpty();
        }
    }

    /**
     * Позитивный тест поиска элемента по уникальному идентификатору.
     */
    @Test
    public void whenFindByIdThenReturnItem() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("FindById");
            tracker.add(item);

            Item result = tracker.findById(item.getId());

            assertThat(result).isNotNull();
            assertThat(result.getName()).isEqualTo("FindById");
        }
    }

    /**
     * Негативный тест поиска элемента по несуществующему идентификатору.
     */
    @Test
    public void whenFindByIdNonExistingThenReturnNull() {
        try (var tracker = new HbmTracker()) {
            Item result = tracker.findById(9999);
            assertThat(result).isNull();
        }
    }
}