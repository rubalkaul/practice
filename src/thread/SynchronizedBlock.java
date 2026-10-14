package thread;

class Counter3 {

	private int count = 0;

	void increment() {

		synchronized (this) {
			count++;
		}
	}

	int getCount() {
		return count;
	}
}

public class SynchronizedBlock {

	public static void main(String[] args) throws InterruptedException {

		Counter3 counter = new Counter3();

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

		System.out.println("Final count: " + counter.getCount());
	}
}