package game;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int result = new MultiplayerGame(
                new NoughtsAndCrossesBoard(10, 10, 3),
                List.of(
//                        new HumanPlayer(new Scanner(System.in)),
                        new SequentialPlayer(),
                        new SequentialPlayer(),
                        new SequentialPlayer(),
                        new SequentialPlayer()
                )
        ).play(true);
        switch (result) {
            case 1:
                System.out.println("First player won");
                break;
            case 2:
                System.out.println("Second player won");
                break;
            case 3:
                System.out.println("Third player won");
                break;
            case 4:
                System.out.println("Fourth player won");
            case 5:
                System.out.println("First player loose");
                break;
            case 6:
                System.out.println("Second player loose");
                break;
            case 7:
                System.out.println("Third player loose");
                break;
            case 8:
                System.out.println("Fourth player loose");
            case 0:
                System.out.println("Draw");
                break;
            default:
                throw new AssertionError("Unknown result " + result);
        }
    }
}
