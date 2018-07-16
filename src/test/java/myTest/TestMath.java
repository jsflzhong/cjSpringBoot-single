package myTest;

/**
 * Created by jsflz
 */
public class TestMath {

    public static void test1() {
        //取参1的参2次方.本例: 2的6次方=64.
        double pow = Math.pow(2, 2 * 5 + 1 - 5);
        long step = Math.round(pow);
        System.out.println("step : " + step);

    }

    public static void main(String[] args) {
        TestMath.test1();


        /**
         step : 64
         */
    }
}
