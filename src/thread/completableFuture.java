package thread;

import java.util.concurrent.CompletableFuture;

public class completableFuture {

	public static void main(String[] args) {

		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
				.thenApply(value -> value + " Java").thenApply(value -> value + " Developer");

		System.out.println("Doing other work...1");
		future.thenAccept(System.out::println);
		System.out.println("Doing other work...2");
		future.join();
		System.out.println("Doing other work...3");
	}
}
