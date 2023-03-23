import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak23796 {
    private static int[][] goUp(int[][] board){ // return is changed
        int[][] newBoard = new int[N][N];

        for (int i = 0; i < board.length; i++) {
            int idx = 0;
            boolean isAdded = false;
            for (int j = 0; j < board[i].length; j++) {
                int cnt = board[j][i];
                if (cnt != 0){
                    if (idx == 0){
                        newBoard[idx++][i] = cnt;
                    }
                    else {
                        if (newBoard[idx-1][i] == cnt && !isAdded){
                            isAdded = true;
                            newBoard[idx-1][i] = cnt<<1;
                        }
                        else { // newBoard[i][idx-1] != cnt
                            newBoard[idx++][i] = cnt;
                            isAdded = false;
                        }
                    }


                }
            }

        }
        return newBoard;
    }
    private static int[][] goDown(int[][] board){ // return is changed
        int[][] newBoard = new int[N][N];
        for (int i = 0; i < board.length; i++) {
            int idx = N-1;
            boolean isAdded = false;
            for (int j = N-1; j >= 0; j--) {
                int cnt = board[j][i];
                if (cnt != 0){
                    if (idx == N-1){
                        newBoard[idx--][i] = cnt;
                    }
                    else {
                        if (newBoard[idx+1][i] == cnt && !isAdded){
                            isAdded = true;
                            newBoard[idx+1][i] = cnt<<1;
                        }
                        else { // newBoard[i][idx-1] != cnt
                            newBoard[idx--][i] = cnt;
                            isAdded = false;
                        }
                    }


                }
            }

        }
        return newBoard;
    }
    private static int[][] goLeft(int[][] board){ // return is changed
        int[][] newBoard = new int[N][N];

        for (int i = 0; i < board.length; i++) {
            int idx = 0;
            boolean isAdded = false;
            for (int j = 0; j < board[i].length; j++) {
                int cnt = board[i][j];
                if (cnt != 0){
                    if (idx == 0){
                        newBoard[i][idx++] = cnt;
                    }
                    else {
                        if (newBoard[i][idx-1] == cnt && !isAdded){
                            isAdded = true;
                            newBoard[i][idx-1] = cnt<<1;
                        }
                        else { // newBoard[i][idx-1] != cnt
                            newBoard[i][idx++] = cnt;
                            isAdded = false;
                        }
                    }


                }
            }

        }
        return newBoard;
    }
    private static int[][] goRight(int[][] board){ // return is changed
        int[][] newBoard = new int[N][N];
        for (int i = 0; i < board.length; i++) {
            int idx = N-1;
            boolean isAdded = false;
            for (int j = N-1; j >= 0; j--) {
                int cnt = board[i][j];
                if (cnt != 0){
                    if (idx == N-1){
                        newBoard[i][idx--] = cnt;
                    }
                    else {
                        if (newBoard[i][idx+1] == cnt && !isAdded){
                            isAdded = true;
                            newBoard[i][idx+1] = cnt<<1;
                        }
                        else { // newBoard[i][idx-1] != cnt
                            newBoard[i][idx--] = cnt;
                            isAdded = false;
                        }
                    }


                }
            }

        }
        return newBoard;
    }
    private static int N = 8;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] board = new int[8][8];
        StringTokenizer st;
        for (int[] ints : board) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < ints.length; i++) {
                ints[i] = Integer.parseInt(st.nextToken());
            }
        }

        char c = (char) br.read();

        switch (c){
            case 'U' :
                board = goUp(board);
                break;
            case 'D' :
                board = goDown(board);
                break;
            case 'L' :
                board = goLeft(board);
                break;
            case 'R' :
                board = goRight(board);
                break;
        }
        for (int[] ints : board) {
            for (int i : ints) {
                if (i == -2147483648)
                    sb.append("2147483648 ");
                else
                    sb.append(i).append(' ');
            }
            sb.setCharAt(sb.length()-1, '\n');
        }

        System.out.println(sb);
    }
}
