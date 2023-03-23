import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Beak9663_1 {
    private static int[] dx = new int[]{1, 0,-1, 0, 1, 1,-1,-1};
    private static int[] dy = new int[]{0, 1, 0,-1, 1,-1, 1,-1};
    private static int N;
    private static int count = 0;

    private static int[][] Chess;

    private static void combination(ArrayList<Integer> list){
        int len = list.size();

        if (len==0){
            for (int i = 0; i < N*N; i++) {
                ArrayList<Integer> cloned = (ArrayList<Integer>)list.clone();
                cloned.add(i);
                combination(cloned);
            }
        }
        else if(len < N){
            for (int i = list.get(len-1) + 1; i < N*N; i++) {
                ArrayList<Integer> cloned = (ArrayList<Integer>)list.clone();
                cloned.add(i);
                combination(cloned);
            }
        } else if (len == N) {// start
            count++;
            Chess = new int[N][N];
            loop:
            for (Integer i : list) {
                int x = i/N;
                int y = i%N;
                if (Chess[x][y] == -1){
                    count--;
                    break;
                }
                Chess[x][y] = 1;
                for (int j = 1; j < N; j++) {
                    for (int k = 0; k < 8; k++) {
                        int xx = x + dx[k]*j;
                        int yy = y + dy[k]*j;
                        if (0 <= xx && xx < N && 0 <= yy && yy < N){
                            if (Chess[xx][yy] == 1){
                                count--;
                                break loop;
                            }
                            else
                                Chess[xx][yy] = -1;
                        }
                    }
                }
            }
        }
    }
    private static void solution(int[][] NQueen, int i){
        System.out.println(i);
        for (int[] ints : NQueen) {
            System.out.println(Arrays.toString(ints));
        }
        if (i<N){
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (NQueen[j][k] == 0){
                        int[][] copy = new int[N][N];
                        for (int l = 0; l < N; l++) {
                            copy[l] = Arrays.copyOf(NQueen[l],N);
                        }
                        copy[j][k] = 1;
                        for (int m = 1; m < N; m++) {
                            for (int n = 0; n < 8; n++) {
                                int xx = j + dx[n]*m;
                                int yy = k + dy[n]*m;
                                if (0 <= xx && xx < N && 0 <= yy && yy < N){
                                    if (copy[xx][yy] == 1){
                                        return;
                                    }
                                    else
                                        copy[xx][yy] = -1;
                                }
                            }
                        }
                        solution(copy, i+1);
                    }
                }
            }
        }
        else if (i == N){ // i==N
            count++;
        }
    }
    private static void solution(int[][] NQueen, int i, int xy){
//        System.out.println(i + " " + xy);
//        for (int[] ints : NQueen) {
//            System.out.println(Arrays.toString(ints));
//        }
        if (i<N){
            for (int xysum = xy+1; xysum < N*N; xysum++) {
                int j = xysum/N;
                int k = xysum%N;
                if (NQueen[j][k] == 0){
                    int[][] copy = new int[N][N];
                    for (int l = 0; l < N; l++) {
                        copy[l] = Arrays.copyOf(NQueen[l],N);
                    }
                    copy[j][k] = 1;
                    for (int m = 1; m < N; m++) {
                        for (int n = 0; n < 8; n++) {
                            int xx = j + dx[n]*m;
                            int yy = k + dy[n]*m;
                            if (0 <= xx && xx < N && 0 <= yy && yy < N){
                                if (copy[xx][yy] == 1){
                                    return;
                                }
                                else
                                    copy[xx][yy] = -1;
                            }
                        }
                    }
                    solution(copy, i+1, j*N + k);
                }
            }
        }
        else if (i == N){ // i==N
            count++;
        }
    }
    private static void solution(int i, int xy){
        System.out.println(i + " " + xy);
        for (int[] ints : Chess) {
            System.out.println(Arrays.toString(ints));
        }
        putx();
        if (i<N){
            for (int xysum = xy+1; xysum < N*N; xysum++) {
                int j = xysum/N;
                int k = xysum%N;
                if (Chess[j][k] == 0){
                    Chess[j][k] = 1;
                    for (int m = 1; m < N; m++) {
                        for (int n = 0; n < 8; n++) {
                            int xx = j + dx[n]*m;
                            int yy = k + dy[n]*m;
                            if (0 <= xx && xx < N && 0 <= yy && yy < N){
                                if (Chess[xx][yy] == 1){
                                    return;
                                }
                                else
                                    Chess[xx][yy] = -1;
                            }
                        }
                    }
                    removex();
                    solution(i+1, j*N + k);
                    Chess[j][k] = 0;
                    putx();
                }
            }

        }
        else if (i == N){ // i==N
            count++;
        }
        Chess[xy/N][xy%N] = 0;
        removex();
    }
    private static void putx(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Chess[i][j] == 1){
                    for (int m = 1; m < N; m++) {
                        for (int n = 0; n < 8; n++) {
                            int xx = i + dx[n]*m;
                            int yy = j + dy[n]*m;
                            if (0 <= xx && xx < N && 0 <= yy && yy < N){
                                Chess[xx][yy] = -1;
                            }
                        }
                    }
                }
            }
        }
    }
    private static void removex(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Chess[i][j] == -1)
                    Chess[i][j] = 0;
            }
        }
    }
    private static void removexy(int xy){
        int x = xy/N;
        int y = xy%N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            Chess = new int[N][N];
            Chess[0][i] = 1;
            solution(1,i);
        }

//        for (int i = 0; i < N; i++) {
//            int[][] arr = new int[N][N];
//            arr[0][i] = 1;
//            for (int j = 1; j < N; j++) {
//                for (int k = 0; k < 8; k++) {
//                    int xx = 0 + dx[k]*j;
//                    int yy = i + dy[k]*j;
//                    if (0 <= xx && xx < N && 0 <= yy && yy < N){
//                        arr[xx][yy] = -1;
//                    }
//                }
//            }

//            System.out.println(i);
//            for (int[] ints : arr) {
//                System.out.println(Arrays.toString(ints));
//            }
//            solution(arr, 1);
//            solution(arr, 1, i);
//        }

        //combination(new ArrayList<>());
        System.out.println(count);
    }
}
