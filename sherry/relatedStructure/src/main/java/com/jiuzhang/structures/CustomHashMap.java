package com.jiuzhang.structures;

public class CustomHashMap<K, V> {

	private final int DEFAULT_SIZE = 16;
	private final float LOAD_FACTOR = 0.75f;

	private int internalCapacity;

	private Entry<K, V>[] internalBucket;

	public CustomHashMap(int internalCapacity) {
		this.internalCapacity = internalCapacity;
		this.internalBucket = new Entry[internalCapacity];
	}

	public CustomHashMap() {
		this.internalCapacity = DEFAULT_SIZE;
		this.internalBucket = new Entry[internalCapacity];
	}

	private class Entry<K, V> {
		K key;
		V value;
		Entry next;

		public Entry(K k, V v) {
			this.key = k;
			this.value = v;
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append(this.value).append("->");
			Entry temp = next;
			while (temp != null) {
				sb.append(temp.value).append("->");
				temp = temp.next;
			}
			sb.append("NULL");
			return sb.toString();
		}
	}


	public int getInternalCapacity() {
		return this.internalCapacity;
	}

	public void put(K key, V val) {
		Entry entry = new Entry(key, val);
		int bucket = getKeyBucket(key);
		if (internalBucket[bucket] == null) {
			internalBucket[bucket] = entry;
		} else {
			int count = 1;
			Entry lastEntry = internalBucket[bucket];
			while (lastEntry.next != null) {
				lastEntry = lastEntry.next;
				count++;
			}
			lastEntry.next = entry;
			if (count >= LOAD_FACTOR * internalCapacity) {
				//recalculate the hashCode and update all entry to new place
				refactorExistingMap();
			}
		}
	}

	private void refactorExistingMap() {
		//update the interal capacity
		internalCapacity = internalCapacity * 2;
		Entry<K, V>[] newBucket = new Entry[internalCapacity];
		for (Entry<K, V> e : internalBucket) {
			while (e != null) {
				int bukectNum = getKeyBucket(e.key);
				if (newBucket[bukectNum] == null) {
					newBucket[bukectNum] = new Entry<>(e.key, e.value);
				} else {
					Entry lastEntry = newBucket[bukectNum];
					while (lastEntry.next != null) {
						lastEntry = lastEntry.next;
					}
					lastEntry.next = new Entry(e.key, e.value);
					e = e.next;
				}
			}
		}
		this.internalBucket = newBucket;
	}


	public V get(K key) {
		int bucket = getKeyBucket(key);
		Entry<K, V> e = internalBucket[bucket];
		System.out.println(e);
		while (e != null) {
			if (e.key.equals(key)) {
				return e.value;
			}
			e = e.next;
		}
		//not found
		return null;
	}

	public V remove(K key) {
		int bucket = getKeyBucket(key);
		Entry<K, V> previous = null;
		Entry<K, V> removedEntry = null;
		Entry<K, V> currentEntry = internalBucket[bucket];
		while (currentEntry != null) {
			if (currentEntry.key.equals(key)) {
				removedEntry = currentEntry;
				if (previous == null) {
					internalBucket[bucket] = internalBucket[bucket].next;
				} else {
					previous.next = currentEntry.next;
				}
				return removedEntry.value;
			}
			previous = currentEntry;
			currentEntry = currentEntry.next;
		}
		// the value not exist in the map
		return null;

	}


	private int getKeyBucket(K key) {
		return key.hashCode() % internalCapacity;
	}
}
