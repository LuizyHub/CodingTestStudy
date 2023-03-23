import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

public class Beak13460_1 {
    private static class P {
        int x, y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            P p = (P) o;

            if (x != p.x) return false;
            return y == p.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static class Simulation{
        P R, B;
        int move;
        public Simulation() {
        }
        public Simulation(P r, P b){
            this(r, b, 0);
        }

        public Simulation(P r, P b, int move) {
            R = r;
            B = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Simulation that = (Simulation) o;

            if (!Objects.equals(R, that.R)) return false;
            return Objects.equals(B, that.B);
        }

        @Override
        public int hashCode() {
            int result = R != null ? R.hashCode() : 0;
            result = 31 * result + (B != null ? B.hashCode() : 0);
            return result;
        }
    }
    private static final int U = 0, D = 1, L = 2, R = 3;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int N, M, min = -1;
    private static boolean isPossible = true;
    private static P holl;
    private static char[][] map;
    private static HashSet<Simulation> visit = new HashSet<>();

    private static Simulation move(Simulation s, int dir){
        Simulation next = new Simulation();
        P rP = null, bP = null;
        switch (dir){
            case R :
                if(s.R.x != s.B.x){
                    bP = moveP(s.B,dir);
                    rP = moveP(s.R, dir);
                }
                else {
                    if (s.R.y < s.B.y){
                        bP = moveP(s.B,dir);
                        map[bP.x][bP.y] = '#';
                        rP = moveP(s.R, dir);
                        map[bP.x][bP.y] = '.';

                    }
                    else {
                        rP = moveP(s.R,dir);
                        map[rP.x][rP.y] = '#';
                        bP = moveP(s.B, dir);
                        map[rP.x][rP.y] = '.';
                    }
                }
                break;
            case L :
                if(s.R.x != s.B.x){
                    bP = moveP(s.B,dir);
                    rP = moveP(s.R, dir);
                }
                else {
                    if (s.R.y > s.B.y){
                        bP = moveP(s.B,dir);
                        map[bP.x][bP.y] = '#';
                        rP = moveP(s.R, dir);
                        map[bP.x][bP.y] = '.';

                    }
                    else {
                        rP = moveP(s.R,dir);
                        map[rP.x][rP.y] = '#';
                        bP = moveP(s.B, dir);
                        map[rP.x][rP.y] = '.';
                    }
                }
                break;
            case  U :
                if(s.R.y != s.B.y){
                    bP = moveP(s.B,dir);
                    rP = moveP(s.R, dir);
                }
                else {
                    if (s.R.x > s.B.x){
                        bP = moveP(s.B,dir);
                        map[bP.x][bP.y] = '#';
                        rP = moveP(s.R, dir);
                        map[bP.x][bP.y] = '.';

                    }
                    else {
                        rP = moveP(s.R,dir);
                        map[rP.x][rP.y] = '#';
                        bP = moveP(s.B, dir);
                        map[rP.x][rP.y] = '.';
                    }
                }
                break;
            case D :
                if(s.R.y != s.B.y){
                    bP = moveP(s.B,dir);
                    rP = moveP(s.R, dir);
                }
                else {
                    if (s.R.x < s.B.x){
                        bP = moveP(s.B,dir);
                        map[bP.x][bP.y] = '#';
                        rP = moveP(s.R, dir);
                        map[bP.x][bP.y] = '.';

                    }
                    else {
                        rP = moveP(s.R,dir);
                        map[rP.x][rP.y] = '#';
                        bP = moveP(s.B, dir);
                        map[rP.x][rP.y] = '.';
                    }
                }
                break;
        }
        if (bP == null){
            isPossible = false;
            return null;
        }else {
            next.B = bP;
        }
        if (rP == null){
            isPossible = true;
            return null;
        }
        else {
            next.R = rP;
        }
        return next;
    }
    private static P moveP(P p, int dir){
        P next = null;
        for (int i = 1;; i++) {
            int nx = p.x + dx[dir]*i;
            int ny = p.y + dy[dir]*i;

            char c = map[nx][ny];
            if (c == 'O'){
                break;
            } else if (c == '#') {
                next = new P(nx - dx[dir], ny - dy[dir]);
                break;
            }
        }
        return next;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        P R = null, B = null;
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c == 'O') {
                    holl = new P(i, j);
                    map[i][j] = c;
                }
                else if (c == 'R') {
                    R = new P(i, j);
                    map[i][j] = '.';
                }
                else if (c == 'B') {
                    B = new P(i, j);
                    map[i][j] = '.';
                }
                else {
                    map[i][j] = c;
                }
            }
        }
        Simulation s = new Simulation(R, B, 0);
        visit.add(s);
        ArrayDeque<Simulation> deque = new ArrayDeque<>();
        deque.add(s);
        loop :
        while (!deque.isEmpty()){
            s = deque.pollFirst();

            for (int i = 0; i < 4; i++) {
                Simulation next = move(s,i);
                if (next == null){
                    if (isPossible){
                        min = s.move + 1;
                        break loop;
                    }
                }
                else {
                    if (visit.contains(next)) continue;
                    next.move = s.move + 1;
                    deque.addLast(next);
                    visit.add(next);
                }
            }
        }
        System.out.println(min);
    }
}

