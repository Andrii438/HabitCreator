package com.tracker.habit.habitCreator.service;

import com.tracker.habit.habitCreator.dto.HabitRecord;
import com.tracker.habit.habitCreator.entity.Habit;
import com.tracker.habit.habitCreator.mapper.HabitMapper;
import com.tracker.habit.habitCreator.mapper.HabitMapperImpl;
import com.tracker.habit.habitCreator.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HabitServiceImpl implements HabitService {
    private HabitRepository repository;
    private HabitMapper habitMapper = new HabitMapperImpl();

    public HabitServiceImpl(HabitRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HabitRecord> getAllHabits(){
        List<HabitRecord> habits = repository.findAll().stream()
                .map( habit -> habitMapper.habitToHabitRecord(habit))
                .collect(Collectors.toList());
         return habits;
    }

    @Override
    public Long saveHabit(HabitRecord habitRecord){
        Habit habit = repository.save(habitMapper.habitRecordToHabit(habitRecord));
        return habit.getId();
    }
}
