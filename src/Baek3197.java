import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek3197 {
    private static class P{
        final int x,y;
        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    private static P[][] p;
    private static int R, C, count;
    private static char[][] map;
    private static boolean[][] visit;
    private static AbstractList<P> Ls;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        p = new P[R][C];
        Ls = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                p[i][j] = new P(i, j);
                char c = s.charAt(j);
                if (c == 'L'){
                    Ls.add(p[i][j]);
                    c = '.';
                }
                map[i][j] = c;
            }
        }
        loop :
        for (count = 0; ; count++) {

            System.out.println(count);
            for (char[] chars : map) {
                System.out.println(chars);
            }

            visit = new boolean[R][C];
            for (int j = 0; j < R; j++) {
                for (int k = 0; k < C; k++) {
                    if (visit[j][k] || (map[j][k] == 'X')) continue;
                    ArrayDeque<P> deque = new ArrayDeque<>();
                    deque.add(p[j][k]);
                    visit[j][k] = true;
                    int Lcount = 0;
                    while (!deque.isEmpty()){
                        P now = deque.pollFirst();

                        for (int l = 0; l < 4; l++) {
                            int nx = now.x + dx[l];
                            int ny = now.y + dy[l];
                            if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                            if (visit[nx][ny]) continue;
                            char c = map[nx][ny];
                            if (Ls.contains(p[nx][ny]))
                                Lcount++;
                            if (Lcount == 2){
                                break loop;
                            }
                            switch (c){
                                case '.' :
                                    deque.addLast(p[nx][ny]);
                                case 'X' :
                                    visit[nx][ny] = true;
                                    map[nx][ny] = '.';
                                    break;
                            }
                        }

                    }
                }
            }
        }

        System.out.println(count);
    }
}
