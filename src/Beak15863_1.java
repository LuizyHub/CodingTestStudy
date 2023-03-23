import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Beak15863_1 {
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
                    {UPDOWN},
                    {LEFTRIGHT}
            };
            cctvDir[3] = new int[][]{
                    {UP, RIGHT},
                    {RIGHT, DOWN},
                    {DOWN, LEFT},
                    {LEFT, UP}
            };
            cctvDir[4] = new int[][]{
                    {UPDOWN, RIGHT},
                    {LEFTRIGHT, DOWN},
                    {UPDOWN, LEFT},
                    {LEFTRIGHT, UP}
            };
            cctvDir[5] = new int[][]{
                    {UPDOWN, LEFTRIGHT}
            };
        }

        public CCTV(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
    private static ArrayList<CCTV> CCTVs = new ArrayList<>();
    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, UPDOWN = 4, LEFTRIGHT = 5;
    private static int N, M, answer = Integer.MAX_VALUE;
    public static int[][] originMap;
    private static TreeSet<Integer>[] wallM, wallN;

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
            case UPDOWN:

                break;
            case LEFTRIGHT:

                break;
        }
    }
    private static long observe(int x, int y, int dir, long map){
        int from, to;
        switch (dir){
            case UP:
                from = wallM[y].floor(x)+1;
                to = x + 1;
                for (int i = from*M; i < to*M; i += M)
                    map |= (1<<(i+y));
                break;
            case DOWN:
                from = x;
                to = wallM[y].ceiling(x);
                for (int i = from*M; i < to*M; i += M)
                    map |= (1<<(i+y));
                break;
            case LEFT:
                from = wallN[x].floor(y)+1;
                to = y + 1;
                map |= (( (1<<(to-from)) - 1)<<(x*M + from));
                break;
            case RIGHT:
                from = y;
                to = wallN[x].ceiling(y);
                map |= (( (1<<(to-from)) - 1)<<(x*M + from));
                break;
            case UPDOWN:
                from = wallM[y].floor(x)+1;
                to = wallM[y].ceiling(x);
                for (int i = from*M; i < to*M; i += M)
                    map |= (1<<(i+y));
                break;
            case LEFTRIGHT:
                from = wallN[x].floor(y)+1;
                to = wallN[x].ceiling(y);
                map |= (( (1<<(to-from)) - 1)<<(x*M + from));
                break;
        }
        printLongBit(map);
        return map;
    }

    private static int countEmpy(int[][] map){
        int count = 0;
        for (int[] ints : map) {
            for (int anInt : ints) {
                if (anInt == 0)
                    count++;
            }
        }
        return count;
    }

    private static void dfs(int[][] map, int cctvIdx){
        if (cctvIdx == CCTVs.size()){
            answer = Math.min(answer, countEmpy(map));
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

    private static void dfs(long map, int cctvIdx){
        if (cctvIdx == CCTVs.size()){
            printLongBit(map);
            answer = Math.min(answer, M*N - Long.bitCount(map));
            return;
        }

        CCTV cctv = CCTVs.get(cctvIdx);
        for (int i = 0; i < CCTV.cctvDir[cctv.num].length; i++) {
            long copyMap = map;
            for (int dir : CCTV.cctvDir[cctv.num][i]) {
                copyMap = observe(cctv.x, cctv.y, dir, copyMap);
            }
            dfs(copyMap, cctvIdx + 1);
        }
    }
    private static void initTree(TreeSet<Integer>[] tree){
        int size = tree.length;
        for (int i = 0; i < size; i++) {
            TreeSet<Integer> newTree = new TreeSet<>();
            newTree.add(-1);
            newTree.add(size == M ? N : M);
            tree[i] = newTree;
        }
    }

    private static long printLongBit(long l){
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(((l>>(i*M+j))&1));
            }
            System.out.println();
        }
        return l;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        originMap = new int[N][M];
        wallM = new TreeSet[M];
        initTree(wallM);
        wallN = new TreeSet[N];
        initTree(wallN);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 6){
                    wallN[i].add(j);
                    wallM[j].add(i);
                } else if (num > 0) {
                    CCTVs.add(new CCTV(i, j, num));
                }
                originMap[i][j] = num;
//                longmap |= (1<<(i*M + j));
            }
        }
//        System.out.println("M");
//        for (TreeSet<Integer> treeSet : wallM) {
//            System.out.println(treeSet);
//        }
//        System.out.println("N");
//        for (TreeSet<Integer> integers : wallN) {
//            System.out.println(integers);
//        }

        dfs(0,0);

//        dfs(originMap,0);

        System.out.println(answer);

    }
}
/*
5 6
0 0 0 0 0 0
0 0 6 0 0 0
0 0 0 0 6 0
6 0 5 0 0 0
0 0 0 0 0 0
 */
