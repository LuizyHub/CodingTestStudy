public class Ain {
    public static void main(String[] args) {
        int i = 0b10001000;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(i & -i));
        System.out.println((int) '0');
    }
}
