import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// use priorityque
public class Beak16236 {
    private static int[] dy = {0, -1, 1, 0};
    private static int[] dx = {-1, 0, 0, 1};
    private static int sharkSize;


    private static int getSharkSize(){
        if (sharkSize > 21)
            return 7;
        else if (sharkSize > 15)
            return 6;
        else if (sharkSize > 10)
            return 5;
        else if (sharkSize > 6)
            return 4;
        else if (sharkSize > 3)
            return 3;
        else
            return 2;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // Max = 20
        int[][] space;
        int insideShark = 0;
        sharkSize = 2;
        int eat = 2;

        int distance = 0;
        int maxSize = 2; // Max 2+1+2+4+(6*N^2-4)
        int sharkX=-1, sharkY=-1;
        space = new int[N][N]; // Max 20*20 400
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n==9) {
                    sharkX = i;
                    sharkY = j;
                    n=0;
                }
                space[i][j] = n;
                maxSize += n;
            }
        }

        ArrayDeque<Integer> que = new ArrayDeque<>();

        boolean ischanged = true;
        loop:
        while (eat != maxSize){ // if sharksize == max -> break;
            int[][] visited = new int[N][N];
            que.addLast(sharkX*N + sharkY);
            visited[sharkX][sharkY] = 1;
            ischanged = false;

            while (!que.isEmpty()){
                int cur = que.pollFirst();
                int curX = cur/N;
                int curY = cur%N;

                if (space[curX][curY] > 0 && space[curX][curY] < sharkSize){
                    distance += (visited[curX][curY] -1);
                    insideShark ++;
                    eat += space[curX][curY];
                    if (sharkSize == insideShark){
                        sharkSize++;
                        insideShark = 0;
                    }
                    space[curX][curY] = 0;
                    sharkX = curX;
                    sharkY = curY;
                    ischanged = true;
                    que.clear();
                    System.out.println("dis " +  distance + " size " + sharkSize + " " + curX + " " + curY);
                    for (int[] ints : space) {
                        System.out.println(Arrays.toString(ints));
                    }
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nextX = curX + dx[i];
                    int nextY = curY + dy[i];

                    if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N && visited[nextX][nextY] ==0 && space[nextX][nextY] <= sharkSize){
                        visited[nextX][nextY] = visited[curX][curY] + 1;
                        que.addLast(nextX*N + nextY);
                    }
                }
            }
            if (!ischanged)
                break;


        }
        for (int[] ints : space) {
            System.out.println(Arrays.toString(ints));
        }

        System.out.println(distance);


    }
}
