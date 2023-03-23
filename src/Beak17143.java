import java.util.Arrays;

public class Beak17143 {
    private static int R, C, M;
    private static int sumShark = 0;
    private static int[][] matrix;  //save idx
    private static final int UP = 0, DOWN = 1, RIGHT = 2, LEFT = 3;
    private static Shark[] sharks;

    private static class Shark{
        public int r,c,s,d,z;
        public boolean isdead = false;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    private static void getShark(int col){
        for (int i = 0; i < R; i++) {
            if (matrix[i][col] != -1){
                Shark shark = sharks[ matrix[i][col]];
                sumShark += shark.z;
                shark.isdead = true;
                return;
            }
        }
    }

    private static void moveShark(){
        for (int i = 0; i < M; i++) {
            Shark shark = sharks[i];
            if (shark.isdead) continue;
            int r,c,mod;
            switch (shark.d){
                case UP:
                    mod = (shark.r - shark.s)%(2*(R-1));
                    r = mod < 0 ? (2*(R-1)) + mod : mod;
                    if (r < R-1){
                        shark.r = r;
                    }
                    else {
                        shark.r = 2*(R-1) - r;
                        shark.d = DOWN;
                    }
                    break;
                case DOWN:
                    r = (shark.r + shark.s)%(2*(R-1));
                    if (r < R-1){
                        shark.r = r;
                    }
                    else {
                        shark.r = 2*(R-1) - r;
                        shark.d = UP;
                    }
                    break;
                case RIGHT:
                    c = (shark.c + shark.s)%(2*(C-1));
                    if (c < C-1){
                        shark.c = c;
                    }
                    else {
                        shark.c = 2*(C-1) - c;
                        shark.d = LEFT;
                    }
                    break;
                case LEFT:
                    mod = (shark.c - shark.s)%(2*(C-1));
                    c = mod < 0 ? (2*(C-1)) + mod : mod;
                    if (c < C-1){
                        shark.c = c;
                    }
                    else {
                        shark.c = 2*(C-1) - c;
                        shark.d = RIGHT;
                    }
                    break;
            }
            if (matrix[shark.r][shark.c] == -1){
                matrix[shark.r][shark.c] = i;
            }
            else {
                Shark preShark = sharks[matrix[shark.r][shark.c]];
                if (shark.z > preShark.z) {
                    matrix[shark.r][shark.c] = i;
                    preShark.isdead = true;
                }
                else {
                    shark.isdead = true;
                }
            }
        }
    }

    private static void initMatrix(){
        for (int i = 0; i < R; i++) {
            Arrays.fill(matrix[i],-1);
        }
    }

    public static void main(String[] args) throws Exception {
        R = read();
        C = read();
        matrix = new int[R][C];
        initMatrix();
        M = read();
        sharks = new Shark[M];

        for (int i = 0; i < M; i++) {
            int r = read()-1;
            int c = read()-1;
            int s = read();
            int d = read()-1;
            int z = read();
            sharks[i] = new Shark(r,c,s,d,z);
            matrix[r][c] = i;
        }

        for (int i = 0; i < C; i++) {
//            for (int[] ints : matrix) {
//                for (int anInt : ints) {
//                    System.out.print((char)(anInt + 'a') + "\t");
//                }
//                System.out.println();
//            }
//            System.out.println();
            getShark(i);
            initMatrix();
            moveShark();
        }

        System.out.println(sumShark);
    }

    private static int read() throws Exception {

        int c, N = System.in.read() - 48;

        while ((c = System.in.read()) > 32) N = 10 * N + c - 48;

        if (c == 13) System.in.read(); // For CRLF

        return N;

    }
}
