import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Beak9328 {
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static class P{
        int x, y;
        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int h, w, key, count;
    private static char[][] map;
    private static boolean[][] visit;
    private static ArrayList<P>[] walls;
    private static ArrayDeque<P> deque;
    private static void check(P p, char c){
        if (c == '$'){
            count++;
            deque.addLast(p);
            visit[p.x][p.y] = true;
        }
        else if (c == '.'){
            deque.addLast(p);
            visit[p.x][p.y] = true;
        }
        else if ('a' <= c && c <= 'z') {
            deque.addLast(p);
            visit[p.x][p.y] = true;
            int idx = c - 'a';
            if ((key & (1<<idx)) == 0){
                key |= (1<<idx);
                if (walls[idx] != null){
                    for (P pw : walls[idx]) {
                        deque.addLast(pw);
                        visit[pw.x][pw.y] = true;
                    }
                }
            }
        }
        else if ('A' <= c && c <= 'Z') {
            int idx = c - 'A';
            if ((key & (1<<idx)) == 0){
                if (walls[idx] == null){
                    walls[idx] = new ArrayList<>();
                }
                walls[idx].add(p);
            }
            else {
                deque.addLast(p);
                visit[p.x][p.y] = true;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- != 0){
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][];
            visit = new boolean[h][w];
            for (int j = 0; j < h; j++) {
                map[j] = br.readLine().toCharArray();
            }
            key = 0;
            count = 0;
            String keys = br.readLine();
            if (!keys.equals("0")) {
                for (int j = 0; j < keys.length(); j++) {
                    key |= (1 << (keys.charAt(j) - 'a'));
                }
            }
            walls = new ArrayList[26];
            deque = new ArrayDeque<>();

            for (int j = 0; j < h; j++) {
                check(new P(j,0), map[j][0]);
                check(new P(j,w-1), map[j][w-1]);
            }

            for (int j = 1; j < w - 1; j++) {
                check(new P(0,j), map[0][j]);
                check(new P(h-1,j), map[h-1][j]);
            }

            while (!deque.isEmpty()){
                P p = deque.pollFirst();
                for (int j = 0; j < 4; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];
                    if (0 <= nx && nx < h && 0 <= ny && ny < w && !visit[nx][ny]){
                        check(new P(nx, ny), map[nx][ny]);
                    }
                }
            }

            sb.append(count).append('\n');

        }

        System.out.println(sb);
    }
}
