import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Beak16234 {
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int N, L, R, ans = 0;
    private static int[][] map;

    private static boolean move(){
        boolean isChanged = false;
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N ; j++) {
                if (visited[i][j]) continue;
                ArrayList<Integer> inBorder = new ArrayList<>();
                inBorder.add(i*N + j);
                visited[i][j] = true;
                int idx = 0;
                int peopleSum = 0;

                while (idx < inBorder.size()){
                    int cur = inBorder.get(idx);
                    int x = cur/N;
                    int y = cur%N;
                    int people = map[x][y];
                    peopleSum += people;

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                        if (visited[nx][ny]) continue;

                        int dif = Math.abs(people - map[nx][ny]);
                        if (L <= dif && dif <= R) {
                            visited[nx][ny] = true;
                            inBorder.add(nx*N + ny);
                        }

                    }

                    idx++;
                }

                if (idx > 1){
                    isChanged = true;
                    peopleSum /= idx;
                    for (int id : inBorder)
                        map[id/N][id%N] = peopleSum;
                }

            }
        }

        return isChanged;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (move())
            ans++;

        System.out.println(ans);
    }
}
