package myTest.testException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.util.StringUtils;

/**
 * Created by jsflz
 * Test how exception system works.
 * Important test and valuable!!
 *
 * @author cj
 */
public class TestException {

    /**
     * 异常处理方法
     * 处理过程记录(重要!):
     * 1.在某个catch捕获异常后,它下面的catch块,不会再捕获该异常.即使下面的catch块抓的是总异常.
     *   例如:第一个catch抓了MyException1后,它下面的所有其他的catch块都不会进去执行!
     * 2.java自己的非检查异常,例如:ArithmeticException(就是除零了),可以抓,也可以不抓.
     *   如果抓了,就会进入该显示抓这个异常的catch块,那么其下的其他catch块就不会再进入了.即使下面的catch块抓的是总异常.
     *   如果没抓,就会进入总异常Exception这个catch块.
     *   注意: 这次的结论是,java自己的非检查异常RuntimeException是可以专门抓到的!也可以用Exception抓到!! 都可以!
     * 3.注意了:如果非检查异常没抓,在上层也没抓,就是:没专门抓该异常,也没抓Excepton的情况下,也就是把下面最后两个catch块给注释了的情况下:
     *   结果是这样的: 在下层发生异常的代码行执行后,会立即结束! 不会再执行任何代码!包括上层catch块下面的那行代码.
     *   初步结论: 如果异常没抓到,则即使try catch了,下面的代码也不会执行!
     *
     * @param hook 客户端传递的hook
     * @author cj
     */
    public static void handException(String hook) {
        try {
            testException1(hook);
        } catch (MyException1 e) {
            System.out.println("@@@MyException1");
            System.out.println("@@@异常信息:" + e.getMessage());
            System.out.println("@@@异常堆栈:" + ExceptionUtils.getStackTrace(e));
        } catch (MyException2 e) {
            System.out.println("@@@MyException2");
            System.out.println("@@@异常信息:" + e.getMessage());
            System.out.println("@@@异常堆栈:" + ExceptionUtils.getStackTrace(e));
        } catch (MyException3 e) {
            System.out.println("@@@MyException3");
            System.out.println("@@@异常信息:" + e.getMessage());
            System.out.println("@@@异常堆栈:" + ExceptionUtils.getStackTrace(e));
        } catch (ArithmeticException e) { //尝试抓java自己的非检查异常,例如除零异常.--成功抓到!
            System.out.println("@@@Java RuntimeException");
            System.out.println("@@@异常信息:" + e.getMessage());
            System.out.println("@@@异常堆栈:" + ExceptionUtils.getStackTrace(e));
        } catch (Exception e) {
            System.out.println("@@@Java Exception");
            System.out.println("@@@异常信息:" + e.getMessage());
            System.out.println("@@@异常堆栈:" + ExceptionUtils.getStackTrace(e));
        }
        System.out.println("@@@结束异常的处理!");
    }

    /**
     * 异常发生方法.
     * 模拟service业务层.
     * 测试重点记录(重要!):
     * 1.该方法在签名上并没有显式的throw任何的异常! 但是上层也是可以捕获的! 即使是这里抛出的是自定义的RuntimeException非检查异常!
     *   结论: 非检查异常不用显式的抛出,上层也是可以抓到的.
     * 2.发生异常后,由于本方法中并没有抓,所以其下行代码都不会执行,会直接返回上层!
     *
     * @param hook 客户端传递的hook
     * @author cj
     */
    public static void testException1(String hook) {
        if (StringUtils.isEmpty(hook)) {
            throw new MyException1("@@@参数hook不能为空!");
        }
        if ("test1".equals(hook)) {
            throw new MyException2("@@@参数不能为\"test1\"");
        }
        if (!"good".equals(hook)) {
            throw new MyException3("@@@参数不正确,正确的参数应该是的:\"good\"");
        }
        int i = 1 / 0;
        System.out.println(i);
    }

    public static void main(String[] args) {
        handException("good1");
    }
}
