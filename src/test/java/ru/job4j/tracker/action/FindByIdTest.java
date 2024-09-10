package ru.job4j.tracker.action;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import ru.job4j.tracker.*;

import static org.mockito.Mockito.*;

class FindByIdTest {

    private Output output;
    private SqlTracker tracker;
    private Input input;
    private FindById findByIdAction;

    @BeforeEach
    void setUp() {
        output = mock(Output.class);
        tracker = mock(SqlTracker.class);
        input = mock(Input.class);
        findByIdAction = new FindById(output);
    }

    @Test
    void whenFindByIdSuccess() {
        int id = 1;
        Item item = new Item("Test item");

        when(input.askInt(anyString())).thenReturn(id);
        when(tracker.findById(id)).thenReturn(item);

        findByIdAction.execute(input, tracker);

        verify(output).println("=== Вывод заявки по id ===");
        verify(tracker).findById(id);
        verify(output).println(item);
    }

    @Test
    void whenFindByIdFails() {
        int id = 1;

        when(input.askInt(anyString())).thenReturn(id);
        when(tracker.findById(id)).thenReturn(null);

        findByIdAction.execute(input, tracker);

        verify(output).println("=== Вывод заявки по id ===");
        verify(tracker).findById(id);
        verify(output).println("Заявка с введенным id: " + id + " не найдена.");
    }
}