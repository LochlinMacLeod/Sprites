/**
 * Size3D.jave
 */
package com.lam.graphics;

public class Size3D {
    public float width = (float )0.0;
    public float height = (float) 0.0;
    public float depth = (float) 0.0;

    public Size3D() {
    }

    public Size3D(com.lam.graphics.Size3D p) {
        this.width = p.width;
        this.height = p.height;
        this.depth = p.depth;
    }

    public Size3D(float width, float height, float depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
}
