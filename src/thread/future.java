package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class future {

	public static void main(String[] args) throws Exception {

		ExecutorService executor = Executors.newSingleThreadExecutor();

		Future<String> future = executor.submit(() -> {

			Thread.sleep(2000);

			return "Task Completed";

		});

		System.out.println("Doing other work...1");

		String result = future.get();

		System.out.println(result);
		System.out.println("Doing other work...2");
		executor.shutdown();
	}
}