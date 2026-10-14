package Practice;

public class MAIN2THREAD {
	public static Object lock = new Object();
	public static int counter = 0;

	static void printeven() {
		synchronized (lock) {
			while (counter <= 10) {
				if (counter % 2 == 0) {
					System.out.println(counter + " EVEN");
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					counter++;
					lock.notify();
				}
			}
		}
	}

	static void printodd() {
		synchronized (lock) {
			while (counter <= 10) {
				if (counter % 2 != 0) {
					System.out.println(counter + " ODD");
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					counter++;
					lock.notify();
				}
			}
		}
	}

	public static void main(String[] args) {
		Runnable even = () -> {
			printeven();
		};
		Runnable odd = () -> {
			printodd();
		};
		Thread eventh = new Thread(even);
		Thread oddth = new Thread(odd);

		oddth.start();
		eventh.start();
	}
}
