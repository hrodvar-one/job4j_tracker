package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.action.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StartUITest {
    @Test
    void whenCreateItem() {
        Output output = new StubOutput();
        Input input = new MockInput(
                new String[] {"0", "Item name", "1"}
        );
        MemTracker tracker = new MemTracker();
        UserAction[] actions = {
                new Create(output),
                new Exit(output)
        };
        new StartUI(output).init(input, tracker, List.of(actions));
        assertThat(tracker.findAll().get(0).getName()).isEqualTo("Item name");
    }

    @Test
    void whenReplaceItemTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = tracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input input = new MockInput(
                new String[] {"0", String.valueOf(one.getId()), replaceName, "1"}
        );
        UserAction[] actions = new UserAction[]{
                new Replace(output),
                new Exit(output)
        };
        new StartUI(output).init(input, tracker, List.of(actions));
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Изменить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Редактирование заявки ===" + ln
                        + "Заявка изменена успешно." + ln
                        + "Меню:" + ln
                        + "0. Изменить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenDeleteItem() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input input = new MockInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new Delete(output),
                new Exit(output)
        };
        new StartUI(output).init(input, tracker, List.of(actions));
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    void whenFindAllAction() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = tracker.add(new Item("test1"));
        Input input = new MockInput(
                new String[] {"0", String.valueOf(one.getId()), "1"}
        );
        UserAction[] actions = new UserAction[]{
                new FindAll(output),
                new Exit(output)
        };
        new StartUI(output).init(input, tracker, List.of(actions));
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Показать все заявки" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод всех заявок ===" + ln
                        + one + ln
                        + "Меню:" + ln
                        + "0. Показать все заявки" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindByNameAction() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = tracker.add(new Item("test1"));
        Input input = new MockInput(
                new String[] {"0", one.getName(), "1"}
        );
        UserAction[] actions = new UserAction[]{
                new FindByName(output),
                new Exit(output)
        };
        new StartUI(output).init(input, tracker, List.of(actions));
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Показать заявки по имени" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод заявок по имени ===" + ln
                        + one + ln
                        + "Меню:" + ln
                        + "0. Показать заявки по имени" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenFindByIdAction() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = tracker.add(new Item("test1"));
        Input input = new MockInput(
                new String[] {"0", String.valueOf(one.getId()), "1"}
        );
        UserAction[] actions = new UserAction[]{
                new FindById(output),
                new Exit(output)
        };
        new StartUI(output).init(input, tracker, List.of(actions));
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Показать заявку по id" + ln
                        + "1. Завершить программу" + ln
                        + "=== Вывод заявки по id ===" + ln
                        + one + ln
                        + "Меню:" + ln
                        + "0. Показать заявку по id" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }

    @Test
    void whenInvalidExit() {
        Output output = new StubOutput();
        Input input = new MockInput(
                new String[] {"7", "0"}
        );
        MemTracker tracker = new MemTracker();
        UserAction[] actions = new UserAction[]{
                new Exit(output)
        };
        new StartUI(output).init(input, tracker, List.of(actions));
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Завершить программу" + ln
                        + "Неверный ввод, вы можете выбрать: 0 .. 0" + ln
                        + "Меню:" + ln
                        + "0. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }
}