package ru.job4j.search;

import java.util.ArrayList;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список пользователей, которые прошли проверку.
     */

    public ArrayList<Person> find(String key) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (person.getName().contains(key)
                    || person.getSurname().contains(key)
                    || person.getPhone().contains(key)
                    || person.getAddress().contains(key)) {
                result.add(person);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Person person = new Person("Petr", "Arsentev", "534872", "Bryansk");
        Person person2 = new Person("Alex", "Petrov", "323123", "Omsk");
        PhoneDictionary phoneDictionary = new PhoneDictionary();
        phoneDictionary.add(person);
        phoneDictionary.add(person2);
        System.out.println(phoneDictionary.find("Swb"));
    }
}
