import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ10828 {
    private static int N;

    private static ArrayDeque<String> stack;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        stack = new ArrayDeque<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            switch (order.charAt(0)) {
                case 'p':
                    if (order.length() == 4) {
                        stack.addLast(st.nextToken());
                    } else {
                        if (stack.isEmpty())
                            sb.append("-1").append('\n');
                        else
                            sb.append(stack.pollLast()).append('\n');
                    }
                    break;
                case 's':
                    sb.append(stack.size()).append('\n');
                    break;
                case 'e':
                    if (stack.isEmpty())
                        sb.append('1').append('\n');
                    else
                        sb.append('0').append('\n');
                    break;
                case 't':
                    if (stack.isEmpty())
                        sb.append("-1").append('\n');
                    else
                        sb.append(stack.peekLast()).append('\n');
                    break;
            }
        }

        System.out.print(sb);
    }
}
