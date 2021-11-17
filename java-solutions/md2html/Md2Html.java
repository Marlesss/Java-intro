package md2html;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class Md2Html {
    static char[] data;
    static int size;
    static int j;

    public static void main(final String[] args) {
        String tags_chars = "*_-`";
        Map<Character, String> specialSymbols = new HashMap<>();
        specialSymbols.put('<', "&lt;");
        specialSymbols.put('>', "&gt;");
        specialSymbols.put('&', "&amp;");
        String inputFileName = args[0];
        String outputFileName = args[1];
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(inputFileName),
                        StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(
                     new OutputStreamWriter(
                             new FileOutputStream(outputFileName),
                             StandardCharsets.UTF_8));
        ) {
            data = new char[1024];
            size = 0;
            int index = 0;
            int symb;
            while (true) {
                symb = in.read();
                if (symb == -1) {
                    break;
                }
                if (size >= data.length) {
                    data = Arrays.copyOf(data, data.length * 2);
                }
                data[size++] = (char) symb;
            }

            Stack<String> tags = new Stack<>();
            boolean data_meeted = false, empty_line = false;
            String paragraph_tag = null;
            int i;
            loop:
            for (j = 0; j < size; j++) {
                if ("/r/n".contains(String.valueOf(data[j]))) {

                    // because of two-character line break
                    if ("/r/n".contains(String.valueOf(data[j + 1]))) {
                        j++;
                    }

                    if (!empty_line) {
                        empty_line = true;
                        continue;
                    }

                    // if two line breaks in a row
                    empty_line = false;
                    if (data_meeted) {
                        // paragraph ended;
                        out.write("</" + paragraph_tag + ">");
                        out.newLine();
                        data_meeted = false;
                    }
                    continue;
                }
                if (empty_line) {
                    // need to switch to a new line
                    if (data_meeted) {
                        out.newLine();
                    }
                    empty_line = false;
                }
                if (!data_meeted) {
                    // first non-line-break symbol
                    data_meeted = true;
                    int header_level = 0;
                    while (j < size && data[j] == '#') {
                        header_level++;
                        j++;
                    }
                    if (j == size) {
                        for (int r = 0; r < header_level; r++) {
                            out.write("#");
                        }
                        break;
                    }
                    if (header_level > 0) {
                        if (Character.isWhitespace(data[j])) {
                            // if header ends correctly
                            paragraph_tag = "h" + header_level;
                            out.write("<" + paragraph_tag + ">");
                            continue;
                        } else {
                            // except it's just a piece of text
                            paragraph_tag = "p";
                            out.write("<" + paragraph_tag + ">");
                            for (int r = 0; r < header_level; r++) {
                                out.write("#");
                            }
                        }
                    } else {
                        paragraph_tag = "p";
                        out.write("<" + paragraph_tag + ">");
                    }
                }

                // check for tags

                if (data[j] == '\\') {
                    j++;
                    if (j == size) {
                        break;
                    }
                    out.write(data[j]);
                    continue;
                }

                if (j < size - 1) {
                    switch (data[j]) {
                        case '`': {
                            // <code>
                            if (tags.size() > 0 && tags.peek().equals("`")) {
                                tags.pop();
                                out.write("</code>");
                                continue loop;
                            }
                            i = find_first("`", j + 1);
                            if (i != -1) {
                                out.write("<code>");
                                tags.push("`");
                                continue loop;
                            }
                            break;
                        }
                        case '*':
                        case '_': {
                            if (data[j] == data[j + 1]) {
                                // <strong>
                                if (tags.size() > 0 && tags.peek().equals(data[j] == '*' ? "**" : "__")) {
                                    tags.pop();
                                    out.write("</strong>");
                                    j++;
                                    continue loop;
                                }
                                i = find_first(data[j] == '*' ? "**" : "__", j + 2);
                                if (i != -1) {
                                    out.write("<strong>");
                                    tags.push(data[j] == '*' ? "**" : "__");
                                    j++;
                                    continue loop;
                                }
                            }
                            // <em>
                            if (tags.size() > 0 && tags.peek().equals(String.valueOf(data[j]))) {
                                tags.pop();
                                out.write("</em>");
                                continue loop;
                            }

                            i = find_first(String.valueOf(data[j]), j + 1);
                            if (i != -1) {
                                out.write("<em>");
                                tags.push(String.valueOf(data[j]));
                                continue loop;
                            }
                            break;
                        }
                        case '-':
                            if (data[j] == data[j + 1]) {
                                if (tags.size() > 0 && tags.peek().equals("--")) {
                                    tags.pop();
                                    out.write("</s>");
                                    j++;
                                    continue loop;
                                }

                                // <s>
                                i = find_first("--", j + 2);
                                if (i != -1) {
                                    out.write("<s>");
                                    tags.push("--");
                                    j++;
                                    continue loop;
                                }
                            }
                            break;
                        case '\'':
                            if (data[j] == data[j + 1]) {
                                if (tags.size() > 0 && tags.peek().equals("''")) {
                                    tags.pop();
                                    out.write("</q>");
                                    j++;
                                    continue loop;
                                }

                                // <q>
                                i = find_first("''", j + 2);
                                if (i != -1) {
                                    out.write("<q>");
                                    tags.push("''");
                                    j++;
                                    continue loop;
                                }
                            }
                            break;
                    }
                }

                // if it's not a tag
                out.write(specialSymbols.getOrDefault(data[j], String.valueOf(data[j])));
            }
            if (data_meeted) {
                out.write("</" + paragraph_tag + ">");
                out.newLine();
                data_meeted = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int find_first(String element, int from) {
        boolean empty_line = false;
        boolean flag;
        for (int i = from; i < size; i++) {
            if ("/r/n".contains(String.valueOf(data[i]))) {

                // because of two-character line break
                if ("/r/n".contains(String.valueOf(data[i + 1]))) {
                    i++;
                }

                if (!empty_line) {
                    empty_line = true;
                    continue;
                }

                // if two line breaks in a row
                return -1;
            }
            empty_line = false;
            flag = true;
            for (int r = 0; r < element.length(); r++) {
                if (!(i + r < size && element.charAt(r) == data[i + r])) {
                    flag = false;
                    break;
                }
            }
            if (data[i - 1] != '\\' && flag) {
                return i;
            }
        }
        return -1;
    }
}

// 750ms