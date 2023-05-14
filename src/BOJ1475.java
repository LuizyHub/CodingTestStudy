import java.io.IOException;

public class BOJ1475 {
    public static void main(String[] args) throws IOException {
        int[] count = new int[10];
        int n = System.in.read() -'0';
        while (n >= 0 && n < 10) {
            count[n]++;
            n = System.in.read() -'0';
        }
        count[6] += count[9] + 1;
        count[6] /= 2;
        int max = 0;
        for (int i = 0; i < 9; i++) {
            max = Math.max(count[i], max);
        }
        System.out.print(max);
    }
}
