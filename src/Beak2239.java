import java.io.IOException;

public class Beak2239 {
    private static int[][] availList = new int[9][9];
    private static int[][] sdoku = new int[9][9];
    private static int searchNum(int bitmask, int start){
        for (int i = start; i <= 9; i++) {
            if (((bitmask >> i) & 1) == 0){
                return i;
            }
        }
        return 0; // if not found;
    }

    private static int searchNum(int bitmask){
        return searchNum(bitmask,1);
    }

    private static void renewList(int[][] checkList, int x, int y, int num){
        for (int j = 0; j < 9; j++) {
            checkList[x][j] |= (1<<num);
            checkList[j][y] |= (1<<num);
        }
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                checkList[(x / 3) * 3 + j][(y / 3) * 3 + k] |= (1<<num);
            }
        }
    }
    private static void fill(int[][] list, int[][] board){
        int maxbit = 8;
        int x = 0, y = 0;
        boolean ischanged = false;
        while (maxbit == 8) {
            ischanged = false;
            maxbit = -1;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == 0){
                        int bitCount = Integer.bitCount(list[i][j]);
                        if (bitCount > maxbit){
                            maxbit = bitCount;
                        }
                        if (bitCount == 8){
                            int num = searchNum(list[i][j]);
                            renewList(list,i,j,num);
                            board[i][j] = num;
                        }else if (!ischanged){
                            x = i; y = j;
                        }
                        ischanged = true;
                    }
                }
            }
        }
        if (!ischanged){
            StringBuilder sb = new StringBuilder();
            for (int[] ints : board) {
                for (int i : ints) {
                    sb.append(i);
                }
                sb.append('\n');
            }
            System.out.print(sb);
            System.exit(0);
            //completed
        } else {
            int num = searchNum(list[x][y]);
            while (num != 0){
                int[][] nextBoard = new int[9][];
                int[][] nextList = new int[9][];
                for (int i = 0; i < 9; i++) {
                    nextBoard[i] = board[i].clone();
                    nextList[i] = list[i].clone();
                }
                renewList(nextList,x,y,num);
                nextBoard[x][y] = num;
                fill(nextList,nextBoard);
                num = searchNum(list[x][y], num + 1);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sdoku[i][j] = System.in.read() - 48;
            }
            System.in.read();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = sdoku[i][j];
                if (num == 0) continue;
                for (int k = 0; k < 9; k++) {
                    availList[k][j] |= (1<<num);
                    availList[i][k] |= (1<<num);
                }
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        availList[(i/3)*3 + k][(j/3)*3 + l] |= (1<<num);
                    }
                }
            }
        }

        fill(availList,sdoku);

    }
}
/*
000500163
049013507
500620804
490105382
218000400
050208900
000000008
005706200
300900600

 */

/*
000500163
049013507
500620804
490105382
218000400
050208900
000000008
005706200
300900600

 */
