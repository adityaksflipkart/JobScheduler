package com.flp;

public class FixedScheduling implements SchedulingStrategy{
    public boolean executeAndSchedule(Job job, Scheduler scheduler){
        Client client = scheduler.getClient(job.getClientId());
        client.execute(job.getCallable());
        scheduler.schedule(job);
        return true;
    }

}
