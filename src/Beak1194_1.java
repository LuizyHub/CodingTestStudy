import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Beak1194_1{
    private static boolean[] keySets = new boolean[0b111111];
    private static int N,M;
    private static int answer = Integer.MAX_VALUE;
    private static char[][] maze; // unchangeable
    private static int wallList = 0b000000;
    private static int[] dx = {1,-1,0,0};
    private static int[] dy = {0,0,1,-1};
    private static class Location{
        public int x;
        public int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static class SimulationPack{
        Location locationMin;
        int hasKeys;
        int moveCount;
        public SimulationPack(Location locationMin, int hasKeys, int moveCount) {
            this.locationMin = locationMin;
            this.hasKeys = hasKeys;
            this.moveCount = moveCount;
        }
    }
    private static class Simulation{
        int[][] wall;
        Location locationMin;
        int hasKeys;
        int moveCount;
        public Simulation(SimulationPack pack){
            this(getSimulation(pack.hasKeys),pack.locationMin, pack.hasKeys, pack.moveCount);
        }
        public Simulation(int[][] wall, Location locationMin, int hasKeys, int moveCount) {
            this.wall = wall;
            this.locationMin = locationMin;
            this.hasKeys = hasKeys;
            this.moveCount = moveCount;
        }
    }
    private static int[][] getSimulation(int haskeys){
        int[][] simulation = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = maze[i][j];
                if (c == '.')
                    simulation[i][j] = 0;
                else if (c == '#')
                    simulation[i][j] = -1;
                else if ('A' <= c && c <= 'F') {
                    if (((haskeys & 0b0000001<<(c-'A')) >> (c-'A')) > 0)
                        simulation[i][j] = 0;
                    else
                        simulation[i][j] = -1;
                }
            }
        }
        return simulation;
    }
    private static void solution(SimulationPack simulationPack){
        ArrayDeque<SimulationPack> simulations = new ArrayDeque<>();
        simulations.addLast(simulationPack);
        loop:
        while (!simulations.isEmpty()){
            Simulation simulation = new Simulation(simulations.pollFirst());
            int[][] wall = simulation.wall;
            Location locationMin = simulation.locationMin;
            int hasKeys = simulation.hasKeys;
            ArrayDeque<Location> deque = new ArrayDeque<>();
            deque.addLast(locationMin);
            wall[locationMin.x][locationMin.y] = 1;
            while (!deque.isEmpty()){
                Location cur = deque.pollFirst();
                if (wall[cur.x][cur.y] + simulation.moveCount > answer)
                    break;
                int key = maze[cur.x][cur.y]-'a';
                if (0 <= key && key <= 6 && ((wallList & (1<<(key)))!=0) ){
                    if (((hasKeys & 0b0000001<<(key)) >> (key)) == 0){
                        int newKeys = hasKeys + (0b0000001<<(key));
                        simulations.addLast(new SimulationPack(cur,newKeys,simulation.moveCount + wall[cur.x][cur.y]-1));
                    }
                }
                if (maze[cur.x][cur.y] == '1'){
                    answer = Math.min(answer, simulation.moveCount + wall[cur.x][cur.y]-1);
                    continue loop;
                }
                for (int i = 0; i < 4; i++) {
                    int x = cur.x + dx[i];
                    int y = cur.y + dy[i];
                    if (0 <= x && x < N && 0 <= y && y < M && wall[x][y] == 0){
                        wall[x][y] = wall[cur.x][cur.y] + 1;
                        deque.addLast(new Location(x,y));
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int keyHas = 0b0000000; // 'a'=0, 'b'=1 ... 'f'=7
        maze = new char[N][M];
        Location locationMin = null;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                maze[i][j] = c;
                if (c == '0')
                    locationMin = new Location(i,j);
                else if ('A' <= c && c <= 'F')
                    wallList = wallList | (1<<(c-'A'));

            }
        }
        keySets[keyHas] = true;
        solution(new SimulationPack(locationMin,keyHas,0));

        if (answer==Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(answer);
    }
}
