import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak9465 {
    private static int N, M;
    private static int[][] sticks = new int[2][];

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (N-- != 0){
            M = Integer.parseInt(br.readLine());
            if (M == 1){
                sb.append(Math.max(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()))).append('\n');
                continue;
            }
            sticks[0] = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                sticks[0][i] = Integer.parseInt(st.nextToken());
            }
            sticks[1] = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                sticks[1][i] = Integer.parseInt(st.nextToken());
            }
            sticks[0][1] += sticks[1][0];
            sticks[1][1] += sticks[0][0];
            for (int i = 2; i < M; i++) {
                sticks[0][i] += Math.max(sticks[1][i-1], sticks[1][i-2]);
                sticks[1][i] += Math.max(sticks[0][i-1], sticks[0][i-2]);
            }
            sb.append(Math.max(sticks[0][M-1], sticks[1][M-1])).append('\n');
        }

        System.out.println(sb);
    }
}