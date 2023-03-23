import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Beak11404 {
    private static final int MAX = 10_000_000;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] Floyd = new int[N][N];
        for (int[] ints : Floyd) {
            Arrays.fill(ints,MAX);
        }
        for (int i = 0; i < N; i++) {
            Floyd[i][i] = 0;
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            Floyd[x][y] = Math.min(Integer.parseInt(st.nextToken()),Floyd[x][y]);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (Floyd[j][i] + Floyd[i][k] < Floyd[j][k])
                        Floyd[j][k] = Floyd[j][i] + Floyd[i][k];
                }
            }
        }
        for (int[] ints : Floyd) {
            for (int in : ints) {
                if (in == MAX)
                    sb.append(0).append(' ');
                else
                    sb.append(in).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
