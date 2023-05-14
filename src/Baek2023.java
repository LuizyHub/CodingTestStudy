import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class Baek2023 {
    private static int N;
    private static ArrayList<Integer>[] lists;
    public static void main(String[] args) throws IOException {
        N = System.in.read()-'0';
        StringBuilder sb = new StringBuilder();
        lists = new ArrayList[N];
        lists[0] = new ArrayList();
        lists[0].add(2);
        lists[0].add(3);
        lists[0].add(5);
        lists[0].add(7);

        for (int i = 1; i < N; i++) {
            lists[i] = new ArrayList();
            for (int num : lists[i-1]) {
                for (int j = 1; j < 10; j+=2) {
                    int next = (num<<3) + (num<<1) + j;
                    if (BigInteger.valueOf(next).isProbablePrime(10))
                        lists[i].add(next);
                }
            }
        }

        for (int i : lists[N-1])
            sb.append(i).append('\n');

        System.out.print(sb);
    }
}
