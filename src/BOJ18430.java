import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ18430 {
    private static class P {
        int x, y;

        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static class Boom {
        public P[] ps = new P[3];
        public int value;

        public Boom(P[] ps, int value) {
            this.ps = ps;
            this.value = value;
        }
    }
    private static int sum = 0;
    private static void backTracking(int idx) {
        max = Math.max(sum, max);

        loop :
        for (int i = idx + 1; i < booms.size(); i++) {
            Boom boom = booms.get(i);

            for (P p : boom.ps) {
                if (visit[p.x][p.y]) continue loop;
            }

            for (P p : boom.ps) {
                visit[p.x][p.y] = true;
            }

            sum += boom.value;

            backTracking(i);

            for (P p : boom.ps) {
                visit[p.x][p.y] = false;
            }

            sum -= boom.value;

        }
    }
    private static ArrayList<Boom> booms;
    private static int N, M, max = 0;
    private static int[][] map;
    private static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        booms = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int periodSum = map[i][0];

            for (int j = 1; j < M; j++) {
                periodSum += map[i][j];

                P[] ps = new P[3];
                ps[0] = new P(i, j-1);
                ps[1] = new P(i, j);

                if (i > 0) {
                    ps[2] = new P(i - 1, j - 1);
                    booms.add(new Boom(ps.clone(), periodSum + map[i-1][j-1] + map[i][j-1]));
                    ps[2] = new P(i - 1, j);
                    booms.add(new Boom(ps.clone(), periodSum + map[i-1][j] + map[i][j]));
                }

                if (i < N - 1) {
                    ps[2] = new P(i + 1, j - 1);
                    booms.add(new Boom(ps.clone(), periodSum + map[i+1][j-1] + map[i][j-1]));
                    ps[2] = new P(i + 1, j);
                    booms.add(new Boom(ps.clone(), periodSum + map[i+1][j] + map[i][j]));
                }

                periodSum -= map[i][j-1];

            }

        }

        backTracking(-1);

        System.out.println(max);

    }
}