package thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockDetector {
	private static final Object lockA = new Object();
	private static final Object lockB = new Object();

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(() -> {
			synchronized (lockA) {
				sleep(100);
				synchronized (lockB) {
					System.out.println("Thread-1 done");
				}
			}
		}, "Thread-1");

		Thread thread2 = new Thread(() -> {
			synchronized (lockB) {
				sleep(100);
				synchronized (lockA) {
					System.out.println("Thread-2 done");
				}
			}
		}, "Thread-2");

		thread1.start();
		thread2.start();

		Thread.sleep(1000); // give the deadlock time to actually happen
		detectDeadlock();
	}

	private static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private static void detectDeadlock() {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

		// Returns IDs of threads stuck in circular lock-waiting, or null if none
		long[] deadlockedThreadIds = threadMXBean.findDeadlockedThreads();

		if (deadlockedThreadIds != null) {
			ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(deadlockedThreadIds, true, true);

			System.out.println("DEADLOCK DETECTED!");
			for (ThreadInfo info : threadInfos) {
				System.out.println("Thread: " + info.getThreadName());
				System.out.println("  State: " + info.getThreadState());
				System.out.println("  Locked on: " + info.getLockName());
				System.out.println("  Owned by: " + info.getLockOwnerName());
			}
		} else {
			System.out.println("No deadlock detected.");
		}
	}
}