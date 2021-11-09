import java.util.Scanner;

public class ProblemA {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a, b, n;
        a = scanner.nextInt();
        b = scanner.nextInt();
        n = scanner.nextInt();
        System.out.println(2 * ((n - b + b - a - 1) / (b - a)) + 1);
    }
}