package ru.job4j.tracker.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;

import static org.mockito.Mockito.*;

class DeleteTest {

    private Output output;
    private MemTracker tracker;
    private Input input;
    private Delete deleteAction;

    @BeforeEach
    void setUp() {
        output = mock(Output.class);
        tracker = mock(MemTracker.class);
        input = mock(Input.class);
        deleteAction = new Delete(output);
    }

    @Test
    void whenDeleteSuccess() {
        int id = 1;
        Item item = new Item("Test item");

        when(input.askInt(anyString())).thenReturn(id);
        when(tracker.findById(id)).thenReturn(item);

        deleteAction.execute(input, tracker);

        verify(output).println("=== Удаление заявки ===");
        verify(tracker).findById(id);
        verify(tracker).delete(id);
        verify(output).println("Заявка удалена успешно.");
    }

    @Test
    void whenDeleteFails() {
        int id = 1;

        when(input.askInt(anyString())).thenReturn(id);
        when(tracker.findById(id)).thenReturn(null);

        deleteAction.execute(input, tracker);

        verify(output).println("=== Удаление заявки ===");
        verify(tracker).findById(id);
        verify(tracker).delete(id);
        verify(output).println("Ошибка удаления заявки.");
    }
}