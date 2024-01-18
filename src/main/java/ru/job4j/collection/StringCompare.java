package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        if (left.length() < right.length()) {
            for (int i = 0; i < left.length(); i++) {
                if (left.charAt(i) != right.charAt(i)) {
                    return Character.compare(left.charAt(i), right.charAt(i));
                }
            }
        } else if (left.length() > right.length()) {
            for (int i = 0; i < right.length(); i++) {
                if (left.charAt(i) != right.charAt(i)) {
                    return Character.compare(left.charAt(i), right.charAt(i));
                }
            }
        } else if (left.length() == right.length()) {
            for (int i = 0; i < right.length(); i++) {
                if (left.charAt(i) != right.charAt(i)) {
                    return Character.compare(left.charAt(i), right.charAt(i));
                }
            }
        }
        return left.length() - right.length();
    }
}
