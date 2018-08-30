package myTest.Guava.testRateLimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Date;

/**
 * Created by Jian.Cui on 2018/8/30.
 * guava的RateLimiter使用的是令牌桶算法，也就是以固定的频率向桶中放入令牌，
 * 例如一秒钟10枚令牌，实际业务在每次响应请求之前都从桶中获取令牌，只有取到令牌的请求才会被成功响应.
 * 获取的方式有两种：阻塞等待令牌或者取不到立即返回失败
 */
public class TestRateLimiter01 {

    public static void test1() {
        //使用Guava的令牌桶算法,每秒向桶中放入2个令牌. 即,下面的acquire()每秒能执行2次.
        RateLimiter limiter = RateLimiter.create(2.0);
        for (int i=0; i<10; i++) {
            //获取令牌
            double acquire = limiter.acquire();
            System.out.println(new Date() + ", @@@获取令牌成功,消耗:" + acquire);
            System.out.println("===============================");
            System.out.println("@@@远程返回.当前循环第:" + (i+1) + " 次/");
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        TestRateLimiter01.test1();
    }

    /**
     * Test log:
        注意时间,的确是每秒最多执行2次.


     Thu Aug 30 10:20:32 CST 2018@@@获取令牌成功,消耗:0.0
     ===============================
     @@@远程返回.当前循环第:1 次/

     Thu Aug 30 10:20:33 CST 2018@@@获取令牌成功,消耗:0.480421
     ===============================
     @@@远程返回.当前循环第:2 次/

     Thu Aug 30 10:20:33 CST 2018@@@获取令牌成功,消耗:0.498461
     ===============================
     @@@远程返回.当前循环第:3 次/

     Thu Aug 30 10:20:34 CST 2018@@@获取令牌成功,消耗:0.49998
     ===============================
     @@@远程返回.当前循环第:4 次/

     Thu Aug 30 10:20:34 CST 2018@@@获取令牌成功,消耗:0.49961
     ===============================
     @@@远程返回.当前循环第:5 次/

     Thu Aug 30 10:20:35 CST 2018@@@获取令牌成功,消耗:0.499207
     ===============================
     @@@远程返回.当前循环第:6 次/

     Thu Aug 30 10:20:35 CST 2018@@@获取令牌成功,消耗:0.499983
     ===============================
     @@@远程返回.当前循环第:7 次/

     Thu Aug 30 10:20:36 CST 2018@@@获取令牌成功,消耗:0.499639
     ===============================
     @@@远程返回.当前循环第:8 次/

     Thu Aug 30 10:20:36 CST 2018@@@获取令牌成功,消耗:0.499261
     ===============================
     @@@远程返回.当前循环第:9 次/

     Thu Aug 30 10:20:37 CST 2018@@@获取令牌成功,消耗:0.499537
     ===============================
     @@@远程返回.当前循环第:10 次/


     Process finished with exit code 0

     */
}
