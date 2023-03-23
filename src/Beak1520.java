import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak1520 {
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int N, M, answer = 0;
    private static int[][] map, cnt;

    private static int dfs(int x, int y){
        if (cnt[x][y] >= 0)
            return cnt[x][y];

        cnt[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (map[nx][ny] < map[x][y])
                cnt[x][y] += dfs(nx, ny);
        }

        return cnt[x][y];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cnt = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                cnt[i][j] = -1;
            }
        }

        cnt[N - 1][M - 1] = 1;

        dfs(0,0);

        System.out.println(cnt[0][0]);
    }
}
