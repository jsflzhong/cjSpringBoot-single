package myTest;

import com.michael.springBoot.domain.User;

import java.util.Optional;

/**
 * Created by Jian.Cui on 2018/11/5.
 * j8的判空.
 * Optional.ofNullable("需要判空的数据").这个ofNullable方法可以接受为值为null的变量.
 *
 * 链式函数:
 * ifPresent() 如果有值,则执行一个函数.(下例test5)
 * orElse() 如果没值,为空,则执行一个方法.(下例test1)
 * isPresent() 判断是否有值,返回true/false.(下例test4)
 *
 *
 * @author Jian.Cui
 */
public class TestOptional {

    /**
     * 如果目标值是空,则执行方法2.
     */
    public static void test1() {
        String s = null;
        //如果s是空,则执行test2(),同时拿到test2()的返回值.
        String s1 = Optional.ofNullable(s).orElse(test2());
        System.out.println("###test1:" + s1);
    }

    public static String test2() {
        System.out.println("@@@test2......");
        return "111";
    }

    public static String test3() {
        System.out.println("@@@test3......");
        return "222";
    }

    /**
     * 判空,返回true/false.
     * 如果目标值是空,则执行方法2,否则执行方法三.
     */
    public static void test4() {
        String s = null;
        //如果s是空,则执行test2(),同时拿到test2()的返回值,否则执行test3
        String s1 = Optional.ofNullable(s).isPresent()?test2() : test3(); //test3被调用.
        System.out.println("###test4:" + s1);
    }

    /**
     * 判空,返回true/false.
     * 如果目标值是空,则执行方法2,否则执行方法三.
     */
    public static void test5() {
        String s = "not null";
        //如果s不是空,有值,则执行test2(),同时拿到test2()的返回值.
        Optional.ofNullable(s).ifPresent(o -> test2());

        //应用场景: 可用于在函数内为外部Pojo赋值,先判断需要赋值的值是否不为空,再赋值.
        User user = new User();
        Integer age = 123;
        Optional.ofNullable(age).ifPresent(o -> user.setAge(age));
        System.out.println("@@@赋值后的user的age:" + user.getAge());
    }

    public static void main(String[] args) {
        //TestOptional.test1();
        //TestOptional.test4();
        TestOptional.test5();
    }
}
