package myTest.testConcurrent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Jian.Cui on 2018/10/1.
 * ThreadLocal has nothing to do with the thread safe!!!!!!
 * We can not use it to CHANGE the value of Shared variable even we use the "volatile"!!!
 * We can only use it to USE the shared variable,such as the DB's connection or sql' session.
 */
public class TestThreadLocal {

    public static int a = 0;

    public volatile static int b = 0;

    private volatile static ThreadLocal<Integer> tl = new ThreadLocal() {
        //要通过覆盖这个方法来给ThreadLocal初始化值,否则get()时空指针.
        @Override
        protected Integer initialValue() {
            return b;
        }
    };


    /**
     * Thread NOT safe
     */
    public static void test1() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                a++;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("plus:" + Thread.currentThread().getName() + ": " + a);
            }).start();
        }
    }

    /**
     * Thread safe with synchronized
     */
    public static void test2() {
        for (int i = 1; i < 10; i++) {
            new Thread(() -> {
                synchronized (TestThreadLocal.class) {
                    a++;
                    try {
                        Thread.currentThread().sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("plus:" + Thread.currentThread().getName() + ": " + a);
                }
            }).start();
        }
    }

    /**
     * Thread NOT safe with synchronized!  NOT SAFE!!!
     * ThreadLocal has nothing to do with the thread safe!!!!!!
     * We can not use it to CHANGE the value of Shared variable even we use the "volatile"!!!
     * We can only use it to USE the shared variable,such as the DB's connection or sql' session.
     */
    public static void test3() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Integer bTl = tl.get();

                bTl++;

                tl.set(bTl);

                System.out.println("plus:" + Thread.currentThread().getName() + ": " + tl.get());

            }).start();
        }

        /**
         * Test Log:
         plus:Thread-1: 1
         plus:Thread-0: 1
         plus:Thread-5: 1
         plus:Thread-4: 1
         plus:Thread-3: 1
         plus:Thread-2: 1
         plus:Thread-6: 1
         plus:Thread-9: 1
         plus:Thread-7: 1
         plus:Thread-8: 1

         */
    }

    //The right way to use ThreadLocal
    private static ThreadLocal<Connection> t2 = new ThreadLocal<Connection>();
    private static Connection initConn = null;

    static {
        try {
            initConn = DriverManager.getConnection("url, name and password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The right way to use ThreadLocal
     */
    public static Connection getConn() {
        Connection c = t2.get();
        if (null == c) t2.set(initConn);
        return t2.get();

    }

    public static void main(String[] args) {
        //test1();

        //test2();

        test3();
    }

}
