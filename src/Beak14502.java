import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Beak14502 {
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static int N, M, Max = 0, noneCount = 0;
    private static int[][] lab;
    private static int[] none;
    private static int[] combination3 = new int[3];
    private static void combination(int i, int idx){
        if (i == 3){
//            System.out.println(combination3[0]/M + " " + combination3[0]%M);
//            System.out.println(combination3[1]/M + " " + combination3[1]%M);
//            System.out.println(combination3[2]/M + " " + combination3[2]%M);
//            System.out.println();
            solution();
        }
        else {
            for (int j = idx+1; j < noneCount; j++) {
                combination3[i] = none[j];
                combination(i+1, j);
            }
        }
    }

    private static void solution() {
        int[][] simulation = new int[N][M];
        ArrayDeque<Integer> deq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                simulation[i][j] = lab[i][j];
                if (lab[i][j] == 2)
                    deq.add(i*M + j);
            }
        }
        for (int i : combination3) {
            simulation[i/M][i%M] = 1;
        }

//        for (int[] ints : simulation) {
//            System.out.println(Arrays.toString(ints));
//        }

        while (!deq.isEmpty()){
            int tmp = deq.pollFirst();
            int x = tmp/M;
            int y = tmp%M;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < M && simulation[nx][ny] == 0){
                    simulation[nx][ny] = 2;
                    deq.addLast(nx*M + ny);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (simulation[i][j] == 0)
                    count++;
            }
        }
        Max = Math.max(Max, count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];
        none = new int[N*M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                lab[i][j] = tmp;
                if (tmp == 0)
                    none[noneCount++] = i*M + j;
            }
        }
        combination(0,-1);

//        for (int[] ints : lab) {
//            System.out.println(Arrays.toString(ints));
//        }

        System.out.println(Max);

    }


}
