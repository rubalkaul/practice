package thread;

public class ExceptionCommunication {

    // Shared variable to store the exception
    static volatile Exception exception = null;

    public static void main(String[] args) {

        // Thread 1: Produces the exception
        Thread producer = new Thread(() -> {
            try {
                System.out.println("Producer thread is running...");

                // Simulate an error
                int result = 10 / 0;

            } catch (Exception e) {
                System.out.println("Producer thread caught an exception.");

                // Store the exception so the other thread can see it
                exception = e;
            }
        });

        // Thread 2: Catches/handles the exception from Thread 1
        Thread consumer = new Thread(() -> {
            try {
                // Wait until producer generates an exception
                while (exception == null) {
                    Thread.sleep(100);
                }

                // Notify the user
                System.out.println("USER NOTIFICATION:");
                System.out.println("An error occurred: " + exception.getMessage());

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
