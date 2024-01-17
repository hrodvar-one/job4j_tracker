package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.assertj.core.api.Assertions.assertThat;

class JobTest {
    @Test
    public void whenComparatorByNameAndPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Impl task", 1)
        );
        assertThat(rsl).isLessThan(2);
    }

    @Test
    public void whenComparatorAbcByNameAndAbcByPriority() {
        Comparator<Job> cmpNamePriority = new JobAbcByName().thenComparing(new JobAbcByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Impl task", 1)
        );
        assertThat(rsl).isLessThan(2);
    }

    @Test
    public void whenComparatorAbcByName() {
        Comparator<Job> cmpAbcName = new JobAbcByName();
        int rsl = cmpAbcName.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isLessThan(4);
    }

    @Test
    public void whenComparatorAbcByPriority() {
        Comparator<Job> cmpAbcPriority = new JobAbcByPriority();
        int rsl = cmpAbcPriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenComparatorDescByName() {
        Comparator<Job> cmpDeskName = new JobDescByName();
        int rsl = cmpDeskName.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenComparatorDescByPriority() {
        Comparator<Job> cmpDeskPriority = new JobDescByPriority();
        int rsl = cmpDeskPriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isLessThan(2);
    }
}