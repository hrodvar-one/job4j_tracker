package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;

public class ProductLabel {
    public List<String> generateLabels(List<Product> products) {
        List<String> list = new ArrayList<>();
        products.stream()
                .filter(product -> product.getStandard() - product.getActual() >= 0)
                .filter(product -> product.getStandard() - product.getActual() <= 3)
                .forEach(product -> list.add((new Label(product.getName(), (float) (product.getPrice() * 0.5))).toString()));
        return list;
    }
}
