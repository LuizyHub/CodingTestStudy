import java.io.*;
import java.util.Arrays;

public class Beak2448 {
    private static BufferedWriter bw;
    private static int N, K;
    private static char[][] stars;
    private static int log2(int i){
        if (i==1)
            return 1;
        int ans = 0;
        while (i != 1){
            i >>= 1;
            ans++;
        }
        return ans;
    }

    private static void setStars(int mid){
        stars[0][mid] = '*';
        stars[1][mid-1] = '*';
        stars[1][mid+1] = '*';
        stars[2][mid-2] = '*';
        stars[2][mid-1] = '*';
        stars[2][mid] = '*';
        stars[2][mid+1] = '*';
        stars[2][mid+2] = '*';
    }
    private static void writeStars() throws IOException {
        bw.write(stars[0]);
        bw.newLine();
        bw.write(stars[1]);
        bw.newLine();
        bw.write(stars[2]);
        bw.newLine();
        refreshStars();
    }
    
    private static void refreshStars(){
        for (char[] star : stars) {
            Arrays.fill(star, ' ');
        }
    }

//    private static void draw(int i, int mid){
//        if (i==3){
//            writeStars(mid);
//        }
//
//        draw(i, mid-3);
//        draw(i, mid+3);
//    }
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        stars = new char[3][N*2];
        refreshStars();


        for (int i = N; i >= 3; i>>=1) {

            setStars(N);
            writeStars();

        }

//        for (int i = 0; i <log2(N/3); i++) {
//            int j = 0;
//            do{
//                setStars(N-3*(1<<i));
//                setStars(N+3*(1<<i));
//                writeStars();
//                j++;
//            }while (j<i);
//        }
        
//        setStars(N);
//        writeStars();
//
//        for (int i = 6; i <= N; i <<= 1) {
//
//
//            setStars(N-(i>>1));
//            setStars(N+(i>>1));
//
//            for (int j = 6; j <= i; j <<= 1) {
//
//                setStars(N-(i>>1));
//                setStars(N+(i>>1));
//                writeStars();
//                for (int k = 2; k < log2(j/3); k++) {
//                    System.out.println(k);
//                    setStars(N-(i>>1));
//                    setStars(N+(i>>1));
//                    writeStars();
//                }
//            }
//        }


//        K = log2(N/3);
//
//        for (int i = 1; i <= K; i++) {
//            for (int j = 0; j < i; j++) {
//                for (int k = 0; k < 1<<i; k <<= 1) {
//                    setStars(N - 3*(1<<i)  );
//                }
//                writeStars();
//            }
////            for (int j = 1; j <= 1<<i; j++) {
////                setStars(N/2);
////                setStars(N/2*3);
////                writeStars();
////            }
//        }


        bw.flush();
    }
}
