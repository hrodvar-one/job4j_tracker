package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определяется по полю приоритет.
     * Для вставки использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        int index = 0;
        for (Task element : tasks) {
            if (task.getPriority() == 0) {
                break;
            }
            if (task.getPriority() == tasks.size()) {
                index = task.getPriority();
                break;
            }
        }
        this.tasks.add(index, task);
    }

    public Task take() {
        return tasks.poll();
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        queue.tasks.add(new Task("high", 1));
        queue.tasks.add(new Task("medium", 2));
        queue.put(new Task("low", 3));
        for (Task t : queue.tasks) {
            System.out.println(t.getDescription());
            System.out.println(t.getPriority());
        }
    }
}
