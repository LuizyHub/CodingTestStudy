import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Beak10026 {
    private static int[] dx = {1,0,-1,0};
    private static int[] dy = {0,1,0,-1};
    private static final int RG = 0, R = 1, G = 2, B = 3;
    private static int N;
    private static boolean[][][] RGB;
    private static int[] countRGB;

    private static void getCountRGB(int rgb){
        ArrayDeque<Integer> que = new ArrayDeque<>();

        for (int i = 0; i < RGB[rgb].length; i++) {
            for (int j = 0; j < RGB[rgb][i].length; j++) {
                if (RGB[rgb][i][j]){
                    countRGB[rgb]++;
                    RGB[rgb][i][j] = false;
                    que.addLast(i*N + j);

                    while (!que.isEmpty()){
                        int cur = que.pollFirst();
                        int curX = cur/N;
                        int curY = cur%N;

                        for (int k = 0; k < 4; k++) {
                            int nextX = curX + dx[k];
                            int nextY = curY + dy[k];
                            if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N && RGB[rgb][nextX][nextY]){
                                RGB[rgb][nextX][nextY] = false;
                                que.addLast(nextX*N + nextY);
                            }
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // Max = 100
        RGB = new boolean[4][N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);
                switch (c){
                    case 'B':
                        RGB[B][i][j] = true;
                        break;
                    case 'R':
                        RGB[R][i][j] = true;
                        RGB[RG][i][j] = true;
                        break;
                    case 'G':
                        RGB[G][i][j] = true;
                        RGB[RG][i][j] = true;
                        break;
                }
            }
        }
        countRGB = new int[4];

        getCountRGB(R);
        getCountRGB(G);
        getCountRGB(B);
        getCountRGB(RG);

        System.out.println((countRGB[R] + countRGB[G] + countRGB[B]) + " " + (countRGB[B] + countRGB[RG]));
    }
}
