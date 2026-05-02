package com.client.tickspeed.ui;

import com.client.tickspeed.core.Engine;
import com.client.tickspeed.core.MathUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayDeque;
import java.util.Deque;

public final class HudRenderer {

    private final Engine e;
    private final Deque<Double> hist = new ArrayDeque<>();

    private int x = 5, y = 5;
    private boolean dragging;

    public HudRenderer(Engine e) {
        this.e = e;
    }

    public void render(MatrixStack m, float td) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        double v = e.mm.timer.cur;
        hist.addLast(v);
        if (hist.size() > 80) hist.removeFirst();

        mc.textRenderer.draw(m, "TS: " + String.format("%.2f", v), x, y, 0xFFFFFF);

        int i = 0;
        long t = mc.world.getTime();

        for (double d : hist) {
            int h = (int)(d * 6);
            float w = (float) MathUtil.wave(t + i, 1.5);

            int col = ((int)(255 * w) << 16) | ((int)(255 * (1 - w)) << 8);

            DrawableHelper.fill(m, x + i, y + 20 - h, x + i + 1, y + 20, col);
            i++;
        }
    }
}
