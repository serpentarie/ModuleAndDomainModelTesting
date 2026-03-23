package second;

import java.util.ArrayList;
import java.util.List;

public class HashTable<K> {

    public enum Point {
        INSERT_START, HASH_CALCULATED, BUCKET_NOT_EMPTY, NODE_ADDED, INSERT_END,
        SEARCH_START, NODE_FOUND, NODE_NOT_FOUND, SEARCH_END,
        DELETE_START, NODE_DELETED_HEAD, NODE_DELETED_INTERNAL, NODE_NOT_FOUND_FOR_DELETE, DELETE_END
    }

    private static class HashNode<K> {
        K key;
        HashNode<K> next;

        HashNode(K key) {
            this.key = key;
        }
    }

    private static final int CAPACITY = 13;

    private final HashNode<K>[] buckets;
    private final List<Point> trace;

    public HashTable() {
        this.buckets = (HashNode<K>[]) new HashNode[CAPACITY];
        this.trace = new ArrayList<>();
    }

    public List<Point> getTrace() {
        return trace;
    }

    public void clearTrace() {
        trace.clear();
    }

    private int hash(K key) {
        trace.add(Point.HASH_CALCULATED);
        return Math.abs(key.hashCode() % CAPACITY);
    }

    public void insert(K key) {
        trace.add(Point.INSERT_START);
        int index = hash(key);

        HashNode<K> head = buckets[index];
        if (head != null) {
            trace.add(Point.BUCKET_NOT_EMPTY);
        }

        HashNode<K> newNode = new HashNode<>(key);
        newNode.next = head;
        buckets[index] = newNode;

        trace.add(Point.NODE_ADDED);
        trace.add(Point.INSERT_END);
    }

    public boolean search(K key) {
        trace.add(Point.SEARCH_START);
        int index = hash(key);

        HashNode<K> current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                trace.add(Point.NODE_FOUND);
                trace.add(Point.SEARCH_END);
                return true;
            }
            current = current.next;
        }

        trace.add(Point.NODE_NOT_FOUND);
        trace.add(Point.SEARCH_END);
        return false;
    }

    public void delete(K key) {
        trace.add(Point.DELETE_START);
        int index = hash(key);

        HashNode<K> current = buckets[index];
        HashNode<K> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                    trace.add(Point.NODE_DELETED_HEAD);
                } else {
                    prev.next = current.next;
                    trace.add(Point.NODE_DELETED_INTERNAL);
                }
                trace.add(Point.DELETE_END);
                return;
            }
            prev = current;
            current = current.next;
        }

        trace.add(Point.NODE_NOT_FOUND_FOR_DELETE);
        trace.add(Point.DELETE_END);
    }
}