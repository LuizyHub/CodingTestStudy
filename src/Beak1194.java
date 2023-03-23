import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Beak1194 {
    private static int N,M;
    private static int answer = Integer.MAX_VALUE;
    private static char[][] maze; // unchangeable
    private static boolean[] wallList = new boolean[7];
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
        boolean[] hasKeys;
        int moveCoount;

        public SimulationPack(Location locationMin, boolean[] hasKeys, int moveCoount) {
            this.locationMin = locationMin;
            this.hasKeys = hasKeys;
            this.moveCoount = moveCoount;
        }
    }
    private static class Simulation{
        int[][] wall;
        Location locationMin;
        boolean[] hasKeys;
        int moveCount;
        public Simulation(SimulationPack pack){
            this(getSimulation(pack.hasKeys),pack.locationMin, pack.hasKeys, pack.moveCoount);
        }

        public Simulation(int[][] wall, Location locationMin, boolean[] hasKeys, int moveCount) {
            this.wall = wall;
            this.locationMin = locationMin;
            this.hasKeys = hasKeys;
            this.moveCount = moveCount;
        }
    }
    private static int[][] getSimulation(boolean[] haskeys){
        int[][] simulation = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = maze[i][j];
                if (c == '.')
                    simulation[i][j] = 0;
                else if (c == '#')
                    simulation[i][j] = -1;
                else if ('A' <= c && c <= 'F') {
                    if (haskeys[c-'A'])
                        simulation[i][j] = 0;
                    else
                        simulation[i][j] = -1;
                }
            }
        }
        return simulation;
    }
    private static boolean[] copyHasKeys(boolean[] hasKeys){
        boolean[] newKeys = new boolean[7];
        for (int i = 0; i < 7; i++) {
            if (hasKeys[i])
                newKeys[i] = true;
        }
        return newKeys;
    }
    private static void solution(SimulationPack simulationPack){
        ArrayDeque<SimulationPack> simulations = new ArrayDeque<>();
        simulations.addLast(simulationPack);
        loop:
        while (!simulations.isEmpty()){
            Simulation simulation = new Simulation(simulations.pollFirst());
            int[][] wall = simulation.wall;
            Location locationMin = simulation.locationMin;
            boolean[] hasKeys = simulation.hasKeys;
//            boolean[] simulationCheck = copyHasKeys(hasKeys);
            ArrayDeque<Location> deque = new ArrayDeque<>();
            deque.addLast(locationMin);

            wall[locationMin.x][locationMin.y] = 1;
            while (!deque.isEmpty()){
                Location cur = deque.pollFirst();
                if (wall[cur.x][cur.y] + simulation.moveCount > answer)
                    break;
                if ('a' <= maze[cur.x][cur.y] && maze[cur.x][cur.y] <= 'f' && wallList[maze[cur.x][cur.y]-'a']){
                    if (!hasKeys[maze[cur.x][cur.y]-'a']){
//                        simulationCheck[maze[cur.x][cur.y]-'a'] = true;
                        boolean[] newKeys = copyHasKeys(hasKeys);
                        newKeys[maze[cur.x][cur.y]-'a'] = true;
                        int[][] newWall = getSimulation(newKeys);
//                        Simulation newSimulation = new Simulation(newWall,cur,newKeys,simulation.moveCount + wall[cur.x][cur.y]-1);
                        simulations.addLast(new SimulationPack(cur,newKeys,simulation.moveCount + wall[cur.x][cur.y]-1));
//                        solution(newSimulation);
                    }
                }
                if (maze[cur.x][cur.y] == '1'){
//                    for (int[] ints : wall) {
//                        System.out.println(Arrays.toString(ints));
//
//                    }
//                    System.out.println(simulation.moveCount);
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
//        int[][] wall = simulation.wall;
//        Location locationMin = simulation.locationMin;
//        boolean[] hasKeys = simulation.hasKeys;
//        ArrayDeque<Location> deque = new ArrayDeque<>();
//        deque.addLast(locationMin);
//
//        wall[locationMin.x][locationMin.y] = 1;
//        while (!deque.isEmpty()){
//            Location cur = deque.pollFirst();
//
//            if ('a' <= maze[cur.x][cur.y] && maze[cur.x][cur.y] <= 'f'){
//                if (!hasKeys[maze[cur.x][cur.y]-'a']){
//                    boolean[] newKeys = copyHasKeys(hasKeys);
//                    newKeys[maze[cur.x][cur.y]-'a'] = true;
//                    int[][] newWall = getSimulation(newKeys);
//                    Simulation newSimulation = new Simulation(newWall,cur,newKeys,simulation.moveCount + wall[cur.x][cur.y]-1);
//                    solution(newSimulation);
//                }
//            }
//            if (maze[cur.x][cur.y] == '1'){
//                answer = Math.min(answer, simulation.moveCount + wall[cur.x][cur.y]-1);
//            }
//
//
//            for (int i = 0; i < 4; i++) {
//                int x = cur.x + dx[i];
//                int y = cur.y + dy[i];
//                if (0 <= x && x < N && 0 <= y && y < M && wall[x][y] == 0){
//                    wall[x][y] = wall[cur.x][cur.y] + 1;
//                    deque.addLast(new Location(x,y));
//                }
//
//            }
//        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        boolean[] keyHas = new boolean[7]; // 'a'=0, 'b'=1 ... 'f'=7
        maze = new char[N][M];
        int[][] wall = new int[N][M]; // wall is -1, can be passed is 0
        Location locationMin = null;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                maze[i][j] = c;
                if (c == '0')
                    locationMin = new Location(i,j);
                else if (c == '#')
                    wall[i][j] = -1;
                else if ('A' <= c && c <= 'F'){
                    wall[i][j] = -1;
                    wallList[c-'A'] = true;

                }

            }
        }

//        Simulation simulation = new Simulation(wall,locationMin,keyHas,0);
        solution(new SimulationPack(locationMin,keyHas,0));

        if (answer==Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(answer);
    }
}
