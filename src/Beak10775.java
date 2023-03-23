import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Beak10775 {
    private static int G, P;
    private static int[] gates;
    private static int putEmpty(int now){
        if (now == 0)
            return -1;
        if (gates[now] == -1){
            gates[now] = now -1;
            return now-1;
        }
        else {
            return gates[now] = putEmpty(gates[now]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        gates = new int[G+1];
        Arrays.fill(gates,-1);
        P = Integer.parseInt(br.readLine());
        int i = 0;
        for (; i < P; i++) {
            if (putEmpty(Integer.parseInt(br.readLine())) == -1)
                break;
        }
        System.out.println(i);
    }
}
