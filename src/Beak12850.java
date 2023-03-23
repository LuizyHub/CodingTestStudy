import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak12850 {
    private static int N;
    private static final int MOD = 1_000_000_007;
    private static int[][] buidings = new int[2][8];
    private static int[] ansMatrix = {1,0,0,1,0,0,0,0};
    private static long[][][] binaryMatrix = new long[32][8][8];
    private static int[][] matrix = {
//           0 1 2 3 4 5 6 7
            {0,1,1,1,0,0,0,0},//0
            {1,0,0,1,0,0,0,0},//1
            {1,0,0,1,1,1,0,0},//2
            {1,1,1,0,0,1,0,0},//3
            {0,0,1,0,0,1,1,0},//4
            {0,0,1,1,1,0,0,1},//5
            {0,0,0,0,1,0,0,1},//6
            {0,0,0,0,0,1,1,0},//7
    };

    private static void initMatrix(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                binaryMatrix[0][i][j] = matrix[i][j];
            }
        }
        for (int i = 1; i < 32; i++) {
            binaryMatrix[i] = mutiplyMatrix(binaryMatrix[i-1], binaryMatrix[i-1]);
        }
    }
    private static long[][] mutiplyMatrix(long[][] a, long[][] b){
        long[][] ans = new long[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    ans[i][j] += a[i][k]*b[k][j];
                    ans[i][j]%=MOD;
                }
            }
        }
        return ans;
    }

    private static void addMatrix(int[] from, int[] to){
        for (int i = 0; i < 8; i++) {
            from[i] += to[i];
            from[i] %= MOD;
        }
    }
    private static int[] mutiplyMatrix(int[] a, int[][] b){
        int[] ans = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                ans[i] += a[j]*b[i][j];
            }
        }
        return ans;
    }
    private static int[] mutiplyMatrix(int[] a, long[][] b){
        int[] ans = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                ans[i] += a[j]*b[i][j];
                ans[i] %= MOD;
            }
        }
        return ans;
    }
    private static int[][] mutiplyMatrix(int[][] a, int[][] b){
        int[][] ans = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    ans[i][j] += a[i][k]*b[k][j];
                }
            }
        }
        return ans;
    }
    private static void solution(int count){
        String s = Integer.toBinaryString(count);
        int size = s.length();
        long[][] toMultiply = new long[8][8];
        for (int i = 0; i < 8; i++) {
            toMultiply[i][i] = 1;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1'){
                toMultiply = mutiplyMatrix(toMultiply,binaryMatrix[size-i-1]);
            }
        }
        ansMatrix = mutiplyMatrix(ansMatrix, toMultiply);

        System.out.println(ansMatrix[1]);



    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        initMatrix();

        solution(N-1);


//        for (int[] ints : matrix) {
//            for (int anInt : ints) {
//                System.out.printf("%d ",anInt);
//            }
//            System.out.println();
//        }


        int[][] hangtwice = matrix;
//        for (int i = 1; i <= 10; i++) {
//            hangtwice = mutiplyMatrix(hangtwice, matrix);
//            System.out.println(i);
//
//            for (int[] ints : hangtwice) {
//                for (int anInt : ints) {
//                    System.out.printf("%d ",anInt);
//                }
//                System.out.println();
//            }
//
//            System.out.println(Arrays.toString(mutiplyMatrix(new int[]{1,0,0,1,0,0,0,0},hangtwice)));
//
//
//            for (int[] ints : hangtwice) {
//                System.out.println(Arrays.toString(ints));
//            }
//
//        }

        int[] hang = {1,0,0,1,0,0,0,0};
//        for (int i = 1; i < N; i++) {
//            hang = mutiplyMatrix(hang,matrix);
//            System.out.println(Arrays.toString(hang));
//        }

        int current = 0;
        int pre = 1;
        buidings[current][0] = 1;
        buidings[current][3] = 1;
        for (int i = 1; i < N; i++) {
            current = (current+1)%2;
            pre = (pre+1)%2;

            buidings[current][0] = ((buidings[pre][1] + buidings[pre][2])%MOD + buidings[pre][3])%MOD;
            buidings[current][1] = (buidings[pre][0] + buidings[pre][3])%MOD;
            buidings[current][2] = (((buidings[pre][0] + buidings[pre][3])%MOD + buidings[pre][4])%MOD + buidings[pre][5])%MOD;
            buidings[current][3] = (((buidings[pre][0] + buidings[pre][1])%MOD + buidings[pre][2])%MOD + buidings[pre][5])%MOD;
            buidings[current][4] = ((buidings[pre][2] + buidings[pre][5])%MOD + buidings[pre][6])%MOD;
            buidings[current][5] = (((buidings[pre][2] + buidings[pre][3])%MOD + buidings[pre][4])%MOD + buidings[pre][7])%MOD;
            buidings[current][6] = (buidings[pre][4] + buidings[pre][7])%MOD;
            buidings[current][7] = (buidings[pre][5] + buidings[pre][6])%MOD;
//
//            System.out.println(i+1);
//            System.out.printf("%d\t%d\n",buidings[current][0],buidings[current][1]);
//            System.out.printf("%d\t%d\n",buidings[current][2],buidings[current][3]);
//            System.out.printf("%d\t%d\n",buidings[current][4],buidings[current][5]);
//            System.out.printf("%d\t%d\n",buidings[current][6],buidings[current][7]);
//            System.out.println();

        }

        System.out.println(buidings[current][1]);

    }
}
/*
js - jb
|  \ |
sa - ml
|  \ |
jl - hk
|    |
hs - hn

0 - 1
| \ |
2 - 3
| \ |
4 - 5
|   |
6 - 7


 */
