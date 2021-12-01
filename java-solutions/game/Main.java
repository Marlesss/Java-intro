package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter 3 numbers divided by space: height 'n', weight 'm' and number 'k'");
        try {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            System.out.println("Enter count of players");
            int count;
            while (true) {
                count = in.nextInt();
                if (2 <= count || count <= 4) {
                    break;
                }
                System.out.println("Invalid input");
                System.out.println("Repeat");
            }
            List<Player> players = new ArrayList<>();
            for (int i = 1; i <= count; i++) {
                System.out.println("Enter name of player " + i);
                System.out.println("HumanPlayer - Human");
                System.out.println("RandomPlayer - Random bot");
                System.out.println("SequentialPlayer - Sequential bot");
                while (true) {
                    String name = in.next();
                    if (name.equals("HumanPlayer")) {
                        players.add(new HumanPlayer(new Scanner(System.in)));
                        break;
                    } else if (name.equals("RandomPlayer")) {
                        players.add(new RandomPlayer());
                        break;
                    } else if (name.equals("SequentialPlayer")) {
                        players.add(new SequentialPlayer());
                        break;
                    }
                    System.out.println("Invalid input. Repeat name of player " + i);
                }
            }
            System.out.println("Game starts!");
            final int result = new MultiplayerGame(
                    new NoughtsAndCrossesBoard(n, m, k),
                    players
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
        } catch (Exception e) {
            System.out.println("Ошибка во время выполения программы");
            System.out.println(e);
        }
    }
}
