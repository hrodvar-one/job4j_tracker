package ru.job4j.tracker.action;

import ru.job4j.tracker.*;

public class FindByName implements UserAction {
    private final Output output;

    public FindByName(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Показать заявки по имени";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        output.println("=== Вывод заявок по имени ===");
        String name = input.askStr("Введите имя: ");
        Item[] items = tracker.findByName(name).toArray(new Item[0]);
        if (items.length > 0) {
            for (Item item : items) {
                output.println(item);
            }
        } else {
            output.println("Заявки с именем: " + name + " не найдены.");
        }
        return true;
    }
}
