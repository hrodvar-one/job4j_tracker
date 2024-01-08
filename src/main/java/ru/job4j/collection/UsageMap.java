package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("kobel.san.sanich@gmail.com", "Aleksandr Kobelskiy");
        for (String s : hashMap.keySet()) {
            System.out.println(s);
        }
    }
}
