package thread;

public class LlambdaTask {

	public static void main(String[] args) {

		Runnable task = () -> {
			System.out.println("Task is running");
		};

		Thread thread = new Thread(task);

		thread.start();
	}
}