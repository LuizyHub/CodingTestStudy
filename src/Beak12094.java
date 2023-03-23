import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak12094 {
    private static int maxSum = 0;
    private static int N;
    private static int[][] goUp(int[][] board){ // return is changed
        int[][] newBoard = new int[N][N];
        boolean isChanged = false;

        for (int i = 0; i < board.length; i++) {
            int idx = 0;
            boolean isAdded = false;
            for (int j = 0; j < board[i].length; j++) {
                int cnt = board[j][i];
                if (cnt != 0){
                    if (!isChanged){
                        if (j != idx)
                            isChanged = true;
                    }
                    if (idx == 0){
                        newBoard[idx++][i] = cnt;
                    }
                    else {
                        if (newBoard[idx-1][i] == cnt && !isAdded){
                            isAdded = true;
                            newBoard[idx-1][i] = cnt<<1;
                            if (!isChanged)
                                isChanged = true;
                        }
                        else { // newBoard[i][idx-1] != cnt
                            newBoard[idx++][i] = cnt;
                            isAdded = false;
                        }
                    }


                }
            }

        }
        if (isChanged)
            return newBoard;
        else
            return null;
    }
    private static int[][] goDown(int[][] board){ // return is changed
        int[][] newBoard = new int[N][N];
        boolean isChanged = false;

        for (int i = 0; i < board.length; i++) {
            int idx = N-1;
            boolean isAdded = false;
            for (int j = N-1; j >= 0; j--) {
                int cnt = board[j][i];
                if (cnt != 0){
                    if (!isChanged){
                        if (j != idx)
                            isChanged = true;
                    }
                    if (idx == N-1){
                        newBoard[idx--][i] = cnt;
                    }
                    else {
                        if (newBoard[idx+1][i] == cnt && !isAdded){
                            isAdded = true;
                            newBoard[idx+1][i] = cnt<<1;
                            if (!isChanged)
                                isChanged = true;
                        }
                        else { // newBoard[i][idx-1] != cnt
                            newBoard[idx--][i] = cnt;
                            isAdded = false;
                        }
                    }


                }
            }

        }
        if (isChanged)
            return newBoard;
        else
            return null;
    }
    private static int[][] goLeft(int[][] board){ // return is changed
        int[][] newBoard = new int[N][N];
        boolean isChanged = false;

        for (int i = 0; i < board.length; i++) {
            int idx = 0;
            boolean isAdded = false;
            for (int j = 0; j < board[i].length; j++) {
                int cnt = board[i][j];
                if (cnt != 0){
                    if (!isChanged){
                        if (j != idx)
                            isChanged = true;
                    }
                    if (idx == 0){
                        newBoard[i][idx++] = cnt;
                    }
                    else {
                        if (newBoard[i][idx-1] == cnt && !isAdded){
                            isAdded = true;
                            newBoard[i][idx-1] = cnt<<1;
                            if (!isChanged)
                                isChanged = true;
                        }
                        else { // newBoard[i][idx-1] != cnt
                            newBoard[i][idx++] = cnt;
                            isAdded = false;
                        }
                    }


                }
            }

        }
        if (isChanged)
            return newBoard;
        else
            return null;
    }
    private static int[][] goRight(int[][] board){ // return is changed
        int[][] newBoard = new int[N][N];
        boolean isChanged = false;

        for (int i = 0; i < board.length; i++) {
            int idx = N-1;
            boolean isAdded = false;
            for (int j = N-1; j >= 0; j--) {
                int cnt = board[i][j];
                if (cnt != 0){
                    if (!isChanged){
                        if (j != idx)
                            isChanged = true;
                    }
                    if (idx == N-1){
                        newBoard[i][idx--] = cnt;
                    }
                    else {
                        if (newBoard[i][idx+1] == cnt && !isAdded){
                            isAdded = true;
                            newBoard[i][idx+1] = cnt<<1;
                            if (!isChanged)
                                isChanged = true;
                        }
                        else { // newBoard[i][idx-1] != cnt
                            newBoard[i][idx--] = cnt;
                            isAdded = false;
                        }
                    }


                }
            }

        }
        if (isChanged)
            return newBoard;
        else
            return null;
    }
    private static int getMax(int[][] arr){
        int max = 0;
        for (int[] ints : arr) {
            for (int i : ints) {
                max = Math.max(max,i);
            }
        }
        return max;
    }

    private static void solution(int count, int[][] board){
        int[][] nextBoard;
        boolean isMoved = false;

        if (count==10){
            maxSum = Math.max(getMax(board), maxSum);
        }
        else {
            nextBoard = goUp(board);
            if (nextBoard != null){
                solution(count+1,nextBoard);
                isMoved = true;
            }
            nextBoard = goDown(board);
            if (nextBoard != null){
                solution(count+1,nextBoard);
                isMoved = true;
            }
            nextBoard = goRight(board);
            if (nextBoard != null){
                solution(count+1,nextBoard);
                isMoved = true;
            }
            nextBoard = goLeft(board);
            if (nextBoard != null){
                solution(count+1,nextBoard);
                isMoved = true;
            }
            if (!isMoved){
                maxSum = Math.max(getMax(board), maxSum);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        StringTokenizer st;
        for (int[] ints : board) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < ints.length; i++) {
                ints[i] = Integer.parseInt(st.nextToken());
            }
        }

        solution(0, board);

        System.out.println(maxSum);
    }
}
