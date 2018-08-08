/**
 * Sprites.java
 *
 * Sprites controls the collection of Sprites visible on the SpriteView.
 * This class is visible only to the package.
 */
package com.lam.graphics.sprites;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

class Sprites {
    /**
     * ComparatorSpriteZ compares Sprites by Z order,
     */
    private class SpriteComparatorZ implements Comparator<Sprite> {
        private final static float tolerance = (float) 0.001;

        /**
         * compare performs a comparison of the given Sprites
         * @param sprite1 First Sprite to compare
         * @param sprite2 Second Sprite to compare
         * @return 0 if of equal depth, negative number if sprite1 is less than sprite2
         * and positive number if sprite1 is greater than sprite2.
         */
        public int compare(Sprite sprite1, Sprite sprite2) {
            float diff = sprite1.point.getZ() - sprite2.point.getZ();

            if (diff < (0 - tolerance)) {
                return -1;
            }
            if (tolerance < diff) {
                return 1;
            }
            return 0;
        }
    }

    // The ordered list of sprites
    private List<Sprite> sprites = new ArrayList<>();

    // The list of effects
    private List<SpriteEffect> effects = new ArrayList<>();

    // The Z order comparator
    private SpriteComparatorZ sorter = new SpriteComparatorZ();

    /**
     * Sprites constructor.  No controlling access, so only visible in
     * the sprites package.
     */
    Sprites() {
    }

    /**
     * addSprite puts a Sprite into the system.
     *
     * @param sprite to add
     */
    public void addSprite(Sprite sprite) {
        sprite.setActive();
        sprites.add(sprite);
    }

    /**
     * removeSprite removes the sprite from the SpriteView.
     *
     * @param sprite to remove
     */
    void removeSprite(Sprite sprite) {
        if (sprites.contains(sprite)) {
            sprite.setComplete();
        }
    }

    /**
     * addEffect add the given sprite effect to the given sprite and puts them
     * onto this SpriteView.
     *
     * @param sprite to add
     */
    void addSpriteEffect(SpriteEffect effect, Sprite sprite) {
        effect.setActive(sprite);
        effects.add(effect);
    }

    /**
     *
     * removeSpriteEffects removes the sprite effect from the SpriteView.
     *
     * @param sprite to remove
     */
    void removeSpriteEffects(Sprite sprite) {
        for (SpriteEffect effect : effects) {
            if (effect.sprite == sprite) {
                effect.setComplete();
            }
        }
    }

    /**
     * clear removes all the Sprites from this list.
     */
    void clear() {
        for (Sprite sprite : sprites) {
            sprite.setComplete();
        }
    }

    /**
     * onUpdate runs through the Sprite list runs the effects on them
     */
    void update(int screenWidth, int screenHeight) {
        for (Sprite s : sprites) {
            if (s.getActive()) {
                s.reset();
            }
        }

        for (SpriteEffect e : effects) {
            if (e.getActive()) {
                e.update(screenWidth, screenHeight);
            }
        }

        Collections.sort(sprites, sorter);
    }

    /**
     * onDraw puts the Sprites to the given canvas
     *
     * @param canvas is the draw area
     */
    void draw(Canvas canvas) {
        for (Sprite s : sprites) {
            s.draw(canvas);
        }
    }

    /**
     * review runs through the list of sprites and effects to remove
     * any which are no longer in use
     */
    void review() {
        Sprite sprite;
        ListIterator<Sprite> iteratorSprites = sprites.listIterator();
        while (iteratorSprites.hasNext()) {
            sprite = iteratorSprites.next();
            if (!sprite.getActive()) {
                iteratorSprites.remove();
                sprite.setInactive();
            }
        }

        SpriteEffect effect;
        ListIterator<SpriteEffect> iteratorEffects = effects.listIterator();
        while (iteratorEffects.hasNext()) {
            effect = iteratorEffects.next();
            if (!effect.getActive()) {
                iteratorEffects.remove();
                effect.setInactive();
            }
        }
    }
}
