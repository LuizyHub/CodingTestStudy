import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Beak18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        HashMap<Integer,Integer> hm = new HashMap<>();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }
        int[] sortedArr = Arrays.copyOf(arr,N);
        Arrays.sort(sortedArr);

        int order = 0;
        hm.put(sortedArr[0],order++);
        for (int i = 1; i < N; i++) {
            if (sortedArr[i-1] != sortedArr[i])
                hm.put(sortedArr[i],order++);
        }

        for (int i : arr) {
            sb.append(hm.get(i)).append(' ');
        }

        System.out.println(sb);
    }
}
