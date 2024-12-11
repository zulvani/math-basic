package id.zulvani.math.basic.informationretrieval;

import id.zulvani.math.basic.informationretrieval.model.Document;
import id.zulvani.math.basic.informationretrieval.model.Matrix;

import java.util.Arrays;
import java.util.regex.Pattern;

public class MatrixProcessor {

    private final Document[] documents;
    private final String query;

    public MatrixProcessor(Document[] documents, String query) {
        this.documents = documents;
        this.query = query;
    }

    public Matrix toIncidentMatrix(){
        Matrix m = new id.zulvani.math.basic.informationretrieval.model.Matrix();

        if (query == null || documents == null) {
            return m;
        }

        // Tokenize the query into terms
        String[] queryTerms = query.split(" ");
        if (queryTerms.length == 0) {
            return m;
        }

        // Create the incidence matrix
        double[][] incidenceMatrix = new double[documents.length][queryTerms.length];

        // Fill the incidence matrix
        for (int i = 0; i < documents.length; i++) {
            String text = documents[i].getDocContent().toLowerCase(); // Convert to lowercase for case-insensitive comparison
            for (int j = 0; j < queryTerms.length; j++) {
                String term = queryTerms[j].toLowerCase();
                // Check if the term is present in the text
                if (text.contains(term)) {
                    incidenceMatrix[i][j] = 1;
                } else {
                    incidenceMatrix[i][j] = 0;
                }
            }
        }

        m.setMatrix(incidenceMatrix);
        m.setxLabel(queryTerms);
        m.setyLabel(Arrays.stream(documents).map(Document::getDocId).toList().toArray(new String[documents.length]));

        for(int j =0; j < m.getxLabel().length; j++) {
            System.out.print("[" + m.getxLabel()[j] + "] ");
        }
        System.out.println();

        for(int i = 0; i < incidenceMatrix.length; i++) {
            for(int j =0; j < incidenceMatrix[i].length; j++) {
                if (j == 0) {
                    System.out.print("[" + m.getyLabel()[i] + "] ");
                }
                System.out.print("[" + incidenceMatrix[i][j] + "] ");
            }
            System.out.println();
        }
        return m;
    }

    public Matrix toCountMatrix(boolean logCount){
        Matrix m = new id.zulvani.math.basic.informationretrieval.model.Matrix();

        if (query == null || documents == null) {
            return m;
        }

        // Tokenize the query into terms
        String[] queryTerms = query.split(" ");
        if (queryTerms.length == 0) {
            return m;
        }

        // Create the incidence matrix
        double[][] countMatrix = new double[documents.length][queryTerms.length];
        double[] tfScore = new double[documents.length];
        double[] cf = new double[queryTerms.length];

        // Fill the incidence matrix
        for (int i = 0; i < documents.length; i++) {
            String text = documents[i].getDocContent().toLowerCase(); // Convert to lowercase for case-insensitive comparison
            for (int j = 0; j < queryTerms.length; j++) {
                String term = queryTerms[j].toLowerCase();
                int c = countWordInText(text, term);
                if (logCount) {
                    countMatrix[i][j] = c > 0 ? 1 + Math.log10(c) : 0;
                } else {
                    countMatrix[i][j] = c;
                }
                tfScore[i] = tfScore[i] + countMatrix[i][j];
            }
        }

        for (int j = 0; j < queryTerms.length; j++) {
            for(int i = 0; i < documents.length;i++) {
                cf[j] = cf[j] + countMatrix[i][j];
            }
        }

        m.setMatrix(countMatrix);
        m.setxLabel(queryTerms);
        m.setTfScore(tfScore);
        m.setCollectionFrequency(cf);
        m.setyLabel(Arrays.stream(documents).map(Document::getDocId).toList().toArray(new String[documents.length]));

        for(int j =0; j < m.getxLabel().length; j++) {
            System.out.print("[" + m.getxLabel()[j] + "] ");
        }
        System.out.println();

        for(int i = 0; i < countMatrix.length; i++) {
            for(int j =0; j < countMatrix[i].length; j++) {
                if (j == 0) {
                    System.out.print("[" + m.getyLabel()[i] + "] ");
                }
                System.out.print("[" + countMatrix[i][j] + "] ");

                if (j == (countMatrix[i].length - 1)) {
                    System.out.print("[" + m.getTfScore()[i] + "] ");
                }
            }
            System.out.println();
        }

        for(int i = 0; i < m.getCollectionFrequency().length; i++) {
            if (i == 0) {
                System.out.print("[CF] ");
            }
            System.out.print("[" + m.getCollectionFrequency()[i] + "] ");
        }
        System.out.println();
        return m;
    }

    public int countWordInText(String text, String word){
        int wordCount = 0;
        text = removeNonAlphaNumeric(text);
        for (String c : text.split(" ")) {
            if (word.equalsIgnoreCase(c)) {
                wordCount++;
            }
        }
        return wordCount;
    }

    public static String removeNonAlphaNumeric(String input) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s]");
        return pattern.matcher(input).replaceAll("");
    }
}