package org.example;

import java.util.UUID;

public class Data implements Cloneable {
    private int a;
    private int b;
    private int r;

    private Point siblingPosition;

    public Data(int a, int b, int r) {
        this.a = a;
        this.b = b;
        this.r = r;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public Point getSiblingPosition() {
        return siblingPosition;
    }

    public void setSiblingPosition(Point siblingPosition) {
        this.siblingPosition = siblingPosition;
    }

    @Override
    public Data clone()  {
        try {
            return (Data) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Data(this.a, this.b, this.r);
        }
    }
}
