package com.flp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class SchedulerImpl implements Scheduler {
    private DelayQueue<Job> jobDelayQueue;
    private ExecutorService executorService;
    private int parallism;
    private ConcurrentMap<String,Client> clients;

    public SchedulerImpl(int parallism) throws InterruptedException, ExecutionException {
        this.parallism=parallism;
        this.jobDelayQueue = new DelayQueue<>() ;
        this.executorService = Executors.newFixedThreadPool(parallism,getThreadFactory());
        clients=new ConcurrentHashMap<>();
        addExecutorThread();
    }

    private ThreadFactory getThreadFactory() {
        return  new ThreadFactory(){
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "schedulerThread" + System.currentTimeMillis());
                thread.setDaemon(true);
                return thread;
            }
        };
    }

    private void addExecutorThread() throws InterruptedException, ExecutionException {
        Collection<Callable<Boolean>> callables=new ArrayList<>();
        Scheduler scheduler=this;
        for(int i=0;i<this.parallism;i++) {
            Callable<Boolean> callable =new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    while(true) {
                        Optional<Job> job = Optional.ofNullable(jobDelayQueue.poll());
                        if (job.isPresent()) {
                            job.get().schedulingStrategy.getSchedulingStrategy().executeAndSchedule(job.get(), scheduler);
                        }
                    }
                }
            } ;
            callables.add(callable);
        }
        List<Future<Boolean>> futures = executorService.invokeAll(callables);
        for (Future<Boolean> future : futures) {
            future.get();
        }
    }

    @Override
    public void schedule(Job job) {
        this.jobDelayQueue.offer(job);
    }

    @Override
    public void registerClient(Client client) {
        this.clients.put(client.getClientId(),client);
    }

    @Override
    public void unregisterClient(String clientId) {
        this.clients.remove(clientId);
    }

    private ConcurrentMap<String, Client> getClients() {
        return clients;
    }

    public Client getClient(String clientId){
        return getClients().get(clientId);
    }
}
