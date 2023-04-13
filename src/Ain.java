import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ain {
    public static void main(String[] args) {
        int i = 0b10001000;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(i & -i));
    }
}
