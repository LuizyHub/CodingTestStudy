import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak16946 {
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
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                int num = '0' -s.charAt(j); //wall is -1
                map[i][j] = num;
            }
        }
        ArrayList<Integer> moveCount = new ArrayList<>();
        moveCount.add(0); //index start from 1
        int numberidx = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0){
                    ArrayDeque<Location> deque = new ArrayDeque<>();
                    map[i][j] = numberidx;
                    deque.addLast(new Location(i,j));
                    moveCount.add(0);
                    int count = 1;
                    while (!deque.isEmpty()){
                        Location l = deque.pollFirst();
                        moveCount.set(numberidx,count++);
                        for (int k = 0; k < 4; k++) {
                            int x = l.x + dx[k];
                            int y = l.y + dy[k];
                            if (0 <= x && x < N && 0 <= y && y < M && map[x][y] == 0){
                                map[x][y] = numberidx;
                                deque.addLast(new Location(x,y));

                            }
                        }
                    }
                    numberidx++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1){
                    int ans = 1;
                    HashSet<Integer> indexSet = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (0 <= x && x < N && 0 <= y && y < M && map[x][y] > 0){
                            indexSet.add(map[x][y]);
                        }
                    }
                    for (Integer integer : indexSet) {
                        ans += moveCount.get(integer);
                    }
                    sb.append(ans%10);
                }
                else
                    sb.append('0');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
