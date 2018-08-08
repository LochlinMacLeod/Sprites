package com.lam.spritework;

import android.graphics.Color;

import com.lam.Global;
import com.lam.graphics.Size3D;
import com.lam.graphics.sprites.SpriteEffect;

public class SpriteEffectStarColor extends SpriteEffect {
    public SpriteEffectStarColor() {
        super();
    }

    @Override
    protected void onUpdate(int screenWidth, int screenHeight) {
        if (sprite instanceof SpriteStar) {
            Size3D size = sprite.getSize();

            if (size.width == 1) {
                SpriteStar star = (SpriteStar) sprite;

                star.getColor();

                switch (Global.random.nextInt(5)) {
                    case 0:
                        star.setColor(Color.argb(100, 100, 0, 0));
                        break;
                    case 1:
                        star.setColor(Color.argb(100, 0, 100, 0));
                        break;
                    case 2:
                        star.setColor(Color.argb(100, 0, 0, 100));
                        break;
                    case 3:
                        star.setColor(Color.argb(100, 100, 100, 100));
                        break;
                    case 4:
                    default:
                        star.setColor(Color.CYAN);
                        break;
                }
            }
        }
        else {
            // Effect may only be assigned to a SpriteStar
            setComplete();
        }
    }
}
