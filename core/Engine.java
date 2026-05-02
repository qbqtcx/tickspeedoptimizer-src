package com.client.tickspeed.core;

import com.client.tickspeed.config.Config;
import com.client.tickspeed.input.InputHandler;
import com.client.tickspeed.module.ModuleManager;
import com.client.tickspeed.ui.ClickGui;
import com.client.tickspeed.ui.HudRenderer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public final class Engine {

    public final ModuleManager mm = new ModuleManager();
    public final Scheduler sched = new Scheduler();
    public final Config cfg = new Config();
    public final InputHandler in = new InputHandler(this);
    public final HudRenderer hud = new HudRenderer(this);
    public final ClickGui gui = new ClickGui(this);

    public void init() {
        cfg.load();
        mm.init();
        in.init();

        ClientTickEvents.END_CLIENT_TICK.register(c -> tick());
        HudRenderCallback.EVENT.register(hud::render);
    }

    private void tick() {
        mm.tick();
        sched.tick();
        in.tick();
    }
}
