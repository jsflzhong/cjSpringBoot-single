package myTest.TestDesignPattern.ProducerAndConsumer;

/**
 * Created by jsflz on 2018/8/13.
 */

import java.util.concurrent.BlockingQueue;

/**
 * Consumer Class
 */
class Consumer implements Runnable{

    private final BlockingQueue sharedQueue;

    public Consumer (BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("Consumer is ready to block...");
                Object element = sharedQueue.take();
                System.out.println("Consumed: "+ element);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
}