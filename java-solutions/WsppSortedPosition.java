import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class WsppSortedPosition {
    public static void main(final String[] args) {
        Map<String, List<Integer>> dict = new TreeMap<>();
        try {
            BufferedWriter out = null;
            String inputFileName = args[0];
            String outputFileName = args[1];
            MyScanner in = new MyScanner(new FileReader(inputFileName, StandardCharsets.UTF_8));
            try {
                int i, start;
                String line, word;
                char chr;
                int line_count = 0;
                while (true) {
                    line = in.nextLine();
                    if (line == null) {
                        break;
                    }
                    line_count++;
                    int word_count = 0;
                    line = line.toLowerCase().stripLeading();
                    MyScanner wordScanner = new MyScanner(line);
                    while (true) {
                        word = wordScanner.nextWord();
                        if (word == null) {
                            break;
                        }
                        dict.putIfAbsent(word, new ArrayList<>());
                        word_count++;
                        dict.get(word).add(line_count);
                        dict.get(word).add(word_count);
                    }
                }
            } finally {
                in.close();
            }
            out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(outputFileName),
                            StandardCharsets.UTF_8
                    )
            );
            try {
                for (String word : dict.keySet()) {
                    out.write(word + " " + dict.get(word).size() / 2);
                    for (int i = 0; i < dict.get(word).size(); i += 2) {
                        out.write(" " + dict.get(word).get(i) + ":" + dict.get(word).get(i + 1));
                    }
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
// 6000ms