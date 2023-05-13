import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Baek1107 {
    private static final int MIN = 0, MAX = 500_000;
    private static int N, M, ans;
    private static ArrayList<Integer> validList;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        validList = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            validList.add(i);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            validList.remove(Integer.parseInt(st.nextToken()));
        }

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(100);
        deque.add(0);
        HashMap<Integer,Integer> visit = new HashMap<>();
        visit.put(100, 0);
        visit.put(0, 0);

        while (!deque.isEmpty()){
            int cur = deque.poll();
            int count = visit.get(cur);

            for (Integer integer : validList) {
                int next;
            }


        }
        System.out.println(ans);
    }
}
