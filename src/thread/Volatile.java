package thread;

class Task {

	volatile boolean running = true;

	void runTask() {

		while (running) {
			// Keep running
		}

		System.out.println("Task stopped");
	}
}

public class Volatile {

	public static void main(String[] args) throws InterruptedException {

		Task task = new Task();

		Thread worker = new Thread(task::runTask);

		worker.start();

		Thread.sleep(1000);

		System.out.println("Stopping worker...");

		task.running = false;

		worker.join();

		System.out.println("Main completed");
	}
}
