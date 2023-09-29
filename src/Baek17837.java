import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek17837 {
    private static final int MAX_TURN = 1000;
    private static final int WHITE = 0, RED = 1, BLUE = 2;
    private static final int RIGHT = 0, LEFT = 1, UP = 2, DOWN = 3;
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {1, -1, 0, 0};
    private static int N, K;
    private static int turn;
    private static int[][] map;
    private static Piece[] pieces;
    private static ArrayList<Piece>[][] pieceMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        pieces = new Piece[K];
        pieceMap = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                pieceMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;

            Piece p = new Piece(i, x, y, dir);
            pieceMap[x][y].add(p);
            pieces[i] = p;
        }

        turnLoop :
        for (turn = 1; turn <= MAX_TURN; turn++) {
            for (Piece p : pieces) {
//                System.out.println("turn = " + turn);
//                for (Piece piece : pieces) {
//                    System.out.println(piece);
//                }
                if (movePiece(p)) break turnLoop;
            }
        }

        System.out.print(turn > MAX_TURN ? -1 : turn);
    }

    private static boolean movePiece(Piece p) {
        int nx = p.x + dx[p.dir];
        int ny = p.y + dy[p.dir];

        if (nx < 0 || ny < 0 || nx >= N || ny >= N) return moveToBlue(p);

        switch (map[nx][ny]) {
            case WHITE :
                return moveToWhite(p, nx, ny);
            case RED :
                return moveToRed(p, nx, ny);
            case BLUE :
                return moveToBlue(p);
            default :
                return false;
        }
    }

    private static boolean moveToWhite(Piece p, int nx, int ny) {
        int idx = pieceMap[p.x][p.y].indexOf(p);
        ArrayDeque<Piece> deque = new ArrayDeque<>();

        for (int i = pieceMap[p.x][p.y].size() - 1; i >= idx; i--) {
            Piece piece = pieceMap[p.x][p.y].remove(i);
            piece.x = nx;
            piece.y = ny;
            deque.addLast(piece);
        }

        while (!deque.isEmpty()) {
            pieceMap[nx][ny].add(deque.pollLast());
        }

        return pieceMap[nx][ny].size() >= 4;
    }

    private static boolean moveToRed(Piece p, int nx, int ny) {
        int idx = pieceMap[p.x][p.y].indexOf(p);

        for (int i = pieceMap[p.x][p.y].size() - 1; i >= idx; i--) {
            Piece piece = pieceMap[p.x][p.y].remove(i);
            piece.x = nx;
            piece.y = ny;
            pieceMap[nx][ny].add(piece);
        }

        return pieceMap[nx][ny].size() >= 4;
    }

    private static boolean moveToBlue(Piece p) {
        p.reverseDir();
        int nx = p.x + dx[p.dir];
        int ny = p.y + dy[p.dir];

        if (nx < 0 || ny < 0 || nx >= N || ny >= N) return false;

        switch (map[nx][ny]) {
            case WHITE :
                return moveToWhite(p, nx, ny);
            case RED :
                return moveToRed(p, nx, ny);
            default :
                return false;
        }
    }

    private static class Piece {
        int num, x, y, dir;

        public Piece(int num, int x, int y, int dir) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void reverseDir() {
            dir = ((dir / 2) * 2) + ((dir + 1) % 2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Piece piece = (Piece) o;
            return num == piece.num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num);
        }

        @Override
        public String toString() {
            return "Piece{" +
                    "num=" + num +
                    ", x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    '}';
        }
    }
}
