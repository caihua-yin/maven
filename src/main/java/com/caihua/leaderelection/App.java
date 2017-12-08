package com.caihua.leaderelection;

import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.Closeable;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;

public class App extends LeaderSelectorListenerAdapter implements Closeable {
    private final LeaderSelector leaderSelector;
    private CuratorFramework client;
    private static final String PATH = "/leader";
    
    public App() {
        client = CuratorFrameworkFactory.newClient("localhost:2181", 
                new ExponentialBackoffRetry(1000, 3));
        
        // create a leader selector using the given path for management
        // all participants in a given leader selection must use the same path
        // ExampleClient here is also a LeaderSelectorListener but this isn't required
        leaderSelector = new LeaderSelector(client, PATH, this);
        
        // Re-queue when it relinquishes leadership
        leaderSelector.autoRequeue();
    }
    
    public void start() throws Exception
    {
        // the selection for this instance doesn't start until the leader selector is started
        // leader selection is done in the background so this call to leaderSelector.start() returns immediately
        client.start();
        
        leaderSelector.start();
    }
    
    @Override
    public void close() throws IOException
    {
        client.close();
        leaderSelector.close();
    }
    
    @Override
    public void takeLeadership(CuratorFramework client) throws Exception
    {
        // we are now the leader. This method should not return until we want to relinquish leadership
        while (true) {
            System.out.println(Calendar.getInstance().getTime() + ": I am leader, I am doing my job...");
            TimeUnit.SECONDS.sleep(1);
        }
        
    }
    public static void main( String[] args ) throws Exception {
        System.out.println(Calendar.getInstance().getTime() + ": I am slave, I am standby...");
        new App().start();
        System.out.println(Calendar.getInstance().getTime() + ": Start finished.");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
