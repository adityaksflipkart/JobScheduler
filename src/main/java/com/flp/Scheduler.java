package com.flp;

public interface Scheduler {
    public void schedule(Job job);
    public void  registerClient(Client client);
    public void unregisterClient(String clientId);
    public Client getClient(String clientId);

}
