package game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        while (true) {
            System.out.println();
            System.out.println("Current position");
            System.out.println(position);
            System.out.println("Enter you move for " + position.getTurn());
            try {
                String row = in.next(), column = in.next();
                Move move = new Move(Integer.parseUnsignedInt(row) - 1,
                        Integer.parseInt(column) - 1, position.getTurn());
                if (!position.isValid(move)) {
                    System.out.println("Your move is invalid");
                    System.out.println("Please enter the correct move");
                    continue;
                }
                return move;
            } catch (NumberFormatException e) {
                System.out.println("You need to enter the row and column numbers separated by a space");
                System.out.println("Please enter the correct move");
            }
        }
    }
}
