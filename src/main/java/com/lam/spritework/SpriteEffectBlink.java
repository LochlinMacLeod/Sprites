package com.lam.spritework;

import com.lam.graphics.Argb;
import com.lam.graphics.sprites.SpriteEffect;

public class SpriteEffectBlink extends SpriteEffect {
    private int fade = 0;
    private int boundLower = 0;
    private int boundUpper = 255;

    public SpriteEffectBlink() {
        //Red = Global.random.nextInt(130) + 100;
        //int amountFade = Global.random.nextInt(3) + 1;
        //fade = Global.random.nextInt(2) == 0 ? 0 - amountFade : amountFade;
    }

    public void set(int fade, int Red, byte boundLower, byte boundUpper) {
        setFade(fade);
        setBounds(boundLower, boundUpper);
    }

    public void setFade(int fade) {
        this.fade = fade;
    }

    public void setBounds(byte boundLower, byte boundUpper) {
        this.boundLower = boundLower;
        this.boundUpper = boundUpper;
    }

    @Override
    public void onUpdate(int screenWidth, int screenHeight) {
        if (sprite instanceof SpriteStar) {
            SpriteStar star = (SpriteStar) sprite;

            int color = star.getColor();

            int alpha = Argb.getAlpha(color) + fade;

            if (alpha < 0) {
                alpha = 0;
            }
            if (255 < alpha) {
                alpha = 255;
            }

            if (alpha <= boundLower || boundUpper <= alpha) {
                fade = 0 - fade;
            }

            color = Argb.setAlpha(color, (byte) alpha);
            star.setColor(color);
        }
        else {
            // Effect may only be assigned to a SpriteStar
            setComplete();
        }
    }
}
