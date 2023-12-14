package com.tracker.habit.habitCreator.repository;

import com.tracker.habit.habitCreator.entity.Habit;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
class HabitRepositoryTest {

    @Autowired
    private HabitRepository habitRepository;

    @Test
    public void testSaveAndFindUser() {
        // Given
        Habit habit = new Habit(1L, "Some habit", 100, null);

        // When
        Habit savedHabit = habitRepository.save(habit);
        Habit foundHabit = habitRepository.findById(savedHabit.getId()).orElse(null);

        // Then
        assertThat(foundHabit).isNotNull();
        assertThat(foundHabit.getName()).isEqualTo("Some habit");
        assertThat(foundHabit.getDayDuration()).isEqualTo(100);
    }
}