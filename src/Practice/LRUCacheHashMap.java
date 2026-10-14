package Practice;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheHashMap {

	private static class Node {
		int key;
		int value;

		Node prev;
		Node next;

		Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	private final int capacity;

	private final Map<Integer, Node> cache = new HashMap<>();

	private final Node head = new Node(0, 0);
	private final Node tail = new Node(0, 0);

	public LRUCacheHashMap(int capacity) {
		this.capacity = capacity;

		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {

		if (!cache.containsKey(key)) {
			return -1;
		}

		Node node = cache.get(key);

		// Move node to front
		remove(node);
		addToFront(node);

		return node.value;
	}

	public void put(int key, int value) {

		// If key already exists
		if (cache.containsKey(key)) {

			Node node = cache.get(key);

			node.value = value;

			remove(node);
			addToFront(node);

			return;
		}

		// Create new node
		Node node = new Node(key, value);

		cache.put(key, node);
		addToFront(node);

		// Cache is full
		if (cache.size() > capacity) {

			Node leastRecentlyUsed = tail.prev;

			remove(leastRecentlyUsed);

			cache.remove(leastRecentlyUsed.key);
		}
	}

	private void addToFront(Node node) {

		node.next = head.next;
		node.prev = head;

		head.next.prev = node;
		head.next = node;
	}

	private void remove(Node node) {

		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	public static void main(String[] args) {

		LRUCacheHashMap cache = new LRUCacheHashMap(3);

		cache.put(1, 100);
		cache.put(2, 200);
		cache.put(3, 300);

		System.out.println(cache.get(1));

		cache.put(4, 400);

		System.out.println(cache.get(2));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));
	}
}