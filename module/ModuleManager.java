package com.client.tickspeed.module;

import com.client.tickspeed.module.impl.TimerModule;

import java.util.*;

public final class ModuleManager {

    public final List<Module> mods = new ArrayList<>();

    public TimerModule timer;

    public void init() {
        timer = new TimerModule();
        mods.add(timer);

        mods.add(new Dummy("Fly", Module.Category.MOVEMENT));
        mods.add(new Dummy("ESP", Module.Category.RENDER));
        mods.add(new Dummy("Aura", Module.Category.COMBAT));
    }

    public void tick() {
        for (Module m : mods)
            if (m.enabled)
                m.tick();
    }

    static final class Dummy extends Module {
        Dummy(String n, Category c) {
            super(n, c);
        }
    }
}
