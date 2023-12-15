package com.tracker.habit.habitCreator.controller;

import com.tracker.habit.habitCreator.dto.HabitRecord;
import com.tracker.habit.habitCreator.entity.Habit;
import com.tracker.habit.habitCreator.service.HabitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/habits/")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping
    public ResponseEntity<List<HabitRecord>> getHabits(){
        return ResponseEntity.ok(habitService.getAllHabits());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Habit> createHabit(@RequestBody HabitRecord habitRecord){
        Long createdId = habitService.saveHabit(habitRecord);
        return ResponseEntity.created(URI.create("http://localhost:8081/api/habits/" + createdId))
                .build();
    }

}
