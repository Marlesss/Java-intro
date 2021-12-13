import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class WsppSortedPosition {
    public static void main(final String[] args) {
        final int length = 100;
        Map<String, Integer[]> dict = new TreeMap<>();
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
                        dict.putIfAbsent(word, new Integer[length]);
                        word_count++;
                        Integer[] dictWord = dict.get(word);
                        if (dictWord[0] == null) {
                            dictWord[0] = 0;
                        }
                        if (dictWord[0] + 2 >= dictWord.length) {
                            dict.put(word, Arrays.copyOf(dictWord, dictWord.length * 2));
                            dictWord = dict.get(word);
                        }
                        dictWord[++dictWord[0]] = line_count;
                        dictWord[++dictWord[0]] = word_count;
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
                for (Map.Entry<String, Integer[]> entry : dict.entrySet()) {
                    Integer[] value = entry.getValue();
                    out.write(entry.getKey() + " " + value[0] / 2);
                    for (int i = 1; i <= value[0]; i += 2) {
                        out.write(" " + value[i] + ":" + value[i + 1]);
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