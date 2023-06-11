import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1004 {
    private static int T, n, x1, y1, x2, y2, parent1, parent2;
    private static int[] x, y, r, parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            n = Integer.parseInt(br.readLine());
            x = new int[n];
            y = new int[n];
            r = new int[n];
            parents = new int[n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                x[i] = Integer.parseInt(st.nextToken());
                y[i] = Integer.parseInt(st.nextToken());
                r[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < n; i++) {
                int parent = -1;
                double minDistance = Double.MAX_VALUE;

                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    double distance = r[j] - Math.sqrt((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j])) - r[i];
                    if (distance < 0) continue;

                    if (distance < minDistance) {
                        parent = j;
                        minDistance = distance;
                    }

                }

                parents[i] = parent;
            }

            parent1 = -1;
            int minDistance = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                int distance = r[i] * r[i] - ((x[i] - x1) * (x[i] - x1) + (y[i] - y1) * (y[i] - y1));
                if (distance < 0) continue;

                if (distance < minDistance) {
                    parent1 = i;
                    minDistance = distance;
                }
            }

            parent2 = -1;
            minDistance = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                int distance = r[i] * r[i] - ((x[i] - x2) * (x[i] - x2) + (y[i] - y2) * (y[i] - y2));
                if (distance < 0) continue;

                if (distance < minDistance) {
                    parent2 = i;
                    minDistance = distance;
                }
            }

            ArrayList<Integer> parents1 = new ArrayList<>();

            parents1.add(parent1);

            while (parent1 != -1) {
                parent1 = parents[parent1];
                parents1.add(parent1);
            }

            ArrayList<Integer> parents2 = new ArrayList<>();

            parents2.add(parent2);

            while (parent2 != -1) {
                parent2 = parents[parent2];
                parents2.add(parent2);
            }

            for (int i = 0; i < parents1.size(); i++) {
                int idx = parents2.indexOf(parents1.get(i));
                if (idx >= 0) {
                    sb.append(idx + i).append('\n');
                    break;
                }
            }
        }

        System.out.print(sb);
    }
}
