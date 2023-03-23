import java.io.*;
import java.util.ArrayDeque;

public class Beak12852_1 {
    private static int N, count = 0;
    private static int[] track;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        track = new int[N+1];
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);

        while (!deque.isEmpty()){
            int now = deque.pollFirst();

            if (now == N){
                while (now != 1){
                    sb.append(now).append(' ');
                    count++;
                    now = track[now];
                }
                sb.append(1).append(' ');
                break;
            }

            int next = now + 1;
            if (track[next] == 0){
                track[next] = now;
                deque.addLast(next);
            }
            if ((next = now * 3) <= N && track[next] == 0){
                track[next] = now;
                deque.addLast(next);
            }
            if ((next = now * 2) <= N && track[next] == 0){
                track[next] = now;
                deque.addLast(next);
            }

        }
        System.out.println(count + "\n" + sb);
    }
}
