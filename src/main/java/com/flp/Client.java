package com.flp;

import java.util.concurrent.*;

public class Client {
    private String clientId;
    private ExecutorService executorService;
    private int parallelsm=1;

    public Client(String clientId, int parallelsm) {
        this.clientId = clientId;
        this.parallelsm = parallelsm;
        executorService= Executors.newFixedThreadPool(parallelsm);
    }

    public String getClientId() {
        return clientId;
    }

    public void execute(Callable callable)  {
        Future submit = executorService.submit(callable);
        try {
            submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private ThreadFactory getThreadFactory() {
        return  new ThreadFactory(){
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r,  clientId+ System.currentTimeMillis());
                thread.setDaemon(false);
                return thread;
            }
        };
    }

}
