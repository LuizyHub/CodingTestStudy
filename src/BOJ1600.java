import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1600 {
    private static int[] horseMoveX = new int[] {-1, -2, -2, -1, 1, 2, 2, 1};
    private static int[] horseMoveY = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};
    private static int[] monkeyMoveX = new int[] {1 ,-1 ,0, 0};
    private static int[] monkeyMoveY = new int[] {0, 0, 1 ,-1};
    private static int K, W, H;
    private static boolean[][] map;
    private static int[][][] count;

    private static boolean isRange(int x, int y) {
        return (x >= 0 && y >= 0 && x < H && y < W);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        if (H == 1 && W == 1) {
            System.out.print(0);
            return;
        }
        map = new boolean[H][W];
        count = new int[K + 1][H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < W; j++) {
                if (st.nextToken().equals("1")) {
                    map[i][j] = true;
                }
            }
        }

        ArrayDeque<Node> deque = new ArrayDeque<>();

        deque.add(new Node(0, 0, 0));
        count[0][0][0] = 1;

        while (!deque.isEmpty()) {
            Node cur = deque.pollFirst();

            if (cur.k < K){
                for (int i = 0; i < 8; i++) {
                    int nx = cur.x + horseMoveX[i];
                    int ny = cur.y + horseMoveY[i];

                    if (!isRange(nx, ny)) continue;

                    if (map[nx][ny]) continue;

                    if (count[cur.k + 1][nx][ny] != 0) continue;

                    if (nx == H - 1 && ny == W - 1) {
                        System.out.print(count[cur.k][cur.x][cur.y]);
                        return;
                    }

                    count[cur.k + 1][nx][ny] = count[cur.k][cur.x][cur.y] + 1;

                    deque.addLast(new Node(cur.k + 1, nx, ny));

                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + monkeyMoveX[i];
                int ny = cur.y + monkeyMoveY[i];

                if (!isRange(nx, ny)) continue;

                if (map[nx][ny]) continue;

                if (count[cur.k][nx][ny] != 0) continue;

                if (nx == H - 1 && ny == W - 1) {
                    System.out.print(count[cur.k][cur.x][cur.y]);
                    return;
                }

                count[cur.k][nx][ny] = count[cur.k][cur.x][cur.y] + 1;

                deque.addLast(new Node(cur.k, nx, ny));
            }
        }

        System.out.print(-1);
    }

    private static class Node {
        int k, x, y;

        public Node(int k, int x, int y) {
            this.k = k;
            this.x = x;
            this.y = y;
        }
    }
}