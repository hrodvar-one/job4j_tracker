package ru.job4j.tracker.action;

import ru.job4j.tracker.*;

public class FindAll implements UserAction {
    private final Output output;

    public FindAll(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Показать все заявки";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        output.println("=== Вывод всех заявок ===");
        Item[] items = tracker.findAll().toArray(new Item[0]);
        if (items.length > 0) {
            for (Item item : items) {
                output.println(item);
            }
        } else {
            output.println("Хранилище еще не содержит заявок");
        }
        return true;
    }
}
