package game;

public class CheatingPlayer implements Player {
    @Override
    public Move makeMove(Position position) {
        final NoughtsAndCrossesBoard board = (NoughtsAndCrossesBoard) position;
        Move first = null;
        for (int r = 0; r < position.getHeight(); r++) {
            for (int c = 0; c < position.getWidth(); c++) {
                final Move move = new Move(r, c, position.getTurn());
                if (position.isValid(move)) {
                    if (first == null) {
                        first = move;
                    } else {
                        board.makeMove(move);
                    }
                }
            }
        }
        return first;
    }
}
