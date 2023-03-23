import java.util.Scanner;

public class Beak1644 {
    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        boolean[] pn =  new boolean[n+1]; //false = prime number, true = not prime number
        int tag = (int)Math.sqrt(n+1);
        for (int i = 2; i <= tag; i++) {
            if(pn[i])
                continue;
            for (int j = i+i; j <n+1 ; j+=i) {
                pn[j] = true;
            }
        }
        long[] sum = new long[n];
        int cnt = 0;
        for (int i = 2; i < n+1; i++) {
            if (!pn[i]) {
                sum[cnt+1] = sum[cnt] + i;
                cnt++;
            }
        }
        int ans = 0;
        for (int i = 0; i < cnt; i++) {
            for (int j = i+1; j <= cnt; j++) {
                long dif = sum[j] - sum[i];
                if (dif == n) {
                    ans++;
                    break;
                }
                else if (dif>n)
                    break;
            }
        }
        System.out.println(ans);
    }
}
