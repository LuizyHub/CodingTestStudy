import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Beak2143 {
    private static int T,N,M;
    private static int[] A,B,periodSumA, periodSumB;
    private static long count = 0;
    private static int binarySearch(int[] a, int key){
        return binarySearch0(a,0,a.length,key);
    }
    private static int binarySearch0(int[] a, int fromIndex, int toIndex,
                                     int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            if (low == high && a[low] == key)
                return low;
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else //midVal == key
                high = mid;
        }
        return -(low+1);  // key not found.
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        periodSumA = new int[(N+1)*N/2];
        int periodSumIdx = 0;
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = A[i - 1] + Integer.parseInt(st.nextToken());
            for (int j = 0; j < i; j++) {
                periodSumA[periodSumIdx++] = A[i] - A[j];
            }
        }
        A = null;

        M = Integer.parseInt(br.readLine());
        B = new int[M+1];
        periodSumB = new int[(M+1)*M/2];
        periodSumIdx = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            B[i] = B[i - 1] + Integer.parseInt(st.nextToken());
            for (int j = 0; j < i; j++) {
                periodSumB[periodSumIdx++] = B[i] - B[j];
            }
        }
        B = null;
        Arrays.sort(periodSumB);

        for (int i = 0; i < periodSumA.length; i++) {
            int need = T - periodSumA[i];
            int idx = binarySearch(periodSumB, need);
            if (idx >= 0){
                int tmp = binarySearch(periodSumB, need +1);
                if (tmp >=0)
                    count += (tmp - idx);
                else
                    count += (-tmp - idx -1);
            }
        }

        System.out.println(count);
    }
}
