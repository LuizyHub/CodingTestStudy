import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek21609 {
    private static final int EMPTY = -2, BLACK = -1, RAINBOW = 0;
    private static int score = 0;
    private static int N, M;
    private static int[][] map;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        print();
        while (getScore()) {
//            print();
            setGravity();
//            print();
            turn90();
//            print();
            setGravity();
//            print();
        }

        System.out.print(score);
    }

    private static void print() {
        System.out.println("score = " + score);
        for (int[] ints : map) {
            for (int i : ints) {
                System.out.print(i >= 0 ? " " + i  + " ": i + " ");
            }
            System.out.println();
        }
    }

    private static void setGravity() {
        int[][] newMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(newMap[i], -2);
        }

        for (int i = 0; i < N; i++) {
            int floor = N - 1;
            for (int j = N - 1; j >= 0; j--) {
                int block = map[j][i];

                if (block == EMPTY) continue;

                if (block == BLACK) {
                    newMap[j][i] = block;
                    floor = j - 1;
                    continue;
                }

                newMap[floor--][i] = block;
            }
        }

        map = newMap;
    }

    private static void turn90() {
        int[][] newMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMap[i][j] = map[j][N - 1 - i];
            }
        }

        map = newMap;
    }

    private static boolean getScore() {
        int maxCount = 1, maxRainbow = 0;
        boolean[][] visited = new boolean[N][N];
        ArrayList<Point> maxGroup = null;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int block = map[i][j];
                if (block == EMPTY || block == BLACK || block == RAINBOW) continue;

                if (visited[i][j]) continue;
                visited[i][j] = true;

                boolean[][] visitedBFS = new boolean[N][N];
                visitedBFS[i][j] = true;

                ArrayDeque<Point> deque = new ArrayDeque<>();
                ArrayList<Point> group = new ArrayList<>();

                Point point = new Point(i, j);
                deque.addLast(point);
                group.add(point);
                int count = 1;
                int rainbowCount = 0;

                while (!deque.isEmpty()) {
                    Point p = deque.pollFirst();

                    for (int k = 0; k < 4; k++) {
                        int nx = p.x + dx[k];
                        int ny = p.y + dy[k];

                        if (nx < 0 || ny < 0 || nx >= N || ny >=N) continue;

                        int nextBlock = map[nx][ny];
                        if (nextBlock == EMPTY || nextBlock == BLACK) continue;

                        if (!(nextBlock == RAINBOW || nextBlock == block)) continue;

                        if (visitedBFS[nx][ny]) continue;
                        visitedBFS[nx][ny] = true;

                        if (nextBlock == RAINBOW) rainbowCount++;
                        else visited[nx][ny] = true;

                        Point nextPoint = new Point(nx, ny);
                        deque.addLast(nextPoint);
                        group.add(nextPoint);
                        count++;
                    }
                }

                if (count < maxCount) continue;
                if (count == maxCount && rainbowCount < maxRainbow) continue;

                maxCount = count;

                maxRainbow = rainbowCount;

                maxGroup = group;
            }
        }

        if (maxCount < 2) return false;

        for (Point point : maxGroup) {
            map[point.x][point.y] = EMPTY;
        }

        score += (maxCount * maxCount);

        return true;
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}