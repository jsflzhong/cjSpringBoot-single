package myTest;

/**
 * Created by Jian.Cui on 2018/9/10.
 */
public class TestString {

    public static void test1() {
        String s = null;
        String s1 = s + ""; //null
        String s2 = s.toString(); //NullPointerException!
    }

    public static void main(String[] args) {
        TestString.test1();
    }
}
