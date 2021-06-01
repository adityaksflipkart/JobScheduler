package com.flp;

public class ScheduleOnceStrategy implements SchedulingStrategy{
    public boolean executeAndSchedule(Job job, Scheduler scheduler){
        Client client = scheduler.getClient(job.getClientId());
        client.execute(job.getCallable());
        return true;
    }
}
