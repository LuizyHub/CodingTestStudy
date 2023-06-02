import java.io.IOException;
import java.util.Arrays;

public class BOJ15651 {
    private static int N, M;
    private static char[] arr;
    private static StringBuilder sb;

    private static void backTracking(int count) {
        if (count == M) {
            sb.append(arr);
            return;
        }

        for (int i = 1; i <= N; i++) {
            arr[count<<1] = (char) (i + '0');
            backTracking(count + 1);
        }
    }
    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        N = System.in.read() - '0';
        System.in.read();
        M = System.in.read() - '0';
        arr = new char[M<<1];

        Arrays.fill(arr, ' ');

        arr[(M<<1) - 1] = '\n';

        backTracking(0);

        System.out.print(sb);
    }
}