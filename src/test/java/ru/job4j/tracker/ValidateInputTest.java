package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ValidateInputTest {

    @Test
    void whenInvalidInput() {
        Output output = new StubOutput();
        Input in = new MockInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(output, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(1);
    }

    @Test
    void whenSuccessInput() {
        Output output = new StubOutput();
        Input in = new MockInput(
                new String[] {"1"}
        );
        ValidateInput input = new ValidateInput(output, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(1);
    }

    @Test
    void whenMultipleSuccessInput() {
        Output output = new StubOutput();
        Input in = new MockInput(
                new String[] {"1", "3", "2", "5"}
        );
        ValidateInput input = new ValidateInput(output, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(1);
        ValidateInput input2 = new ValidateInput(output, in);
        int selected2 = input2.askInt("Enter menu:");
        assertThat(selected2).isEqualTo(3);
        ValidateInput input3 = new ValidateInput(output, in);
        int selected3 = input3.askInt("Enter menu:");
        assertThat(selected3).isEqualTo(2);
        ValidateInput input4 = new ValidateInput(output, in);
        int selected4 = input4.askInt("Enter menu:");
        assertThat(selected4).isEqualTo(5);
    }

    @Test
    void whenNegativeSuccessInput() {
        Output output = new StubOutput();
        Input in = new MockInput(
                new String[] {"-2"}
        );
        ValidateInput input = new ValidateInput(output, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(-2);
    }
}