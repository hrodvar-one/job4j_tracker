package ru.job4j.poly;

public class FileStore implements Store {
    private String path;

    public FileStore() { }

    public FileStore(String path) {
        this.path = path;
    }

    public void save(String data) {
        //Тут реализация хранения данных в файле
    }

    public String read() {
        // Тут чтение файла
        return null;
    }
}
