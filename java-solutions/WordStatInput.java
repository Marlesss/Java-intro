import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatInput {
    public static void main(final String[] args) {
        int current = 0;
        int oneSize = 100;
        MyScanner in;
        Map<String, Integer> dict = new LinkedHashMap<>();
        try {
            BufferedWriter out;
            String inputFileName = args[0];
            String outputFileName = args[1];
            in = new MyScanner(new FileReader(inputFileName, StandardCharsets.UTF_8));
            String word;
            while (in.hasNextWord()) {
                word = in.nextWord().toLowerCase();
                dict.putIfAbsent(word, 0);
                dict.put(word, dict.get(word) + 1);
            }
            in.close();
            out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(outputFileName),
                            StandardCharsets.UTF_8
                    )
            );
            try {
                for (String x : dict.keySet()) {
                    out.write(x + " " + dict.get(x));
                    out.newLine();
                }
            } finally {
                out.close();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Arguments entering error: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        }

    }

}
