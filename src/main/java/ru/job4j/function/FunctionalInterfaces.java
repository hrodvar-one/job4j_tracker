package ru.job4j.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        BiConsumer<Integer, String> biConsumer = (first, second) -> map.put(first, second);
        biConsumer.accept(1, "one");
        biConsumer.accept(2, "two");
        biConsumer.accept(3, "three");
        biConsumer.accept(4, "four");
        biConsumer.accept(5, "five");
        biConsumer.accept(6, "six");
        biConsumer.accept(7, "seven");

        BiPredicate<Integer, String> biPredicate = (first, second) -> first % 2 == 0 || map.get(first).length() == 4;
        for (Integer key : map.keySet()) {
            if (biPredicate.test(key, "one")) {
                System.out.println("key: " + key + " value: " + map.get(key));
            }
        }

        Supplier<List<String>> supplier = () -> (List<String>) map.values();
        List<String> strings = new ArrayList<>(supplier.get());

        Consumer<String> consumer = (first) -> System.out.println(first);
        Function<String, String> function = (firstString) -> firstString.toUpperCase();
        for (String string : strings) {
            consumer.accept(function.apply(string));
        }
    }
}
