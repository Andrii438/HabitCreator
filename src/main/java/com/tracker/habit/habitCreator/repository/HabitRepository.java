package com.tracker.habit.habitCreator.repository;

import com.tracker.habit.habitCreator.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

}
