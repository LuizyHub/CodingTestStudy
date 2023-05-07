import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class BOJ10875 {
    private static final long MAXL = 100_000_000;
    private static class Point {
        private static HashMap<Long, Point> map;
        static {
            map = new HashMap<>();
        }
        public final int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public static Point getPoint(int x, int y) {
            if (!(-L <= x && x <= L && -L <= y && y<= L)) return null;
            Point p = map.get(x * MAXL + y);
            if (p == null) {
                p = new Point(x,y);
                map.put(x * MAXL + y, p);
            }
            else
                p = null;
            return p;
        }
    }
    private static int[] dx = {0, 1, -1, 0};
    private static int[] dy = {1, 0, 0, -1};
    private static int direction = 0; // 0 : Right, 1 : Down, 2 : Left, 3 : Up
    private static int L, N, t, time = 0;
    private static char dir;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
        }

    }
}
