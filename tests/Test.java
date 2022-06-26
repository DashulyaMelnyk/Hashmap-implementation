import org.junit.Before;
import p1.Array;
import p1.HashMap;

import static org.junit.Assert.*;

public class Test {

    private HashMap<String, Array<Integer>> hashMap;
    private Array<Integer> arr1;
    private Array<Integer> arr2;
    private Array<Integer> arr3;
    private Integer m1, m2, m3, m4, m5, m6;
    private String s1, s2, s3;


    @Before
    public void beforeTest(){
        m1 = 1;
        m2 = 7;
        m3 = 3;
        m4 = 2;
        m5 = 5;
        m6 = 6;
        s1 = "a";
        s2 = "b";
        s3 = "c";
        hashMap = new HashMap<>();
    }

    @org.junit.Test
    public void createArray(){
        arr1 = new Array<Integer>();
        assertNotNull(arr1);
        assertEquals(0, arr1.size());
    }

    @org.junit.Test
    public void testAddToArray(){
        arr1 = new Array<Integer>();
        arr1.add(m1);
        assertEquals(1, arr1.size());
        arr1.add(m3);
        arr1.add(m1);
        assertEquals(3, arr1.size());
    }

    @org.junit.Test
    public void testGetFromArray(){
        arr1 = new Array<Integer>();
        arr1.add(m1);
        arr1.add(m3);
        arr1.add(m1);
        assertEquals(m1, arr1.get(0));
        assertNull(arr1.get(11));
        assertNotNull(arr1.get(1));
    }
    @org.junit.Test
    public void testSetInArray(){
        arr1 = new Array<Integer>();
        arr1.add(m1);
        arr1.add(m3);
        arr1.add(m1);
        arr1.set(2,m5);
        assertNotNull(arr1.get(2));
        assertEquals(m5, arr1.get(2));
    }
    @org.junit.Test
    public void testClearArray(){
        arr1 = new Array<Integer>();
        arr1.add(m1);
        arr1.add(m3);
        arr1.add(m1);
        assertEquals(m1, arr1.get(0));
        arr1.clear();
        assertNull(arr1.get(0));
        assertEquals(0, arr1.size());
        assertNotEquals(m1, arr1.get(1));
    }

    @org.junit.Test
    public void testPutGetHashMap(){
        arr1 = new Array<Integer>();
        arr2 = new Array<Integer>();
        arr3 = new Array<Integer>();
        arr1.add(m1);
        arr1.add(m3);
        arr1.add(m1);
        arr2.add(m2);
        arr2.add(m3);
        arr3.add(m4);
        arr3.add(m6);
        arr3.add(m5);
        arr3.add(m1);
        assertNotNull(hashMap);
        assertEquals(0, hashMap.size());
        hashMap.put(s1,arr1);
        assertNotNull(hashMap.get(s1));
        assertEquals(arr1, hashMap.get(s1));
        hashMap.put(s1,arr2);
        assertEquals(arr2, hashMap.get(s1));
        assertEquals(1, hashMap.size());
    }
    @org.junit.Test
    public void testRemoveNodeHashMap(){
        arr1 = new Array<Integer>();
        arr2 = new Array<Integer>();
        arr3 = new Array<Integer>();
        arr1.add(m1);
        arr1.add(m3);
        arr1.add(m1);
        arr2.add(m2);
        arr2.add(m3);
        arr3.add(m4);
        arr3.add(m6);
        arr3.add(m5);
        arr3.add(m1);
        assertNotNull(hashMap);
        hashMap.put(s1,arr1);
        hashMap.put(s2,arr2);
        hashMap.put(s3,arr3);
        hashMap.remove(s2);
        assertNull(hashMap.get(s2));
        assertEquals(arr1, hashMap.get(s1));
    }

    @org.junit.Test
    public void testContainsNodeHashMap(){
        arr1 = new Array<Integer>();
        arr2 = new Array<Integer>();
        arr3 = new Array<Integer>();
        arr1.add(m1);
        arr1.add(m3);
        arr1.add(m1);
        arr2.add(m2);
        arr2.add(m3);
        arr3.add(m4);
        arr3.add(m6);
        arr3.add(m5);
        arr3.add(m1);
        assertNotNull(hashMap);
        hashMap.put(s1,arr1);
        hashMap.put(s2,arr2);
        hashMap.put(s3,arr3);
        assertTrue(hashMap.contains(s2));
        hashMap.remove(s2);
        assertEquals(false, hashMap.contains(s2));
        assertEquals(true, hashMap.contains(s1));
        assertEquals(2, hashMap.size());
    }

    @org.junit.Test
    public void testClearHashMap(){
        arr1 = new Array<Integer>();
        arr2 = new Array<Integer>();
        arr1.add(m1);
        arr1.add(m3);
        arr1.add(m1);
        arr2.add(m2);
        arr2.add(m3);
        hashMap.put(s1,arr1);
        hashMap.put(s2,arr2);
        assertEquals(2, hashMap.size());
        hashMap.clear();
        assertEquals(0, hashMap.size());
    }
}
