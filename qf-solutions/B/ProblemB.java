import java.util.Scanner;

public class ProblemB {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextInt();
        long x = -710 * 25000;
        while (n > 0) {
            System.out.println(x);
            x += 710;
            n--;
        }
    }
}
