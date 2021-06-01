package com.flp;

import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public  class  Job implements Delayed {
    public Strategy schedulingStrategy;
    private long intervel;
    private String clientId;
    private Callable callable;

    public Job(Strategy schedulingStrategy, long intervel, String clientId, Callable callable) {
        this.schedulingStrategy = schedulingStrategy;
        this.intervel = intervel;
        this.clientId = clientId;
        this.callable = callable;
    }

    public Strategy getSchedulingStrategy() {
        return schedulingStrategy;
    }

    public long getIntervel() {
        return intervel;
    }

    public String getClientId() {
        return clientId;
    }

    public Callable getCallable() {
        return callable;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        intervel--;
        return unit.convert(intervel, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long diff=this.getIntervel() - ((Job)o).getIntervel();
        if(diff<0)
            return -1;
        if(diff>0){
            return 1;
        }
        return 0;
    }
}



