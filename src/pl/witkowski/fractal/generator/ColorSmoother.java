package pl.witkowski.fractal.generator;

import java.awt.*;

public class ColorSmoother {

    public static Color smooth(Color color, Color newColor, int functionValue) {
        int k1 = functionValue % 256;
        int k2 = 255 - k1;
        int red = (k1 * color.getRed() + k2 * newColor.getRed()) / 255;
        int green = (k1 * color.getGreen() + k2 * newColor.getGreen()) / 255;
        int blue = (k1 * color.getBlue() + k2 * newColor.getBlue()) / 255;
        return new Color(red, green, blue);
    }
}
