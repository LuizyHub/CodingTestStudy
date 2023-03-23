import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Beak12852 {
    private static int N;
    private static int[] track;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        track = new int[N+1];
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        ArrayList<Integer> order = new ArrayList<>();
        deque.addLast(N);

        while (!deque.isEmpty()){
            int now = deque.pollFirst();

            if (now == 1){
                while (now != N){
                    order.add(now);
                    now = track[now];
                }
                break;
            }

            int next = now - 1;
            if (track[next] == 0){
                track[next] = now;
                deque.addLast(next);
            }
            if (now % 3 == 0 && track[next = now / 3] == 0){
                track[next] = now;
                deque.addLast(next);
            }
            if (now % 2 ==0 && track[next = now / 2] == 0){
                track[next] = now;
                deque.addLast(next);
            }

        }
        sb.append(order.size()).append('\n');
        sb.append(N).append(' ');
        for (int i = order.size() - 1; i >= 0; i--) {
            sb.append(order.get(i)).append(' ');
        }
        System.out.print(sb);
    }
}
