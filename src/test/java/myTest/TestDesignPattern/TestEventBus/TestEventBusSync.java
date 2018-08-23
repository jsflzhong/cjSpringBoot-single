package myTest.TestDesignPattern.TestEventBus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created by jsflz on 2018/8/13.
 * Synchronization
 * 是观察者模式的一种改进.
 * 不用自定义很多类,google帮我处理好了.
 * 而且观察者模式是一对多,即,一个主题,N个观察者. 1:N. 当主题有变化时,通知N个观察者.
 * 但是Guava这个事件总线,却好像可以相反: 即,N个主题 : N个观察者. 当N个主题有变化时,通知N个观察者.
 * 适用于类似:监控通知 之类的情况.
 * 这是"同步"的例子.
 *
 * @author cj
 */
public class TestEventBusSync {

    /**
     * Consumer1
     * Must be 1 param.
     * @param message
     */
    @Subscribe
    public void sub1(String message) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ",sub1...");
        Thread.sleep(1000);
        System.out.println("sub1:" + message);
    }

    /**
     * Consumer2
     * Must be 1 param.
     * @param message
     */
    @Subscribe
    public void sub2(String message) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ",sub2...");
        Thread.currentThread().sleep(1300);
        String name = Thread.currentThread().getName();
        System.out.println("name= " + name);
        System.out.println("sub2:" + message);
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "...");
        //Producer with synchronize.
        EventBus eventBus = new EventBus();
        eventBus.register(new TestEventBusSync());//注册事件.注意,发布者是通过这里的类型,来匹配订阅者是哪个的.
        eventBus.post("ssdf");// 触发事件处理
        //Synchronously run this with main thread.
        System.out.println(Thread.currentThread().getName() + ",Main thread is not running until finish calling the event");
    }

    /**
     * Test log:
     *
     sub2:ssdf
     sub1:ssdf
     Main thread is not running until finish calling the event
     */
}
