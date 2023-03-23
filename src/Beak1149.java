import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] home = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                home[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i < N; i++) {
            home[i][0] += Math.min(home[i-1][1],home[i-1][2]);
            home[i][1] += Math.min(home[i-1][0],home[i-1][2]);
            home[i][2] += Math.min(home[i-1][0],home[i-1][1]);
        }
        int ans = Math.min(home[N-1][0], Math.min(home[N-1][1],home[N-1][2]));
        System.out.println(ans);
    }
}
