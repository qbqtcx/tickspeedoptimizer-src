package com.client.tickspeed.input;

import com.client.tickspeed.core.Engine;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public final class InputHandler {

    private final Engine e;
    private KeyBinding gui;

    public InputHandler(Engine e) {
        this.e = e;
    }

    public void init() {
        gui = reg("gui", GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    public void tick() {
        while (gui.wasPressed()) {
            e.gui.toggle();
        }
    }

    private KeyBinding reg(String n, int k) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(n, k, "tickspeed"));
    }
}
