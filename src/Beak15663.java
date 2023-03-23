import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Beak15663 {
    private static int[] ints,arr;
    private static boolean[] visited;
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
            ArrayList<Integer> arr = (ArrayList<Integer>)list.clone();
            arr.add(ints[0]);
            combination(arr);
            for (int i = 1; i < N; i++) {
                if (ints[i-1] == ints[i])
                    continue;
                arr = (ArrayList<Integer>)list.clone();
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
    private static void backtracking(int num) {
        if (num == M) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        int value = -1;
        for (int i = 0; i < N; i++) {
            if (!visited[i] && (value != ints[i])) {
                value = ints[i];
                visited[i] = true;
                arr[num] = ints[i];
                backtracking(num + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ints = new int[N];
        arr = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ints);
        backtracking(0);
//        combination(new ArrayList<>());
//        System.out.println(sb);
    }
}
