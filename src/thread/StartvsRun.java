package thread;

public class StartvsRun {

	public static void main(String[] args) {

		Thread thread = new Thread(() -> {
			System.out.println("Running in: " + Thread.currentThread().getName());
		});

		// Creates a new thread
		thread.start();

		// Normal method call - does NOT create a new thread
		// thread.run();
	}
}