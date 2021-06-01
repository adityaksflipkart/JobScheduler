package com.flp;

public class FixedrateScheduling implements SchedulingStrategy{
    public boolean executeAndSchedule(Job job, Scheduler scheduler){
        Client client = scheduler.getClient(job.getClientId());
        scheduler.schedule(job);
        client.execute(job.getCallable());
        return true;
    }
}
