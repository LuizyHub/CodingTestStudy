import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ain {

    public static int n;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int[][] matrix;
    public static boolean[][] visited; // 해당 dfs 방문
    public static int[][] res;

    public static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (visited[nx][ny] || matrix[nx][ny] <= matrix[x][y]) continue;

            if (res[nx][ny] == 0) res[nx][ny] = res[x][y] + 1;
            else res[nx][ny] += res[x][y];
            visited[nx][ny] = true;
            dfs(nx, ny);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        res = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (res[i][j] != 0) continue;
                visited = new boolean[n][n];
                dfs(i, j);
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, res[i][j]);
            }
        }
        System.out.println(++answer);
    }
}
