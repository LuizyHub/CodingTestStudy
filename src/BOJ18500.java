import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ18500 {
    private static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
    private static int[] dr = {1, 0, 0, -1};
    private static int[] dc = {0, 1, -1, 0};
    private static int R, C, N;
    private static char[][] map;

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (char[] chars : map) {
            sb.append(chars).append('\n');
        }
        System.out.print(sb);
    }

    private static boolean isRange (Point p) {
        return !(p.r < 0 || p.c < 0 || p.r >= R || p.c >= C);
    }

    private static void drop(HashSet<Point> cluster) {
        int[] minRow = new int[C];

        Arrays.fill(minRow, -1);

        int minDrop = Integer.MAX_VALUE;

        loop :
        for (Point p : cluster) {
            int row = p.r;

            int height = 0;

            while (++row < R) {
                if (height >= minDrop) continue loop;
                if (map[row][p.c] == 'x' && !cluster.contains(new Point(row,p.c))) break;
                height++;
            }

            minDrop = Math.min(height, minDrop);
        }

        if (minDrop == Integer.MAX_VALUE) return;

        for (Point p : cluster) {
            map[p.r][p.c] = '.';
        }

        for (Point p : cluster) {
            map[p.r + minDrop][p.c] = 'x';
        }

    }

    private static void remove(int r, int c) {
        boolean[][] visited = new boolean[R][C];
        loop :
        for (int i = 0; i < 4; i++) {
            Point p = new Point(r + dr[i], c + dc[i]);

            if (!isRange(p)) continue;
            if (map[p.r][p.c] == '.' || visited[p.r][p.c] || p.r == (R - 1)) continue;

            ArrayDeque<Point> deque = new ArrayDeque<>();
            HashSet<Point> cluster = new HashSet<>();
//            ArrayList<Point> cluster = new ArrayList<>();

            visited[p.r][p.c] = true;
            deque.addLast(p);
            cluster.add(p);

            while (!deque.isEmpty()) {
                p = deque.pollFirst();

                for (int j = 0; j < 4; j++) {
                    Point np = new Point(p.r + dr[j], p.c + dc[j]);
                    if (!isRange(np)) continue;
                    if (visited[np.r][np.c] || map[np.r][np.c] == '.') continue;
                    if (np.r == R - 1) continue loop;

                    visited[np.r][np.c] = true;
                    deque.addLast(np);
                    cluster.add(np);
                }
            }
            drop(cluster);
//            System.out.println(cluster);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        boolean isLeft = true;
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());
            int row = R - height;
            int col = -1;

            if (isLeft)
                col = new String(map[row]).indexOf('x');
            else
                col = new String(map[row]).lastIndexOf('x');
            isLeft = !isLeft;

            if (col == -1) continue;

            map[row][col] = '.';

            remove(row, col);

//            print();

        }
        print();
    }
}
