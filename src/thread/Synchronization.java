package thread;

class Counter1 {

	int count = 0;

	synchronized void increment() {
		count++;
	}
}

public class Synchronization {

	public static void main(String[] args) throws InterruptedException {

		Counter1 counter = new Counter1();

		Thread t1 = new Thread(() -> {

			for (int i = 0; i < 1000; i++) {
				counter.increment();
			}

		});

		Thread t2 = new Thread(() -> {

			for (int i = 0; i < 1000; i++) {
				counter.increment();
			}

		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("Final count: " + counter.count);
	}
}
