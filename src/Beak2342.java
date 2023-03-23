import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak2342 {
    private static final int[][] cost = {
//           0  1  2  3  4
            {1, 2, 2, 2, 2},//0
            {2, 1, 3, 4, 3},//1
            {2, 3, 1, 3, 4},//2
            {2, 4, 3, 1, 3},//3
            {2, 3, 4, 3, 1} //4
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] minMove = new int[5][5];
        int[][] moveCount = new int[5][5];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int now = Integer.parseInt(st.nextToken());
        minMove[0][now] = minMove[now][0] = cost[0][now];
        moveCount[0][now] = moveCount[now][0] = 1;
        int pre = now;
        int size = 1;
        while (true){
            now = Integer.parseInt(st.nextToken());
            if (now==0)
                break;
            int[] nowY = new int[5];
            int[] Xnow = new int[5];
            for (int j = 0; j < 5; j++) {
                if (j!=pre){
                    if (moveCount[j][pre] == size){
                        Xnow[j] = minMove[j][pre] + cost[pre][now]; // [j][now]
                    }
                    if (moveCount[pre][j] == size){
                        nowY[j] = minMove[pre][j] + cost[pre][now]; // [now][j]
                    }
                }
                else {
                    int tmp = Integer.MAX_VALUE;
                    for (int k = 0; k < 5; k++) {
                        if (moveCount[j][k] == size){
                            tmp = Math.min(tmp, minMove[j][k] + cost[k][now]); // [j][now]
                        }
                    }
                    Xnow[j] = tmp;
                    tmp = Integer.MAX_VALUE;
                    for (int k = 0; k < 5; k++) {
                        if (moveCount[k][j] == size){
                            tmp = Math.min(tmp, minMove[k][j] + cost[k][now]); // [now][j]
                        }
                    }
                    nowY[j] = tmp;
                }
            }

            for (int j = 0; j < 5; j++) {
                if (Xnow[j] != Integer.MAX_VALUE && Xnow[j] != 0){
                    moveCount[j][now] = size+1;
                    minMove[j][now] = Xnow[j];
                }
                if (nowY[j] != Integer.MAX_VALUE && nowY[j] != 0){
                    moveCount[now][j] = size+1;
                    minMove[now][j] = nowY[j];
                }
            }
            if (nowY[now] != Integer.MAX_VALUE && Xnow[now] != Integer.MAX_VALUE)
                minMove[now][now] = Math.min(nowY[now],Xnow[now]);
//            System.out.println("moveCount");
//            for (int[] ints : moveCount) {
//                System.out.println(Arrays.toString(ints));
//            }
//            System.out.println("minMove");
//            for (int[] ints : minMove) {
//                System.out.println(Arrays.toString(ints));
//            }
//            System.out.println("now = " + now);
            pre = now;
            size++;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            if (moveCount[i][pre] == size)
                ans = Math.min(ans, minMove[i][pre]);
            if (moveCount[pre][i] == size)
                ans = Math.min(ans, minMove[pre][i]);
        }
        System.out.println(ans);
    }
}
