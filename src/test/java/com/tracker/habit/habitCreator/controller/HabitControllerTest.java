package com.tracker.habit.habitCreator.controller;

import com.tracker.habit.habitCreator.dto.HabitRecord;
import com.tracker.habit.habitCreator.service.HabitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HabitController.class)
class HabitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HabitService habitService;

    @Test
    void getHabits() throws Exception {
        when(habitService.getAllHabits())
                .thenReturn(List.of(
                        new HabitRecord(1L, "Eat healthy food", 25, 1L),
                        new HabitRecord(2L, "Read book", 100, 2L)
                ));

        mockMvc.perform(get("/api/habits/"))
                .andExpectAll( result -> status().isOk(),
                               result -> content().string(containsString("Eat healthy food")));
    }


}