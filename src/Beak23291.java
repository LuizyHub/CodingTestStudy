import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Beak23291 {
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static class P{
        int x,y;

        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "P{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    private static int N, K;

    private static int[] bowl;
    private static int[][] matrix;
    private static P[] ps;
    private static int getMax(){
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, bowl[i]);
        }
        return max;
    }

    private static int getMin(){
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            min = Math.min(min, bowl[i]);
        }
        return min;
    }
    private static void balance(){
        int[] change = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 4; j++) {
                int nx = ps[i].x + dx[j];
                int ny = ps[i].y + dy[j];
                int nextIdx = matrix[nx][ny];
                if (nextIdx == 0) continue;

                int dif = bowl[i] - bowl[nextIdx];
                if (dif > 0){
                    dif /= 5;
                    change[i] -= dif;
                    change[nextIdx] += dif;
                }
                else {
                    dif = (-dif) / 5;
                    change[i] += dif;
                    change[nextIdx] -= dif;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            bowl[i] += (change[i]/2);
        }
    }
    private static void solution(){
        int min = getMin();
        for (int i = 1; i <= N; i++)
            if (bowl[i] == min)
                bowl[i] += 1;
        int start = 1;
        int left = 0, height = 0;
        for (int i = 2;; i++) {
            left = i/2;
            height = (i+1)/2;
            if (start + left + height - 1 > N) break;
            for (int j = 0; j < left; j++) {
                int idx = j + start;
                for (int k = 1; k <= height; k++) {
                    int bowlIdx = matrix[k][idx];
                    int nx = left - j + 1;
                    int ny = left  - j + idx + k - 1;
                    matrix[nx][ny] = bowlIdx;
                    ps[bowlIdx].x = nx;
                    ps[bowlIdx].y = ny;
                    matrix[k][idx] = 0;
                }
            }

//            System.out.println("start = " + start);
//            printFish();
//            if (start + left + (i+1)/2 + (i+2)/2 - 1 > N)break;

            start += left;

        }


        balance();
//        printFish();
        int idx = 1;
        for (int i = start; i < left + start; i++) {
            for (int j = 1; j <= height; j++) {
                int bowlIdx = matrix[j][i];
                matrix[1][idx] = bowlIdx;
                ps[bowlIdx].x = 1;
                ps[bowlIdx].y = idx;
                matrix[j][i] = 0;
                idx++;
            }
        }
//        printFish();

        for (int i = 1; i <= N/2; i++) {
            int bowlIdx = matrix[1][i];
            matrix[2][N-i+1] = bowlIdx;
            ps[bowlIdx].x = 2;
            ps[bowlIdx].y = N-i+1;
            matrix[1][i] = 0;
        }
//        printFish();
//        for (int i = N/4+1; i <= N/2; i++) {
//            int bowlIdx = matrix[1][i];
//            matrix[2][N-i+1] = bowlIdx;
//            matrix[1][i] = 0;
//        }
//        printFish();

        for (int i = 1 + N/2; i <= 3*N/4; i++) {
            for (int j = 1; j <=2 ; j++) {
                int bowlIdx = matrix[j][i];
                matrix[4-j+1][N - i + N/2 + 1] = bowlIdx;
                ps[bowlIdx].x = 4-j+1;
                ps[bowlIdx].y = N - i + N/2 + 1;
                matrix[j][i] = 0;
            }
        }
//        printFish();

        balance();
//        printFish();
        idx = 1;
        start = 3*N/4 + 1;
        left = N/4;
        height = 4;
        for (int i = start; i < left + start; i++) {
            for (int j = 1; j <= height; j++) {
                int bowlIdx = matrix[j][i];
                matrix[1][idx] = bowlIdx;
                ps[bowlIdx].x = 1;
                ps[bowlIdx].y = idx;
                matrix[j][i] = 0;
                idx++;
            }
        }
//        printFish();



    }
    private static void printFish(){
        System.out.println("matrix : ");
        for (int i = N + 1; i >= 0; i--) {
            System.out.print(i + "[");
            for (int j = 0; j < N + 1; j++) {
                System.out.print(bowl[matrix[i][j]] + ", ");
            }
            System.out.print("\b\b]\n");
        }
        for (int i = 0; i <= N + 1; i++)
            System.out.print("  " + i);
        System.out.println();
        System.out.println(Arrays.toString(ps));
    }
    private static void print(){
        System.out.println("matrix : ");
        for (int i = N + 1; i >= 0; i--)
            System.out.println(i + Arrays.toString(matrix[i]));
        for (int i = 0; i <= N + 1; i++)
            System.out.print("  " + i);
        System.out.println();
        System.out.println(Arrays.toString(ps));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ps = new P[N + 1];
        bowl = new int[N + 1];
        matrix = new int[N + 2][N + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            bowl[i] = num;
            matrix[1][i] = i;
            ps[i] = new P(1,i);
        }
        int ans = 0;
        while (getMax() - getMin() > K){
            ans++;
            solution();
        }
        System.out.println(ans);


//        System.out.println(Arrays.toString(bowl));
//
//        solution();

    }

}
/*
16 7
5 2 3 14 9 2 11 8 1 2 3 4 5 6 7 8

32 7
5 2 3 14 9 2 11 8 1 2 3 4 5 6 7 8 5 2 3 14 9 2 11 8 1 2 3 4 5 6 7 8
 */
