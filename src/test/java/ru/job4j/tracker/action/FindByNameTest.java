package ru.job4j.tracker.action;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;

import java.util.List;

import static org.mockito.Mockito.*;

class FindByNameTest {

    @Test
    void whenItemsFoundThenPrintItems() {
        Output output = mock(Output.class);
        Input input = mock(Input.class);
        MemTracker tracker = mock(MemTracker.class);
        Item item1 = new Item(1, "test1");
        Item item2 = new Item(2, "test2");
        FindByName findByName = new FindByName(output);

        when(input.askStr("Введите имя: ")).thenReturn("test");
        when(tracker.findByName("test")).thenReturn(List.of(item1, item2));

        findByName.execute(input, tracker);

        verify(output).println("=== Вывод заявок по имени ===");
        verify(output).println(item1);
        verify(output).println(item2);
    }

    @Test
    void whenItemsNotFoundThenPrintNotFoundMessage() {
        Output output = mock(Output.class);
        Input input = mock(Input.class);
        MemTracker tracker = mock(MemTracker.class);
        FindByName findByName = new FindByName(output);

        when(input.askStr("Введите имя: ")).thenReturn("nonexistent");
        when(tracker.findByName("nonexistent")).thenReturn(List.of());

        findByName.execute(input, tracker);

        verify(output).println("=== Вывод заявок по имени ===");
        verify(output).println("Заявки с именем: nonexistent не найдены.");
    }
}