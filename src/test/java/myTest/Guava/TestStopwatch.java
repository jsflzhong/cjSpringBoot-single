package myTest.Guava;


import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * Created by jsflz on 2018/8/23.
 * 计算中间代码的运行时间
 */
public class TestStopwatch {

    public static void test() {
        Stopwatch stopwatch = Stopwatch.createStarted();

        int n = 1;
        for(int i=0; i<100000; i++){
            n += i;
        }
        System.out.println(n);

        long nanos = stopwatch.elapsed(TimeUnit.MILLISECONDS);//TimeUnit 可以指定时间输出精确到多少时间

        System.out.println(nanos);
    }

    public static void main(String[] args) {
        test();
    }
}
