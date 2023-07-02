import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ28279 {
    private static int N;
    private static ArrayDeque<String> deque;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        deque = new ArrayDeque<>();
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        String s;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            switch (c) {
                case '1' :
                    deque.addFirst(st.nextToken());
                    break;
                case '2' :
                    deque.addLast(st.nextToken());
                    break;
                case '3' :
                    s = deque.pollFirst();
                    sb.append(s == null ? "-1" : s).append('\n');
                    break;
                case '4' :
                    s = deque.pollLast();
                    sb.append(s == null ? "-1" : s).append('\n');
                    break;
                case '5' :
                    sb.append(deque.size()).append('\n');
                    break;
                case '6' :
                    sb.append(deque.isEmpty() ? "1" : "0").append('\n');
                    break;
                case '7' :
                    s = deque.peekFirst();
                    sb.append(s == null ? "-1" : s).append('\n');
                    break;
                case '8' :
                    s = deque.peekLast();
                    sb.append(s == null ? "-1" : s).append('\n');
                    break;
            }
        }
        System.out.print(sb);
    }
}