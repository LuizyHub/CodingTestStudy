import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak2618 {
    static class P{
        public static P Pnull = new P(0,0);
        public int x, y;

        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("P{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append('}');
            return sb.toString();
        }
    }
    static class Simulation{
        public P p1, p2;
        public int cost, idx; // %2 == 0 s1, %2 == 1 s2
        public Simulation(P p1, P p2, int cost) {
            this.p1 = p1;
            this.p2 = p2;
            this.cost = cost;
            this.idx = 0;
        }

        public Simulation(P p1, P p2, int cost, int idx) {
            this.p1 = p1;
            this.p2 = p2;
            this.cost = cost;
            this.idx = idx;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("\nSimulation{");
            sb.append("p1=").append(p1);
            sb.append(", p2=").append(p2);
            sb.append(", cost=").append(cost);
            sb.append(", idx=").append(idx/2);
            sb.append(", idx=").append(idx%2);
            sb.append('}');
            return sb.toString();
        }
    }
    private static int getCost(P p1, P p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
    private static int N, W, sum = 0;
    private static int[] costs; // moveSum from first case;
    private static P[] Ws;
    private static Simulation[] s1, s2;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        Ws = new P[W + 1];
        Ws[0] = new P(1,1);
        costs = new int[W + 1];
        s1 = new Simulation[W + 1];
        s1[0] = new Simulation(new P(1,1), new P(N, N), 0, 0);
        s1[0].idx = 0;
        s2 = new Simulation[W + 1];
        s2[0] = new Simulation(new P(1,1), new P(N, N), 0, 0);
        s2[0].idx = 0;
        StringTokenizer st;
        for (int i = 1; i <= W; i++) {
            st = new StringTokenizer(br.readLine());
            Ws[i] = new P(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            costs[i] = costs[i - 1] + getCost(Ws[i-1], Ws[i]);
        }
        int changeCostValue = 2*N - 2*costs[1];

        for (int i = 1; i <= W; i++) {
            s1[i] = new Simulation(Ws[i], s1[i - 1].p2, s1[i - 1].cost + getCost(Ws[i - 1], Ws[i]), (i-1)*2 + 0);
            s2[i] = new Simulation(s2[i - 1].p1, Ws[i], s2[i - 1].cost + getCost(Ws[i - 1], Ws[i]), (i-1)*2 + 1);
            int tmpCost;

            tmpCost = s2[i - 1].cost
                    + getCost(s2[i - 1].p1, Ws[i]); // p1 move
            if (tmpCost < s1[i].cost){
                s1[i].p2 = s2[i - 1].p2;
                s1[i].cost = tmpCost;
                s1[i].idx = (i-1)*2 + 1;
            }

            tmpCost = s1[i - 1].cost
                    + getCost(s1[i - 1].p2, Ws[i]); // p2 move
            if (tmpCost < s2[i].cost){
                s2[i].p1 = s1[i - 1].p1;
                s2[i].cost = tmpCost;
                s2[i].idx = (i-1)*2 + 0;
            }


            for (int j = 0; j < i - 1; j++) {
                tmpCost = s1[j].cost
                        + getCost(s1[j].p1, Ws[i]) // p1 move
                        + getCost(s1[j].p2, Ws[j + 1]) + costs[i - 1] - costs[j + 1]; // p2 move
                if (tmpCost < s1[i].cost){
                    s1[i].p2 = Ws[i - 1];
                    s1[i].cost = tmpCost;
                    s1[i].idx = j*2 + 0;
                }

                tmpCost = s2[j].cost
                        + getCost(s2[j].p1, Ws[i]) // p1 move
                        + costs[i - 1] - costs[j]; // p2 move
                if (tmpCost < s1[i].cost){
                    s1[i].p2 = Ws[i - 1];
                    s1[i].cost = tmpCost;
                    s1[i].idx = j*2 + 1;
                }

                tmpCost = s2[j].cost
                        + getCost(s2[j].p1, Ws[j + 1]) + costs[i - 1] - costs[j + 1] // p1 move
                        + getCost(s2[j].p2, Ws[i]); // p2 move
                if (tmpCost < s2[i].cost){
                    s2[i].p1 = Ws[i -1];
                    s2[i].cost = tmpCost;
                    s2[i].idx = j*2 + 1;
                }

                tmpCost = s1[j].cost
                        + costs[i - 1] - costs[j]
                        + getCost(s1[j].p2, Ws[i]); // p2 move
                if (tmpCost < s2[i].cost){
                    s2[i].p1 = Ws[i -1];
                    s2[i].cost = tmpCost;
                    s2[i].idx = j*2 + 0;
                }
            }
        }

//        System.out.println(Arrays.toString(costs));
//        System.out.println(Arrays.toString(s1));
//        System.out.println(Arrays.toString(s2));

        int[] tracks = new int[W + 1];

        boolean isS1 = false;
        int idx = 0;

        if (s2[W].cost < s1[W].cost){
            sb.append(s2[W].cost).append('\n');
            isS1 = false;
            idx = s2[W].idx;
        }
        else {
            sb.append(s1[W].cost).append('\n');
            isS1 = true;
            idx = s1[W].idx;
        }
        for (int i = W; i > 0;) {
            if (isS1){
                tracks[i] = 1;
                if (idx % 2 == 0){
                    for (int j = idx / 2 + 1; j < i; j++) {
                        tracks[j] = 2;
                    }
                    i = idx / 2;
                    idx = s1[i].idx;
                    isS1 = true;
                }
                else {
                    for (int j = idx / 2 + 1; j < i; j++) {
                        tracks[j] = 2;
                    }
                    i = idx / 2;
                    idx = s2[i].idx;
                    isS1 = false;
                }
            }
            else {
                tracks[i] = 2;
                if (idx % 2 == 1){
                    for (int j = idx / 2 + 1; j < i; j++) {
                        tracks[j] = 1;
                    }
                    i = idx / 2;
                    idx = s2[i].idx;
                    isS1 = false;
                }
                else {
                    for (int j = idx / 2 + 1; j < i; j++) {
                        tracks[j] = 1;
                    }
                    i = idx / 2;
                    idx = s1[i].idx;
                    isS1 = true;
                }
            }
        }

        for (int i = 1; i <= W; i++) {
            sb.append(tracks[i]).append('\n');
        }

        System.out.println(sb);
    }
}
