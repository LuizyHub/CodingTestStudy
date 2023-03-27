import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek9202 {
    private static class P{
        int x, y;
        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int[] dx = {1, 1, 1, -1, -1, -1, 0, 0};
    private static int[] dy = {1, 0, -1, 1, 0, -1, 1, -1};
    private static class Node{
        int idx = -1;
        Node[] next = new Node[26];
        Node addNode(char c){
            int index = c - 'A';
            if (next[index] == null)
                return next[index] = new Node();
            else
                return next[index];
        }
        Node getNode(char c){
            return next[c - 'A'];
        }
    }
    private static int N, score = 0, count = 0;
    private static String max;
    private static final int[] scores = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    private static String[] words;
    private static char[][] map = new char[4][4];
    private static boolean[] usedWord;
    private static Node node0;
    private static void addWord(int idx){
        String s = words[idx];
        Node cur = node0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cur = cur.addNode(c);
        }
        cur.idx = idx;
    }
    private static boolean[][] visitNode;
    private static void dfs(P p, Node node){
        if (node == null) return;
        if (node.idx != -1 && !usedWord[node.idx]){
            String s = words[node.idx];
            score += scores[s.length()];
            count++;
            if (s.length() > max.length()) {
                max = s;
            }
            else if (s.length() == max.length()) {
                if (s.compareTo(max) < 0)
                    max = s;
            }
            usedWord[node.idx] = true;
        }
        for (int i = 0; i < 8; i++) {
            int nx = p.x + dx[i];
            int ny = p.y + dy[i];
            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;
            Node next = node.getNode(map[nx][ny]);
            if (next != null && !visitNode[nx][ny]) {
                visitNode[nx][ny] = true;
                dfs(new P(nx, ny), next);
                visitNode[nx][ny] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        node0 = new Node();
        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            addWord(i);
        }
        br.readLine();
        int T = Integer.parseInt(br.readLine());
        visitNode = new boolean[4][4];
        while (T-- > 0){
            for (int i = 0; i < 4; i++) {
                map[i] = br.readLine().toCharArray();
            }
            usedWord = new boolean[N];
            count = score = 0;
            max = "";

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    visitNode[i][j] = true;
                    dfs(new P(i, j), node0.getNode((map[i][j])));
                    visitNode[i][j] = false;
                }
            }
            sb.append(score).append(' ').append(max).append(' ').append(count).append('\n');
            if (T > 0)
                br.readLine();
        }

        System.out.println(sb);
    }
}
