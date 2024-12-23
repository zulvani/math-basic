package id.zulvani.math.basic.informationretrieval;

import id.zulvani.math.basic.informationretrieval.model.Document;
import id.zulvani.math.basic.informationretrieval.model.Matrix;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class MatrixProcessor {

    private final Document[] documents;
    private final String query;

    private final long numberOfDocument;

    public MatrixProcessor(Document[] documents, String query) {
        this.documents = documents;
        this.query = query;
        this.numberOfDocument = documents.length;
    }

    public Matrix toIncidentMatrix(boolean verbose){
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
        double[] jaccardValues = new double[documents.length];

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
            jaccardValues[i] = calculateJaccard(query, text);
            documents[i].setJaccardValue(jaccardValues[i]);
        }

        m.setMatrix(incidenceMatrix);
        m.setxLabel(queryTerms);
        m.setJarccardValue(jaccardValues);
        m.setyLabel(Arrays.stream(documents).map(Document::getDocId).toList().toArray(new String[documents.length]));

        if (verbose) {
            for (int j = 0; j < m.getxLabel().length; j++) {
                System.out.print("[" + m.getxLabel()[j] + "] ");
            }
            System.out.println();

            for (int i = 0; i < incidenceMatrix.length; i++) {
                for (int j = 0; j < incidenceMatrix[i].length; j++) {
                    if (j == 0) {
                        System.out.print("[" + m.getyLabel()[i] + "] ");
                    }
                    System.out.print("[" + incidenceMatrix[i][j] + "] ");

                    if (j == (incidenceMatrix[i].length - 1)) {
                        System.out.print("[" + jaccardValues[i] + "] ");
                    }
                }
                System.out.println();
            }
        }
        return m;
    }

    public Matrix toCountMatrix(boolean verbose, boolean logCount, boolean documentFrequency){
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
        double[] termFrequencies = new double[documents.length];
        double[] cf = new double[queryTerms.length]; // how many documents that term occur
        int[] dft = new int[queryTerms.length];

        if (documentFrequency) {
            // calculate document frequency: how many documents that term occur
            for (int j = 0; j < queryTerms.length; j++) {
                String term = queryTerms[j].toLowerCase();
                for (Document document : documents) {
                    String text = document.getDocContent().toLowerCase(); // Convert to lowercase for case-insensitive comparison
                    if (text.contains(term)) {
                        cf[j] = cf[j] + 1;
                    }
                }
            }

            for (int j = 0; j < queryTerms.length; j++) {
                cf[j] = Math.log10(numberOfDocument/cf[j]);
            }
        }

        // calculate term frequency for each documents
        for (int i = 0; i < documents.length; i++) {
            String text = documents[i].getDocContent().toLowerCase(); // Convert to lowercase for case-insensitive comparison
            for (int j = 0; j < queryTerms.length; j++) {
                String term = queryTerms[j].toLowerCase();
                if (documentFrequency) {
                    double c = countWordInText(text, term, cf[j]);
                    countMatrix[i][j] = c;
                    documents[i].setDfT(c + documents[i].getDfT());
                }
                else {
                    double c = countWordInText(text, term, 1);
                    countMatrix[i][j] = c;
                    termFrequencies[i] = termFrequencies[i] + countMatrix[i][j];
                    documents[i].setTermFrequency(termFrequencies[i]); // Term Frequency without Log10
                }
            }
        }

        for (Document doc : documents){
            if (logCount) {
                double log10TermFrequency = doc.getTermFrequency() > 0 ? 1 + Math.log10(doc.getTermFrequency()) : 0;
                doc.setLog10TermFrequency(log10TermFrequency);
            }

            if (documentFrequency) {
                double idft = doc.getDfT() > 0 ? Math.log10(numberOfDocument / doc.getDfT()) : 0;
                doc.setIdfWeight(idft);
            }
        }

        m.setMatrix(countMatrix);
        m.setxLabel(queryTerms);
        m.setTfScore(termFrequencies);
        m.setCollectionFrequency(cf);
        m.setDocumentFrequency(dft);
        m.setyLabel(Arrays.stream(documents).map(Document::getDocId).toList().toArray(new String[documents.length]));

        if (verbose) {
            for (int j = 0; j < m.getxLabel().length; j++) {
                System.out.print("[" + m.getxLabel()[j] + "] ");
            }
            System.out.println();

            for (int i = 0; i < countMatrix.length; i++) {
                for (int j = 0; j < countMatrix[i].length; j++) {
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

            for (int i = 0; i < m.getCollectionFrequency().length; i++) {
                if (i == 0) {
                    System.out.print("[CF] ");
                }
                System.out.print("[" + m.getCollectionFrequency()[i] + "] ");
            }
            System.out.println();

            for (int i = 0; i < m.getDocumentFrequency().length; i++) {
                if (i == 0) {
                    System.out.print("[DFt] ");
                }
                System.out.print("[" + m.getDocumentFrequency()[i] + "] ");
            }
            System.out.println();
        }
        return m;
    }

    public double countWordInText(String text, String word, double i){
        double wordCount = 0;
        text = removeNonAlphaNumeric(text);
        for (String c : text.split(" ")) {
            if (word.equalsIgnoreCase(c)) {
                wordCount = wordCount + i;
            }
        }
        return wordCount;
    }

    public static String removeNonAlphaNumeric(String input) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s]");
        return pattern.matcher(input).replaceAll("");
    }

    public double calculateJaccard(String a, String b) {
        // Split strings into sets of words
        Set<String> setA = new HashSet<>(Arrays.asList(a.split(" ")));
        Set<String> setB = new HashSet<>(Arrays.asList(b.split(" ")));

        Set<String> intersectionSet = new HashSet<>(setA);
        Set<String> unionSet = new HashSet<>(setA);
        unionSet.addAll(setB);
        intersectionSet.retainAll(setB);

        BigDecimal intersection = BigDecimal.valueOf(intersectionSet.size());
        BigDecimal union = BigDecimal.valueOf(unionSet.size());

        return intersection.divide(union, 2,  RoundingMode.HALF_DOWN).doubleValue();
    }
}
