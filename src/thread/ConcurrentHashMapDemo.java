package thread;

import java.util.Map;
import java.util.concurrent.*;

public class ConcurrentHashMapDemo {
	public static void main(String[] args) throws InterruptedException {
		Map<String, Integer> map = new ConcurrentHashMap<>();

		ExecutorService executor = Executors.newFixedThreadPool(5);

		// 5 threads, each incrementing the same set of keys 1000 times
		for (int i = 0; i < 5; i++) {
			executor.submit(() -> {
				for (int j = 0; j < 1000; j++) {
					map.merge("counterA", 1, Integer::sum); // atomic increment
					map.merge("counterB", 1, Integer::sum);
				}
			});
		}

		executor.shutdown();
		executor.awaitTermination(10, TimeUnit.SECONDS);

		System.out.println(map);
		// Expected: {counterA=5000, counterB=5000}
	}
}