import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Beak7569 {

    static class Point{
        public int x;
        public int y;
        public int z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static int[] px = new int[]{1,0,-1,0,0,0};
    private static int[] py = new int[]{0,-1,0,1,0,0};
    private static int[] pz = new int[]{0,0,0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayDeque<Point> deque = new ArrayDeque<>();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        // Z, X ,Y
        int[][][] tomatos = new int[H][M][N]; // -1 none, 0 not, 1 yes
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    tomatos[i][j][k] = tmp;
                    if (tmp == 1)
                        deque.addLast(new Point(j,k,i));
                }
            }
        }
        while (!deque.isEmpty()){
            Point p = deque.pollFirst();
            for (int k = 0; k < 6; k++) {
                int x = p.x+px[k];
                int y = p.y+py[k];
                int z = p.z+pz[k];
                if (0 <= z && z < H && 0 <= x && x < M && 0 <= y && y < N && tomatos[z][x][y] == 0){
                    tomatos[z][x][y] = tomatos[p.z][p.x][p.y] + 1;
                    deque.addLast(new Point(x, y, z));
                }
            }
        }
        int max = 0;
        loop:
        for (int[][] tomato : tomatos) {
            for (int[] ints : tomato) {
                for (int i : ints) {
                    if (i==0){
                        max = 0;
                        break loop;
                    }
                    max = Math.max(max, i);
                }
            }
        }
        System.out.println(max-1);
    }
}
