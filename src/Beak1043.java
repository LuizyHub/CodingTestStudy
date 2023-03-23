import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Beak1043 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // people
        int M = Integer.parseInt(st.nextToken()); // party

        st = new StringTokenizer(br.readLine());
        ArrayDeque<Integer> trues = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        int num = Integer.parseInt(st.nextToken());

        if (num == 0){
            System.out.println(M);
            System.exit(0);
        }

        for (int i = 0; i < num; i++) { //get true people
            int person = Integer.parseInt(st.nextToken());
            visited[person] = true;
            trues.add(person);
        }

//        HashSet<HashSet<Integer>> sets = new HashSet<>();
        HashSet<Integer>[] sets = new HashSet[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            sets[i] = new HashSet<Integer>();
            for (int j = 0; j < tmp; j++) {
                sets[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        HashSet<Integer> removedParty = new HashSet<>();
        while (!trues.isEmpty()){
            int person = trues.pollFirst();
//            System.out.println(person);
            for (int i = 0; i < M; i++) {
                if (removedParty.contains(i))
                    continue;
                if (sets[i].contains(person)) {
                    for (Integer integer : sets[i]) {
                        if (!visited[integer]) {
//                            System.out.println(i + "case" + integer);
                            trues.addLast(integer);
                            visited[integer] = true;
                        }
                    }
                    removedParty.add(i);
                }
            }
        }
        System.out.println(M - removedParty.size());
    }
}
