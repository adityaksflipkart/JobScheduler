package com.flp;

// In-memory Job Scheduler library with given schedule strategies..

// 1. Executes a job at a given point of time - T0
// 2. Fixed interval of X minutes - T0/T1, (T1+X)/T2, (T2+X)/T3, ... - X minutes after the completion of previous execution
// 3. Fixed rate of X minutes - T0/T1, (T0+X)/T2, (T0+2X)/T3, ... - X minutes after the start of previous execution
// 4. Parallelism can be tuned by the client



//T1 -- 5min , 10MIN      T---0 , T5, 10min, T15--T20, 10mins , T30

//        T0, T10, T20, T30 ....
/// delay queue --

///JOB ----


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scheduler scheduler = SchedulerFactory.getScheduler(5);
        SchedulerFactory.registerClient("Client1", 3, scheduler);
        Job fixed = new Job(Strategy.ONCE, 10, "Client1", new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                System.out.println("job1 is executed");
                return true;
            }
        });
        scheduler.schedule(fixed);

  /*      Job fixedRate = new Job(Strategy.FIXED_RATE, 10, "Client1", new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                System.out.println("job1 is executed");
                return true;
            }
        });
        scheduler.schedule(fixedRate);*/

/*        Job fixedIntervel = new Job(Strategy.FIXED_INTERVEL, 10, "Client1", new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                System.out.println("job1 is executed "+System.currentTimeMillis());
                return true;
            }
        });
        scheduler.schedule(fixedIntervel);*/
    }
}
