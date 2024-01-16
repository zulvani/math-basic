package id.zulvani.math.basic.arithmetic;

public class Data implements Cloneable {
    private int a;
    private int b;
    private int z;

    private Point siblingPosition;

    public Data(int a, int b, int z) {
        this.a = a;
        this.b = b;
        this.z = z;
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

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
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
            return new Data(this.a, this.b, this.z);
        }
    }
}
