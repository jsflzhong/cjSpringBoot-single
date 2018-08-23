package myTest.TestDesignPattern.ProducerAndConsumer;

/**
 * Created by jsflz on 2018/8/13.
 */

import java.util.concurrent.BlockingQueue;

/**
 * Producer Class
 */
class Producer implements Runnable {

    private final BlockingQueue sharedQueue;

    public Producer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        //Ten times to test.
        for(int i=0; i<10; i++){
            try {
                System.out.println("Produced: " + i);
                sharedQueue.put(i);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }

}