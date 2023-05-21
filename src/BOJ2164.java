import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ2164 {
    private static int N, last;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            deque.addLast(i);
        }

        last = deque.pollFirst();

        while (!deque.isEmpty()) {
            deque.addLast(deque.pollFirst());
            last = deque.pollFirst();
        }

        System.out.print(last);
    }
}
