import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
public class BOJ5373_2 {
    private static class Block {
        char color;

        public Block(char color) {
            this.color = color;
        }
        public Block() {}
    }
    private static class Cube {
        Block[][] blocks;
        ArrayList<Block> around;
        private static final int SIZE = 3;
        {
            blocks = new Block[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    blocks[i][j] = new Block();
                }
            }
            around = new ArrayList<>();
        }
        public Cube() {}
        public Cube(char color) {
            setColor(color);
        }
        public void setColor(char color) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    blocks[i][j].color = color;
                }
            }
        }
        public void addAround(Block... blocks) {
            for (Block block : blocks) {
                around.add(block);
            }
        }
        private void turn(char dir) {
            int order = -1;
            if (dir == '+')
                order = 1;
            if (blocks[1][1].color == 'w')
                order *= -1;
            char[] temp = new char[2];
            if (order == -1) {
                temp[0] = blocks[0][0].color;
                temp[1] = blocks[0][1].color;

                blocks[0][0].color = blocks[2][0].color;
                blocks[0][1].color = blocks[1][0].color;

                blocks[2][0].color = blocks[2][2].color;
                blocks[1][0].color = blocks[2][1].color;

                blocks[2][2].color = blocks[0][2].color;
                blocks[2][1].color = blocks[1][2].color;

                blocks[0][2].color = temp[0];
                blocks[1][2].color = temp[1];
            }
            else {
                temp[0] = blocks[0][0].color;
                temp[1] = blocks[0][1].color;

                blocks[0][0].color = blocks[0][2].color;
                blocks[0][1].color = blocks[1][2].color;

                blocks[0][2].color = blocks[2][2].color;
                blocks[1][2].color = blocks[2][1].color;

                blocks[2][2].color = blocks[2][0].color;
                blocks[2][1].color = blocks[1][0].color;

                blocks[2][0].color = temp[0];
                blocks[1][0].color = temp[1];
            }
        }
        public void spin(char dir) {
            char[] temp = new char[SIZE];
            switch (dir) {
                case '+' :
                    for (int i = 0; i < SIZE; i++) {
                        temp[i] = around.get(around.size() - SIZE + i).color;
                    }
                    for (int i = around.size() - SIZE; i > 0; i-= SIZE) {
                        for (int j = 0; j < SIZE; j++) {
                            around.get(i + j).color = around.get(i + j - SIZE).color;
//                            around.set(i + j, around.get(i + j - SIZE));
                        }
                    }
                    for (int i = 0; i < SIZE; i++) {
                        around.get(i).color = temp[i];
//                        around.set(i, temp[i]);
                    }

                    break;
                case '-' :
                    for (int i = 0; i < SIZE; i++) {
                        temp[i] = around.get(i).color;
                    }
                    for (int i = 0; i < around.size() - SIZE; i += SIZE) {
                        for (int j = 0; j < SIZE; j++) {
                            around.get(i + j).color = around.get(i + j + SIZE).color;
//                            around.set(i + j, around.get(i + j + SIZE));
                        }
                    }
                    for (int i = 0; i < SIZE; i++) {
                        around.get(around.size() - SIZE + i).color = temp[i];
//                        around.set(around.size() - 1 - SIZE + i, temp[i]);
                    }

                    break;
            }
            turn(dir);
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
            cubes[i] = new Cube();
        }

        //U
        {
            cubes[U].addAround(cubes[L].blocks[2][0]);
            cubes[U].addAround(cubes[L].blocks[1][0]);
            cubes[U].addAround(cubes[L].blocks[0][0]);

            cubes[U].addAround(cubes[B].blocks[0]);

            cubes[U].addAround(cubes[R].blocks[0][2]);
            cubes[U].addAround(cubes[R].blocks[1][2]);
            cubes[U].addAround(cubes[R].blocks[2][2]);

            cubes[U].addAround(cubes[F].blocks[2][2]);
            cubes[U].addAround(cubes[F].blocks[2][1]);
            cubes[U].addAround(cubes[F].blocks[2][0]);
        }

        //D
        {
            cubes[D].addAround(cubes[R].blocks[2][0]);
            cubes[D].addAround(cubes[R].blocks[1][0]);
            cubes[D].addAround(cubes[R].blocks[0][0]);

            cubes[D].addAround(cubes[B].blocks[2][2]);
            cubes[D].addAround(cubes[B].blocks[2][1]);
            cubes[D].addAround(cubes[B].blocks[2][0]);

            cubes[D].addAround(cubes[L].blocks[0][2]);
            cubes[D].addAround(cubes[L].blocks[1][2]);
            cubes[D].addAround(cubes[L].blocks[2][2]);

            cubes[D].addAround(cubes[F].blocks[0]);
        }

        //F
        {
            cubes[F].addAround(cubes[L].blocks[2][2]);
            cubes[F].addAround(cubes[L].blocks[2][1]);
            cubes[F].addAround(cubes[L].blocks[2][0]);

            cubes[F].addAround(cubes[U].blocks[2]);

            cubes[F].addAround(cubes[R].blocks[2][2]);
            cubes[F].addAround(cubes[R].blocks[2][1]);
            cubes[F].addAround(cubes[R].blocks[2][0]);

            cubes[F].addAround(cubes[D].blocks[2][2]);
            cubes[F].addAround(cubes[D].blocks[2][1]);
            cubes[F].addAround(cubes[D].blocks[2][0]);
        }

        //B
        {
            cubes[B].addAround(cubes[R].blocks[0]);

            cubes[B].addAround(cubes[U].blocks[0][2]);
            cubes[B].addAround(cubes[U].blocks[0][1]);
            cubes[B].addAround(cubes[U].blocks[0][0]);

            cubes[B].addAround(cubes[L].blocks[0]);

            cubes[B].addAround(cubes[D].blocks[0]);
        }

        //L
        {
            cubes[L].addAround(cubes[B].blocks[2][0]);
            cubes[L].addAround(cubes[B].blocks[1][0]);
            cubes[L].addAround(cubes[B].blocks[0][0]);

            cubes[L].addAround(cubes[U].blocks[0][0]);
            cubes[L].addAround(cubes[U].blocks[1][0]);
            cubes[L].addAround(cubes[U].blocks[2][0]);

            cubes[L].addAround(cubes[F].blocks[2][0]);
            cubes[L].addAround(cubes[F].blocks[1][0]);
            cubes[L].addAround(cubes[F].blocks[0][0]);

            cubes[L].addAround(cubes[D].blocks[2][0]);
            cubes[L].addAround(cubes[D].blocks[1][0]);
            cubes[L].addAround(cubes[D].blocks[0][0]);
        }

        //R
        {
            cubes[R].addAround(cubes[F].blocks[0][2]);
            cubes[R].addAround(cubes[F].blocks[1][2]);
            cubes[R].addAround(cubes[F].blocks[2][2]);

            cubes[R].addAround(cubes[U].blocks[2][2]);
            cubes[R].addAround(cubes[U].blocks[1][2]);
            cubes[R].addAround(cubes[U].blocks[0][2]);

            cubes[R].addAround(cubes[B].blocks[0][2]);
            cubes[R].addAround(cubes[B].blocks[1][2]);
            cubes[R].addAround(cubes[B].blocks[2][2]);

            cubes[R].addAround(cubes[D].blocks[0][2]);
            cubes[R].addAround(cubes[D].blocks[1][2]);
            cubes[R].addAround(cubes[D].blocks[2][2]);
        }
    }
    private static void setCube() {
        for (int i = 0; i < 6; i++) {
            cubes[i].setColor(colors[i]);
        }
    }

    private static void print() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(' ');
            }
            for (int j = 0; j < 3; j++) {
                System.out.print(cubes[B].blocks[i][j].color);
            }
            System.out.println();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cubes[L].blocks[i][j].color);
            }
            for (int j = 0; j < 3; j++) {
                System.out.print(cubes[D].blocks[i][j].color);
            }
            for (int j = 0; j < 3; j++) {
                System.out.print(cubes[R].blocks[i][j].color);
            }
            System.out.println();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(' ');
            }
            for (int j = 0; j < 3; j++) {
                System.out.print(cubes[F].blocks[i][j].color);
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(' ');
            }
            for (int j = 0; j < 3; j++) {
                System.out.print(cubes[U].blocks[i][j].color);
            }
            System.out.println();
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
        initCube();

        while (testCount-- > 0) {
//            System.out.println(testCount);
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            setCube();
            for (int i = 0; i < n; i++) {
                String order = st.nextToken();
                cubes[cubeMap.get(order.charAt(0))].spin(order.charAt(1));
                System.out.println(i);
                print();
            }

            for (int i = 0; i < Cube.SIZE; i++) {
                for (int j = 0; j < Cube.SIZE; j++) {
                    sb.append(cubes[U].blocks[i][j].color);
                }
                sb.append('\n');
            }
        }

        System.out.print(sb);
    }
}
