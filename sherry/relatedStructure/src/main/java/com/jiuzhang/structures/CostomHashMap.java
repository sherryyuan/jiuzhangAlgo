package com.jiuzhang.structures;

public class CostomHashMap<K, V> {

	private final int DEFAULT_SIZE = 18;
	private final int bucketSize;

	private Entry<K, V>[] internalBucket;

	public CostomHashMap(int bucketSize) {
		this.bucketSize = bucketSize;
		this.internalBucket = new Entry[bucketSize];
	}

	public CostomHashMap() {
		this.bucketSize = DEFAULT_SIZE;
		this.internalBucket = new Entry[DEFAULT_SIZE];
	}

	private class Entry<K, V> {
		K key;
		V value;
		Entry next;

		public Entry(K k, V v) {
			this.key = k;
			this.value = v;
		}
	}


	public void put(K key, V val) {
		Entry entry = new Entry(key, val);
		int bucket = getKeyBucket(key);
		if (internalBucket[bucket] == null) {
			internalBucket[bucket] = entry;
		} else {
			Entry lastEntry = internalBucket[bucket];
			while (lastEntry.next != null) {
				lastEntry = lastEntry.next;
			}
			lastEntry.next = entry;
		}
	}

	public V get(K key) {
		int bucket = getKeyBucket(key);
		Entry<K, V> e = internalBucket[bucket];
		while (e != null) {
			if (e.key.equals(key)) {
				return e.value;
			}
			e = e.next;
		}
		//not found
		return null;
	}

	public void remove(K key) {
		int bucket = getKeyBucket(key);
		Entry<K, V> e = internalBucket[bucket];
		while (e != null) {
			if (e.key.equals(key)) {
				e = e.next;
			}
			return;
		}

	}


	private int getKeyBucket(K key) {
		return key.hashCode() % bucketSize;
	}
}
