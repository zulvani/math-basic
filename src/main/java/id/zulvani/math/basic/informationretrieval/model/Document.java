package id.zulvani.math.basic.informationretrieval.model;

public class Document {
    private String docId;
    private String docContent;
    private String title;

    private double jaccardValue;
    private double termFrequency;

    private double idfWeight;

    public Document(String docId, String docContent) {
        this.docId = docId;
        this.docContent = docContent;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocContent() {
        return docContent;
    }

    public void setDocContent(String docContent) {
        this.docContent = docContent;
    }

    public double getJaccardValue() {
        return jaccardValue;
    }

    public void setJaccardValue(double jaccardValue) {
        this.jaccardValue = jaccardValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getTermFrequency() {
        return termFrequency;
    }

    public void setTermFrequency(double termFrequency) {
        this.termFrequency = termFrequency;
    }

    public double getIdfWeight() {
        return idfWeight;
    }

    public void setIdfWeight(double idfWeight) {
        this.idfWeight = idfWeight;
    }
}
