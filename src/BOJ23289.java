import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ23289 {
    private static final int RIGHT = 0, LEFT = 1, UP = 2, DOWN = 3, Target = 5;

    private static final int[] dx = new int[]{0, 0, -1, 1};
    private static final int[] dy = new int[]{1, -1, 0, 0};
    private static int R, C, K, W, count = 0;
    private static Block[][] temperateMap;
    private static ArrayList<Heater> heaters;
    private static ArrayList<Target> targets;
    private static boolean[][][] wallMap;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static void initWall() throws IOException {
        wallMap = new boolean[R + 2][C + 2][4];

        W = Integer.parseInt(br.readLine());

        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            if (dir == 0) {
                wallMap[x][y][UP] = true;
                wallMap[x - 1][y][DOWN] = true;
            }
            else if (dir == 1){
                wallMap[x][y][RIGHT] = true;
                wallMap[x][y + 1][LEFT] = true;
            }
        }
    }

    private static class Target {
        final int x, y;
        final Block block;

        public Target(int x, int y) {
            this.x = x;
            this.y = y;
            block = temperateMap[x][y];
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Target{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append(", block=").append(block.temperate);
            sb.append('}');
            return sb.toString();
        }
    }

    private static class Heater {
        final int x, y, dir;

        public Heater(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    private static class Block {
        final int x, y;
        int temperate = 0;
        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }

        static boolean isRange(int x, int y) {
            return (0 < x && x <= R && 0 < y && y <= C);
        }
    }

    private static void initBlock() {
        temperateMap = new Block[R + 1][C + 1];

        for (int i = 0; i < temperateMap.length; i++) {
            for (int j = 0; j < temperateMap[i].length; j++) {
                temperateMap[i][j] = new Block(i, j);
            }
        }
    }

    private static int[][] changes;

    private static void initChanges() {
        changes = new int[R + 1][C + 1];
    }

    private static void fetchChanges() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                temperateMap[i][j].temperate += changes[i][j];
                changes[i][j] = 0;
            }
        }
    }

    private static void print() {
        System.out.println();
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                System.out.print(temperateMap[i][j].temperate + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        initBlock();

        heaters = new ArrayList<>();
        targets = new ArrayList<>();
        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                String dir = st.nextToken();
                if (dir.equals("0")) continue;

                if (dir.equals("5")) {
                    targets.add(new Target(i, j));
                }
                else {
                    heaters.add(new Heater(i, j, Integer.parseInt(dir) - 1));
                }
            }
        }

        initWall();

        initChanges();

        loop :
        while (count++ < 100) {
            runHeaters();

            moderateTemperate();

            spread();

            for (Target target : targets) {
                if (target.block.temperate < K) continue loop;
            }

            break;
        }

        System.out.print(count);
    }

    private static void moderateTemperate() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                int temperate = temperateMap[i][j].temperate;

                for (int k = 0; k < 4; k += 3) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (!Block.isRange(nx, ny) || wallMap[i][j][k]) continue;

                    int fetch = (temperate - temperateMap[nx][ny].temperate) / 4;
                    changes[i][j] -= fetch;
                    changes[nx][ny] += fetch;
                }
            }
        }
        
        fetchChanges();
    }

    private static void spread() {
        for (int i = 1; i <= R; i++) {
            if (temperateMap[i][1].temperate != 0)
                temperateMap[i][1].temperate--;
            if (temperateMap[i][C].temperate != 0)
                temperateMap[i][C].temperate--;
        }

        for (int j = 2; j < C; j++) {
            if (temperateMap[1][j].temperate != 0)
                temperateMap[1][j].temperate--;
            if (temperateMap[R][j].temperate != 0)
                temperateMap[R][j].temperate--;
        }
    }
    private static void runHeaters() {
        for (Heater heater : heaters) {
            int dir = heater.dir;
            if (wallMap[heater.x][heater.y][dir]) continue;
            int nx = heater.x + dx[dir];
            int ny = heater.y + dy[dir];

            if (!Block.isRange(nx, ny)) continue;

            boolean[][] visit = new boolean[R + 1][C + 1];
            ArrayList<Point> wind = new ArrayList<>();
            wind.add(new Point(nx, ny));
            visit[nx][ny] = true;

            for (int i = 5; i > 0; i--) {
                ArrayList<Point> next = new ArrayList<>();
                for (Point p : wind) {
                    temperateMap[p.x][p.y].temperate += i;
                    if (i == 1) continue;
                    for (int j = 0; j < 3; j++) {
                        if (wallMap[p.x][p.y][dirs[dir][j]]) continue;
                        nx = p.x + dirx[dir][j];
                        ny = p.y + diry[dir][j];
                        if (!Block.isRange(nx, ny) || wallMap[nx][ny][getOpposite(dir)] || visit[nx][ny]) continue;

                        visit[nx][ny] = true;
                        next.add(new Point(nx, ny));
                    }
                }
                wind = next;
            }
        }
    }
    private static int getOpposite(int dir) {
        switch (dir) {
            case RIGHT:
                return LEFT;
            case LEFT:
                return RIGHT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
        }
        return -1;
    }
    private static int[][] dirx;
    private static int[][] diry;
    private static int[][] dirs;

    static {
        dirx = new int[4][3];
        dirx[RIGHT] = new int[]{-1, 0, 1};
        dirx[LEFT] = new int[]{-1, 0, 1};
        dirx[UP] = new int[]{-1, -1, -1};
        dirx[DOWN] = new int[]{1, 1, 1};

        diry = new int[4][3];
        diry[RIGHT] = new int[]{1, 1, 1};
        diry[LEFT] = new int[]{-1, -1, -1};
        diry[UP] = new int[]{-1, 0, 1};
        diry[DOWN] = new int[]{-1, 0, 1};

        dirs = new int[4][3];
        dirs[RIGHT] = new int[]{UP, RIGHT, DOWN};
        dirs[LEFT] = new int[]{UP, LEFT, DOWN};
        dirs[UP] = new int[]{LEFT, UP, RIGHT};
        dirs[DOWN] = new int[]{LEFT, DOWN, RIGHT};
    }
}