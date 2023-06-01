import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ1966 {
    private static int T, N, M;
    private static int[] arr, priority;

    private static ArrayDeque<Integer> deque;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N];
            priority = new int[10];

            deque = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int tmp = Integer.parseInt(st.nextToken());
                priority[tmp]++;
                arr[i] = tmp;
                deque.addLast(i);
            }

            int count = 0;
            int cur;
            int max = 9;

            do {
                while (priority[max] == 0) {
                    max--;
                }
                while (arr[cur = deque.pollFirst()] != max) {
                    deque.addLast(cur);
                }
                count++;
                priority[max]--;
            } while (cur != M);

            sb.append(count).append('\n');

        }

        System.out.print(sb);
    }
}
