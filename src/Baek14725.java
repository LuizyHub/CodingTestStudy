import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek14725 {
    private static class Node{
        String s;
        ArrayList<Node> v = new ArrayList<>();
        public Node(String s) {
            this.s = s;
        }
    }
    private static int N;
    private static StringBuilder sb;
    private static void dfs(Node now, int depth){
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        sb.append(now.s).append('\n');
        now.v.sort((o1, o2) -> o1.s.compareTo(o2.s));
        for (Node node : now.v) {
            dfs(node, depth + 1);
        }

    }
    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Node node0 = new Node("");
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int depth = Integer.parseInt(st.nextToken());
            Node now = node0;
            for (int j = 0; j < depth; j++) {
                String s = st.nextToken();
                int idx = -1;
                for (int k = 0; k < now.v.size(); k++) {
                    if (now.v.get(k).s.equals(s)){
                        idx = k;
                        break;
                    }
                }
                if (idx != -1){
                    now = now.v.get(idx);
                }
                else {
                    Node next = new Node(s);
                    now.v.add(next);
                    now = next;
                }
            }
        }
        node0.v.sort((o1, o2) -> o1.s.compareTo(o2.s));
        for (Node node : node0.v) {
            dfs(node, 0);
        }
        System.out.println(sb);
    }
}
