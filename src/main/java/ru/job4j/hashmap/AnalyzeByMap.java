package ru.job4j.hashmap;

import java.lang.reflect.Array;
import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        int totalPoints = 0;
        int subjectCount = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                totalPoints += subject.score();
                subjectCount++;
            }
        }
        return (double) totalPoints / subjectCount;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> result = new ArrayList<>();
        for (Pupil pupil : pupils) {
            int totalPoints = 0;
            int subjectCount = 0;
            for (Subject subject : pupil.subjects()) {
                totalPoints += subject.score();
                subjectCount++;
            }
            result.add(new Label(pupil.name(), (double) totalPoints / subjectCount));
        }
        return result;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> result = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();
        int totalPoints;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                totalPoints = subject.score();
                map.put(subject.name(), map.getOrDefault(subject.name(), 0) + totalPoints);
            }
        }
        for (Map.Entry<String, Integer> s : map.entrySet()) {
            result.add(new Label(s.getKey(), (double) s.getValue() / pupils.size()));
        }
        return result;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> result = new ArrayList<>();
        for (Pupil pupil : pupils) {
            int totalPoints = 0;
            for (Subject subject : pupil.subjects()) {
                totalPoints += subject.score();
            }
            result.add(new Label(pupil.name(), totalPoints));
        }
        result.sort(Comparator.naturalOrder());
        return result.get(result.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> result = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();
        int totalPoints;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                totalPoints = subject.score();
                map.put(subject.name(), map.getOrDefault(subject.name(), 0) + totalPoints);
            }
        }
        for (Map.Entry<String, Integer> s : map.entrySet()) {
            result.add(new Label(s.getKey(), (double) s.getValue()));
        }
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i).getName().compareTo(result.get(i + 1).getName()) > 0) {
                Label temp = result.get(i);
                result.set(i, result.get(i + 1));
                result.set(i + 1, temp);
            }
        }
        return result.get(1);
    }
}
