import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak1562 {
    private static final long[] FACTORIAL = {1L,1L,2L,6L,24L,
            120L,720L,5_040L,40_320L, 362_880L,
            3_628_800L, 39_916_800L, 479_001_600L, 6_227_020_800L, 87_178_291_200L,
            1_307_674_368_000L, 20_922_789_888_000L, 355_687_428_096_000L, 6_402_373_705_728_000L, 121_645_100_408_832_000L,
            2_432_902_008_176_640_000L
    };
    private static int[][] down = new int[10][10]; //length
    private static int[][] up = new int[][]{
//       0  1  2  3  4  5  6  7  8  9
        {0,0,0,0,0,0,0,0,0,0},//0
        {18,19,18,17,16,15,14,13,12,11},//1
        {17,18,19,18,17,16,15,14,13,12},//2
        {16,17,18,19,18,17,16,15,14,13},//3
        {15,16,17,18,19,18,17,16,15,14},//4
        {14,15,16,17,18,19,18,17,16,15},//5
        {13,14,15,16,17,18,19,18,17,16},//6
        {12,13,14,15,16,17,18,19,18,17},//7
        {11,12,13,14,15,16,17,18,19,18},//8
        {10,11,12,13,14,15,16,17,18,19},//9
    };
    private static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        down[9][0] = 10;
        down[9][2] = 12;
        down[8][0] = 11;
        down[7][0] = 12;
        up[1][9] = 11;
        down[1][9] = 11;
        up[2][9] = 12;
        down[2][9] = 12;

//        System.out.println(getRepitionCombination(9,1));
        int N = Integer.parseInt(br.readLine());
//        int N = 12;

        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (((i+j)%2) == (N%2))continue;
//                if (down[i][j] == 0) continue;
                int dif = N - up[i][j];
                if (dif >= 0){
                    count += getRepitionCombination(9,dif/2)%1_000_000_000;
                }
            }
        }

        System.out.println(count%1_000_000_000);

    }
    private static long getRepitionCombination(int n, int r){
        return getCombination(n+r-1,r);
    }
    private static long getCombination(int n, int r){
//        r = Math.min(r, n-r);
        return FACTORIAL[n] / FACTORIAL[r] / FACTORIAL[n-r];
    }
}
/*
10
9876543210
/*
 */
/*
11
89876543210
98765432101
10123456789
/*
/*
12
989876543210
987876543210
987676543210
987656543210
987654543210
987654343210
987654323210
987654321210
987654321010
987654321012

898765432101

789876543210

101234567898

210123456789


 */

/*
432101234567898765

5678987654321012345
5432101234567898765

7898765432101234567

876543210123456789

9876543210123456789
 */

/*
13
9898765432101
9878765432101
9876765432101
98765432101
98765432101
98765432101
9876543210
9876543210
9876543210
9876543210



98765676543210
98765432123210
17
28
39






 */
