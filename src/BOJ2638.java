import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ2638 {
    private static int[] dx = new int[] {1, -1, 0, 0}, dy = new int[] {0, 0, 1, -1};
    private static int N, M, ans = -1;
    private static boolean[][] map;
    private static int[][] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (st.nextToken().equals("1")) {
                    map[i][j] = true;
                }
            }
        }

        ArrayDeque<P> cheese = new ArrayDeque<>();

        do {
            for (P p : cheese) {
                map[p.x][p.y] = false;
            }
            cheese.clear();

            ArrayDeque<P> deque = new ArrayDeque<>();
            deque.add(new P(0, 0));

            count = new int[N][M];

            while (!deque.isEmpty()) {
                P cur = deque.pollFirst();

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                    if (!map[nx][ny] && count[nx][ny] == 0) {
                        deque.addLast(new P(nx, ny));
                        count[nx][ny] = -1;
                    }

                    if (map[nx][ny]) {
                        count[nx][ny]++;
                        if (count[nx][ny] == 2) {
                            cheese.add(new P(nx, ny));
                        }
                    }

                }

            }
            ans++;
        }while (!cheese.isEmpty());

        System.out.print(ans);

    }
    private static class P {
        int x, y;

        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
