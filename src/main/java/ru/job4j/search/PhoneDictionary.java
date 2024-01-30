package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

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
        Predicate<Person> personName = person -> person.getName().contains(key);
        Predicate<Person> personSurname = person -> person.getSurname().contains(key);
        Predicate<Person> personPhone = person -> person.getPhone().contains(key);
        Predicate<Person> personAddress = person -> person.getAddress().contains(key);
        Predicate<Person> combine = personName.or(personSurname).or(personPhone).or(personAddress);
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
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
