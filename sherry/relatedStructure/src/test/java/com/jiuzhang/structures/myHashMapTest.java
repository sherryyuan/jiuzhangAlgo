package com.jiuzhang.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CostomHashMapTest {

	@Test
	void put() {
		CostomHashMap<Integer, String> testMap = new CostomHashMap<>();
		testMap.put(1, "test_1");
		testMap.put(2, "test_2");
		testMap.put(19,"test_3");

	}

	@Test
	void get() {
		CostomHashMap<Integer, String> testMap = new CostomHashMap<>();
		testMap.put(1, "test_1");
		testMap.put(2, "test_2");
		testMap.put(19,"test_3");
		String result = testMap.get(19);
		assertEquals("test_3", result);
	}

	@Test
	void remove() {
		CostomHashMap<Integer, String> testMap = new CostomHashMap<>();
		testMap.put(1, "test_1");
		testMap.put(2, "test_2");
		testMap.put(19,"test_3");
		testMap.remove(1);
		assertNull(testMap.get(1));
		assertEquals("test_3",testMap.get(19));

	}
}