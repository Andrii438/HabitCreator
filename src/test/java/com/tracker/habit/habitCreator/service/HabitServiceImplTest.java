package com.tracker.habit.habitCreator.service;

import com.tracker.habit.habitCreator.entity.Habit;
import com.tracker.habit.habitCreator.mapper.HabitMapper;
import com.tracker.habit.habitCreator.mapper.HabitMapperImpl;
import com.tracker.habit.habitCreator.repository.HabitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HabitServiceTest {

    @InjectMocks
    private HabitServiceImpl habitService;

    @Mock
    private HabitRepository repository;

    @Test
    void getAllHabits() {
        when(repository.findAll()).thenReturn(List.of(
                new Habit(1L, "Eat healthy food", 25, 1L),
                new Habit(2L, "Read book", 100, 2L),
                new Habit(3L, "Go to the gym", 28, 3L),
                new Habit(4L, "Learn English", 39, 4L)
        ));

        assertThat(habitService.getAllHabits()).isNotNull();
        assertThat(habitService.getAllHabits().size()).isEqualTo(4);
    }
}