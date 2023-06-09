import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ18258 {
    private static int N;
    private static ArrayDeque<String> deque;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        deque = new ArrayDeque<>();
        StringTokenizer st;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());

            String order = st.nextToken();

            switch (order.charAt(0)) {
                case 'p' :
                    if (order.length() == 3) {
                        if (deque.isEmpty())
                            sb.append("-1\n");
                        else
                            sb.append(deque.pollFirst()).append('\n');
                    }
                    else
                        deque.addLast(st.nextToken());
                    break;
                case 's' :
                    sb.append(deque.size()).append('\n');
                    break;
                case 'e' :
                    if (deque.isEmpty())
                        sb.append("1\n");
                    else
                        sb.append("0\n");
                    break;
                case 'f' :
                    if (deque.isEmpty())
                        sb.append("-1\n");
                    else
                        sb.append(deque.peekFirst()).append('\n');
                    break;
                case 'b' :
                    if (deque.isEmpty())
                        sb.append("-1\n");
                    else
                        sb.append(deque.peekLast()).append('\n');
                    break;
            }
        }

        System.out.print(sb);
    }
}
