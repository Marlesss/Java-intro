public class Sum {
    public static void main(final String[] args) {
        int ans = 0;
        for (String arg : args) {
            MyScanner scanner = new MyScanner(arg);
            while (scanner.hasNextInt()) {
                ans += scanner.nextInt();
            }
        }
        System.out.println(ans);
    }
}
// 140ms -> 80ms