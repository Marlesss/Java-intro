import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class WsppSortedPosition {
    public static void main(final String[] args) {
        Map<String, List<Integer>> dict = new TreeMap<>();
        try {
            String inputFileName = args[0];
            String outputFileName = args[1];
            MyScanner in = new MyScanner(new FileReader(inputFileName, StandardCharsets.UTF_8));
            try {
                String line, word;
                int line_count = 0;
                while (true) {
                    line = in.nextLine();
                    if (line == null) {
                        break;
                    }
                    line_count++;
                    int word_count = 0;
                    line = line.toLowerCase();
                    MyScanner wordScanner = new MyScanner(line);
                    while (true) {
                        word = wordScanner.nextWord();
                        if (word == null) {
                            break;
                        }
                        dict.putIfAbsent(word, new ArrayList<>());
                        word_count++;
                        List<Integer> dictWord = dict.get(word);
                        dictWord.add(line_count);
                        dictWord.add(word_count);
                    }
                }
            } finally {
                in.close();
            }
            try (BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(outputFileName),
                            StandardCharsets.UTF_8
                    )
            )) {
                for (Map.Entry<String, List<Integer>> entry : dict.entrySet()) {
                    List<Integer> value = entry.getValue();
                    out.write(entry.getKey() + " " + value.size() / 2);
                    for (int i = 0; i < value.size(); i += 2) {
                        out.write(" " + value.get(i) + ":" + value.get(i + 1));
                    }
                    out.newLine();
                }
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