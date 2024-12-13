package id.zulvani.math.basic.file;

import java.util.List;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {

    private String csvFile;
    private String separator;

    public CSVReader(String csvFile) {
        this.csvFile = csvFile;
        this.separator = ",";
    }

    public CSVReader(String csvFile, String separator) {
        this.csvFile = csvFile;
        this.separator = separator;
    }

    public List<String> read(int[] columns, int limit, boolean firstLineIsHeader) {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            int i = 0;
            while ((line = br.readLine()) != null) {
                if (firstLineIsHeader && i > 0) {
                    if (columns.length == 0) {
                        result.add(line);
                    } else {
                        String[] values = this.split(line);
                        StringBuilder sb = new StringBuilder();
                        boolean hasEmpty = false;
                        for (int j = 0; j < values.length; j++) {
                            for (int c : columns) {
                                if (j == c) {
                                    if(values[j].trim().equalsIgnoreCase("")){
                                        hasEmpty = true;
                                    }
                                    sb.append(values[j]).append(",");
                                }
                            }
                        }

                        if (!hasEmpty) {
                            result.add(sb.toString());
                        }
                    }

                    if (i == limit) {
                        return result;
                    }
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String[] split(String input){
        // Define the regex to split by commas but keep quoted sections intact
        String regex = "(\".*?\"|[^,]+)";

        // Create a pattern and matcher
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // List to store the split parts
        List<String> result = new ArrayList<>();

        // Match all parts
        while (matcher.find()) {
            String match = matcher.group(1);
            if (match.startsWith("\"") && match.endsWith("\"")) {
                // Remove quotes if present
                match = match.substring(1, match.length() - 1);
            }
            result.add(match);
        }

        return result.toArray(new String[result.size()]);
    }

}
