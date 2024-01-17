package ru.job4j.hashmap;

public record Label(String name, double score) implements Comparable<Label> {
    @Override
    public int compareTo(Label o) {
        return Double.compare(this.score, o.score);
    }

    @Override
    public double score() {
        return score;
    }

    public String getName() {
        return name;
    }
}
