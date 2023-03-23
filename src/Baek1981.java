import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek1981 {
    public static final int[] dx = {-1, 0, 0, 1};
    public static final int[] dy = {0, 1, -1, 0};
    private static class P{
        public final int x, y;
        private P(int x, int y) {
            this.x = x;
            this.y = y;
        }
        private static P[][] ps;
        static {
            ps = new P[201][201];
        }
        public static P get(int x, int y){
            if (ps[x][y] == null){
                return ps[x][y] = new P(x, y);
            }
            return ps[x][y];
        }
    }
    private static int N;
    private static int[][] map;
    private static HashSet<Integer> find(P minmax){
        int min = minmax.x;
        int max = minmax.y;
        HashSet<Integer> set = new HashSet<>();
        boolean[][] visit = new boolean[N][N];
        ArrayDeque<P> deque = new ArrayDeque<>();
        deque.addLast(P.get(0,0));
        visit[0][0] = true;
        while (!deque.isEmpty()){
            P p = deque.pollFirst();
            if (p.x == N-1 && p.y == N-1)
                return null;

            for (int i = 0; i < 4; i++) {
                int x = p.x + dx[i];
                int y = p.y + dy[i];
                if (x < 0 || y < 0 || x >= N || y >= N) continue;
                if (visit[x][y]) continue;
                if (min <= map[x][y] && map[x][y] <= max){
                    visit[x][y] = true;
                    deque.addLast(P.get(x,y));
                }
                else {
                    set.add(map[x][y]);
                }
            }
        }
        return set;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        P init = P.get(Math.min(map[0][0], map[N-1][N-1]), Math.max(map[0][0], map[N-1][N-1]));
        PriorityQueue<P> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.y - o1.x, o2.y - o2.x));
        HashSet<P> visit = new HashSet<>();
        queue.add(init);
        visit.add(init);

        while (!queue.isEmpty()){
            P p = queue.poll();

            HashSet<Integer> next = find(p);

            if (next == null){
                System.out.println(p.y - p.x);
                break;
            }

            for (Integer i : next) {
                if (i > p.y){
                    P nextp = P.get(p.x, i);
                    if (visit.contains(nextp)) continue;
                    queue.add(nextp);
                    visit.add(nextp);
                }
                else if (i < p.x){
                    P nextp = P.get(i, p.y);
                    if (visit.contains(nextp)) continue;
                    queue.add(nextp);
                    visit.add(nextp);
                }
            }
        }
    }
}