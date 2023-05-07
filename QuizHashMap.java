// **********************************************************************************
// Title: OnlineQuizPlatform
// Author: Demetri Jamison
// Course Section: CMIS201-ONL1 (Seidel) Spring 2023
// File: QuizHashMap.java
// Description: The QuizHashMap is a data structure that allows mapping keys to values using hash functions.
// This class will store question and correct answer pair.

// **********************************************************************************
package com.example.javafxonlinequizplatform.datastructure;
import java.util.LinkedList;

public class QuizHashMap<K, V> {
    private LinkedList<Entry<K, V>>[] buckets;
    private int size;

    public QuizHashMap(int initialCapacity) {
        buckets = new LinkedList[initialCapacity];
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new LinkedList<>();
        }
        for (Entry<K, V> entry : buckets[bucketIndex]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        buckets[bucketIndex].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        if (buckets[bucketIndex] != null) {
            for (Entry<K, V> entry : buckets[bucketIndex]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        int bucketIndex = hashCode % buckets.length;
        if (bucketIndex < 0) {
            bucketIndex += buckets.length;
        }
        return bucketIndex;
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
