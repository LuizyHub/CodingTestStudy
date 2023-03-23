import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Beak7576 {

    private static int[] px = new int[]{1,0,-1,0};
    private static int[] py = new int[]{0,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayDeque<Point> deque = new ArrayDeque<>();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] tomatos = new int[M][N]; // -1 none, 0 not, 1 yes
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                tomatos[i][j] = tmp;
                if (tmp == 1)
                    deque.addLast(new Point(i, j));
            }
        }
        while (!deque.isEmpty()){
            Point p = deque.pollFirst();
            for (int k = 0; k < 4; k++) {
                int x = p.x+px[k];
                int y = p.y+py[k];
                if (0 <= x && x < M && 0 <= y && y < N && tomatos[x][y] == 0){
                    tomatos[x][y] = tomatos[p.x][p.y] + 1;
                    deque.addLast(new Point(x, y));
                }
            }
        }
        int max = 0;
        loop:
        for (int[] tomato : tomatos) {
            for (int i : tomato) {
                if (i==0){
                    max = 0;
                    break loop;
                }
                max = Math.max(max, i);
            }
        }
        System.out.println(max-1);
    }
}
