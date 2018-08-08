/**
 * MainSpriteView.java
 */
package com.lam.spritework;

import android.content.Context;
import android.util.AttributeSet;

import com.lam.Global;
import com.lam.graphics.sprites.Sprite;
import com.lam.graphics.sprites.SpriteEffect;
import com.lam.graphics.sprites.SpriteView;

/**
 * MainSpriteView
 */
public class MainSpriteView extends SpriteView {
    private static final String LOG_TAG = "MainSpriteView";

    private SpriteFactory factory;

    /**
     * MainSpriteView constructor
     *
     * @param context Interface to global information about an application environment
     */
    public MainSpriteView(Context context) {
        super(context);
    }

    /**
     * MainView constructor
     *
     * @param context Interface to global information about an application environment
     * @param attrs
     */
    public MainSpriteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public MainSpriteView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    /**
     * setup sets the background and adds the sprites to the view.
     */
    @Override
    public void setup() {
        super.setup();

        factory = new SpriteFactory(getContext());

        setBackground(new SpriteBackgroundGradient());

        Sprite sprite;
        SpriteEffectMove effectMove;

        for (int i = 0; i < 1; ++i) {
            sprite = factory.createSpaceship();
            sprite.setPosition(100 + Global.random.nextInt(1000),100 + Global.random.nextInt(1000), 0);
            sprite.setVisible(true);

            effectMove = new SpriteEffectMove();
            effectMove.set( Global.random.nextInt(5), Global.random.nextInt(5), 0);
            addSpriteEffect(effectMove, sprite);

            addSprite(sprite);
        }
        for (int i = 0; i < 3; ++i) {
            sprite = factory.createAsteroid();
            sprite.setPosition(100 + Global.random.nextInt(1000),100 + Global.random.nextInt(1000), 0);
            sprite.setVisible(true);

            effectMove = new SpriteEffectMove();
            effectMove.set( Global.random.nextInt(5), Global.random.nextInt(5), 0);
            addSpriteEffect(effectMove, sprite);

            addSprite(sprite);
        }
        for (int i = 0; i < 100; ++i) {
            sprite = factory.createStar();
            sprite.setPosition(100 + Global.random.nextInt(1000),100 + Global.random.nextInt(1000), 0);
            sprite.setVisible(true);

            // TODO: Add effects

            addSprite(sprite);
        }
    }
}
