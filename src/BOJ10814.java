import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10814 {
    private static class Person {
        int idx, age;
        String name;

        public Person(int idx, int age, String name) {
            this.idx = idx;
            this.age = age;
            this.name = name;
        }
    }
    private static Person[] people;
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new Person[N];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            people[i] = new Person(i, Integer.parseInt(st.nextToken()), st.nextToken());
        }

        Arrays.sort(people, (p1, p2) -> Integer.compare(p1.age, p2.age) == 0 ? Integer.compare(p1.idx, p2.idx) : Integer.compare(p1.age, p2.age));

        StringBuilder sb = new StringBuilder();

        for (Person person : people) {
            sb.append(person.age).append(' ').append(person.name).append('\n');
        }

        System.out.print(sb);
    }
}
