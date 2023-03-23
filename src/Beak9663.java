import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Beak9663 {
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Chess = new int[N][N];
//        ArrayList<Integer> arr = new ArrayList<>();
//        arr.add(1);
//        arr.add(7);
//        arr.add(8);
//        arr.add(14);
//        combination(arr);
        combination(new ArrayList<>());
        System.out.println(count);
    }
}
