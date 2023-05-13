import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ5373_1 {
    private static final int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;
    private static Point[] points;
    static {
        points = new Point[6];
        points[U] = new Point(0,0);
        points[D] = new Point(3,3);
        points[F] = new Point(6,3);
        points[B] = new Point(0, 3);
        points[L] = new Point(3, 0);
        points[R] = new Point(3, 6);
    }
    private static int testCount, n;
    private static HashMap<Character, Integer> positionMap;
    private static char[][] cubeMap;
    private static char[][] cubeUp;
    private static void initCube(){
        cubeUp = new char[][]{
                {'w', 'w', 'w'}, // Up
                {'w', 'w', 'w'},
                {'w', 'w', 'w'}
        };
        cubeMap = new char[][]{
                {' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ',' '}, // Back
                {' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ',' '},
                {' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ',' '},
                {'g', 'g', 'g', 'y', 'y', 'y', 'b', 'b','b'}, // Left, Down, Right
                {'g', 'g', 'g', 'y', 'y', 'y', 'b', 'b','b'},
                {'g', 'g', 'g', 'y', 'y', 'y', 'b', 'b','b'},
                {' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ',' '}, // Front
                {' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ',' '},
                {' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ',' '}
        };
    }

    private static void spin(char[][] cube, Point point, char order) {
        char temp;
        int x = point.x;
        int y = point.y;
        switch (order) {
            case '+' :
                temp = cube[x + 1][y];
                cube[x + 1][y] = cube[x + 2][y];
                cube[x + 2][y] = cube[x + 2][y + 1];
                cube[x + 2][y + 1] = cube[x + 2][y + 2];
                cube[x + 2][y + 2] = cube[x + 1][y + 2];
                cube[x + 1][y + 2] = cube[x][y + 2];
                cube[x][y + 2] = cube[x][y + 1];
                cube[x][y + 1] = cube[x][y];
                cube[x][y] = temp;
                break;
            case '-' :
                temp = cube[x][y + 1];
                cube[x][y + 1] = cube[x][y + 2];
                cube[x][y + 2] = cube[x + 1][y + 2];
                cube[x + 1][y + 2] = cube[x + 2][y + 2];
                cube[x + 2][y + 2] = cube[x + 2][y + 1];
                cube[x + 2][y + 1] = cube[x + 2][y];
                cube[x + 2][y] = cube[x + 1][y];
                cube[x + 1][y] = cube[x][y];
                cube[x][y] = temp;
                break;
        }
    }

    private static void print(char[][] chars) {
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
        }
    }
    private static void move(int side, char order) {
        char temp;
        if (side == U) {
            spin(cubeUp, points[U], order);
        }
        else {
            spin(cubeMap, points[side], order);
            switch (side) {
                case D :
                    break;
                case F :
                    if (order == '+') {
                        temp = cubeMap[5][8];
                        for (int i = 8; i > 0; i--)
                            cubeMap[5][i] = cubeMap[5][i - 1];
                        cubeMap[5][0] = cubeUp[2][0];
                        cubeUp[2][0] = cubeUp[2][1];
                        cubeUp[2][1] = cubeUp[2][2];
                        cubeUp[2][2] = temp;
                    }
                    else {
                        temp = cubeMap[5][0];
                        for (int i = 0; i < 8; i++)
                            cubeMap[5][i] = cubeMap[5][i + 1];
                        cubeMap[5][8] = temp;
                    }
                    break;
                case B :
                    if (order == '+') {
                        temp = cubeMap[3][8];
                        for (int i = 8; i > 0; i--)
                            cubeMap[3][i] = cubeMap[3][i - 1];
                        cubeMap[3][0] = cubeUp[0][0];
                        cubeUp[0][0] = cubeUp[0][1];
                        cubeUp[0][1] = cubeUp[0][2];
                        cubeUp[0][2] = temp;
                    }
                    else {
                        temp = cubeMap[3][0];
                        for (int i = 0; i < 8; i++)
                            cubeMap[3][i] = cubeMap[3][i + 1];
                        cubeMap[3][8] = temp;
                    }
                    break;
                case L :
                    break;
                case R :
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        cubeUp = new char[][]{
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}
        };
//        print(cubeUp);
//        spin(cubeUp,new Point(),'+');
//        print(cubeUp);
//        spin(cubeUp,new Point(),'-');
//        print(cubeUp);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        testCount = Integer.parseInt(br.readLine());

        positionMap = new HashMap<>();
        positionMap.put('U', 0);
        positionMap.put('D', 1);
        positionMap.put('F', 2);
        positionMap.put('B', 3);
        positionMap.put('L', 4);
        positionMap.put('R', 5);

        while (testCount-- > 0) {
            n = Integer.parseInt(br.readLine());
            initCube();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                String order = st.nextToken();
                move(positionMap.get(order.charAt(0)), order.charAt(1));
//                print(cubeMap);
            }

            for (int i = 0; i < 3; i++) {
                sb.append(cubeUp[i]).append('\n');
            }
        }

        System.out.print(sb);

    }
}
