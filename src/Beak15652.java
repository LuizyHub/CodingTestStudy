import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Beak15652 {
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
            for (int i = 1; i <= N; i++) {
                ArrayList<Integer> arr = (ArrayList<Integer>)list.clone();
                arr.add(i);
                combination(arr);
            }
        } else {
            for (int i = list.get(len-1); i <= N; i++) {
                ArrayList<Integer> arr = (ArrayList<Integer>)list.clone();
                arr.add(i);
                combination(arr);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        combination(new ArrayList<>());
        System.out.println(sb);
    }
}
