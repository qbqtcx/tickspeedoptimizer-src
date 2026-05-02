package com.client.tickspeed.core;

import java.util.*;

public final class Scheduler {
    private final List<Task> tasks = new ArrayList<>();
    private int t;

    public void run(Runnable r, int d) {
        tasks.add(new Task(t + d, r));
    }

    public void tick() {
        t++;
        for (Iterator<Task> it = tasks.iterator(); it.hasNext();) {
            Task tk = it.next();
            if (tk.time <= t) {
                tk.r.run();
                it.remove();
            }
        }
    }

    private record Task(int time, Runnable r) {}
}
