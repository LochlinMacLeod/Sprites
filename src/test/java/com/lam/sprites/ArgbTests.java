package com.lam.sprites;

import com.lam.graphics.Argb;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgbTests {
    @Test
    public void test_byteToInt() {
        assertEquals(0, Argb.byteToInt((byte) 0));
        assertEquals(16, Argb.byteToInt((byte) 16));
        assertEquals(255, Argb.byteToInt((byte) 255));
    }

    @Test
    public void test_getAlpha() {
        assertEquals((byte) 0x00, Argb.getAlpha(0x00FFFFFF));
        assertEquals((byte) 0xFF, Argb.getAlpha(0xFFFFFFFF));
        assertEquals((byte) 0x16, Argb.getAlpha(0x16FFFFFF));
    }

    @Test
    public void test_getAlphaInt() {
        assertEquals(0x00, Argb.getAlphaInt(0x00FFFFFF));
        assertEquals(0xFF, Argb.getAlphaInt(0xFFFFFFFF));
        assertEquals(0x16, Argb.getAlphaInt(0x16FFFFFF));
    }

    @Test
    public void test_setAlpha() {
        assertEquals(0x10FFFFFF, Argb.setAlpha(0x00FFFFFF, (byte) 0x10));
        assertEquals(0x00FFFFFF, Argb.setAlpha(0xFFFFFFFF, (byte) 0x00));
        assertEquals(0xFFFFFFFF, Argb.setAlpha(0x16FFFFFF, (byte) 0xFF));
    }

    @Test
    public void test_getRed() {
        assertEquals((byte) 0x00, Argb.getRed(0xFF00FFFF));
        assertEquals((byte) 0xFF, Argb.getRed(0xFFFFFFFF));
        assertEquals((byte) 0x16, Argb.getRed(0xFF16FFFF));
    }

    @Test
    public void test_getRedInt() {
        assertEquals(0x00, Argb.getRedInt(0xFF00FFFF));
        assertEquals(0xFF, Argb.getRedInt(0xFFFFFFFF));
        assertEquals(0x16, Argb.getRedInt(0xFF16FFFF));
    }

    @Test
    public void test_setRed() {
        assertEquals(0xFF10FFFF, Argb.setRed(0xFFFFFFFF, (byte) 0x10));
        assertEquals(0xFF00FFFF, Argb.setRed(0xFFFFFFFF, (byte) 0x00));
        assertEquals(0xFFFFFFFF, Argb.setRed(0xFF00FFFF, (byte) 0xFF));
    }

    @Test
    public void test_getGreen() {
        assertEquals((byte) 0x00, Argb.getGreen(0xFFFF00FF));
        assertEquals((byte) 0xFF, Argb.getGreen(0xFFFFFFFF));
        assertEquals((byte) 0x16, Argb.getGreen(0xFFFF16FF));
    }

    @Test
    public void test_getGreenInt() {
        assertEquals(0x00, Argb.getGreenInt(0xFFFF00FF));
        assertEquals(0xFF, Argb.getGreenInt(0xFFFFFFFF));
        assertEquals(0x16, Argb.getGreenInt(0xFFFF16FF));
    }

    @Test
    public void test_setGreen() {
        assertEquals(0xFFFF10FF, Argb.setGreen(0xFFFFFFFF, (byte) 0x10));
        assertEquals(0xFFFF00FF, Argb.setGreen(0xFFFFFFFF, (byte) 0x00));
        assertEquals(0xFFFFFFFF, Argb.setGreen(0xFFFF00FF, (byte) 0xFF));
    }

    @Test
    public void test_getBlue() {
        assertEquals((byte) 0x00, Argb.getBlue(0xFFFFFF00));
        assertEquals((byte) 0xFF, Argb.getBlue(0xFFFFFFFF));
        assertEquals((byte) 0x16, Argb.getBlue(0xFFFFFF16));
    }

    @Test
    public void test_getBlueInt() {
        assertEquals(0x00, Argb.getBlueInt(0xFFFFFF00));
        assertEquals(0xFF, Argb.getBlueInt(0xFFFFFFFF));
        assertEquals(0x16, Argb.getBlueInt(0xFFFFFF16));
    }

    @Test
    public void test_setBlue() {
        assertEquals(0xFFFFFF10, Argb.setBlue(0xFFFFFFFF, (byte) 0x10));
        assertEquals(0xFFFFFF00, Argb.setBlue(0xFFFFFFFF, (byte) 0x00));
        assertEquals(0xFFFFFFFF, Argb.setBlue(0xFFFFFF00, (byte) 0xFF));
    }

    @Test
    public void test_split() {
        byte[] argb;

        argb = Argb.split(0x00000000);
        assertEquals((byte) 0x00, argb[Argb.ALPHA]);
        assertEquals((byte) 0x00, argb[Argb.RED]);
        assertEquals((byte) 0x00, argb[Argb.GREEN]);
        assertEquals((byte) 0x00, argb[Argb.BLUE]);

        argb = Argb.split(0xFFFFFFFF);
        assertEquals((byte) 0xFF, argb[Argb.ALPHA]);
        assertEquals((byte) 0xFF, argb[Argb.RED]);
        assertEquals((byte) 0xFF, argb[Argb.GREEN]);
        assertEquals((byte) 0xFF, argb[Argb.BLUE]);

        argb = Argb.split(0x1122AABB);
        assertEquals((byte) 0x11, argb[Argb.ALPHA]);
        assertEquals((byte) 0x22, argb[Argb.RED]);
        assertEquals((byte) 0xAA, argb[Argb.GREEN]);
        assertEquals((byte) 0xBB, argb[Argb.BLUE]);
    }

    @Test
    public void test_splitInt() {
        int[] argb;

        argb = Argb.splitInt(0x00000000);
        assertEquals(0x00, argb[Argb.ALPHA]);
        assertEquals(0x00, argb[Argb.RED]);
        assertEquals(0x00, argb[Argb.GREEN]);
        assertEquals(0x00, argb[Argb.BLUE]);

        argb = Argb.splitInt(0xFFFFFFFF);
        assertEquals(0xFF, argb[Argb.ALPHA]);
        assertEquals(0xFF, argb[Argb.RED]);
        assertEquals(0xFF, argb[Argb.GREEN]);
        assertEquals(0xFF, argb[Argb.BLUE]);

        argb = Argb.splitInt(0x1122AABB);
        assertEquals(0x11, argb[Argb.ALPHA]);
        assertEquals(0x22, argb[Argb.RED]);
        assertEquals(0xAA, argb[Argb.GREEN]);
        assertEquals(0xBB, argb[Argb.BLUE]);
    }

    @Test
    public void test_join_byte() {
        assertEquals(0x00000000, Argb.join((byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00));
        assertEquals(0xFFFFFFFF, Argb.join((byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF));
        assertEquals(0x11223344, Argb.join((byte)0x11, (byte)0x22, (byte)0x33, (byte)0x44));
    }

    @Test
    public void test_join_int() {
        assertEquals(0x00000000, Argb.join(0, 0, 0, 0));
        assertEquals(0xFFFF0000, Argb.join(256, 380, -3, -8));
    }

    @Test
    public void test_steps() {
        int[] steps = Argb.steps(0x00000000, 0xFFFFFFFF, 14);

        assertEquals(16, steps.length);
        assertEquals(0x00000000, steps[0]);
        assertEquals(0x11111111, steps[1]);
        assertEquals(0x22222222, steps[2]);
        assertEquals(0x33333333, steps[3]);
        assertEquals(0x44444444, steps[4]);
        assertEquals(0x55555555, steps[5]);
        assertEquals(0x66666666, steps[6]);
        assertEquals(0x77777777, steps[7]);
        assertEquals(0x88888888, steps[8]);
        assertEquals(0x99999999, steps[9]);
        assertEquals(0xAAAAAAAA, steps[10]);
        assertEquals(0xBBBBBBBB, steps[11]);
        assertEquals(0xCCCCCCCC, steps[12]);
        assertEquals(0xDDDDDDDD, steps[13]);
        assertEquals(0xEEEEEEEE, steps[14]);
        assertEquals(0xFFFFFFFF, steps[15]);
    }

    @Test
    public void test_blend() {
        int[] colors = new int[] {0xFFFFFFFF, 0x00000000};
        assertEquals(0x7F7F7F7F, Argb.blend(colors));
    }

    @Test
    public void test_fade() {
        assertEquals(0x80808080, Argb.fade(0xFFFFFFFF, 0.5, 0.5, 0.5, 0.5));
    }

    @Test
    public void test_add() {
        assertEquals(0x03040506, Argb.add(0x00000000, 3, 4, 5, 6));
    }

    @Test
    public void test_toString() {
        assertEquals("FFFFFFFF", Argb.toString(0xFFFFFFFF));
        assertEquals("00000000", Argb.toString(0x00000000));
        assertEquals("12345678", Argb.toString(0x12345678));
    }
}