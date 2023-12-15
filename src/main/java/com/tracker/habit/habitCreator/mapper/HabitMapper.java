package com.tracker.habit.habitCreator.mapper;

import com.tracker.habit.habitCreator.dto.HabitRecord;
import com.tracker.habit.habitCreator.entity.Habit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface HabitMapper {

    HabitRecord habitToHabitRecord(Habit habit);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "dayDuration", source = "dayDuration"),
            @Mapping(target = "ownerId", source = "ownerId")
    })
    Habit habitRecordToHabit(HabitRecord record);
}
