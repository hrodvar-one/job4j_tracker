package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tracker {
//    private final Item[] items = new Item[100];
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;
    private int size = 0;

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
//            if (items[index].getId() == id) {
            if (items.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public Item add(Item item) {
        item.setId(ids++);
//        items[size++] = item;
        items.add(size++, item);
        return item;
    }

//    public Item[] findAll() {
    public List<Item> findAll() {
//        return Arrays.copyOf(items, size);
        return List.copyOf(items);
    }

    public Item[] findByName(String key) {
        Item[] rsl = new Item[size];
        int count = 0;
        for (int index = 0; index < size; index++) {
//            Item name = items[index];
            Item name = items.get(index);
            if (key.equals(name.getName())) {
                rsl[count] = name;
                count++;
            }
        }
        return Arrays.copyOf(rsl, count);
    }

    public Item findById(int id) {
        int index = indexOf(id);
//        return index != -1 ? items[index] : null;
        return index != -1 ? items.get(index) : null;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            item.setId(id);
//            items[index] = item;
            items.set(index, item);
        }
        return rsl;
    }

    public void delete(int id) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
//            System.arraycopy(items, index + 1, items, index, size - index - 1);
            System.arraycopy(items.toArray(), index + 1, items.toArray(), index, size - index - 1);
//            items[size - 1] = null;
            items.set(size - 1, null);
            size--;
        }
    }
}