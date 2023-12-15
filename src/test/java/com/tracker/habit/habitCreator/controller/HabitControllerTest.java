package com.tracker.habit.habitCreator.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.tracker.habit.habitCreator.dto.HabitRecord;
import com.tracker.habit.habitCreator.entity.Habit;
import com.tracker.habit.habitCreator.service.HabitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    void createHabitTest() throws Exception {
        HabitRecord habitRecord = new HabitRecord(1L, "Eat healthy food", 25, 1L);
        String json = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).writeValueAsString(habitRecord);
        when(habitService.saveHabit(habitRecord))
                .thenReturn(1L);

        mockMvc.perform(post("/api/habits/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(result -> result.getResponse().getContentAsString().equals("http://localhost:8081/api/habits/" + 1L));
        verify(habitService).saveHabit(habitRecord);
    }


}