package p1;

import java.util.*;


/**
 * Resizable-array implementation of the Iterable interface.  Implements
 * all optional list operations, and permits all elements, except null.
 * @author Daria Melnyk
 * @param <E> the type of elements in this list
 */
public class Array<E> implements Iterable{
    int size;
    protected E[] list;

    /**
     * Creates the array with the size that equals 0.
     */
    public Array(){
        list = (E[]) new Object[0];
        this.size = 0;
    }

    /**
     * Inserts the specified element at the last position in this list.
     * @param element
     * @throws NullPointerException if we try to insert a null element
     */
    public void add(E element){
        if(element==null){throw new NullPointerException();}
        E[] newList = (E[]) new Object[size+1];
        Iterator iter =this.iterator();
        int i = 0;
        while(iter.hasNext()){
            newList[i++] = (E)iter.next();
        }
        newList[size++] = element;
        list = newList;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index - index of the element to return
     * @return the element at the specified position in this list
     */
    public E get(int index){
        if(index >= size) return null;
        return list[index];
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index - index of the element to replace
     * @param element – element to be stored in the specified position
     */
    public void set(int index, E element){
        Objects.checkIndex(index, size);
        list[index] = element;
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
     */
    public int size(){ return size;}
    public void clear(){
        final E[] li = list;
        for(int i = 0; i<li.length; i++){
            li[i] = null;
        }
        size = 0;
    }

    /**
     * Forms the String of an array by iterating
     * @return a String representing the elements that are in array
     */
    @Override public String toString(){
        String s = "[";
        for(int i = 0; i < list.length; i++){
            if(i == list.length-1){
                s += list[i].toString();
            }else s += list[i].toString() + ", ";
        }
        s += ("]");
        return s;
    }

    /**
     * @return the new Iterator for an array
     */
    @Override public Iterator iterator(){
        return new Iterator() {
            int cursor;       // index of next element to return
            int lastRet = -1; // index of last element returned; -1 if no such

            /**
             * @return true if the next element exists and doesn't equals null
             */
            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            /**
             * Gets the next element of the list
             * @return the next element of the list
             */
            @Override
            public Object next() {
                int i = cursor;
                if (i >= size)
                    throw new NoSuchElementException();
                E[] elementData = list;
                if (i >= elementData.length)
                    throw new ConcurrentModificationException();
                cursor = i + 1;
                return elementData[lastRet = i];
            }
        };
    }

}
