package com.lam.graphics;

public class Point3D {
    float x = (float) 0.0;
    float y = (float) 0.0;
    float z = (float) 0.0;

    public Point3D() {
    }

    public Point3D(Point3D p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    public Point3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.x = y; }

    public float getZ() { return z; }
    public void setZ(float x) { this.z = z; }

    public Point3D add(Point3D point) {
        this.x += point.x;
        this.y += point.y;
        this.z += point.z;

        return this;
    }

    public Point3D add(float deltaX, float deltaY, float deltaZ) {
        this.x += deltaX;
        this.y += deltaY;
        this.z += deltaZ;

        return this;
    }


    public double magnitude() {
        return (Math.sqrt(x*x + y*y + z*z));
    }
}
