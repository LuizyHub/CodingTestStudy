import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Beak7579 {
    private static int N, M;
    private static int sumC = 0;
    private static ArrayList<App> apps = new ArrayList<>();

    private static class App implements Comparable<App> {
        public int m;
        public int c;
        public double r;

        public App(int m, int c) {
            this.m = m;
            this.c = c;
            r = (double)m/c;
        }

        @Override
        public int compareTo(App o) {
            int returnValue = Integer.compare(this.c, o.c);
            if (returnValue == 0)
                return Double.compare(o.r, this.r);
            return returnValue;
        }

        @Override
        public String toString() {
            return "App{" +
                    "m=" + m +
                    ", c=" + c +
                    ", r=" + r +
                    '}';
        }
    }
    private static int solution() {

        int left = 1;
        int right = sumC;

        while (left < right) {
            int mid = (left + right) / 2;
            if (isPossible(mid,0, 0)) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return left;
    }
    private static boolean isPossible(int target,int start, int sum) {
        if (sum >= M)
            return true;
        int pre = 0;
        for (int i = start; i < apps.size(); i++) {
            App app = apps.get(i);
            if (app.c == pre) continue;
            if (app.c > target) break;
            else if (app.c == target){
                if (sum + app.m >= M)
                    return true;
            }
            else {
                if (isPossible(target - app.c, i + 1, sum + app.m))
                    return true;
            }
            pre = app.c;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1,st2;
        st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());
        st1 = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int m = Integer.parseInt(st1.nextToken());
            int c = Integer.parseInt(st2.nextToken());
            if (c == 0){
                M -= m;
                continue;
            }
            sumC += c;
            apps.add(new App(m,c));
        }
        apps.sort(App::compareTo);
//        System.out.println(apps);
        System.out.println(solution());


    }
}
