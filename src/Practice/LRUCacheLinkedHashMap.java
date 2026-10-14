package Practice;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedHashMap extends LinkedHashMap<Integer, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int capacity;

	public LRUCacheLinkedHashMap(int capacity) {
		super(capacity, 0.75f, true);
		this.capacity = capacity;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {

		return size() > capacity;
	}

	public static void main(String[] args) {

		LRUCacheLinkedHashMap cache = new LRUCacheLinkedHashMap(3);

		cache.put(1, 100);
		cache.put(2, 200);
		cache.put(3, 300);

		System.out.println(cache.get(1));

		cache.put(4, 400);

		System.out.println(cache.get(2));
	}
}