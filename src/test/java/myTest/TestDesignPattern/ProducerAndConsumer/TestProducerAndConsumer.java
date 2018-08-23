package myTest.TestDesignPattern.ProducerAndConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by jsflz on 2018/8/13.
 * Test for producer and consumer design pattern with blocking queue.
 *
 * Application:
 *  1.Log
 *  2.Warning.
 *
 *  Advantage:
 *   Asynchronous !
 *
 * @author cj
 */
public class TestProducerAndConsumer {

    public static void main(String args[]){

        //Creating a shared Blocking queue! Default capacity is: Integer.MAX_VALUE
        BlockingQueue sharedQueue = new LinkedBlockingQueue(1000);

        //Creating Producer and Consumer Thread
        Thread prodThread = new Thread(new Producer(sharedQueue));
        Thread consThread = new Thread(new Consumer(sharedQueue)); //Should use fixed thread pool?

        //Starting producer and Consumer thread
        prodThread.start();
        consThread.start();

        /**
         Test log:

         Produced: 0
         Produced: 1
         Produced: 2
         Produced: 3
         Produced: 4
         Produced: 5
         Produced: 6
         Produced: 7
         Produced: 8
         Produced: 9
         Consumer is ready to block...
         Consumed: 0
         Consumer is ready to block...
         Consumed: 1
         Consumer is ready to block...
         Consumed: 2
         Consumer is ready to block...
         Consumed: 3
         Consumer is ready to block...
         Consumed: 4
         Consumer is ready to block...
         Consumed: 5
         Consumer is ready to block...
         Consumed: 6
         Consumer is ready to block...
         Consumed: 7
         Consumer is ready to block...
         Consumed: 8
         Consumer is ready to block...
         Consumed: 9
         Consumer is ready to block...

         */
    }
}
