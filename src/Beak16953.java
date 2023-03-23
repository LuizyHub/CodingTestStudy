import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Beak16953 {
    private static  int A;
    private static  int B;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        bfs();
    }

    private static void bfs() {
        HashMap<Integer,Integer> set = new HashMap<>();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(B);
        set.put(B,1);
        while (!deque.isEmpty()){
            int cnt = deque.pollFirst();
            if (cnt == A) {
                System.out.println(set.get(cnt));
                return;
            }
            if (cnt%2 == 0){
                int tmp = cnt/2;
                if (tmp >= A  && !set.containsKey(tmp)){
                    set.put(tmp, set.get(cnt)+1);
                    deque.addLast(tmp);
                }
            }
            if (cnt%10 == 1){
                int tmp = cnt/10;
                if (tmp >= A  && !set.containsKey(tmp)){
                    set.put(tmp, set.get(cnt)+1);
                    deque.addLast(tmp);
                }
            }
        }
        System.out.println(-1);
    }
}
