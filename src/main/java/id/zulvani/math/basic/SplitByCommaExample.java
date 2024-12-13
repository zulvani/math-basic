package id.zulvani.math.basic;

import java.util.*;
import java.util.regex.*;

public class SplitByCommaExample {
    public static void main(String[] args) {
        String input = "1,abc,\"aa,bb,cc\"";

        String[] arr = split(input);


        // Print the results
        for (int i = 0; i < arr.length; i++) {
            System.out.println("String " + (i + 1) + " = " + arr[i]);
        }
    }

    public static String[] split(String input){
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
