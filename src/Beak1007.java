import java.io.*;
import java.util.StringTokenizer;

public class Beak1007 {
    private static int[][] points;
    private static int N;
    private static double answer;
    private static void getMatching(int matchCount, int sumX, int sumY, int idx){
        if (matchCount == N/2){
            double minMatch = Math.sqrt((long)sumX*sumX + (long)sumY*sumY);
            answer = Math.min(answer,minMatch);
        }
        else{
            for (int i = idx; i < N; i++) {
                if (N/2 - matchCount > N - i) break;
                getMatching(matchCount+1, sumX - points[i][0]*2, sumY - points[i][1]*2, i+1);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (T-- != 0){
            N = Integer.parseInt(br.readLine()); // upto 20 , N%2 == 0
            answer = Double.MAX_VALUE;
            points = new int[N][2];
            int sumAllX = 0 , sumAllY = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                sumAllX += points[i][0] = Integer.parseInt(st.nextToken()); // -100_000 ~ 100_000
                sumAllY += points[i][1] = Integer.parseInt(st.nextToken()); // -100_000 ~ 100_000
            }
            getMatching(0,sumAllX,sumAllY,0);
            bw.write(Double.toString(answer));
            bw.newLine();
        }
        bw.flush();
    }
}
