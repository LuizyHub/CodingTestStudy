import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Beak1761 {
    private static class Node{
        int idx, num;

        public Node(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
    private static Node[][] nodes;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N+1][2];
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if (nodes[from][0] == null)
                nodes[from][0] = new Node(to, num);
            else
                nodes[from][1] = new Node(to, num);

            if (nodes[to][0] == null)
                nodes[to][0] = new Node(from, num);
            else
                nodes[to][1] = new Node(from, num);
        }
        LinkedList<Integer> orderd = new LinkedList<>();
        orderd.add(1);
        orderd.addLast(nodes[1][0].idx);
        if (nodes[1][1] != null)
            orderd.addFirst(nodes[1][1].idx);
        int pre = 1;
        int cur = orderd.peekLast();
        while (nodes[cur][1] != null){
            int tmp = cur;
            if (nodes[cur][0].idx == pre) {
                cur = nodes[cur][1].idx;
            }
            else
                cur = nodes[cur][0].idx;
            orderd.addLast(cur);
            pre = tmp;
        }
        pre = 1;
        cur = orderd.peekFirst();
        while (nodes[cur][1] != null){
            int tmp = cur;
            if (nodes[cur][0].idx == pre)
                cur = nodes[cur][1].idx;
            else
                cur = nodes[cur][0].idx;
            orderd.addFirst(cur);
            pre = tmp;
        }
        int[] index = new int[N+1];
        int[] sum = new int[N+1];
        sum[0] = 0;
        int idx = 1;
        boolean skipFlag = false;
        System.out.println(orderd);
        for (Integer i : orderd) {
            index[i] = idx;
            if (!skipFlag){
                skipFlag = true;
                idx++;
                pre = i;
                continue;
            }
            sum[idx] = sum[idx - 1] + (nodes[idx][0].idx == pre ? nodes[idx][0].num : nodes[idx][1].num);
            pre = i;
        }

        System.out.println(Arrays.toString(index));
        System.out.println(Arrays.toString(sum));
//        M = Integer.parseInt(br.readLine());
//        System.out.println(sb);
    }
}
