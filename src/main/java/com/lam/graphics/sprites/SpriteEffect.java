package com.lam.graphics.sprites;

/**
 * SpriteEffect is an abstract class to build effects for sprites.
 */
public abstract class SpriteEffect {
    private SpriteState state = SpriteState.Inactive;
    protected Sprite sprite;

    protected final boolean getActive() {
        return ( state == SpriteState.Active && sprite != null && sprite.getActive() );
    }

    /**
     * release cleans up the effect
     */
    protected final void setActive(Sprite sprite) {
        this.sprite = sprite;
        state = SpriteState.Active;
    }

    /**
     * release cleans up the effect
     */
    protected final void setComplete() {
        state = SpriteState.Complete;
        sprite = null;
    }

    /**
     * release cleans up the effect
     */
    protected final void setInactive() {
        state = SpriteState.Inactive;
    }

    /**
     * update performs the effect onto the Sprite
     * @param screenWidth Horizontal width of the screen
     * @param screenHeight Vertical height of the screen
     */
    final void update(int screenWidth, int screenHeight) {
        if (state == SpriteState.Active) {
            onUpdate(screenWidth, screenHeight);
        }
    }

    /**
     * onUpdate performs the effect onto the Sprite.  If the onUpdate is complete,
     * it must mark the state as SpriteState.Complete.
     * @param screenWidth Horizontal width of the screen
     * @param screenHeight Vertical height of the screen
     */
    protected abstract void onUpdate(int screenWidth, int screenHeight);
}
