import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak12850_2 {
    private static int N;
    private static final int MOD = 1_000_000_007;
    private static int[][] buidings = new int[2][8];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
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