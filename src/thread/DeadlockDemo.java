package thread;

public class DeadlockDemo {
	private static final Object lockA = new Object();
	private static final Object lockB = new Object();

	public static void main(String[] args) {
		Thread thread1 = new Thread(() -> {
			synchronized (lockA) {
				System.out.println(Thread.currentThread().getName() + " acquired Lock A");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				} // give thread2 time to grab lockB

				System.out.println(Thread.currentThread().getName() + " waiting for Lock B");
				synchronized (lockB) {
					System.out.println(Thread.currentThread().getName() + " acquired Lock B");
				}
			}
		}, "Thread-1");

		Thread thread2 = new Thread(() -> {
			synchronized (lockB) {
				System.out.println(Thread.currentThread().getName() + " acquired Lock B");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}

				System.out.println(Thread.currentThread().getName() + " waiting for Lock A");
				synchronized (lockA) {
					System.out.println(Thread.currentThread().getName() + " acquired Lock A");
				}
			}
		}, "Thread-2");

		thread1.start();
		thread2.start();
	}
}
