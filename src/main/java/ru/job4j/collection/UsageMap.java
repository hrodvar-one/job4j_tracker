package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("kobel.san.sanich@gmail.com", "Aleksandr Kobelskiy");
        hashMap.put("parsentev@yandex.ru", "Petr Arsentev");
        hashMap.put("omskobl@yandex.ru", "Ivan Sidorov");
        hashMap.put("omskobl@yandex.ru", "Ivan Petrov");
        for (String s : hashMap.keySet()) {
            System.out.println(s);
        }
    }
}
