package id.zulvani.math.basic;
public class Point {
    private int row;
    private int column;

    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isEqual(Point p) {
        return (p.getRow() == this.getColumn() && p.getColumn() == this.getColumn());
    }
}
