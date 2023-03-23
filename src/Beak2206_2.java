import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


public class Beak2206_2 {
    private static class Node{
        int mode; // 0 unbroken,  1broken
        int x,y; // location
        int dist; //distance

        public Node(int mode, int x, int y, int dist) {
            this.mode = mode;
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = -1;
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][][] visited = new boolean[2][N][M];
        char[][] maze = new char[N][];
        for (int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
        }
        ArrayDeque<Node> deque = new ArrayDeque<>();
        deque.addLast(new Node(0,0,0,0));
        visited[0][0][0] = true;
        while (!deque.isEmpty()){
            Node cur = deque.pollFirst();
            if (cur.x == N-1 && cur.y == M-1){
                ans = cur.dist+1;
                break;
            }
            for (int k = 0; k < 4; k++) {
                int nextX = cur.x + dx[k];
                int nextY = cur.y + dy[k];
                if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M){
                    if (cur.mode == 1 && maze[nextX][nextY] == '0' && !visited[1][nextX][nextY]){
                        visited[1][nextX][nextY] = true;
                        deque.addLast(new Node(1,nextX,nextY, cur.dist+1));
                    } else if (cur.mode == 0 && maze[nextX][nextY] == '0' && !visited[0][nextX][nextY]) {
                        visited[0][nextX][nextY] = true;
                        deque.addLast(new Node(0,nextX,nextY, cur.dist+1));
                    } else if (cur.mode == 0 && maze[nextX][nextY] == '1' && !visited[1][nextX][nextY]) { // when meet wall
                        int countZero = 0;
                        for (int i = 0; i < 4; i++) {
                            int tmpx = nextX + dx[i];
                            int tmpy = nextY + dy[i];
                            if (0 <= tmpx && tmpx < N && 0 <= tmpy && tmpy < M && maze[tmpx][tmpy] == '0'){
                                countZero++;
                            }
                        }
                        if (countZero >= 2) { // can be passed
                            visited[1][nextX][nextY] = true;
                            deque.addLast(new Node(1,nextX,nextY, cur.dist+1));
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
