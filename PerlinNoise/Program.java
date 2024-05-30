package PerlinNoise;

import java.lang.reflect.Array;

public class Program {
    public static void main(String[] args) {
        System.out.println("Here is a Perlin Noise Function");
        System.out.println();
        //int[][] arr = new int[100][100]; 
        for (double i = 0.0; i < 10.0; i += 1.0) {
            for (double j = 0.0; j < 10.0; j += 1.0) {
                double d = PerlinNoiseImplementation.noise(i, j);
                if (d > 0 && d < 0.05) {
                    System.out.println("00");
                }
                if (d > 0.5 && d < 0.1) {
                    System.out.println("0000");
                }
                if (d > 0.1 && d < 0.2) {
                    System.out.println("000000");
                }
                if (d > 0.2 && d < 0.3) {
                    System.out.println("00000000");
                }
                if (d > 0.3 && d < 0.4) {
                    System.out.println("0000000000");
                }
                if (d > 0.4 && d < 0.5) {
                    System.out.println("000000000000");
                }
                if (d > 0.5 && d < 0.6) {
                    System.out.println("00000000000000");
                }
                if (d > 0.6 && d < 0.7) {
                    System.out.println("0000000000000000");
                }
                if (d > 0.7 && d < 0.8) {
                    System.out.println("000000000000000000");
                }
                if (d > 0.8 && d < 0.9) {
                    System.out.println("00000000000000000000");
                }
                if (d > -0.05 && d < 0) {
                    System.out.println("00");
                }
                if (d > -0.1 && d < -0.05) {
                    System.out.println("0000");
                }
                if (d > -0.2 && d < -0.1) {
                    System.out.println("000000");
                }
                if (d > -0.3 && d < -0.2) {
                    System.out.println("00000000");
                }
                if (d > -0.4 && d < -0.3) {
                    System.out.println("0000000000");
                }
                if (d > -0.5 && d < -0.4) {
                    System.out.println("000000000000");
                }
                if (d > -0.6 && d < -0.5) {
                    System.out.println("00000000000000");
                }
                if (d > -0.7 && d < -0.6) {
                    System.out.println("0000000000000000");
                }
                if (d > -0.8 && d < -0.7) {
                    System.out.println("000000000000000000");
                }
                if (d > -0.9 && d < -0.8) {
                    System.out.println("00000000000000000000");
                }
                //arr[(int) (i*10)][(int) (j*10)] = (int) PerlinNoiseImplementation.noise(i, j) * 10000;
            }
        }
        System.out.println("Here is a completely Random Function");
        System.out.println();

    for (double i = 0.0; i < 10.0; i += 1.0) {
        for (double j = 0.0; j < 10.0; j += 1.0) {
            double d = Math.random() * 2;
            if (d > 1.0) {
                d = -1 * Math.random();
            }
            if (d > 0 && d < 0.1) {
                System.out.println("0000");
            }
            if (d > 0.1 && d < 0.2) {
                System.out.println("000000");
            }
            if (d > 0.2 && d < 0.3) {
                System.out.println("00000000");
            }
            if (d > 0.3 && d < 0.4) {
                System.out.println("0000000000");
            }
            if (d > 0.4 && d < 0.5) {
                System.out.println("000000000000");
            }
            if (d > 0.5 && d < 0.6) {
                System.out.println("00000000000000");
            }
            if (d > 0.6 && d < 0.7) {
                System.out.println("0000000000000000");
            }
            if (d > 0.7 && d < 0.8) {
                System.out.println("000000000000000000");
            }
            if (d > 0.8 && d < 0.9) {
                System.out.println("00000000000000000000");
            }
            if (d > -0.1 && d < 0) {
                System.out.println("0000");
            }
            if (d > -0.2 && d < -0.1) {
                System.out.println("000000");
            }
            if (d > -0.3 && d < -0.2) {
                System.out.println("00000000");
            }
            if (d > -0.4 && d < -0.3) {
                System.out.println("0000000000");
            }
            if (d > -0.5 && d < -0.4) {
                System.out.println("000000000000");
            }
            if (d > -0.6 && d < -0.5) {
                System.out.println("00000000000000");
            }
            if (d > -0.7 && d < -0.6) {
                System.out.println("0000000000000000");
            }
            if (d > -0.8 && d < -0.7) {
                System.out.println("000000000000000000");
            }
            if (d > -0.9 && d < -0.8) {
                System.out.println("00000000000000000000");
            }
            //arr[(int) (i*10)][(int) (j*10)] = (int) PerlinNoiseImplementation.noise(i, j) * 10000;
        }
    }
}
}