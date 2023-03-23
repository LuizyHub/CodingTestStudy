import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak10942 {
    private static int N;
    private static int[] board;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        board = new int[N];
        int[][] isPal = new int[N][N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            board[i] = num;
            if (i>0 && board[i-1] == board[i])
                isPal[i-1][i] = 1;
            if (i>1 && board[i-2] == board[i])
                isPal[i-2][i] = 1;
            for (int j = 1; j < i-1; j++) {
                if (isPal[j][i-1] == 1 && board[j-1] == board[i])
                    isPal[j-1][i] = 1;
            }
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            if (from == to)
                sb.append(1).append('\n');
            else
                sb.append(isPal[from][to]).append('\n');
        }
        System.out.println(sb);
    }
}
