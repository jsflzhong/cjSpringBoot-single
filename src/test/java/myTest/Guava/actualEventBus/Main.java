package myTest.Guava.actualEventBus;

import myTest.Guava.actualEventBus.factory.AsyncEventBusFactory;
import myTest.Guava.actualEventBus.subscribe.MonitorCounterSubscriber;
import myTest.Guava.actualEventBus.subscribe.SecondSubscriber;

/**
 * Created by Jian.Cui on 2018/8/29.
 * 测试类
 */
public class Main {

    public static void main(String[] args) {

        //注册第一个订阅者,然后发送事件.
        AsyncEventBusFactory.postEvent(new MonitorCounterSubscriber(),"stringEvent1!!~~");

        System.out.println("################################################################");

        //注册第二个订阅者,然后发送事件.(注意观察第一个订阅者是否还能收到该事件,目标是不应该收到,因为这次只注册了第二个订阅者.
        //但是由于EventBus做成单例了,而且在上面又注册过第一个订阅者,所以第一个订阅者还是可能收到的)
        //经过测试,如果不做处理,那么第一个果然还是能收到.
        //所以在eventBus工厂那边的postEvent方法里,在post事件之后,把已注册的订阅者给取消注册了.
        //现在只有每次传入的订阅者才可以收到事件了.
        AsyncEventBusFactory.postEvent(new SecondSubscriber(),"stringEvent2!!~~");
    }

    /**
     * Test log:

     ################################################################ (异步,所以主线程在调用完eventBus后,就回来继续执行下去了,所以这行会先打印.)
     @@@MonitorCounterSubscribe got the event:stringEvent1!!~~
     @@@SecondSubscriber got the event:stringEvent2!!~~
     */
}

