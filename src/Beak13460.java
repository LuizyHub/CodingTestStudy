import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

public class Beak13460 {
    private static class P {
        int x, y;
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

        public Simulation(P r, P b) {
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
    private static int N, M, min = Integer.MAX_VALUE;
    private static boolean isPossible = true;
    private static P holl;
    private static char[][] map;
    private static HashSet<Simulation> visit = new HashSet<>();
    private static Simulation moveRight(Simulation s){
        Simulation next = new Simulation();
        P p = null;
        if (s.B.x == s.R.x){
            if (s.B.y < s.R.y){
                p = movePRight(s.B);
                if (p == null){
                    isPossible = false;
                    return null;
                }
                else {
                    next.B =p;
                }
                map[next.B.x][next.B.y] = '#';
                p = movePRight(s.R);
                map[next.B.x][next.B.y] = '.';
                if (p == null){
                    isPossible = true;
                    return null;
                }
                else {
                    next.R = p;
                }
            }
            else {
                p = movePRight(s.R);
                if (p == null){
                    if (movePRight(s.B) == null)
                        isPossible = false;
                    else
                        isPossible = true;
                    return null;
                }
                else {
                    next.R =p;
                }
                map[next.R.x][next.R.y] = '#';
                p = movePRight(s.B);
                map[next.R.x][next.R.y] = '.';
                if (p == null){
                    isPossible = false;
                    return null;
                }
                else {
                    next.B = p;
                }
            }
        }
        else {
            p = movePRight(s.B);
            if (p == null){
                isPossible = false;
                return null;
            }
            else {
                next.B =p;
            }
            p = movePRight(s.R);
            if (p == null){
                isPossible = true;
                return null;
            }
            else {
                next.R = p;
            }
        }
        return next;
    }
    private static P movePRight(P p){
        P next = null;
        for (int i = p.y; i < M; i++) {
            char c = map[p.x][i];
            if (c == '#'){
                next = new P(p.x, i - 1);
                break;
            } else if (c == 'O') {
                break;
            }
        }
        return next;
    }
//    private static Simulation moveLeft(Simulation s){
//    }
//    private static Simulation moveUp(Simulation s){
//    }
//    private static Simulation moveDown(Simulation s){
//    }
    private static void bfs(Simulation s, int move){

        Simulation next;
        next = moveRight(s);
        if (visit.contains(s)){

        }

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
        Simulation s = new Simulation(R, B);
        visit.add(s);
        bfs(s, 0);


    }
}
