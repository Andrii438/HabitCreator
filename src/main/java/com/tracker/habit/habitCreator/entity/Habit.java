package com.tracker.habit.habitCreator.entity;

import jakarta.persistence.*;

import java.util.Objects;


@Entity(name = "habits")
public class Habit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "day_duration")
    private int dayDuration;
    @Column(name = "owner_id")
    private Long ownerId;

    public Habit(Long id, String name, int dayDuration, Long ownerId) {
        this.id=id;
        this.name = name;
        this.dayDuration = dayDuration;
        this.ownerId = ownerId;
    }

    public Habit() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDayDuration() {
        return dayDuration;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Habit habit = (Habit) o;

        if (!id.equals(habit.id)) return false;
        return Objects.equals(name, habit.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dayDuration=" + dayDuration +
                ", ownerId=" + ownerId +
                '}';
    }
}
