package com.flp;

public interface SchedulingStrategy {
    public boolean executeAndSchedule(Job job, Scheduler scheduler);
}
