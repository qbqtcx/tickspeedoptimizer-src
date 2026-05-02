package com.client.tickspeed.ui;

import com.client.tickspeed.core.Engine;
import com.client.tickspeed.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public final class ClickGui extends Screen {

    private final Engine e;
    private boolean open;

    public ClickGui(Engine e) {
        super(Text.literal("gui"));
        this.e = e;
    }

    public void toggle() {
        open = !open;
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.setScreen(open ? this : null);
    }

    @Override
    public void render(net.minecraft.client.util.math.MatrixStack m, int mx, int my, float d) {
        int x = 20, y = 20;

        for (Module mod : e.mm.mods) {
            drawTextWithShadow(m, MinecraftClient.getInstance().textRenderer,
                mod.name + (mod.enabled ? " [ON]" : " [OFF]"),
                x, y, mod.enabled ? 0x00FF00 : 0xFF0000);

            y += 12;
        }

        super.render(m, mx, my, d);
    }

    @Override
    public boolean mouseClicked(double mx, double my, int b) {
        int x = 20, y = 20;

        for (Module mod : e.mm.mods) {
            if (mx >= x && mx <= x + 100 && my >= y && my <= y + 10) {
                mod.toggle();
            }
            y += 12;
        }

        return super.mouseClicked(mx, my, b);
    }
}
