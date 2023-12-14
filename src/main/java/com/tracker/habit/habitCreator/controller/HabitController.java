package com.tracker.habit.habitCreator.controller;

import com.tracker.habit.habitCreator.dto.HabitRecord;
import com.tracker.habit.habitCreator.service.HabitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/habits/")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping
    public List<HabitRecord> getHabits(){
        return habitService.getAllHabits();
    }

}
