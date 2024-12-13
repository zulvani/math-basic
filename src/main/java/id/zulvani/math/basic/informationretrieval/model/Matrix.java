package id.zulvani.math.basic.informationretrieval.model;

public class Matrix {
    private String[] xLabel;
    private String[] yLabel;

    // term frequency in the document (log10 n) where n is the term frequency in a document
    private double tfScore[];

    // term
    private double collectionFrequency[];
    // df(t) is the document frequency, the number of documents that t occurs in
    private int documentFrequency[];

    private double idfWeight[];
    private double[][] matrix;

    private double[] jarccardValue;

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

    public double[] getCollectionFrequency() {
        return collectionFrequency;
    }

    public void setCollectionFrequency(double[] collectionFrequency) {
        this.collectionFrequency = collectionFrequency;
    }

    public int[] getDocumentFrequency() {
        return documentFrequency;
    }

    public void setDocumentFrequency(int[] documentFrequency) {
        this.documentFrequency = documentFrequency;
    }

    public double[] getIdfWeight() {
        return idfWeight;
    }

    public void setIdfWeight(double[] idfWeight) {
        this.idfWeight = idfWeight;
    }

    public double[] getJarccardValue() {
        return jarccardValue;
    }

    public void setJarccardValue(double[] jarccardValue) {
        this.jarccardValue = jarccardValue;
    }
}
