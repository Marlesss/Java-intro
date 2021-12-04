import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;


public class Wspp {
    public static void main(final String[] args) {
        Map<String, ArrayList<Integer>> dict = new LinkedHashMap<>();
        try {
            String inputFileName = args[0];
            String outputFileName = args[1];
            MyScanner in = new MyScanner(new FileReader(inputFileName, StandardCharsets.UTF_8));
            try {
                String line, word;
                int count = 0;
                while (true) {
                    line = in.nextLine();
                    if (line == null) {
                        break;
                    }
                    line = line.toLowerCase().stripLeading();
                    MyScanner wordScanner = new MyScanner(line);
                    while (true) {
                        word = wordScanner.nextWord();
                        if (word == null) {
                            break;
                        }
                        dict.putIfAbsent(word, new ArrayList<>());
                        count++;
                        dict.get(word).add(count);
                    }
                }
            } finally {
                in.close();
            }
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(outputFileName),
                            StandardCharsets.UTF_8
                    )
            );
            try {
                for (String word : dict.keySet()) {
                    out.write(word + " " + dict.get(word).size());
                    for (Integer x : dict.get(word)) {
                        out.write(" " + x);
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