package com.client.tickspeed.config;

import com.google.gson.*;
import java.io.*;
import java.util.Map;

public final class Config {

    private final File f = new File("config/tickspeed.json");
    private final Gson g = new GsonBuilder().setPrettyPrinting().create();

    public JsonObject data = new JsonObject();

    public void load() {
        try {
            if (!f.exists()) return;
            data = JsonParser.parseReader(new FileReader(f)).getAsJsonObject();
        } catch (Exception ignored) {}
    }

    public void save() {
        try {
            f.getParentFile().mkdirs();
            try (FileWriter w = new FileWriter(f)) {
                g.toJson(data, w);
            }
        } catch (Exception ignored) {}
    }

    public void saveModule(String name, Map<String, Double> set) {
        JsonObject o = new JsonObject();
        for (var e : set.entrySet())
            o.addProperty(e.getKey(), e.getValue());
        data.add(name, o);
        save();
    }
}
