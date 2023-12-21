package org.example;

import java.util.UUID;

public class Data {
    private int a;
    private int b;
    private int r;

    private String id;

    public Data() {
    }

    public Data(int a, int b, int r) {
        this.id = UUID.randomUUID().toString();
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
}
