package myTest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jsflz
 */
public class TestList {

    public static void test1() {
        String appNo = "abcdefg";
        List<String> list = Arrays.asList(appNo);
        System.out.println("list : " + list);

        String appNo2 = "123456";
        List<String> list2 = Arrays.asList(appNo,appNo2);
        System.out.println("list2 : " + list2);
    }

    public static void main(String[] args) {
        TestList.test1();
    }


    /**
     list : [abcdefg]
     list2 : [abcdefg, 123456]
     */
}
