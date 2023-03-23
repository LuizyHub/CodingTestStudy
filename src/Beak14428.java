import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak14428 {
    private static int N, M, startIdx;
    private static class Node{
        int num, idx;
        public Node() {this(Integer.MAX_VALUE,0);}
        public Node(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
    private static Node MaxNode = new Node(Integer.MAX_VALUE, 0);
    private static Node[] nodeTree;
    private static int log(int num){
        int ans = 0;
        while (num > 0){
            ans++;
            num >>= 1;
        }
        return ans;
    }
    private static long query(int start, int end){
        Node minNode = MaxNode;
        start = start + startIdx - 1;
        end = end + startIdx - 1;
        while (start <= end){
            if (start % 2 == 1){
                if (nodeTree[start].num == minNode.num)
                    minNode = (nodeTree[start].idx < minNode.idx ? nodeTree[start] : minNode);
                else if (nodeTree[start].num < minNode.num)
                    minNode = nodeTree[start];
            }
            if (end % 2 == 0){
                if (nodeTree[end].num == minNode.num)
                    minNode = (nodeTree[end].idx < minNode.idx ? nodeTree[end] : minNode);
                else if (nodeTree[end].num < minNode.num)
                    minNode = nodeTree[end];
            }

            start = (start + 1) / 2;
            end = (end - 1) / 2;

        }
        return minNode.idx;
    }
    private static void updateTree(int idx, int num){
        Node node = new Node(num, idx);
        idx = startIdx + idx - 1;
        nodeTree[idx] = node;
        idx >>= 1;
        while (idx > 0){
            if (nodeTree[idx*2].num <= (nodeTree[idx*2 + 1] == null ? Integer.MAX_VALUE : nodeTree[idx*2 + 1].num))
                nodeTree[idx] = nodeTree[idx*2];
            else
                nodeTree[idx] = nodeTree[idx*2 + 1];

            idx >>= 1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        int depth = log(N);
        startIdx = 1<<depth;
        nodeTree = new Node[(startIdx<<1)+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            updateTree(i,Integer.parseInt(st.nextToken()));
        }
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("1")){
                updateTree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            else {
                sb.append(query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append('\n');
            }
        }

        System.out.println(sb);
    }
}
