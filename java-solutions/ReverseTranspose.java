import java.util.Arrays;

public class ReverseTranspose {
    public static void main(final String[] args) {
        MyScanner sc = new MyScanner(System.in);
        String input;
        int k = 1000;
        String[] order = new String[k];
        int orderSize = 0;
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            if (orderSize >= order.length) {
                order = Arrays.copyOf(order, order.length + k);
            }

            order[orderSize] = input;
            orderSize++;
        }
        sc.close();
        int maxLen = 0;
        int[][] nums = new int[orderSize][k];
        int[] numsCount = new int[orderSize];
        for (int i = 0; i < orderSize; i++) {
            MyScanner myScanner = new MyScanner(order[i]);
            int j = 0;
            while (myScanner.hasNextInt()) {
                if (j >= nums[i].length) {
                    nums[i] = Arrays.copyOf(nums[i], nums[i].length + k);
                }
                nums[i][j++] = myScanner.nextInt();
            }
            numsCount[i] = j;
            if (j > maxLen) {
                maxLen = j;
            }
        }
        for (int x = 0; x < maxLen; x++) {
            for (int i = 0; i < orderSize; i++) {
                if (numsCount[i] > x) {
                    System.out.print(nums[i][x] + " ");
                }
            }
            System.out.println();
        }
    }
}
// 2100ms -> 1200ms -> 1500ms(with Base)