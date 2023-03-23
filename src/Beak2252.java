import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Beak2252 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Integer[] number = new Integer[N];
        for (int i = 0; i < N; i++) {
            number[i] = i;
        }
        int[][] compares = new int[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int small = Integer.parseInt(st.nextToken())-1;
            int tall = Integer.parseInt(st.nextToken())-1;
            compares[small][tall] = 1;
            compares[tall][small] = -1;
        }
        Arrays.sort(number,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return compares[o1][o2];
            }
        });
        for (int i = 0; i < N; i++) {
            sb.append(number[i]+1).append(' ');
        }
        System.out.println(Arrays.toString(number));

        System.out.println(sb);
    }
}
