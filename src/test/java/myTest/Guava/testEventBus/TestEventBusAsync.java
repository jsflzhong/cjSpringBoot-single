package myTest.Guava.testEventBus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.Executors;

/**
 * Created by jsflz on 2018/8/13.
 * Synchronization
 * 是观察者模式的一种改进.
 * 不用自定义很多类,google帮我处理好了.
 * 而且观察者模式是一对多,即,一个主题,N个观察者. 1:N. 当主题有变化时,通知N个观察者.
 * 但是Guava这个事件总线,却好像可以相反: 即,N个主题 : N个观察者. 当N个主题有变化时,通知N个观察者.
 * 适用于类似:监控通知 之类的情况.
 * 这是"异步"的例子.
 * 底层原理:
 *      Guava的异步事件总线中,维护了一个并发安全队列: private final ConcurrentLinkedQueue<EventWithHandler> eventsToDispatch
 *      消费事件任务则维护了一个线程池，一个Executor实现，这个实现由开发者自行定义提供。
 *
 * @author cj
 */
public class TestEventBusAsync {

    @Subscribe
    @AllowConcurrentEvents //Thread safe
    public void sub1(String message) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("sub1 running : " + message);
    }

    @Subscribe
    @AllowConcurrentEvents //Thread safe
    public void sub2(String message) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("sub2 running : " + message);
    }

    public static void main(String[] args) {
        //Producer with asynchronous
        EventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(2));
        eventBus.register(new TestEventBusAsync());//注册事件
        eventBus.post("ssdf");// 触发事件处理
        //Asynchronously run this with main thread.
        System.out.println("Main thread is still running without waiting for calling the event");

    }

    /**
     * Test log:
     *
     Main thread is still running without waiting for calling the event
     sub1 running : ssdf
     sub2 running : ssdf
     */
}
