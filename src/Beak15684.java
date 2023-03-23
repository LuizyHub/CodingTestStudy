import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak15684 {
    private static int N, M, answer = 4;
    private static boolean[][] stairs;

    private static boolean solution(){
        for (int j = 1; j <= N; j++) {
            int cnt = j;
            for (int i = 0; i < M; i++) {
                if (stairs[i][cnt-1])
                    cnt--;
                else if (stairs[i][cnt])
                    cnt++;
            }
            if (cnt != j)
                return false;
        }
        return true;
    }

    private static void printStair(){
        System.out.println();
        for (int i = 1; i <= N; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < M; i++) {
            System.out.print((i+1) + " ");
            for (int j = 1; j < N ; j++) {
                if (stairs[i][j])
                    System.out.print("_ ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        stairs = new boolean[M][N+1];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            stairs[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())] = true;
        }

        if (solution()){
            System.out.print(0);
            return;
        }

        int mod = N-1;
        int size = M*mod;

        for (int i = 0; i < size; i++) {
            int xi = i / mod;
            int yi = i % mod + 1;
            if (stairs[xi][yi-1] || stairs[xi][yi] || stairs[xi][yi+1]) continue;
            stairs[xi][yi] = true;
            printStair();
            if (solution()){
                stairs[xi][yi] = false;
                answer = 1;
                break;
            }
            for (int j = i+1; j < size && answer > 2; j++) {
                int xj = j / mod;
                int yj = (j % mod) + 1;
                if (stairs[xj][yj-1] || stairs[xj][yj] || stairs[xj][yj+1]) continue;
                stairs[xj][yj] = true;
                printStair();
                if (solution()){
                    stairs[xj][yj] = false;
                    answer = 2;
                    break;
                }
                for (int k = j+1; k < size && answer > 3; k++) {
                    int xk = k / mod;
                    int yk = (k % mod) + 1;
                    if (stairs[xk][yk - 1] || stairs[xk][yk] || stairs[xk][yk + 1]) continue;
                    stairs[xk][yk] = true;
                    printStair();
                    if (solution()) {
                        stairs[xk][yk] = false;
                        answer = 3;
                        break;
                    }
                    stairs[xk][yk] = false;
                }
                stairs[xj][yj] = false;
            }
            stairs[xi][yi] = false;
        }

        System.out.println(answer == 4 ? -1 : answer);
    }
}
