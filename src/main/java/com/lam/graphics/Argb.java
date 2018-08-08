package com.lam.graphics;

/**
 * Argb class to help manipulate a color as an integer.
 */
final public class Argb {
    public final static int TRANSPARENT = 0x00000000;

    public final static int ALPHA = 0;
    public final static int RED = 1;
    public final static int GREEN = 2;
    public final static int BLUE = 3;

    /**
     * Argb constructor.  Block instantiation.
     */
    private Argb() { }

    public static int byteToInt(byte b) { return (int) b & 0xFF; }

    public static byte getAlpha(int value) {
        return (byte) ((value >> 24) & 0x000000FF);
    }
    public static int getAlphaInt(int value) { return (value >> 24) & 0x000000FF; }
    public static int setAlpha(int value, byte alpha) {
        return (value & 0x00FFFFFF) | (((int) alpha & 0xFF) << 24);
    }

    public static byte getRed(int value) {
        return (byte) ((value >> 16) & 0x000000FF);
    }
    public static int getRedInt(int value) { return (value >> 16) & 0x000000FF; }
    public static int setRed(int value, byte red) {
        return (value & 0xFF00FFFF) | (((int)red & 0xFF) << 16);
    }

    public static byte getGreen(int value) {
        return (byte) ((value >> 8) & 0x000000FF);
    }
    public static int getGreenInt(int value) {
        return (value >> 8) & 0x000000FF;
    }
    public static int setGreen(int value, byte green) {
        return (value & 0xFFFF00FF) | (((int)green & 0xFF) << 8);
    }

    public static byte getBlue(int value) {
        return (byte) (value & 0x000000FF);
    }
    public static int getBlueInt(int value) {
        return value & 0x000000FF;
    }
    public static int setBlue(int value, byte blue) {
        return (value & 0xFFFFFF00) | ((int) blue & 0xFF);
    }

    public static byte[] split(int value) {
        return new byte[] {
                getAlpha(value),
                getRed(value),
                getGreen(value),
                getBlue(value)
        };
    }
    public static int[] splitInt(int value) {
        return new int[] {
                getAlphaInt(value),
                getRedInt(value),
                getGreenInt(value),
                getBlueInt(value)
        };
    }

    public static int join(byte alpha, byte red, byte green, byte blue) {
        int value = 0;
        value = ((int) alpha & 0xFF);
        value = (value << 8) + ((int) red & 0xFF);
        value = (value << 8) + ((int) green & 0xFF);
        value = (value << 8) + ((int) blue & 0xFF);
        return value;
    }

    public static int join(int alpha, int red, int green, int blue) {
        int value = 0;
        value = ((int) bound(alpha) & 0xFF);
        value = (value << 8) + ((int) bound(red) & 0xFF);
        value = (value << 8) + ((int) bound(green) & 0xFF);
        value = (value << 8) + ((int) bound(blue) & 0xFF);
        return value;
    }

    /**
     * steps creates a list of colors which move from the first color to the last color.
     *
     * @param first color
     * @param last  color
     * @param steps between the colors
     * @return An array of the colors starting with the first and moving to the last
     */
    public static int[] steps(int first, int last, int steps) {
        int[] result = new int[steps + 2];
        result[0] = first;
        result[steps + 1] = last;

        double deltaAlpha = ((double) Argb.getAlphaInt(last)- Argb.getAlphaInt(first)) / ((double) (steps + 1));
        double deltaRed = ((double) Argb.getRedInt(last) - Argb.getRedInt(first)) / ((double) (steps + 1));
        double deltaGreen = ((double) Argb.getGreenInt(last) - Argb.getGreenInt(first)) / ((double) (steps + 1));
        double deltaBlue = ((double) Argb.getBlueInt(last) - Argb.getBlueInt(first)) / ((double) (steps + 1));
        double alpha = Argb.getAlphaInt(first);
        double red = Argb.getRedInt(first);
        double green = Argb.getGreenInt(first);
        double blue = Argb.getBlueInt(first);

        for (int i = 1; i <= steps; ++i) {
            alpha += deltaAlpha;
            red += deltaRed;
            green += deltaGreen;
            blue += deltaBlue;

            result[i] = Argb.join(
                    (byte) (((int) alpha) & 0xFF),
                    (byte) (((int) red) & 0xFF),
                    (byte) (((int) green) & 0xFF),
                    (byte) (((int) blue) & 0xFF));
        }

        return result;
    }

    /**
     * blend averages each part of the given list of colors
     *
     * @param colors List of SpriteColors to blend
     * @return A new ArgbWork which is the average of the given ArgbWorks.
     */
    public static int blend(int[] colors) {
        int alpha = 0;
        int red = 0;
        int green = 0;
        int blue = 0;

        for (int i = 0; i < colors.length; ++i) {
            alpha += getAlphaInt(colors[i]);
            red += getRedInt(colors[i]);
            green += getGreenInt(colors[i]);
            blue += getBlueInt(colors[i]);
        }

        return join(
            (byte) (alpha / colors.length),
            (byte) (red / colors.length),
            (byte) (green / colors.length),
            (byte) (blue / colors.length)
        );
    }

    /**
     * fade multiplies the deltas with the same parts.
     *
     * @paran color The color to modify
     * @param deltaAlpha change to the alpha value
     * @param deltaRed   change to the red value
     * @param deltaGreen change to the green value
     * @param deltaBlue  change to the blue value
     * @return The ArgbWork with the modified parts.
     */
    public static int fade(int color, double deltaAlpha, double deltaRed, double deltaGreen, double deltaBlue) {
        return join(
            bound(getAlphaInt(color) * deltaAlpha),
            bound(getRedInt(color) * deltaRed),
            bound(getGreenInt(color) * deltaGreen),
            bound(getBlueInt(color) * deltaBlue)
        );
    }

    /**
     * add sums the deltas with the same parts.
     *
     * @param deltaAlpha change to the alpha value
     * @param deltaRed   change to the red value
     * @param deltaGreen change to the green value
     * @param deltaBlue  change to the blue value
     * @return The ArgbWork with the modified parts.
     */
    public static int add(int color, double deltaAlpha, double deltaRed, double deltaGreen, double deltaBlue) {
        return join(
                bound(getAlphaInt(color) + deltaAlpha),
                bound(getRedInt(color) + deltaRed),
                bound(getGreenInt(color) + deltaGreen),
                bound(getBlueInt(color) + deltaBlue)
        );
    }

    /**
     * toString converts the given integer to a string in the ArgbWork format.
     *
     * @param value The integer to convert.
     * @return A string in the format "XXXXXXXX", where X is a single Hex number
     */
    public static String toString(int value) {
        return String.format("%08X", value);
    }

    /**
     * bound limits the value to the 0 - 255 byte range.
     *
     * @param value to constrain
     * @return Constrained value
     */
    private static byte bound(int value) {
        if (value < 0) {
            return (byte) ((int) 0x00);
        }

        if (255 < value) {
            return (byte) ((int) 0xFF);
        }

        return (byte) value;
    }

    /**
     * bound limits the value to the 0 - 255 byte range.  The input double is rounded
     * to the nearest whole number.
     *
     * @param value to constrain
     * @return Constrained value
     */
    private static byte bound(double value) {
        return bound( (int) Math.round(value) );
    }
}
