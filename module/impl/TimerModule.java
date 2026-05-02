package com.client.tickspeed.module.impl;

import com.client.tickspeed.core.MathUtil;
import com.client.tickspeed.module.Module;
import net.minecraft.client.MinecraftClient;

public final class TimerModule extends Module {

    public double tgt = 1.0;
    public double cur = 1.0;
    private double acc;

    public TimerModule() {
        super("Timer", Category.MOVEMENT);
        settings.put("speed", 1.0);
    }

    @Override
    public void tick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        tgt = settings.get("speed");
        cur = MathUtil.exp(cur, tgt);

        acc += cur;
        int loops = (int) acc;
        acc -= loops;

        for (int i = 0; i < loops; i++) {
            mc.player.tick();

            double x = 0;
            for (int j = 0; j < 40; j++)
                x += Math.sin(j * cur) * Math.cos(cur / (j + 1));
        }
    }
}
