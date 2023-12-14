package com.tracker.habit.habitCreator.mapper;

import com.tracker.habit.habitCreator.dto.HabitRecord;
import com.tracker.habit.habitCreator.entity.Habit;
import org.mapstruct.Mapper;

@Mapper
public interface  HabitMapper {

    HabitRecord toRedord(Habit habit);

    Habit fromRecord(HabitRecord record);
}
