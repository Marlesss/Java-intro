import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ProblemM {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        int t = scanner.nextInt();
        while (t > 0) {
            solve();
            t--;
        }
    }

    static void solve() {
        int n = scanner.nextInt();
        int ans = 0;
        Map<Integer, Integer> c = new HashMap<>();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        for (int j = n - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                ans += c.getOrDefault(2 * a[j] - a[i], 0);
            }
            c.putIfAbsent(a[j], 0);
            c.put(a[j], c.get(a[j]) + 1);
        }
        System.out.println(ans);
    }

}