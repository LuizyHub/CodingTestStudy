import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11049 {
    private static int N;
    protected static int[][]m;
    protected static int[][]s;

    public static void matrixChainOrder(int[] dims) {
        int n = dims.length - 1;
        m = new int[n][n];
        s = new int[n][n];

        for (int lenMinusOne = 1; lenMinusOne < n; lenMinusOne++) {
            for (int i = 0; i < n - lenMinusOne; i++) {
                int j = i + lenMinusOne;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = m[i][k] + m[k+1][j] + dims[i]*dims[k+1]*dims[j+1];
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    public void printOptimalParenthesizations() {
        boolean[] inAResult = new boolean[s.length];
        printOptimalParenthesizations(s, 0, s.length - 1, inAResult);
    }

    void printOptimalParenthesizations(int[][]s, int i, int j,  /* for pretty printing: */ boolean[] inAResult) {
        if (i != j) {
            printOptimalParenthesizations(s, i, s[i][j], inAResult);
            printOptimalParenthesizations(s, s[i][j] + 1, j, inAResult);
            String istr = inAResult[i] ? "_result " : " ";
            String jstr = inAResult[j] ? "_result " : " ";
            System.out.println(" A_" + i + istr + "* A_" + j + jstr);
            inAResult[i] = true;
            inAResult[j] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[] dims = new int[N + 1];

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            dims[i] = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        }
        st = new StringTokenizer(br.readLine());
        dims[N - 1] = Integer.parseInt(st.nextToken());
        dims[N] = Integer.parseInt(st.nextToken());

        matrixChainOrder(dims);

        System.out.print(m[0][N - 1]);

        System.out.println("m");
        for (int[] ints : m) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("s");
        for (int[] ints : s) {
            System.out.println(Arrays.toString(ints));
        }
    }
}