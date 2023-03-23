import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak16236_1 {
    private static class Node implements Comparable<Node> {
        public int distance;
        public int x;
        public int y;

        public Node(int distance, int x, int y) {
            this.distance = distance;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if (o.distance != distance){
                return distance - o.distance; //smaller
            } else if (o.x != x) { //distance is same
                return x - o.x; //bigger
            }
            else {// distance same, x same
                return y - o.y; //bigger
            }
        }
    }
    private static int[] dy = {0, -1, 1, 0};
    private static int[] dx = {-1, 0, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // Max = 20
        int[][] space;
        int insideShark = 0;
        int sharkSize = 2;

        int distance = 0;
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
            }
        }

        PriorityQueue<Node> que = new PriorityQueue<>();

        boolean ischanged = true;
        while (ischanged){ // if sharksize == max -> break;
            int[][] visited = new int[N][N];
            que.add(new Node(1,sharkX,sharkY));
            visited[sharkX][sharkY] = 1;
            ischanged = false;

            while (!que.isEmpty()){
                Node cur = que.poll();
                int curX = cur.x;
                int curY = cur.y;

                if (space[curX][curY] > 0 && space[curX][curY] < sharkSize){
                    distance += (visited[curX][curY] -1);
                    insideShark++;
                    if (sharkSize == insideShark){
                        sharkSize++;
                        insideShark = 0;
                    }
                    space[curX][curY] = 0; // fish is gone
                    sharkX = curX;
                    sharkY = curY;
                    ischanged = true; // shark position changed
                    que.clear();
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nextX = curX + dx[i];
                    int nextY = curY + dy[i];

                    if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N
                            && visited[nextX][nextY] ==0 && space[nextX][nextY] <= sharkSize){
                        visited[nextX][nextY] = visited[curX][curY] + 1;
                        que.add(new Node(visited[nextX][nextY], nextX, nextY));
                    }
                }
            }
        }
        System.out.println(distance);
    }
}
