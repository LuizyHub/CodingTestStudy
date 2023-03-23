public class Beak15649 {
    private static int M, N, length;
    private static char[] chars;
    private static boolean[] visit;
    private static StringBuilder sb = new StringBuilder();
    private static void dfs(int idx){
        if (idx == length)
            sb.append(chars);
        else {
            for (int c = 1; c <= M; c++) {
                if (visit[c]) continue;
                visit[c] = true;
                chars[idx] = (char) (c + '0');
                dfs(idx + 2);
                visit[c] = false;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        M = System.in.read() - '0';
        System.in.read();
        N = System.in.read() - '0';
        visit = new boolean[M + 1];
        length = N << 1;
        chars = new char[length];
        for (int i = 1; i < length - 2; i += 2)
            chars[i] = ' ';
        chars[length - 1] = '\n';
        dfs(0);
        System.out.println(sb);
    }
}
