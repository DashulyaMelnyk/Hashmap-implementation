package p1;
import java.util.*;


/**
 * Hash table based implementation of the Map interface.
 * This implementation provides optional map operations, and permits null values and the null key.
 * An instance of HashMap has two parameters that affect its performance: initial capacity and load factor.
 * The capacity is the number of buckets in the hash table, and the initial capacity is simply the capacity at the time the hash table is created.
 * The load factor is a measure of how full the hash table is allowed to get before its capacity is automatically increased.
 * @author Daria Melnyk
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class HashMap<K,V> implements Map<K,V> {
    /**The table, initialized on first use, and resized as necessary.*/
    HashMap.Node<K, V> buckets[];
    private int size = 0;
    private final float loadFactor;
    private int capacity;

    /**
     * A constructor with no parameters that creates a map with the capacity = 16 and loadFactor = 0.75f
     */
    public HashMap() {
        this(16, 0.75f);
    }

    /**
     * Creates a new array in a special way
     * @param length The length of a new array (normally capacity)
     * @return a new array of Node<K,V>
     */
    @SuppressWarnings("unchecked")
    private Node<K,V>[] newArray(int length)
    {
        return (Node<K,V>[])new Node[length];
    }

    /**
     * Creates a map with the specified capacity and loadFactor
     * @param capacity the number of buckets in table
     * @param loadFactor the maximum occupancy of hash table
     */
    public HashMap(int capacity, float loadFactor) {
        this.loadFactor = loadFactor;
        this.capacity = capacity;
        this.buckets = newArray(capacity);
    }

    /**
     * Gets a new iterator of keys
     * @return new CIterator
     */
    @Override
    public Iterator<K> iterator() {
        return new CIterator();
    }

    /**
     * Adds a new element in hash table. In case of added key earlier, it rewrites
     * the value of the node in table with a new one. The index of the bucket in which
     * a node will be added is calculated with a key hash code and as it can be negative
     * we take the module of it. At the end we check if hashtable is overfilled, and in the
     * true case we call the function expand() that will make it bigger.
     * Implements Map.put and related methods.
     * @param key - key with which the specified value is to be associated
     * @param value - value to be associated with the specified key
     */
    @Override
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException();
        }
            final int index = Math.abs(key.hashCode() % (buckets.length-1));
            final Node<K, V> head = buckets[index];
            Node<K, V> current = head;
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            // if key is not already in map, we will insert it in head
            buckets[index] = new HashMap.Node<K, V>(key, value, head);  // bucket may/may not be empty
            size++;
            double load = size / capacity;
            if (load > loadFactor) expand();

    }

    /** Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * If this map contains a mapping from a key k to a value v such that then
     * this method returns v; otherwise it returns null. (There can be at most one such mapping.)
     * Implements Map.get and related methods.
     * @param key - the key
     * @return the value that is associated with the specified key, or null if none
     */
    @Override
    public V get(K key) {
        Node<K,V> kvNode = buckets[key.hashCode() % (buckets.length-1)];
        while(kvNode != null){
            if (kvNode.key.equals(key)) {
                return kvNode.value;}
            kvNode = kvNode.next;
        }
        return null;
    }

    /** Removes the mapping for the specified key from this map if present.
     * @param key - the key of the node that should be deleted
     * @return the previous value associated with key, or
     * null if there was no mapping for {@code key}.
     */
    @Override
    public V remove(K key) {
        final int index = key.hashCode() % (buckets.length-1);
        final Node<K,V> head = buckets[index];
        if (head == null) return null;
        if (head.key.equals(key)){ //To remove a head
            V oldValue = head.value;
            buckets[index] = head.next;
            size--;
            return oldValue;
        }
        // To remove not head ->
        Node<K,V> prev = head;
        Node<K,V> current = head.next;
        while (current != null) {
            if (key.equals(current.key)) {
                V oldValue = current.value;
                prev.next = current.next;  // remove Node
                size--;
                return oldValue;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    /** Returns true if this map contains a mapping for the specified key.
     * @param key – The key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key.
     */
    @Override
    public boolean contains(K key) {
        if(size == 0 || buckets == null){
            return false;
        }
        final int index = Math.abs(key.hashCode() % (buckets.length-1));
        Node<K,V> kvNode = buckets[index];
        while(kvNode != null){
            if (kvNode.key.equals(key)) return true;
            kvNode = kvNode.next;
        }
        return false;
    }

    /**
     * Gets the size of the hash table
     * @return the int that represents the size of the hash table
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Indicates if the hash table is empty
     * @return true if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all the mappings from this map. The map will be empty after this call returns.
     */
    @Override
    public void clear() {
        HashMap.Node<K, V>[] tab = buckets;
        if (tab != null && size > 0) {
            Arrays.fill(tab, null);
            buckets = newArray(capacity);
            size = 0;
        }
    }

    /**
     * Prints the hash table with the special mask. Iterates throw all the mappings
     * printing its key and value in a special way.
     */
    public void printMap(){
            Node<K,V>[] tab;
            if (size > 0 && (tab = buckets) != null) {
                for (int i = 0; i < tab.length; ++i) {
                    for (Node<K,V> e = tab[i]; e != null; e = e.next) {
                        System.out.println(e.getKey() + " : " + e.getValue().toString());
                    }
                }
            }
    }

    /**
     * Resizes the buckets by doubling the capacity, and rewriting throw the iteration.
     */
    public void expand() {
        capacity = 2 * capacity;
        HashMap.Node<K, V>[] oldBuckets = buckets;
        size = 0;

        buckets = (Node<K, V>[]) new Node[capacity];  // No first class generics!

        for (Node<K, V> n : oldBuckets) {  // rehash entries in bucket n
            while (n != null) {
                put(n.key, n.value);
                n = n.next;
            }
        }
    }

    /**
     * Basic hash node, used for entries.
     * @author Daria Melnyk
     * @param <K> the type of keys maintained by this node
     * @param <V> the type of node value
     */
    private static class Node<K,V>
    {
        final K key;
        V value;
        HashMap.Node<K,V> next;

        /**
         * Creates the Node with the specified key, value and the pointer on next node
         * @param key - the key of the node
         * @param value - the value of the node
         * @param next - the pointer on next node
         */
        Node(K key, V value, HashMap.Node<K,V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * Gets the node key
         * @return the node's key
         */
        public final K getKey()        { return key; }

        /**
         * Gets the node value
         * @return the node's value
         */
        public final V getValue()      { return value; }

        /**
         * Forms the String of object of node
         * @return a String representing the node e.g. "1 = Antonio"
         */
        public final String toString() { return key + "=" + value.toString(); }


        /**
         * Sets a new value for the Node
         * @param newValue - the value that should be inserted
         * @return the value that was previously added to the node
         */
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

    }

    /**
     * Iterator based on implementation of interface Iterator<K>.
     * Is used to iterate the hash tables by using its methods next() and hasNext()
     */
    private class CIterator implements Iterator<K>
    {
        HashMap.Node<K,V> next;        // next entry to return
        HashMap.Node<K,V> current;     // current entry
        int index;             // current slot
        int count;

        /**
         * The constructor with no parameters creates the iterator for the hash table
         * with the count that equals capacity.
         */
        public CIterator(){
            count = capacity;
            p1.HashMap.Node<K,V>[] t = buckets;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { // advance to first entry
                do {} while (index < t.length && (next = t[index++]) == null);
            }
        }

        /**
         * @return true if the next element exists and doesn't equals null
         */
        @Override public boolean hasNext() {
            return next != null;
        }

        /**
         * Gets the next Node
         * @return the next of the node
         */
        @Override public K next() {
            HashMap.Node<K,V>[] t;
            HashMap.Node<K,V> e = next;
            if (capacity != count)
                throw new ConcurrentModificationException();
            if (e == null)
                throw new NoSuchElementException();
            if ((next = (current = e).next) == null && (t = buckets) != null) {
                do {} while (index < t.length && (next = t[index++]) == null);
            }
            return e.getKey();
        }

    }
}
