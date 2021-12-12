import java.io.*;
import java.util.Arrays;

public class MyScanner {
    private final Reader in;
    private IOException lastException;
    private final int sz = 1024;
    private final char[] buffer = new char[sz];
    private int readFrom = 0, readTo = 0;

    public MyScanner(Reader in) {
        this.in = in;
    }

    public MyScanner(File source) throws FileNotFoundException {
        this(new FileReader(source));
    }

    public MyScanner(String source) {
        this(new StringReader(source));
    }

    public MyScanner(InputStream source) {
        this(new InputStreamReader(source));
    }

    private int fillBuf() {
        return fillBuf(0);
    }

    private int fillBuf(int off) {
        int read;
        try {
            read = in.read(buffer, off, sz - off);
        } catch (IOException e) {
            lastException = e;
            read = -1;
        }
        readFrom = -1;
        readTo = read - 1;
        return read;
    }

    private int read() {
        if (readFrom >= readTo) {
            if (fillBuf() == -1) {
                return -1;
            }
        }
        readFrom++;
        return buffer[readFrom];
    }

    private int removeStart(int off) {
        System.arraycopy(Arrays.copyOfRange(buffer, off, sz), 0, buffer, 0, sz - off);
        readFrom = -1;
        int oldReadTo = readTo;
        int x = fillBuf(sz - off);
        readTo = oldReadTo - off;
        if (x != -1) {
            readTo += x;
        }
        return x;
    }

    public String nextLine() {
        StringBuilder line = new StringBuilder();
        int i;
        while (true) {
            if (readFrom >= readTo) {
                if (fillBuf() == -1) {
                    if (line.isEmpty()) {
                        return null;
                    }
                    return line.toString();
                }
            }
            for (i = readFrom + 1; i <= readTo; i++) {
                if (buffer[i] == '\n') {
                    if (i > 0 && buffer[i - 1] == '\r') {
                        line.append(Arrays.copyOfRange(buffer, readFrom + 1, i - 1));
                    } else {
                        line.append(Arrays.copyOfRange(buffer, readFrom + 1, i));
                    }
                    readFrom = i;
                    return line.toString();
                }
            }
            if (buffer[readTo - 1] == '\r') {
                line.append(Arrays.copyOfRange(buffer, readFrom + 1, readTo));
            } else {
                line.append(Arrays.copyOfRange(buffer, readFrom + 1, readTo + 1));
            }
            readFrom = readTo;
        }
    }

    public boolean hasNextLine() {
        if (readFrom >= readTo) {
            return fillBuf() != -1;
        }
        return true;
    }

    public boolean hasNext() {
        while (true) {
            if (readFrom >= readTo) {
                if (fillBuf() == -1)
                    return false;
            }
            int start = -1;
            for (int i = readFrom + 1; i <= readTo; i++) {
                if (Character.isWhitespace(buffer[i])) {
                    if (start != -1) {
                        return true;
                    }
                } else {
                    if (start == -1) {
                        start = i;
                    }
                }
            }
            if (start != -1) {
                if (removeStart(start) <= 0) {
                    return true;
                }
            } else {
                readFrom = readTo;
            }
        }
    }

    public String next() {
        if (hasNext()) {
            int start = -1;
            int i;
            for (i = readFrom + 1; i <= readTo; i++) {
                if (Character.isWhitespace(buffer[i])) {
                    if (start != -1) {
                        readFrom = i - 1;
                        return String.valueOf(Arrays.copyOfRange(buffer, start, i));
                    }
                } else {
                    if (start == -1) {
                        start = i;
                    }
                }
            }
            if (start != -1) {
                readFrom = readTo;
                return String.valueOf(Arrays.copyOfRange(buffer, start, readTo + 1));
            }
        }
        return null;
    }

    public boolean hasNextInt() {
        int starts = -1;
        StringBuilder line = new StringBuilder();
        while (true) {
            if (readFrom >= readTo) {
                if (fillBuf() == -1)
                    return false;
            }
            int i;
            for (i = readFrom + 1; i <= readTo; i++) {
                if (Character.isWhitespace(buffer[i])) {
                    if (starts != -1) {
                        try {
                            Integer.parseInt(line.toString());
                            return true;
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    }
                } else {
                    if (starts == -1) {
                        starts = i;
                    }
                    line.append(buffer[i]);
                }
            }
            if (starts != -1) {
                if (i == sz) {
                    removeStart(starts);
                    line = new StringBuilder();
                } else {
                    return true;
                }
            } else {
                readFrom = readTo;
            }
        }
    }

    public int nextInt() {
        if (hasNextInt()) {
            int starts = -1;
            StringBuilder line = new StringBuilder();
            for (int i = readFrom + 1; i <= readTo; i++) {
                if (Character.isWhitespace(buffer[i])) {
                    if (starts != -1) {
                        readFrom = i - 1;
                        return Integer.parseInt(line.toString());
                    }
                } else {
                    if (starts == -1) {
                        starts = i;
                    }
                    line.append(buffer[i]);
                }
            }
            readFrom = readTo;
            return Integer.parseInt(line.toString());

        }
        return 0;
    }

    public boolean hasNextWord() {
        while (true) {
            if (readFrom >= readTo) {
                if (fillBuf() == -1)
                    return false;
            }
            int start = -1;
            for (int i = readFrom + 1; i <= readTo; i++) {
                char chr = buffer[i];
                boolean partOfLetter = (Character.isLetter(chr) | chr == '\''
                        | Character.getType(chr) == Character.DASH_PUNCTUATION);
                if (!partOfLetter) {
                    if (start != -1) {
                        return true;
                    }
                } else {
                    if (start == -1) {
                        start = i;
                    }
                }
            }
            if (start != -1) {
                if (removeStart(start) <= 0) {
                    return true;
                }
            } else {
                readFrom = readTo;
            }
        }
    }

    public String nextWord() {
        if (hasNextWord()) {
            int start = -1;
            int i;
            for (i = readFrom + 1; i <= readTo; i++) {
                char chr = buffer[i];
                boolean partOfLetter = (Character.isLetter(chr) | chr == '\''
                        | Character.getType(chr) == Character.DASH_PUNCTUATION);
                if (!partOfLetter) {
                    if (start != -1) {
                        readFrom = i - 1;
                        return String.valueOf(Arrays.copyOfRange(buffer, start, i));
                    }
                } else {
                    if (start == -1) {
                        start = i;
                    }
                }
            }
            if (start != -1) {
                readFrom = readTo;
                return String.valueOf(Arrays.copyOfRange(buffer, start, readTo + 1));
            }

        }
        return null;
    }

    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            lastException = e;
        }
    }

    public IOException ioException() {
        return lastException;
    }
}
