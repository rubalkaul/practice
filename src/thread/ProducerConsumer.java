package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> queue =
                new ArrayBlockingQueue<>(5);

        Thread producer = new Thread(() -> {

            try {

                for (int i = 1; i <= 10; i++) {

                    queue.put(i);

                    System.out.println(
                            "Produced: " + i
                    );
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {

            try {

                for (int i = 1; i <= 10; i++) {

                    Integer value = queue.take();

                    System.out.println(
                            "Consumed: " + value
                    );
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Processing completed");
    }
}