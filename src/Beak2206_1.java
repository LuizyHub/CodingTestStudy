import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Beak2206_1 {
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = Integer.MAX_VALUE;
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] map2 = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                int num = s.charAt(j) - '0';
                if (num == 1)
                    num = Integer.MAX_VALUE;
                map[i][j] = num;
                map2[i][j] = num;
            }
        }


        map[0][0] = 1;

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);
        while (!deque.isEmpty()){
            int cur = deque.pollFirst();
            if (cur == N*M-1){
                ans = Math.min(ans, map[N-1][M-1]);
            }
            int x = cur / M;
            int y = cur % M;

            for (int k = 0; k < 4; k++) {
                int nextX = x + dx[k];
                int nextY = y + dy[k];
                if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M && map[nextX][nextY] == 0){
                    map[nextX][nextY] = map[x][y] + 1;
                    deque.addLast(nextX*M + nextY);
                }
            }
        }


        map2[N-1][M-1] = -1;
        deque = new ArrayDeque<>();
        deque.addLast(N*M-1);
        while (!deque.isEmpty()){
            int cur = deque.pollFirst();
            int x = cur / M;
            int y = cur % M;

            for (int k = 0; k < 4; k++) {
                int nextX = x + dx[k];
                int nextY = y + dy[k];
                if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M && map2[nextX][nextY] == 0){
                    map2[nextX][nextY] = map2[x][y] - 1;
                    deque.addLast(nextX*M + nextY);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == Integer.MAX_VALUE){
                    int positiveValue=Integer.MAX_VALUE;
                    int negativeValue=Integer.MIN_VALUE;

                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (0 <= x && x < N && 0 <= y && y < M ){
                            if (map[x][y] > 0){
                                positiveValue = Math.min(positiveValue, map[x][y]);
                            }
                            if (map2[x][y] < 0){
                                negativeValue = Math.max(negativeValue, map2[x][y]);
                            }
                        }
                    }
                    if (positiveValue != Integer.MAX_VALUE && negativeValue != Integer.MIN_VALUE){
                        ans = Math.min(ans, positiveValue - negativeValue + 1);
                    }
                }
            }
        }

        for (int[] ints : map) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();
        for (int[] ints : map2) {
            System.out.println(Arrays.toString(ints));
        }

        if (ans == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(ans);
    }
}
