import java.util.ArrayList;
import java.util.Arrays;

public class Solution150364 {
    class Node{
        int num;
        ArrayList<Integer> vertex;
        public Node(int num, ArrayList<Integer> vertex) {
            this.num = num;
            this.vertex = vertex;
        }

        int idx = -1;
        int drop(){
            idx = (idx + 1) % vertex.size();
            return vertex.get(idx);
        }

    }
    Node[] nodes;
    int N;
    public int[] solution(int[][] edges, int[] target) {
        int[] answer = {};
        N = target.length;
        nodes = new Node[N];

        for (int i = 0; i < N; i++) {
            if (target[i] == 0){
                nodes[i] = new Node(i, new ArrayList<>());
            }
            else {
                nodes[i] = new Node(i, null);
            }
        }

        for (int[] ints : edges) {
            nodes[ints[0]-1].vertex.add(ints[1]-1);
        }

        for (Node node : nodes) {
            if (node.vertex != null)
                node.vertex.sort(Integer::compareTo);
        }

        ArrayList<Integer>[] targetList = new ArrayList[N];

        for (int i = 0; i < N; i++)
            targetList[i] = new ArrayList<>();

        int i;
        loop :
        for (i = 0;; i++) {
            Node node = nodes[0];
            while (node.vertex != null){
                node = nodes[node.drop()];
            }
            targetList[node.num].add(i);

            for (int j = 0; j < N; j++) {
                if (target[j] == 0) continue;

                int len = targetList[j].size();

                if (len > target[j]){
                    return new int[]{-1};
                }

                if (!(target[j] <= len * 3)) continue loop;
            }
            break;
        }

        // 개수 <= 목표 <= 개수 * 3
        // 개수 * 3 <= 목표  또는 개수 * 1 <= 목표
        //개수 1개 목표 5 : 1 <= 5 <= 3
        //개수 2개 목표 5 : 2 <= 5 <= 6 : 3,2
        //개수 3개 목표 5 : 3 <= 5 <= 9 : 2,2,1
        //개수 6개 목표 5 : 6 <= 5 <= 18

        answer = new int[i + 1];

        for (int j = 0; j < N; j++) {
            if (target[j] == 0) continue;
            int size = targetList[j].size();
            for (int k = 0; k < size; k++) {
                if ((target[j] - 1) <= (size - k - 1) * 3){
                    answer[targetList[j].get(k)] = 1;
                    target[j] -= 1;
                }
                else if ((target[j] - 2) <= (size - k - 1) * 3){
                    answer[targetList[j].get(k)] = 2;
                    target[j] -= 2;
                }
                else {
                    answer[targetList[j].get(k)] = 3;
                    target[j] -= 3;
                }
            }

        }

        return answer;
    }

    public static void main(String[] args) {
        Solution150364 s = new Solution150364();
        System.out.println(Arrays.toString(s.solution(new int[][]{{2, 4}, {1, 2}, {6, 8}, {1, 3}, {5, 7}, {2, 5}, {3, 6}, {6, 10}, {6, 9}}, new int[]{0, 0, 0, 3, 0, 0, 5, 1, 2, 3})));
        System.out.println(Arrays.toString(s.solution(new int[][]{{1, 2}, {1, 3}}, new int[]{0, 7, 3})));
        System.out.println(Arrays.toString(s.solution(new int[][]{{1, 3}, {1, 2}}, new int[]{0, 7, 1})));
    }
}
