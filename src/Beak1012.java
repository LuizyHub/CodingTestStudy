import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Beak1012 {

    private static int[] px = new int[]{1,0,-1,0};
    private static int[] py = new int[]{0,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] cab = new int[M][N]; //0 none, 1 exist, 2 visited
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                cab[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
            }
            int count = 0;
            for (int i = 0; i < cab.length; i++) {
                for (int j = 0; j < cab[i].length; j++) {
                    if (cab[i][j] == 1){
                        count++;
                        ArrayDeque<Point> deque = new ArrayDeque<>();
                        deque.addLast(new Point(i,j));
                        cab[i][j] = 2;
                        while (!deque.isEmpty()){
                            Point p = deque.pollFirst();
                            for (int k = 0; k < 4; k++) {
                                int x = p.x+px[k];
                                int y = p.y+py[k];
                                if (0 <= x && x < M && 0 <= y && y < N && cab[x][y] == 1){
                                    cab[x][y] = 2;
                                    deque.addLast(new Point(x, y));
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(count);
        }
    }
}
