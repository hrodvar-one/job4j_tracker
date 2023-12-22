package ru.job4j.ex;

public class FinfEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int result = -1;
        for (int i = 0; i < value.length; i++) {
            if (value[i].equals(key)) {
                result = i;
            }
        }
        if (result == -1) {
            throw new ElementNotFoundException("Элемент не найден");
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            System.out.println(indexOf(new String[]{"Огонь", "Солнце", "Окружность"}, "Окружность"));
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}
