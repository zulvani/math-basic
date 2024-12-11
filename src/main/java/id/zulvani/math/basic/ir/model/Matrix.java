package id.zulvani.math.basic.ir.model;

public class Matrix {
    private String[] xLabel;
    private String[] yLabel;

    private double tfScore[];
    private double[][] matrix;

    public String[] getxLabel() {
        return xLabel;
    }

    public void setxLabel(String[] xLabel) {
        this.xLabel = xLabel;
    }

    public String[] getyLabel() {
        return yLabel;
    }

    public void setyLabel(String[] yLabel) {
        this.yLabel = yLabel;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[] getTfScore() {
        return tfScore;
    }

    public void setTfScore(double[] tfScore) {
        this.tfScore = tfScore;
    }
}
