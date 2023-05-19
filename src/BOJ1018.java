import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1018 {
    private static int N, M, count = 0, min = Integer.MAX_VALUE;
    private static int[][] periodSum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        periodSum = new int[N + 1][M + 1];

        boolean tag;

        for (int i = 1; i <= N; i++) {
            periodSum[i][0] = periodSum[i - 1][M];
            String s = br.readLine();
            if ((i & 1) == 1)
                tag = false;
            else
                tag = true;

            for (int j = 0; j < M; j++) {
                if (tag) {
                    if (s.charAt(j) == 'W')
                        count++;
                }
                else {
                    if (s.charAt(j) == 'B')
                        count++;
                }

                periodSum[i][j + 1] = count;

                tag = !tag;
            }
        }

        for (int i = 8; i <= N; i++) {

            for (int j = 8; j <= M; j++) {
                int sum = 0;
                for (int k = 0; k < 8; k++) {
                    sum += (periodSum[i - k][j] - periodSum[i - k][j - 8]);
                }
                sum = Math.min((64 - sum), sum);
                min = Math.min(min, sum);
            }

        }

        System.out.print(min);
    }
}
