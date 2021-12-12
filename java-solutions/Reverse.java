public class Reverse {

    public static void main(final String[] args) {
        MyScanner reader = new MyScanner(System.in);
        MyScanner scanner = null;
        int limit = 1000000;
        int inputSize = 0;
        int numsSize;
        String line;
        String num;
        String[] input = new String[limit];
        String[] nums = new String[limit];
        while (true) {
            line = reader.nextLine();
            if (line == null) {
                break;
            }
            input[inputSize] = line;
            inputSize++;
        }
        reader.close();
        inputSize--;
        while (inputSize >= 0) {
            scanner = new MyScanner(input[inputSize]);
            numsSize = 0;
            while (true) {
                num = scanner.next();
                if (num == null) {
                    break;
                }
                nums[numsSize] = num;
                numsSize++;
            }
            numsSize--;
            while (numsSize >= 0) {
                System.out.print(nums[numsSize] + " ");
                numsSize--;
            }
            System.out.println();
            inputSize--;
        }
        scanner.close();
    }

}
// 2500ms -> 750ms -> 500ms (BufReader) -> 546ms (modern alg)
// 400ms recurse

// 18s -> 15s -> 13s (recurs)