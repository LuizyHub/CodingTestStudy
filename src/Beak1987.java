import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak1987 {
    private static int N, M, max = 0;
    private static char[][] map;
    private static int[] dx = {1, 0, 0, -1};
    private static int[] dy = {0, 1, -1, 0};
    private static void dfs(int x, int y, int count, int key){
        max = Math.max(max, count);
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            char c = map[nx][ny];
            if ((key & (1<<(c-'A'))) == 0)
                dfs(nx,ny,count + 1, (key | (1<<(c-'A'))));
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        dfs(0,0, 1, (1<<(map[0][0]-'A')));

        System.out.println(max);
    }
}