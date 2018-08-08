/**
 * SpriteFactory.java
 */
package com.lam.spritework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lam.graphics.sprites.Sprite;
import com.lam.graphics.sprites.SpriteEffect;
import com.lam.sprites.R;

import java.util.Random;

public class SpriteFactory {
    Bitmap bmpAsteroid;
    Bitmap bmpSpaceship;

    public SpriteFactory(Context context) {
        bmpAsteroid = BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid);
        bmpSpaceship = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship);
    }

    public Sprite createAsteroid() {
       return new SpriteAsteroid(bmpAsteroid);
    }

    public Sprite createSpaceship() {
        return new SpriteSpaceship(bmpSpaceship);
    }

    public Sprite createStar() {
        return new SpriteStar();
    }

    public SpriteEffect addEffectPulse(Sprite sprite, Random r) {

        // TODO: MORE !!!

        return null;
    }
}
