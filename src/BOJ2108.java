import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2108 {
    private static int N, mean, median, mode, range, sum = 0;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            sum += arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        if (sum >= 0)
            mean = sum / N + (((sum % N)<<1) >= N ? 1 : 0);
        else
            mean = sum / N + (((-(sum % N))<<1) >= N ? -1 : 0);

        sb.append(mean).append('\n');

        median = arr[N>>1];
        sb.append(median).append('\n');

        boolean isSecond = false;
        int count = 1, maxCount = 0, before = 4001;
        for (int i : arr) {
            if (before == i) {
                count++;
            }
            else {
                count = 1;
            }

            if (count == maxCount) {
                if (mode != i && !isSecond) {
                    mode = i;
                    isSecond = true;
                }
            }
            else if (count > maxCount) {
                maxCount = count;
                isSecond = false;
                mode = i;
            }

            before = i;
        }

        sb.append(mode).append('\n');

        range = arr[N - 1] - arr[0];

        sb.append(range).append('\n');

        System.out.print(sb);
    }
}
