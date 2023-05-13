import java.io.*;
import java.util.*;

public class Baek17071 {
    private static class Node{
        int x, sec;

        public Node(int x, int sec) {
            this.x = x;
            this.sec = sec;
        }
    }
    private static int N, K, ans = -1;
    private static final int MAX = 500_000;
    private static boolean[][] visit = new boolean[MAX + 1][1001];
    private static int[] ks = new int[1001];
    private static int getK(int sec){
        if (ks[sec] != 0) return ks[sec];
        return ks[sec] = K + sec*(sec+1)/2;
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());;
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ArrayDeque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(N, 0));
        visit[N][0] = true;
        while (!deque.isEmpty()){
            Node cur = deque.pollFirst();
            int k = getK(cur.sec);
            if (k > MAX)
                break;
            if (cur.x == k){
                ans = cur.sec;
                break;
            }

            int next = cur.x * 2;
            if (next <= MAX && !visit[next][cur.sec + 1]){
                deque.addLast(new Node(next, cur.sec + 1));
                visit[next][cur.sec + 1] = true;
            }
            next = cur.x + 1;
            if (next <= MAX && !visit[next][cur.sec + 1]){
                deque.addLast(new Node(next, cur.sec + 1));
                visit[next][cur.sec + 1] = true;
            }
            next = cur.x - 1;
            if (next >= 0 && !visit[next][cur.sec + 1]){
                deque.addLast(new Node(next, cur.sec + 1));
                visit[next][cur.sec + 1] = true;
            }

        }
        System.out.print(ans);
    }
}
