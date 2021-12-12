public class SumFloat {
    public static void main(final String[] args) {
        float ans = 0;
        for (String arg : args) {
            MyScanner scanner = new MyScanner(arg);
            while (scanner.hasNext()) {
                ans += Float.parseFloat(scanner.next());
            }
        }
        System.out.println(ans);
    }
}
// 140ms -> 110ms