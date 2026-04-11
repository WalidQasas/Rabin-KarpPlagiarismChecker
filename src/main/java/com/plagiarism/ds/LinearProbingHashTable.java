package com.plagiarism.ds;

import java.util.Objects;

public class LinearProbingHashTable<K, V> implements HashTable<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;
    private static final Object DELETED = new Object(); // for lazy deletion

    private K[] keys;
    private V[] values;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public LinearProbingHashTable() {
        this.capacity = INITIAL_CAPACITY;
        this.keys = (K[]) new Object[capacity];
        this.values = (V[]) new Object[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        return (Math.abs(key.hashCode()) % capacity);
    }

    @Override
    public void put(K key, V value) {
        if (size >= capacity * LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int i = hash(key);
        while (keys[i] != null && keys[i] != DELETED) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
            i = (i + 1) % capacity;
        }

        keys[i] = key;
        values[i] = value;
        size++;
    }

    @Override
    public V get(K key) {
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i] != DELETED && keys[i].equals(key)) {
                return values[i];
            }
            i = (i + 1) % capacity;
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void remove(K key) {
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i] != DELETED && keys[i].equals(key)) {
                keys[i] = (K) DELETED;
                values[i] = null;
                size--;
                return;
            }
            i = (i + 1) % capacity;
        }
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @SuppressWarnings("unchecked")
    private void resize() {
        K[] oldKeys = keys;
        V[] oldValues = values;
        int oldCapacity = capacity;
        capacity *= 2;
        keys = (K[]) new Object[capacity];
        values = (V[]) new Object[capacity];
        size = 0;

        for (int i = 0; i < oldCapacity; i++) {
            if (oldKeys[i] != null && oldKeys[i] != DELETED) {
                put(oldKeys[i], oldValues[i]);
            }
        }
    }
}
