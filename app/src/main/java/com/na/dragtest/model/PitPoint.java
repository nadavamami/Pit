package com.na.dragtest.model;

import android.support.annotation.NonNull;

/**
 * Created by Nadav Amami on 24/11/2017.
 * model objects that contains the pit point
 */

public class PitPoint implements Comparable<PitPoint>{
    private int id;
    private float x;
    private float y;

    public PitPoint(int id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public PitPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PitPoint() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PitPoint point = (PitPoint) o;

        if (id != point.id) return false;
        if (Float.compare(point.x, x) != 0) return false;
        return Float.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }

    @Override
    public int compareTo(@NonNull PitPoint point) {
        return (int) (this.getX() - point.getX());
    }
}
