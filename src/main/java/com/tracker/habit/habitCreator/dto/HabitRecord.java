package com.tracker.habit.habitCreator.dto;

public record HabitRecord(
        Long id,
        String name,
        int dayDuration,
        Long ownerId) {
}
