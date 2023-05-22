import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ10866 {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayDeque<String> deque = new ArrayDeque<>();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String s = st.nextToken();

            if (s.equals("push_front")) {
                deque.addFirst(st.nextToken());
            } else if (s.equals("push_back")) {
                deque.addLast(st.nextToken());
            } else if (s.equals("pop_front")) {
                if (deque.isEmpty())
                    sb.append("-1").append('\n');
                else
                    sb.append(deque.pollFirst()).append('\n');
            } else if (s.equals("pop_back")) {
                if (deque.isEmpty())
                    sb.append("-1").append('\n');
                else
                    sb.append(deque.pollLast()).append('\n');
            } else if (s.equals("size")) {
                sb.append(deque.size()).append('\n');
            } else if (s.equals("empty")) {
                if (deque.isEmpty())
                    sb.append('1').append('\n');
                else
                    sb.append('0').append('\n');
            } else if (s.equals("front")) {
                if (deque.isEmpty())
                    sb.append("-1").append('\n');
                else
                    sb.append(deque.peekFirst()).append('\n');
            } else if (s.equals("back")) {
                if (deque.isEmpty())
                    sb.append("-1").append('\n');
                else
                    sb.append(deque.peekLast()).append('\n');
            }
        }

        System.out.print(sb);
    }
}
