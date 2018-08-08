/**
 * SpriteView.java
 *
 * SpriteView controls the sprites on a view.  It handles the sprites, sprite effects,
 * update, and drawing of the canvas.  The frame rate control is also in this class.
 */
package com.lam.graphics.sprites;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class SpriteView extends SurfaceView {
    private static final String LOG_TAG = "SpriteView";
    private static final int defaultFPS = 30;
    private static final int maxFPS = 60;

    protected SpriteState state = SpriteState.Active;

    // Area of the SpriteView
    protected int width = 0;
    protected int height = 0;

    private SpriteViewDisplayThread displayThread;
    private Sprites sprites;
    private ISpriteBackground background;

    public SpriteView(Context context) {
         super(context);
         setUp();
    }

    public SpriteView(Context context, AttributeSet attrs) {
         super(context, attrs);
         setUp();
    }

    public SpriteView(Context context, AttributeSet attrs, int defStyle) {
         super(context, attrs, defStyle);
         setUp();
    }

    /**
     * setFramesPerSecond changes the frames per second displayed.
     * @param fps New frames per second rate.  Must be in range 1 to 100 inclusive.
     */
    public void setFramesPerSecond(int fps) {
         displayThread.setFramesPerSecond(fps);
    }

    /**
     * setUp Creates and starts the display thread.
     * @see <a href=https://developer.android.com/reference/android/view/SurfaceHolder>Android SurfaceHolder</a>/>
     */
    private void setUp() {
        sprites = new Sprites();

        displayThread = new SpriteViewDisplayThread(this, defaultFPS);

        SurfaceHolder surfaceHolder = getHolder();

        surfaceHolder.addCallback(
            /**
             * SurfaceHolder.Callback() is used to keep track of the frames
             */
            new SurfaceHolder.Callback() {
                /**
                 * surfaceCreated is called when the surface is created
                 * @param holder The SurfaceHolder whose surface has created.
                 */
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    displayThread.activate(true);
                    displayThread.start();
                }

                /**
                 * surfaceChanged is called when the surface is changed.
                 * @param holder The SurfaceHolder whose surface has changed.
                 * @param format The new PixelFormat of the surface.
                 * @param width The new width of the surface.
                 * @param height The new height of the surface.
                 */
                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                }

                /**
                 * surfaceDestroyed is called to clean up objects when the
                 * view is closing.
                 * @param holder The SurfaceHolder whose surface is closing.
                 */
                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    displayThread.activate(false);

                    boolean retry = true;

                    while (retry) {
                        try {
                            displayThread.join();
                            retry = false;
                        } catch (InterruptedException ex) {
                            Log.e(LOG_TAG, "InterruptedException: " + ex.getMessage());
                        }
                    }
                }
            }
        );
    }

    /**
     * pause stops the sprite updates on this view.
     */
    public void pause() {
        state = SpriteState.Inactive;
    }

    /**
     * play continues the sprite updates on this view.
     */

    public void play() {
        state = SpriteState.Active;
    }

    /**
     * setBackground changes the background display.
     * @param background New IBackground for this SpriteView
     */
    public void setBackground(ISpriteBackground background) {
        this.background = background;
    }

    /**
     * clear removes all Sprites from the SpriteView.
     */
    public void clear() {
        sprites.clear();
    }

    /**
     * addSprite adds a Sprite to the SpriteView.
     * @param sprite
     */
    public void addSprite(Sprite sprite) {
        sprites.addSprite(sprite);
    }

    /**
     * removeSprite removes a Sprite from the SpriteView.
     * @param sprite
     */
    public void removeSprite(Sprite sprite) {
        sprites.removeSprite(sprite);
    }

    /**
     * addEffect
     * @param effect
     * @param sprite
     */
    public void addSpriteEffect(SpriteEffect effect, Sprite sprite) {
        sprites.addSpriteEffect(effect, sprite);
    }

    /**
     * onSizeChange is called when the view size changes.
     * @param width New view width
     * @param height New view height
     * @param oldWidth Old view width
     * @param oldheight Old view height
     */
    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldheight) {
        super.onSizeChanged(width, height, oldWidth, oldheight);

        if (this.width != width || this.height != height) {
            this.width = width;
            this.height = height;

            setup();
        }
    }

    /**
     * setup initializes the view.
     */
    public void setup() {
    }

    /**
     * nextFrame updates and draws the sprites.  Keep the canvas locked
     * for as short a time as possible.
     */
    final private void nextFrame() {
        if (state == SpriteState.Active && this.getHolder().getSurface().isValid()) {
            if (background != null) {
                background.update();
            }
            sprites.update(width, height);

            Canvas canvas = this.getHolder().lockCanvas();

            try {
                synchronized (this.getHolder()) {
                    if (background != null) {
                        background.draw(canvas);
                    }
                    sprites.draw(canvas);
                }
            } finally {
                this.getHolder().unlockCanvasAndPost(canvas);
            }

            sprites.review();
        }
    }

    /**
     * SpriteViewDisplayThread controls the updates to the SpriteView.
     * @see <a href=https://developer.android.com/reference/java/lang/Thread>Thread</a>/>
     *
     */
    private class SpriteViewDisplayThread extends Thread {
        private static final String LOG_TAG = "SpriteViewDisplayThread";

        private boolean active = false;
        private SpriteView view;
        private long ticksPerFrame;

        /**
         * SpriteViewDisplayThread
         *
         * @param view SpriteView drawing with each frame
         * @param fps New frames per second rate.  Must be in range 1 to 100 inclusive.
         */
        SpriteViewDisplayThread(SpriteView view, int fps) {
            this.view = view;
            setFramesPerSecond(fps);
        }

        /**
         * setFramesPerSecond changes the frames per second displayed.
         * @param fps New frames per second rate.  Must be in range 1 to 100 inclusive.
         */
        public void setFramesPerSecond(int fps) {
            if (0 < fps && fps <= maxFPS) {
                ticksPerFrame = 1000 / fps;
            }
        }

        /**
         * activate changes the state of the thread
         * @param state true if the thread should be active, false to stop
         */
        synchronized public void activate(boolean state) {
            active = state;
        }

        /**
         * run displays each frame then sleeps until the next frame needs to be drawn.
         */
        @Override
        final public void run() {
            long startTime;
            long sleepTime;

            while (active) {
                startTime = System.currentTimeMillis();

                view.nextFrame();

                sleepTime = ticksPerFrame - (System.currentTimeMillis() - startTime);

                try {
                    if (sleepTime > 0) {
                        sleep(sleepTime);
                    } else {
                        sleep(10);
                    }
                } catch (Exception ex) {
                    Log.e(LOG_TAG, "Exception: " + ex.getMessage());
                }
            }
        }
    }
}
