package myTest.Guava.actualEventBus.subscribe;

import com.google.common.eventbus.Subscribe;

/**
 * Created by Jian.Cui on 2018/8/29.
 */
public class SecondSubscriber implements Subscriber{

    @Subscribe
    public void subscribe(String stringEvent) {
        System.out.println("@@@SecondSubscriber got the event:" + stringEvent);
    }

}
