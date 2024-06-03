package ru.job4j.tracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenReplaceItemIsSuccessful() {
        SqlTracker tracker = new SqlTracker(connection);
        Item original = new Item();
        original.setName("Original Name");
        original.setCreated(LocalDateTime.now().withNano(0));
        tracker.add(original);

        Item replacement = new Item();
        replacement.setName("New Name");
        replacement.setCreated(LocalDateTime.now().withNano(0));

        boolean result = tracker.replace(original.getId(), replacement);
        assertTrue(result);

        Item replacedItem = tracker.findById(original.getId());
        assertNotNull(replacedItem);
        assertEquals(replacement.getName(), replacedItem.getName());
        assertEquals(replacement.getCreated(), replacedItem.getCreated());
    }

    @Test
    public void whenReplaceItemIsNotSuccessful() {
        SqlTracker tracker = new SqlTracker(connection);
        Item replacement = new Item();
        replacement.setName("New Name");
        replacement.setCreated(LocalDateTime.now().withNano(0));

        boolean result = tracker.replace(999, replacement);
        assertFalse(result);
    }

    @Test
    public void whenDeleteExistingItemThenItemIsDeleted() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item();
        item.setName("Test Item");
        item.setCreated(LocalDateTime.now().withNano(0));
        tracker.add(item);

        tracker.delete(item.getId());
        Item deletedItem = tracker.findById(item.getId());
        assertNull(deletedItem);
    }

    @Test
    public void whenDeleteNonExistingItemThenNoException() {
        SqlTracker tracker = new SqlTracker(connection);
        assertDoesNotThrow(() -> tracker.delete(999));
    }

    @Test
    public void whenFindAllThenReturnAllItems() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item();
        item1.setName("Item 1");
        item1.setCreated(LocalDateTime.now().withNano(0));
        tracker.add(item1);

        Item item2 = new Item();
        item2.setName("Item 2");
        item2.setCreated(LocalDateTime.now().withNano(0));
        tracker.add(item2);

        List<Item> items = tracker.findAll();
        assertEquals(2, items.size());
        assertTrue(items.stream().anyMatch(item -> "Item 1".equals(item.getName())));
        assertTrue(items.stream().anyMatch(item -> "Item 2".equals(item.getName())));
    }

    @Test
    public void whenFindAllWithNoItemsThenReturnEmptyList() {
        SqlTracker tracker = new SqlTracker(connection);
        List<Item> items = tracker.findAll();
        assertTrue(items.isEmpty());
    }

    @Test
    public void whenFindByNameThenReturnMatchingItems() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item();
        item1.setName("Item");
        item1.setCreated(LocalDateTime.now().withNano(0));
        tracker.add(item1);

        Item item2 = new Item();
        item2.setName("Item");
        item2.setCreated(LocalDateTime.now().withNano(0));
        tracker.add(item2);

        Item item3 = new Item();
        item3.setName("Different");
        item3.setCreated(LocalDateTime.now().withNano(0));
        tracker.add(item3);

        List<Item> items = tracker.findByName("Item");
        assertEquals(2, items.size());
        assertTrue(items.stream().allMatch(item -> "Item".equals(item.getName())));
    }

    @Test
    public void whenFindByNameWithNoMatchingItemsThenReturnEmptyList() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item();
        item.setName("Different");
        item.setCreated(LocalDateTime.now().withNano(0));
        tracker.add(item);

        List<Item> items = tracker.findByName("NonExisting");
        assertTrue(items.isEmpty());
    }

    @Test
    public void whenFindByIdThenReturnMatchingItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item();
        item.setName("Test Item");
        item.setCreated(LocalDateTime.now().withNano(0));
        tracker.add(item);

        Item foundItem = tracker.findById(item.getId());
        assertNotNull(foundItem);
        assertEquals(item.getId(), foundItem.getId());
        assertEquals(item.getName(), foundItem.getName());
        assertEquals(item.getCreated(), foundItem.getCreated());
    }

    @Test
    public void whenFindByIdWithNoMatchingItemThenReturnNull() {
        SqlTracker tracker = new SqlTracker(connection);
        Item foundItem = tracker.findById(999);
        assertNull(foundItem);
    }
}