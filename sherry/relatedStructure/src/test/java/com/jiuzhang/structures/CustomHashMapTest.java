package com.jiuzhang.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomHashMapTest {

	@Test
	void put() {
		CustomHashMap<Integer, String> testMap = new CustomHashMap<>();
		testMap.put(1, "test_1");
		testMap.put(2, "test_2");
		testMap.put(19,"test_3");

	}

	@Test
	void get() {
		CustomHashMap<Integer, String> testMap = new CustomHashMap<>();
		testMap.put(1, "test_1");
		testMap.put(2, "test_2");
		testMap.put(19,"test_3");
		String result = testMap.get(19);
		assertEquals("test_3", result);
	}

	@Test
	void remove() {
		CustomHashMap<Integer, String> testMap = new CustomHashMap<>(5);
		testMap.put(1, "test_1");
		testMap.put(2, "test_2");
		testMap.put(6,"test_3");
		testMap.put(11,"test_4");
		String result = testMap.remove(6);
		assertEquals("test_3", result);
		assertNull(testMap.get(6));
		result = testMap.remove(1);
		assertEquals("test_1", result);
		assertEquals("test_4",testMap.get(11));
		testMap.put(1, "test_5");
		assertEquals("test_5", testMap.get(1));
	}
}