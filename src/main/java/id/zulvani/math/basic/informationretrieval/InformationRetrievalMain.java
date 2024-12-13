package id.zulvani.math.basic.informationretrieval;

import id.zulvani.math.basic.file.CSVReader;
import id.zulvani.math.basic.informationretrieval.model.Document;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.time.Duration;

public class InformationRetrievalMain {

    public InformationRetrievalMain(){

        // test read document
        CSVReader csvReader = new CSVReader("/Users/aguszulvani/privacy/MKOM/SEMESTER-3/STBI/UTS/News.csv");
        List<String> csvContent = csvReader.read(new int[]{0,2,7}, -1, true);
        int top = 10;

        Document[] documents = csvContent.stream().map(e -> {
            String[] lines = e.split(",");
            Document doc = new Document(lines[0], lines[2]);
            doc.setTitle(lines[1]);
            return doc;
        }).toList().toArray(new Document[csvContent.size()]);

        String[] queries = new String[]{
                "pekerja asing indonesia",
                "pertandingan sepak bola dunia",
                "olahraga paling digemari masyarakat indonesia",
                "olimpiade tokyo",
                "memperingati hari jadi sepak bola",
                "indonesia menjadi tuan rumah piala dunia",
                "olimpiade olahraga paling bergengsi",
                "ketakutan olahrawagan dalam pertandingan",
                "pertandingan paling seru",
                "vaksin sebagai politik dalam dunia olahraga"
        };

//        for (String query : queries) {
//            MatrixProcessor im = new MatrixProcessor(documents, query);
//            Instant startTime = Instant.now();
//            im.toIncidentMatrix(false);
//            Instant endTime = Instant.now();
//            Duration duration = Duration.between(startTime, endTime);
//            System.out.println("Query: " + query);
//            System.out.println("Searching Time: " + duration.toMillis() + " ms");
//
//            List<Document> result = Arrays.asList(documents);
//            result.sort(Comparator.comparingDouble(Document::getJaccardValue).reversed());
//            result = result.subList(1, top);
//
//            for(Document doc : result) {
//                System.out.println(doc.getDocId() + "," + doc.getTitle() + "," + doc.getJaccardValue());
//            }
//            System.out.println("----");
//        }

//        for (String query : queries) {
//            MatrixProcessor im = new MatrixProcessor(documents, query);
//            Instant startTime = Instant.now();
//            im.toCountMatrix(false, false, false);
//            Instant endTime = Instant.now();
//            Duration duration = Duration.between(startTime, endTime);
//            System.out.println("Query: " + query);
//            System.out.println("Searching Time: " + duration.toMillis() + " ms");
//
//            List<Document> result = Arrays.asList(documents);
//            result.sort(Comparator.comparingDouble(Document::getTermFrequency).reversed());
//            result = result.subList(1, top);
//            for (Document doc : result) {
//                System.out.println(doc.getDocId() + "," + doc.getTitle() + "," + doc.getTermFrequency());
//            }
//            System.out.println("----");
//        }

//        for (String query : queries) {
//            MatrixProcessor im = new MatrixProcessor(documents, query);
//            Instant startTime = Instant.now();
//            im.toCountMatrix(false, true, false);
//            Instant endTime = Instant.now();
//            Duration duration = Duration.between(startTime, endTime);
//            System.out.println("Query: " + query);
//            System.out.println("Searching Time: " + duration.toMillis() + " ms");
//
//            List<Document> result = Arrays.asList(documents);
//            result.sort(Comparator.comparingDouble(Document::getTermFrequency).reversed());
//            result = result.subList(1, top);
//            for (Document doc : result) {
//                System.out.println(doc.getDocId() + "," + doc.getTitle() + "," + doc.getTermFrequency());
//            }
//            System.out.println("----");
//        }

        for (String query : queries) {
            MatrixProcessor im = new MatrixProcessor(documents, query);
            Instant startTime = Instant.now();
            im.toCountMatrix(false, false, true);
            Instant endTime = Instant.now();
            Duration duration = Duration.between(startTime, endTime);
            System.out.println("Query: " + query);
            System.out.println("Searching Time: " + duration.toMillis() + " ms");

            List<Document> result = Arrays.asList(documents);
            result.sort(Comparator.comparingDouble(Document::getTermFrequency).reversed());
            result = result.subList(1, top);
            for (Document doc : result) {
                System.out.println(doc.getDocId() + "," + doc.getTitle() + "," + doc.getTermFrequency());
            }
            System.out.println("----");
        }
    }

    public static void main(String[] args) {
        new InformationRetrievalMain();
    }

}
