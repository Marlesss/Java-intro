import java.util.Scanner;

public class ProblemI {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x, y, h;
        int xl = 0, xr = 0, yl = 0, yr = 0;
        for (int i = 0; i < n; i++) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            h = scanner.nextInt();
            if (i == 0) {
                xl = x - h;
                xr = x + h;
                yl = y - h;
                yr = y + h;
            } else {
                if (x - h < xl) {
                    xl = x - h;
                }
                if (x + h > xr) {
                    xr = x + h;
                }
                if (y - h < yl) {
                    yl = y - h;
                }
                if (y + h > yr) {
                    yr = y + h;
                }
            }
        }
        if (xr - xl > yr - yl) {
            h = ((xr - xl) + 1) / 2;
        } else {
            h = ((yr - yl) + 1) / 2;
        }
        x = (xl + xr) / 2;
        y = (yl + yr) / 2;
        System.out.println(x + " " + y + " " + h);
    }
}
