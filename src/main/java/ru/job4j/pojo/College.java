package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFio("Aleksandr Kobelskiy");
        student.setGroup("Job4j");
        student.setReceiptDate(new Date(2023, 9, 19, 9, 50));
        System.out.println(student.getFio() + " studies in a group " + student.getGroup() + " with " + student.getReceiptDate());
    }
}
