package thread;

public class join {

	public static void main(String[] args) throws InterruptedException {

		Thread thread = new Thread(() -> {

			System.out.println("Worker thread started");

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			System.out.println("Worker thread completed");
		});

		thread.start();

		// Main thread waits for worker thread
		thread.join();

		System.out.println("Main thread completed");
	}
}