import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Beak15863 {
    private static class CCTV{
        public int x, y, num;
        public static int[][][] cctvDir = new int[6][][];

        static {
            cctvDir[1] = new int[][]{
                    {UP},
                    {DOWN},
                    {LEFT},
                    {RIGHT}
            };
            cctvDir[2] = new int[][]{
                    {UP, DOWN},
                    {LEFT, RIGHT}
            };
            cctvDir[3] = new int[][]{
                    {UP, RIGHT},
                    {RIGHT, DOWN},
                    {DOWN, LEFT},
                    {LEFT, UP}
            };
            cctvDir[4] = new int[][]{
                    {UP, RIGHT, DOWN},
                    {RIGHT, DOWN, LEFT},
                    {DOWN, LEFT, UP},
                    {LEFT, UP, RIGHT}
            };
            cctvDir[5] = new int[][]{
                    {UP, RIGHT, DOWN, LEFT}
            };
        }

        public CCTV(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
    private static ArrayList<CCTV> CCTVs = new ArrayList<>();
    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    private static int N, M, answer = Integer.MAX_VALUE;
    public static int[][] originMap;

    private static void observe(int x, int y, int dir, int[][] map){
        switch (dir){
            case UP:
                for (int i = x; i >= 0; i--) {
                    if (originMap[i][y] == 6) break;
                    map[i][y] = -1;
                }
                break;
            case DOWN:
                for (int i = x; i < N; i++) {
                    if (originMap[i][y] == 6) break;
                    map[i][y] = -1;
                }
                break;
            case LEFT:
                for (int i = y; i >= 0; i--) {
                    if (originMap[x][i] == 6) break;
                    map[x][i] = -1;
                }
                break;
            case RIGHT:
                for (int i = y; i < M; i++) {
                    if (originMap[x][i] == 6) break;
                    map[x][i] = -1;
                }
                break;
        }
    }

    private static int countEmpty(int[][] map){
        int count = 0;
        for (int[] ints : map)
            for (int i : ints)
                if (i == 0)
                    count++;
        return count;
    }

    private static void dfs(int[][] map, int cctvIdx){
        if (cctvIdx == CCTVs.size()){
            answer = Math.min(answer, countEmpty(map));
            return;
        }

        CCTV cctv = CCTVs.get(cctvIdx);

        for (int i = 0; i < CCTV.cctvDir[cctv.num].length; i++) {
            int[][] copyMap = new int[N][M];

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    copyMap[j][k] = map[j][k];
                }
            }

            for (int dir : CCTV.cctvDir[cctv.num][i]) {
                observe(cctv.x, cctv.y, dir, copyMap);
            }

            dfs(copyMap, cctvIdx + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        originMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num > 0 && num < 6) {
                    CCTVs.add(new CCTV(i, j, num));
                }
                originMap[i][j] = num;
            }
        }

        dfs(originMap,0);

        System.out.println(answer);

    }
}
