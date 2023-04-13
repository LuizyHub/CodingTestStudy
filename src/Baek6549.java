import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek6549 {
    static class Block{
        int height, idx;

        public Block(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }
    public static ArrayList<Integer> list;
    static long max;
    static PriorityQueue<Block> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (true){
            list = new ArrayList<>();
            list.add(0);
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            if (!st.hasMoreTokens()) break;
            while (st.hasMoreTokens()){
                list.add(Integer.parseInt(st.nextToken()));
            }

            list.add(0);
            max = 0L;

            queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.height, o1.height));

            for (int i = 0; i < list.size(); i++) {
                int h = list.get(i);


                Block block = queue.poll();
                while (block != null && block.height > h){
                    max = Math.max((long) block.height * (i - block.idx), max);
                    block = queue.poll();
                }

                if (block == null){
                    queue.add(new Block(h, i));
                } else if (block.height < h) {
                    queue.add(block);
                    queue.add(new Block(h, i));
                }
                else { // height is same, put smaller index
                    queue.add(block);
                }

            }
            queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.height, o1.height));

            for (int i = list.size() - 1; i >= 0; i--) {
                int h = list.get(i);


                Block block = queue.poll();
                while (block != null && block.height > h){
                    max = Math.max((long) block.height * (block.idx - i), max);
                    block = queue.poll();
                }

                if (block == null){
                    queue.add(new Block(h, i));
                } else if (block.height < h) {
                    queue.add(block);
                    queue.add(new Block(h, i));
                }
                else { // height is same, put smaller index
                    queue.add(block);
                }

            }

            sb.append(max).append('\n');
        }
        System.out.print(sb);
    }
}