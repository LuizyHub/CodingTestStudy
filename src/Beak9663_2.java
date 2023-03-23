import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak9663_2 {
    private static int N;
    private static int count = 0;
    private static int[] Queens;
    private static void solution(int i){
        if (i == N){
            count++;
        }
        else if (i == 0){
            for (int j = 0; j < N; j++) {
                Queens[0] = j;
                solution(1);
            }
        }
        else {
            loop:
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < i; k++) {
                    if (j == Queens[k] || i + j == k + Queens[k] || i - j == k - Queens[k])
                        continue loop;
                }
                Queens[i] = j;
                solution(i+1);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Queens = new int[N];
        solution(0);
        System.out.println(count);
    }
}
