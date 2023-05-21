import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ10845 {
    private static int N;

    private static ArrayDeque<String> deque;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String order = st.nextToken();

            if (order.equals("push")) {
                deque.addLast(st.nextToken());
            }
            else if (order.equals("pop")) {
                if (!deque.isEmpty()) {
                    sb.append(deque.pollFirst()).append('\n');
                }
                else sb.append("-1\n");
            }
            else if (order.equals("size")) {
                sb.append(deque.size()).append('\n');
            }
            else if (order.equals("empty")) {
                if (!deque.isEmpty())
                    sb.append("0\n");
                else
                    sb.append("1\n");
            }
            else if (order.equals("front")) {
                if (!deque.isEmpty()) {
                    sb.append(deque.peekFirst()).append('\n');
                }
                else
                    sb.append("-1\n");
            } else if (order.equals("back")) {
                if (!deque.isEmpty()) {
                    sb.append(deque.peekLast()).append('\n');
                }
                else
                    sb.append("-1\n");
            }
        }

        System.out.print(sb);
    }
}
