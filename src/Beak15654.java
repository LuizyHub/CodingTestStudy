import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Beak15654 {
    private static int[] ints;
    private static int N;
    private static int M;
    private static StringBuilder sb = new StringBuilder();
    private static void combination(ArrayList<Integer> list){
        int len = list.size();
        if (len == M){
            for (Integer integer : list) {
                sb.append(integer).append(' ');
            }
            sb.append('\n');
        } else if (len == 0) {
            for (int i = 0; i < N; i++) {
                ArrayList<Integer> arr = (ArrayList<Integer>)list.clone();
                arr.add(ints[i]);
                combination(arr);
            }
        } else {
            for (int i = 0; i < N; i++) {
                if (list.contains(ints[i]))
                    continue;
                ArrayList<Integer> arr = (ArrayList<Integer>)list.clone();
                arr.add(ints[i]);
                combination(arr);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ints = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ints);
        combination(new ArrayList<>());
        System.out.println(sb);
    }
}
