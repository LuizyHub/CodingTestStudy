import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Beak2206 {
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = Integer.MAX_VALUE;
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().chars().map(in -> in == '0' ? 0 : -1).toArray();
        }


        for (int i = 0; i < N; i++) {
            loop:
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1){
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (0 <= x && x < N && 0 <= y && y < M)
                            if (map[x][y] == 0){
                                count++;
                            }
                    } // check if it can be route
                    if (count < 2)
                        continue loop;
                    System.out.println("i = " + i);
                    System.out.println("j = " + j);
                    System.out.println();
                    int[][] simulation = new int[N][];
                    for (int k = 0; k < N; k++) {
                        simulation[k] = Arrays.stream(map[k]).toArray();
                    }
                    simulation[0][0] = 1;
                    simulation[i][j] = 0;

                    ArrayDeque<Integer> deque = new ArrayDeque<>();
                    deque.addLast(0);
                    while (!deque.isEmpty()){
                        int cur = deque.pollFirst();
                        if (cur == N*M-1){
                            ans = Math.min(ans, simulation[N-1][M-1]);
                            continue loop;
                        }
                        int x = cur / M;
                        int y = cur % M;

                        for (int k = 0; k < 4; k++) {
                            int nextX = x + dx[k];
                            int nextY = y + dy[k];
                            if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M && simulation[nextX][nextY] == 0){
                                simulation[nextX][nextY] = simulation[x][y] + 1;
                                deque.addLast(nextX*M + nextY);
                            }
                        }
                    }
                }
            }
        }

        map[0][0] = 1;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);
        while (!deque.isEmpty()) {
            int cur = deque.pollFirst();
            if (cur == N * M - 1) {
                ans = Math.min(ans, map[N - 1][M - 1]);
                break;
            }
            int x = cur / M;
            int y = cur % M;
            for (int k = 0; k < 4; k++) {
                int nextX = x + dx[k];
                int nextY = y + dy[k];
                if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M && map[nextX][nextY] == 0) {
                    map[nextX][nextY] = map[x][y] + 1;
                    deque.addLast(nextX * M + nextY);
                }
            }
        }

        if (ans == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(ans);
    }
}
