package game;

import java.util.List;

public class MultiplayerGame {
    private final Board board;
    private final List<Player> players;

    public MultiplayerGame(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.board.setCountOfPlayers(players.size());
    }

    public int play(boolean log) {
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                final int result = makeMove(players.get(i), i + 1, log);
                if (result != -1) {
                    return result;
                }
            }
        }
    }

    private int makeMove(Player player, int no, boolean log) {
        final Move move = player.makeMove(board.getPosition());
        final GameResult result = board.makeMove(move);
        if (log) {
            System.out.println();
            System.out.println("Player: " + no);
            System.out.println(move);
            System.out.println(board);
            if (result == GameResult.WIN || result == GameResult.LOOSE) {
                System.out.println("Result: " + no + " player " + result);
            } else {
                System.out.println("Result: " + result);
            }
        }
        switch (result) {
            case WIN:
                return no;
            case LOOSE:
                return no + 4;
            case DRAW:
                return 0;
            case UNKNOWN:
                return -1;
            default:
                throw new AssertionError("Unknown makeMove result " + result);
        }
    }
}
