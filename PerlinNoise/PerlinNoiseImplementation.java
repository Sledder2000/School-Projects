package PerlinNoise;

public class PerlinNoiseImplementation {
    private static final int PERSISTENCE = 1;
    private static final int OCTAVES = 2;

    public static double noise(double x, double y) {
        double total = 0;
        double frequency = Math.pow(2, OCTAVES);
        double amplitude = PERSISTENCE;

        for (int i = 0; i < OCTAVES; i++) {
            total += interpolatedNoise(x * frequency, y * frequency) * amplitude;
            frequency /= 2;
            amplitude *= PERSISTENCE;
        }

        return total;
    }

    private static double interpolatedNoise(double x, double y) {
        int integerX = (int) x;
        double fractionalX = x - integerX;

        int integerY = (int) y;
        double fractionalY = y - integerY;

        double v1 = smoothNoise(integerX, integerY);
        double v2 = smoothNoise(integerX + 1, integerY);
        double v3 = smoothNoise(integerX, integerY + 1);
        double v4 = smoothNoise(integerX + 1, integerY + 1);

        double i1 = interpolate(v1, v2, fractionalX);
        double i2 = interpolate(v3, v4, fractionalX);

        return interpolate(i1, i2, fractionalY);
    }

    private static double smoothNoise(int x, int y) {
        double corners = (randomNoise(x - 1, y - 1) + randomNoise(x + 1, y - 1) + randomNoise(x - 1, y + 1) + randomNoise(x + 1, y + 1)) / 16;
        double sides = (randomNoise(x - 1, y) + randomNoise(x + 1, y) + randomNoise(x, y - 1) + randomNoise(x, y + 1)) / 8;
        double center = randomNoise(x, y) / 4;

        return corners + sides + center;
    }

    private static double interpolate(double a, double b, double x) {
        double ft = x * Math.PI;
        double f = (1 - Math.cos(ft)) * 0.5;

        return a * (1 - f) + b * f;
    }

    private static double randomNoise(int x, int y) {
        int n = x + y * 57;
        n = (n << 13) ^ n;
        return (1.0 - ((n * (n * n * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824.0);
    }
}
