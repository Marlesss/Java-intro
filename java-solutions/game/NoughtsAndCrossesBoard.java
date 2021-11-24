package game;

import java.util.Arrays;
import java.util.Map;

public class NoughtsAndCrossesBoard implements Board, Position {
    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0",
            Cell.M, "-",
            Cell.B, "|"
    );

    private final Cell[][] field;
    private final int n, m, k;
    private final int charLength;
    private Cell turn;
    private int countOfPlayers;

    public NoughtsAndCrossesBoard(int n, int m, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        field = new Cell[n][m];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;

        int r = n;
        if (m > r) {
            r = m;
        }
        charLength = getIntLength(r);
    }

    public void setCountOfPlayers(int countOfPlayers) {
        this.countOfPlayers = countOfPlayers;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            return GameResult.LOOSE;
        }

        field[move.getRow()][move.getCol()] = move.getValue();
        if (checkWin()) {
            return GameResult.WIN;
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }

        if (turn == Cell.X) {
            turn = Cell.O;
        } else if (turn == Cell.O) {
            if (countOfPlayers == 2) {
                turn = Cell.X;
            } else {
                turn = Cell.M;
            }
        } else if (turn == Cell.M) {
            if (countOfPlayers == 3) {
                turn = Cell.X;
            } else {
                turn = Cell.B;
            }
        } else {
            turn = Cell.X;
        }
        return GameResult.UNKNOWN;
    }

    private boolean checkDraw() {
        int count = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (field[r][c] == Cell.E) {
                    count++;
                }
            }
        }
        if (count == 0) {
            return true;
        }
        return false;
    }

    private boolean checkWin() {
        for (int r = 0; r < n; r++) {
            int count = 0;
            for (int c = 0; c < m; c++) {
                if (field[r][c] == turn) {
                    count++;
                    if (count == k) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        for (int c = 0; c < m; c++) {
            int count = 0;
            for (int r = 0; r < n; r++) {
                if (field[r][c] == turn) {
                    count++;
                    if (count == k) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }

        for (int r = 0; r < n + m - 1; r++) {
            int count1 = 0;
            int count2 = 0;
            for (int c = 0; c <= r; c++) {
                if (r - c < n && c < m) {
                    if (field[r - c][c] == turn) {
                        count1++;
                        if (count1 == k) {
                            return true;
                        }
                    } else {
                        count1 = 0;
                    }
                    if (field[n - 1 - (r - c)][c] == turn) {
                        count2++;
                        if (count2 == k) {
                            return true;
                        }
                    } else {
                        count2 = 0;
                    }
                }
            }
        }
        return false;
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getCol() && move.getCol() < m
                && field[move.getRow()][move.getCol()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(charLength));
        for (int i = 1; i <= m; i++) {
            sb.append(" ".repeat(charLength - getIntLength(i)));
            sb.append(i);
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < n; r++) {
            sb.append(r + 1);
            sb.append(" ".repeat(charLength - getIntLength(r + 1)));
            for (Cell cell : field[r]) {
                sb.append(" ".repeat(charLength - 1));
                sb.append(CELL_TO_STRING.get(cell));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }

    private int getIntLength(int x) {
        int ans = 0;
        while (x > 0) {
            ans++;
            x /= 10;
        }
        return ans;
    }

    @Override
    public int getWidth() {
        return m;
    }


    @Override
    public int getHeight() {
        return n;
    }

}
