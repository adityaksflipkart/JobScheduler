package com.flp;

import java.util.concurrent.ExecutionException;

public class SchedulerFactory {

    public static  Scheduler getScheduler(int parallism) throws ExecutionException, InterruptedException {
        return  new SchedulerImpl(parallism);
    }

    public static void registerClient(String clientId,int parallesm, Scheduler scheduler){
        Client client = new Client(clientId, parallesm);
        scheduler.registerClient(client);
    }
}
