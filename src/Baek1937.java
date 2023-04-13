import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1937 {
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static class P{
        final int x, y;
        private static P[][] ps;
        static {
            ps = new P[N][N];
        }

        private P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static P get(int x, int y){
            if (ps[x][y] != null)
                return ps[x][y];
            return ps[x][y] = new P(x,y);
        }
    }
    private static int N, ans = 0;
    private static int[][] map;
    private static int[][] depth;
    private static int dfs(P p){
        if (depth[p.x][p.y] >= 0)
            return depth[p.x][p.y];

        depth[p.x][p.y] = 0;

        int max = -1;
        for (int i = 0; i < 4; i++) {
            int nx = p.x + dx[i];
            int ny = p.y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (map[nx][ny] > map[p.x][p.y])
                max = Math.max(dfs(P.get(nx, ny)), max);
        }
        depth[p.x][p.y] = max + 1;

        return depth[p.x][p.y];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        depth = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                depth[i][j] = -1;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, dfs(P.get(i,j)));
            }
        }
        System.out.println(ans + 1);
    }
}
