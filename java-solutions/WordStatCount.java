import base.Pair;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

class Comp implements Comparator<Object[]> {

    @Override
    public int compare(Object[] x, Object[] y) {
        if ((int) x[1] < (int) y[1])
            return -1;
        if ((int) x[1] > (int) y[1])
            return 1;
        if ((int) x[2] < (int) y[2])
            return -1;
        if ((int) x[2] > (int) y[2])
            return 1;
        return 0;
    }
}

public class WordStatCount {
    public static void main(final String[] args) {
        Map<String, Integer> firstInput = new HashMap<>();
        Map<String, Integer> dict = new HashMap<>();
        MyScanner in;
        try {
            BufferedWriter out;
            String inputFileName = args[0];
            String outputFileName = args[1];
            in = new MyScanner(new FileReader(inputFileName, StandardCharsets.UTF_8));
            String word;
            int i = 0;
            while (in.hasNextWord()) {
                word = in.nextWord().toLowerCase();
                dict.putIfAbsent(word, 0);
                firstInput.putIfAbsent(word, i++);
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
                i = 0;
                Object[][] data = new Object[dict.size()][3];
                for (String x : dict.keySet()) {
                    data[i++] = new Object[]{x, dict.get(x), firstInput.get(x)};
                }
                Comparator<Object[]> comparator = new Comp();
                Arrays.sort(data, comparator);

                for (Object[] x : data) {
                    out.write(x[0] + " " + x[1]);
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