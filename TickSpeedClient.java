package com.client.tickspeed;

import com.client.tickspeed.core.Engine;
import net.fabricmc.api.ClientModInitializer;

public final class TickSpeedClient implements ClientModInitializer {
    public static final Engine ENG = new Engine();

    @Override
    public void onInitializeClient() {
        ENG.init();
    }
}
