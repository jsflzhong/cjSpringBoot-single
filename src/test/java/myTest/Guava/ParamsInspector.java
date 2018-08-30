package myTest.Guava;

import com.google.common.base.Preconditions;
import com.michael.springBoot.domain.User;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.assertj.core.util.Strings;

import java.util.function.Function;

/**
 * Created by jsflz on 2018/8/23.
 * A demo to demonstrate how to check the params with Guava's api,which can be used directly.
 *
 * @author cj
 */
public class ParamsInspector {

    /**
     * 定义检查参数的底层方法.
     *
     * @param obj      Object
     * @param function Function
     * @author cj
     */
    public static void inspectParams(Object obj, Function function) {
        function.apply(obj);
    }

    /**
     * 定义检查参数的业务方法_函数式
     * 可被service或controller直接调用.
     * 具体需要检查的字段和异常信息,需要根据业务自定义.
     *
     * @param user user
     * @author cj
     */
    public static void inspectParams1(User user) {
        inspectParams(user, (u) -> {
            Preconditions.checkArgument(user != null, "user can not be null!");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(user.getId()), "userId can not be empty!");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(user.getPassword()), "pswd can not be empty!");
            return null;
        });
    }

    /**
     * 定义检查参数的业务方法_非函数式
     * 可被service或controller直接调用.
     * 具体需要检查的字段和异常信息,需要根据业务自定义.
     *
     * @param user user
     * @author cj
     */
    public static void inspectParams2(User user) {
        Preconditions.checkArgument(user != null, "user can not be null!");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(user.getId()), "userId can not be empty!");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(user.getPassword()), "pswd can not be empty!");
    }

    /**
     * 模拟一个service,来调用上面的检查
     */
    public static void test_service(User user) {
        //inspectParams1(user); //函数式
        inspectParams2(user); //非函数式
    }

    /**
     * 模拟一个Controller,在调用上面service的时候,统一处理异常.
     */
    public static void test_controller(User user) {
        try {
            test_service(user);
        } catch (IllegalArgumentException e) {
            System.out.println("@@@参数非法!异常:" + e.getMessage());
        } catch (Exception e) {
            System.out.println("@@@发生异常:" + ExceptionUtils.getStackTrace(e));
        }
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId("1");
        user.setPassword("2");
        test_controller(user);
    }
}
