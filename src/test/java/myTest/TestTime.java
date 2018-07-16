package myTest;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jsflz
 */
public class TestTime {

    public static void test1() {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("currentTimeMillis : " + currentTimeMillis);

        Timestamp nowTime = new Timestamp(currentTimeMillis);
        System.out.println("nowTime : " + nowTime);

        Timestamp nextTime = new Timestamp(currentTimeMillis + 3 * 1000);
        System.out.println("nextTime : " + nextTime);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("timestamp : " + timestamp);
    }

    /**
     * 测试加减天数.
     */
    public static void addOrSubtractDay() {

        //加一天
        Date date = addDaysToDate(new Date(), 1);
        System.out.println("加一天后的天时间 : " + date);

        //减一天
        Date date2 = addDaysToDate(new Date(), -1);
        System.out.println("减一天后的天时间 : " + date2);
    }

    /**
     * 测试加减毫秒数
     */
    public static void addMillis() {

        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        System.out.println("nowTime : " + nowTime);
        long step = 5000L;
        Timestamp nextTime = new Timestamp(System.currentTimeMillis() + step);
        System.out.println("nextTime 5 seconds later: " + nextTime);
    }


    /**
     * 天数加减方法
     * @param from 源天时间
     * @param days 加的天数 (如果要减,则传入负数即可)
     * @return 加减后的天数.
     */
    private static Date addDaysToDate(Date from, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(from);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    public static void main(String[] args) {
        //TestTime.test1();
        /**
         currentTimeMillis : 1529387406449
         nowTime : 2018-06-19 13:50:06.449
         nextTime : 2018-06-19 13:50:09.449
         timestamp : 2018-06-19 13:50:06.475
         */

       // TestTime.addOrSubtractDay();
        /**
         加一天后的天时间 : Wed Jun 20 16:50:34 CST 2018
         减一天后的天时间 : Mon Jun 18 16:50:34 CST 2018
         */

        addMillis();
        /**
         nowTime : 2018-06-25 14:06:23.673
         nextTime 5 seconds later: 2018-06-25 14:06:28.687
         */
    }
}
