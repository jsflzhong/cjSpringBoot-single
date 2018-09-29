package myTest;

import org.apache.shiro.util.Assert;

/**
 * Created by Jian.Cui on 2018/9/10.
 */
public class TestString {

    public static void test1() {
        String s = null;
        String s1 = s + ""; //null
        String s2 = s.toString(); //NullPointerException!
    }

    public static void test2() {
        String s1 = "";
        System.out.println(s1 == null);

        String s2 = null;
        System.out.println(s2 == null);

        Assert.notNull(s2,"@@@Can not be null!");

    }

    public static void main(String[] args) {
        TestString.test2();
    }
}
