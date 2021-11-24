package game;

public interface Board {
    Position getPosition();

    GameResult makeMove(Move move);

    void setCountOfPlayers(int countOfPlayers);
}
