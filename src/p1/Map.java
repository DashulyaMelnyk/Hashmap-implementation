package p1;

public interface Map<K,V> extends Iterable<K>
{
    void put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean contains(K key);
    int size();
    boolean isEmpty();
    void clear();
}
