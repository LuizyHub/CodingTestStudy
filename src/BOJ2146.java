import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2146 {
    private static int[] dx = new int[] {1, -1, 0, 0};
    private static int[] dy = new int[] {0, 0, 1, -1};
    private static int N;
    private static boolean[][] map;
    private static int[][] island;
    private static int minBridge = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        island = new int[N][N];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (st.nextToken().equals("1")) {
                    map[i][j] = true;
                }
            }
        }

        int mapIdx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!map[i][j] || island[i][j] != 0) continue;

                island[i][j] = mapIdx;

                ArrayDeque<P> deque = new ArrayDeque<>();
                deque.add(new P(i, j));

                while (!deque.isEmpty()) {
                    P cur = deque.pollFirst();

                    for (int k = 0; k < 4; k++) {
                        int nx = cur.x + dx[k];
                        int ny = cur.y + dy[k];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                        if (!map[nx][ny] || island[nx][ny] != 0) continue;

                        island[nx][ny] = mapIdx;

                        deque.addLast(new P(nx, ny));
                    }
                }

                mapIdx++;

            }
        }

        for (int[] ints : island) {
            System.out.println(Arrays.toString(ints));
        }

        int islandCount = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (island[i][j] != islandCount) continue;

                ArrayDeque<P> deque = new ArrayDeque<>();

                deque.add(new P(i, j));
                boolean[][] visit = new boolean[N][N];
                visit[i][j] = true;

                loop :
                while (!deque.isEmpty()) {
                    P cur = deque.pollFirst();
                    if (-island[cur.x][cur.y] >= minBridge) {
                        break loop;
                    }

                    for (int k = 0; k < 4; k++) {
                        int nx = cur.x + dx[k];
                        int ny = cur.y + dy[k];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                        if (visit[nx][ny]) continue;

                        int num = island[nx][ny];


                        if (num == islandCount) {
                            deque.addFirst(new P(nx, ny));
                            visit[nx][ny] = true;
                            continue;
                        }
                        else if (num > islandCount) {
                            minBridge = Math.min(-island[cur.x][cur.y], minBridge);
                            break loop;
                        }
                        if (num == 0) {
                            island[nx][ny] = Math.min(0, island[cur.x][cur.y]) - 1;
                            if (-island[nx][ny] < minBridge){
                                deque.addLast(new P(nx, ny));
                            }
                        }
                    }
                }

                for (int[] ints : island) {
                    System.out.println(Arrays.toString(ints));
                }

                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < N; l++) {
                        island[k][l] = Math.max(island[k][l], 0);
                    }
                }


                islandCount++;
            }
        }

        System.out.print(minBridge);




    }

    private static class P {
        int x, y;

        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
