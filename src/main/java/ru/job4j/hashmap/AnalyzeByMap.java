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
                if (map.containsKey(subject.name())) {
                    map.put(subject.name(), map.get(subject.name()) + totalPoints);
                } else {
                    map.put(subject.name(), subject.score());
                }
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
                if (map.containsKey(subject.name())) {
                    map.put(subject.name(), map.get(subject.name()) + totalPoints);
                } else {
                    map.put(subject.name(), subject.score());
                }
            }
        }
        for (Map.Entry<String, Integer> s : map.entrySet()) {
            result.add(new Label(s.getKey(), (double) s.getValue()));
        }
        result.sort(Comparator.naturalOrder());
        return result.get(result.size() - 1);
    }
}
