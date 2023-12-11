package ru.job4j.oop;

public class Student1 {
    public void music() {
        System.out.println("Tra tra tra");
    }

    public void sing() {
        System.out.println("I believe I can fly");
    }

    public static void main(String[] args) {
        Student1 petya = new Student1();
        petya.music();
        petya.music();
        petya.music();
        petya.sing();
        petya.sing();
        petya.sing();
    }
}
