import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
/*
                     B(0,0) B(0,1) B(0,2)
                     B(1,0) B(1,1) B(1,2)
                     B(2,0) B(2,1) B(2,2)
L(0,0) L(0,1) L(0,2) D(0,0) D(0,1) D(0,2) R(0,0) R(0,1) R(0,2)
L(1,0) L(1,1) L(1,2) D(1,0) D(1,1) D(1,2) R(1,0) R(1,1) R(1,2)
L(2,0) L(2,1) L(2,2) D(2,0) D(2,1) D(2,2) R(2,0) R(2,1) R(2,2)
                     F(0,0) F(0,1) F(0,2)
                     F(1,0) F(1,1) F(1,2)
                     F(2,0) F(2,1) F(2,2)

                     U(0,0) U(0,1) U(0,2)
                     U(1,0) U(1,1) U(1,2)
                     U(2,0) U(2,1) U(2,2)
 */

public class BOJ5373 {
    private static class Cube {
        private static final int SIZE = 3, LEFT = 0, TOP = 1, RIGHT = 2, BOTTOM = 3;
        char[][] map;
        public Cube[] around;
        public int[] dir;
        {
            map = new char[SIZE][SIZE];
            around = new Cube[4];
            dir = new int[4];
        }
        public Cube(char color) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    map[i][j] = color;
                }
            }
        }
        public void setAround(Cube left, Cube up, Cube right, Cube down) {
            around[0] = left;
            around[1] = up;
            around[2] = right;
            around[3] = down;
        }
        public void setDir(int left, int top, int right, int bottom) {
            dir[0] = left;
            dir[1] = top;
            dir[2] = right;
            dir[3] = bottom;
        }
        public char[] getMap(int dir) {
            char[] chars = new char[3];

            switch (dir) {
                case LEFT:
                    for (int i = 0; i < SIZE; i++) {
                        chars[i] = map[SIZE - i - 1][0];
                    }
                    break;
                case TOP :
                    for (int i = 0; i < SIZE; i++) {
                        chars[i] = map[0][i];
                    }
                    break;
                case RIGHT:
                    for (int i = 0; i < SIZE; i++) {
                        chars[i] = map[i][SIZE - 1];
                    }
                    break;
                case BOTTOM:
                    for (int i = 0; i < SIZE; i++) {
                        chars[i] = map[SIZE - 1][SIZE - i - 1];
                    }
                    break;
            }
            return chars;
        }

        public void setMap(int dir, char[] chars) {
            switch (dir) {
                case LEFT:
                    for (int i = 0; i < SIZE; i++) {
                        map[SIZE - i - 1][0] = chars[i];
                    }
                    break;
                case TOP :
                    for (int i = 0; i < SIZE; i++) {
                        map[0][i] = chars[i];
                    }
                    break;
                case RIGHT:
                    for (int i = 0; i < SIZE; i++) {
                        map[i][SIZE - 1] = chars[i];
                    }
                    break;
                case BOTTOM:
                    for (int i = 0; i < SIZE; i++) {
                        map[SIZE - 1][SIZE - i - 1] = chars[i];
                    }
                    break;
            }
        }
    }
    private static final int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;
    private static char[] colors = new char[]{'w', 'y', 'r', 'o', 'g', 'b'};
    private static int testCount, n;
    private static Cube[] cubes;
    private static HashMap<Character, Integer> cubeMap;
    private static void initCube() {
        cubes = new Cube[6];
        for (int i = 0; i < 6; i++) {
            cubes[i] = new Cube(colors[i]);
        }
        cubes[U].setAround(cubes[L], cubes[B], cubes[R], cubes[F]);
        cubes[U].setDir(Cube.TOP, Cube.TOP, Cube.TOP, Cube.TOP);

        cubes[D].setAround(cubes[L], cubes[F], cubes[R], cubes[B]);
        cubes[D].setDir(Cube.BOTTOM, Cube.BOTTOM, Cube.BOTTOM, Cube.BOTTOM);

        cubes[F].setAround(cubes[L], cubes[U], cubes[R], cubes[D]);
        cubes[F].setDir(Cube.RIGHT, Cube.BOTTOM, Cube.LEFT, Cube.TOP);

        cubes[B].setAround(cubes[R], cubes[U], cubes[L], cubes[D]);
        cubes[B].setDir(Cube.RIGHT, Cube.TOP, Cube.LEFT, Cube.BOTTOM);

        cubes[L].setAround(cubes[B], cubes[U], cubes[F], cubes[D]);
        cubes[L].setDir(Cube.RIGHT, Cube.LEFT, Cube.LEFT, Cube.LEFT);

        cubes[R].setAround(cubes[F], cubes[U], cubes[B], cubes[D]);
        cubes[R].setDir(Cube.RIGHT, Cube.RIGHT, Cube.LEFT, Cube.RIGHT);
    }
    private static void move(Cube cube, char dir) {
        switch (dir) {
            case '-' :
                cube.around[0].setMap(cube.dir[0], cube.around[1].getMap(cube.dir[1]));
                cube.around[1].setMap(cube.dir[1], cube.around[2].getMap(cube.dir[2]));
                cube.around[2].setMap(cube.dir[2], cube.around[3].getMap(cube.dir[3]));
                cube.around[3].setMap(cube.dir[3], cube.around[0].getMap(cube.dir[0]));
                break;
            case '+' :
                cube.around[0].setMap(cube.dir[0], cube.around[3].getMap(cube.dir[3]));
                cube.around[1].setMap(cube.dir[1], cube.around[0].getMap(cube.dir[0]));
                cube.around[2].setMap(cube.dir[2], cube.around[1].getMap(cube.dir[1]));
                cube.around[3].setMap(cube.dir[3], cube.around[2].getMap(cube.dir[2]));
                break;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        testCount = Integer.parseInt(br.readLine());

        cubeMap = new HashMap<>();
        cubeMap.put('U', 0);
        cubeMap.put('D', 1);
        cubeMap.put('F', 2);
        cubeMap.put('B', 3);
        cubeMap.put('L', 4);
        cubeMap.put('R', 5);

        while (testCount-- > 0) {
            n = Integer.parseInt(br.readLine());
            initCube();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                String order = st.nextToken();
                move(cubes[cubeMap.get(order.charAt(0))], order.charAt(1));
            }

            for (int i = 0; i < Cube.SIZE; i++) {
                sb.append(cubes[U].map[i]).append('\n');
            }
        }

        System.out.print(sb);
    }
}
