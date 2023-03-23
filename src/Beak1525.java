import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Beak1525 {
    public static class Puzzle{
        int N;
        int index0;
        int deep;

        public Puzzle(int n, int index0, int deep) {
            N = n;
            this.index0 = index0;
            this.deep = deep;
        }

        @Override
        public int hashCode() {
            return N;
        }

        @Override
        public boolean equals(Object obj) {
            Puzzle p = (Puzzle) obj;
            if (this.N == p.N)
                return true;
            else
                return false;
        }

    }

    public static int getIndex(int N, int index){
        return N/pow10[index]%10;
    }
    private static int[] pow10 = {1,10,100,1000,10000,100000,1000000,10000000,100000000,1000000000};

    private static int change(int from, int to, int N){
        return N - getIndex(N,from)*pow10[from] + getIndex(N,from)*pow10[to] - getIndex(N,to)*pow10[to] + getIndex(N,to)*pow10[from];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int start = 0;
        int startindex0 = -1;
        for (int i = 0; i < 9; i++) {
            if (i%3 == 0)
                st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            start += num * pow10[8-i];
            if (num ==0)
                startindex0 = 8-i;
        }

        ArrayDeque<Puzzle> deque = new ArrayDeque<>();
        HashSet<Puzzle> visited = new HashSet<>();
        Puzzle startPosition = new Puzzle(start, startindex0,0);
        deque.addLast(startPosition);
        visited.add(startPosition);

        int countdeepth = -1; // graph deepness
        while (!deque.isEmpty()){
            Puzzle cur = deque.pollFirst();
            int N = cur.N;
            if (N == 123456780){
                countdeepth = cur.deep;
                break;
            }
            int next = N;
            Puzzle nextPuzzle = null;
            switch (cur.index0){
                case 0:
                    //change 1
                    next = change(0,1,N);
                    nextPuzzle = new Puzzle(next,1,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //change 3
                    next = change(0,3,N);
                    nextPuzzle = new Puzzle(next,3,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    break;
                case 1:
                    //0
                    next = change(1,0,N);
                    nextPuzzle = new Puzzle(next,0,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //2
                    next = change(1,2,N);
                    nextPuzzle = new Puzzle(next,2,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //4
                    next = change(1,4,N);
                    nextPuzzle = new Puzzle(next,4,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    break;
                case 2:
                    //1
                    next = change(2,1,N);
                    nextPuzzle = new Puzzle(next,1,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //5
                    next = change(2,5,N);
                    nextPuzzle = new Puzzle(next,5,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    break;
                case 3:
                    //0
                    next = change(3,0,N);
                    nextPuzzle = new Puzzle(next,0,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //4
                    next = change(3,4,N);
                    nextPuzzle = new Puzzle(next,4,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //6
                    next = change(3,6,N);
                    nextPuzzle = new Puzzle(next,6,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    break;
                case 4:
                    //1
                    next = change(4,1,N);
                    nextPuzzle = new Puzzle(next,1,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //3
                    next = change(4,3,N);
                    nextPuzzle = new Puzzle(next,3,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //5
                    next = change(4,5,N);
                    nextPuzzle = new Puzzle(next,5,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //7
                    next = change(4,7,N);
                    nextPuzzle = new Puzzle(next,7,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    break;
                case 5:
                    //2
                    next = change(5,2,N);
                    nextPuzzle = new Puzzle(next,2,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //4
                    next = change(5,4,N);
                    nextPuzzle = new Puzzle(next,4,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //8
                    next = change(5,8,N);
                    nextPuzzle = new Puzzle(next,8,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    break;
                case 6:
                    //3
                    next = change(6,3,N);
                    nextPuzzle = new Puzzle(next,3,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //7
                    next = change(6,7,N);
                    nextPuzzle = new Puzzle(next,7,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    break;
                case 7:
                    //4
                    next = change(7,4,N);
                    nextPuzzle = new Puzzle(next,4,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //6
                    next = change(7,6,N);
                    nextPuzzle = new Puzzle(next,6,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //8
                    next = change(7,8,N);
                    nextPuzzle = new Puzzle(next,8,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    break;
                case 8:
                    //5
                    next = change(8,5,N);
                    nextPuzzle = new Puzzle(next,5,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    //7
                    next = change(8,7,N);
                    nextPuzzle = new Puzzle(next,7,cur.deep+1);
                    if (!visited.contains(nextPuzzle)){
                        visited.add(nextPuzzle);
                        deque.addLast(nextPuzzle);
                    }
                    break;

            }
        }
        System.out.println(countdeepth);

    }
}
/*
index
8 7 6
5 4 3
2 1 0

1 6 3
4 2 5
7 8 0

 */
