package id.zulvani.math.basic.ir.model;

public class Document {
    private String docId;
    private String docContent;

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
}
