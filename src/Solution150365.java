import java.util.Arrays;

public class Solution150365 {
    char[] dir = {'u', 'r', 'l', 'd'};
    int[] dx = {1, 0, 0, -1}; // up, right, left, down
    int[] dy = {0, -1, 1, 0};
    boolean[][][] map;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        x--;y--;r--;c--;
        map = new boolean[k + 1][n][m];
        map[0][x][y] = true;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < m; l++) {
                    if (map[i - 1][j][l]){
                        for (int o = 0; o < 4; o++) {
                            int nx = j + dx[o];
                            int ny = l + dy[o];
                            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                            map[i][nx][ny] = true;
                        }
                    }
                }
            }
        }

        if (!map[k][r][c])
            return "impossible";

        char[] routes = new char[k];

        for (int i = k - 1; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                int nx = r + dx[j];
                int ny = c + dy[j];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (map[i][nx][ny]){
                    routes[i] = dir[j];
                    r = nx;
                    c = ny;
                    break;
                }
            }
        }

//        for (int i = 0; i <= k; i++) {
//            System.out.println(i);
//            for (boolean[] booleans : map[i]) {
//                System.out.println(Arrays.toString(booleans));
//            }
//        }
        return new String(routes);
    }

    public static void main(String[] args) {
        Solution150365 s = new Solution150365();
        System.out.println(s.solution(3,4,2,3,3,1,5));
        System.out.println(s.solution(2,2,1,1,2,2,2));
        System.out.println(s.solution(3,3,1,2,3,3,4));
    }
}
