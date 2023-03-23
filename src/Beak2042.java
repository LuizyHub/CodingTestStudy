import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Beak2042 {
    private static int N, M, K;
    private static BigInteger[] periodSum;
    private static TreeMap<Integer,BigInteger> changes = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        periodSum = new BigInteger[N+1];
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        periodSum[0] = BigInteger.ZERO;
        for (int i = 1; i <= N; i++) {
            periodSum[i] = periodSum[i-1].add(new BigInteger(br.readLine()));
        }
//        System.out.println(Arrays.toString(periodSum));
        changes.put(0,BigInteger.ZERO);
        for (int i = 0; i < M + K; i++) {
//            System.out.println(changes);
//            for (int j = 1; j <= N; j++) {
//                System.out.print("[1:" + periodSum[j].add(changes.get(changes.floorKey(j))) + "]");
//            }
//            System.out.println();
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("1")){
                int b = Integer.parseInt(st.nextToken());

                BigInteger value = new BigInteger(st.nextToken())
                        .subtract(periodSum[b].subtract(periodSum[b-1]))
                        .add(changes.floorEntry(b-1).getValue());

                if (changes.containsKey(b)){
                    BigInteger pre = changes.remove(b);
                    changes.put(b, value);
                    value = value.subtract(pre);
                }
                else {
                    changes.put(b, value);
                }

                Integer next = changes.ceilingKey(b+1);
                while (next != null){
                    changes.put(next, changes.get(next).add(value));
                    next = changes.ceilingKey(next+1);
                }
            }
            else {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                sb.append(periodSum[to]
                        .add(changes.floorEntry(to).getValue())
                        .subtract(periodSum[from-1])
                        .subtract(changes.floorEntry(from-1).getValue()))
                        .append('\n');
            }
        }
        System.out.println(sb);
    }
}
/*
5 2 2
1
2
3
4
5
1 5 2
2 2 5
1 3 6
2 3 5

M * MlogM
K * logM

 */
