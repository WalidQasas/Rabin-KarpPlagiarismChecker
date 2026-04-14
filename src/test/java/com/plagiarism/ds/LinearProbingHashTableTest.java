package com.plagiarism.ds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinearProbingHashTableTest {
    private HashTable<String, Integer> hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new LinearProbingHashTable<>();
    }

    @Test
    void testPutAndGet() {
        hashTable.put("apple", 1);
        hashTable.put("banana", 2);
        assertEquals(1, hashTable.get("apple"));
        assertEquals(2, hashTable.get("banana"));
    }

    @Test
    void testUpdateValue() {
        hashTable.put("apple", 1);
        hashTable.put("apple", 10);
        assertEquals(10, hashTable.get("apple"));
    }

    @Test
    void testRemove() {
        hashTable.put("apple", 1);
        hashTable.remove("apple");
        assertNull(hashTable.get("apple"));
        assertTrue(hashTable.isEmpty());
    }

    @Test
    void testCollisionHandlingAndResizing() {
        for (int i = 0; i < 20; i++) {
            hashTable.put("key" + i, i);
        }
        assertEquals(20, hashTable.size());
        for (int i = 0; i < 20; i++) {
            assertEquals(i, hashTable.get("key" + i));
        }
    }
}
