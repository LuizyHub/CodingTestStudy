import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Beak4963 {
    private static int[] dx = {1,0,-1,0,1,-1,1,-1};
    private static int[] dy = {0,1,0,-1,1,-1,-1,1};
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boolean[][] island;
        while (true){
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken()); // Max = 50
            int H = Integer.parseInt(st.nextToken()); // Max = 50
            if (H == 0 && W == 0) {
                break;
            }
            island = new boolean[H][W];
            for (boolean[] islands : island) {
                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < islands.length; i++) {
                    if (Integer.parseInt(st.nextToken()) == 1)
                        islands[i] = true;
                }
            }

            int count = 0;

            ArrayDeque<Integer> que = new ArrayDeque<>();

            for (int i = 0; i < island.length; i++) {
                for (int j = 0; j < island[i].length; j++) {
                    if (island[i][j]){
                        count++;
                        island[i][j] = false;
                        que.addLast(i*W + j);

                        while (!que.isEmpty()){
                            int cur = que.pollFirst();
                            int curX = cur/W; // H
                            int curY = cur%W; // W

                            for (int k = 0; k < 8; k++) {
                                int nextX = curX + dx[k];
                                int nextY = curY + dy[k];
                                if (0 <= nextX && nextX < H && 0 <= nextY && nextY < W && island[nextX][nextY]){
                                    island[nextX][nextY] = false;
                                    que.addLast(nextX*W + nextY);
                                }

                            }
                        }
                    }
                }
            }
            sb.append(count).append('\n');
        } // while(true) end
        System.out.println(sb);
    }
}
