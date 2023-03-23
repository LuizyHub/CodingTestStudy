import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Beak1194_2 {
    private static boolean[] keySets = new boolean[0b111111];
    private static int N,M;
    private static char[][] maze; // unchange
    private static int[][][] map;
    private static int wallList = 0b000000;
    private static int[] dx = {1,-1,0,0};
    private static int[] dy = {0,0,1,-1};
    private static class Location{
        public int x;
        public int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static class Node{
        int mode;
        Location l;

        public Node(int mode, Location l) {
            this.mode = mode;
            this.l = l;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int answer = -1;
        maze = new char[N][];
        map = new int[0b111111+1][N][M];
        Location locationMin = null;
        for (int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                char c = maze[i][j];
                if (c == '0')
                    locationMin = new Location(i,j);
                else if ('A' <= c && c <= 'F')
                    wallList = wallList | (1<<(c-'A'));
            }
        }

        ArrayDeque<Node> deque = new ArrayDeque<>();
        deque.addLast(new Node(0,locationMin));
        map[0][locationMin.x][locationMin.y] = 1;

        while (!deque.isEmpty()){
            Node cur = deque.pollFirst();

            if (maze[cur.l.x][cur.l.y] == '1'){
                answer = map[cur.mode][cur.l.x][cur.l.y] - 1;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int x = cur.l.x + dx[i];
                int y = cur.l.y + dy[i];
                if (!(0 <= x && x < N && 0 <= y && y < M))
                    continue;
                char c = maze[x][y];
                if (c == '#')
                    continue;
                if ('a' <= c && c <= 'f'){
                    int newMode = cur.mode;
                    if ((wallList & (1<<(c-'a')))!=0){ // check key need
                        newMode = cur.mode | (1<<(c-'a'));
                    }
                    if (map[newMode][x][y] == 0){
                        map[newMode][x][y] = map[cur.mode][cur.l.x][cur.l.y] + 1;
                        deque.addLast(new Node(newMode, new Location(x,y)));
                    }
                }
                else if ('A' <= c && c <= 'F'){ //when meet door
                    if (((cur.mode & (1<<(c-'A')))!=0)){
                        if (map[cur.mode][x][y] == 0){
                            map[cur.mode][x][y] = map[cur.mode][cur.l.x][cur.l.y] + 1;
                            deque.addLast(new Node(cur.mode, new Location(x,y)));
                        }
                    }
                }
                else { // when meet blank
                    if (map[cur.mode][x][y] == 0){
                        map[cur.mode][x][y] = map[cur.mode][cur.l.x][cur.l.y] + 1;
                        deque.addLast(new Node(cur.mode, new Location(x,y)));
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
