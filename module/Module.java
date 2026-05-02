package com.client.tickspeed.module;

import java.util.HashMap;
import java.util.Map;

public abstract class Module {

    public boolean enabled = true;
    public String name;
    public Category cat;

    public Map<String, Double> settings = new HashMap<>();

    public Module(String n, Category c) {
        name = n;
        cat = c;
    }

    public void toggle() {
        enabled = !enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    public void onEnable() {}
    public void onDisable() {}
    public void tick() {}

    public enum Category {
        COMBAT, MOVEMENT, RENDER, MISC
    }
}
