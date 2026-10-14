package thread;

import java.util.concurrent.ConcurrentHashMap;

public class concurrentHashMap {

	public static void main(String[] args) throws InterruptedException {

		ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

		Thread t1 = new Thread(() -> {

			for (int i = 1; i <= 5; i++) {
				map.put(i, "User-" + i);
			}

		});

		Thread t2 = new Thread(() -> {

			for (int i = 6; i <= 10; i++) {
				map.put(i, "User-" + i);
			}

		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println(map);
	}
}