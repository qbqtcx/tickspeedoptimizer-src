package com.client.tickspeed.core;

public final class MathUtil {

    public static double lerp(double a, double b, double f) {
        return a + (b - a) * f;
    }

    public static double exp(double c, double t) {
        double d = t - c;
        return c + d * 0.1 + Math.signum(d) * 0.002;
    }

    public static double clamp(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }

    public static double wave(long t, double s) {
        return Math.sin(t * 0.05 * s) * 0.5 + 0.5;
    }
}
