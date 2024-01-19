
package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private Node sentinel;
    private int size;

    /* The first item (if exists) is at sentinel.next */
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        private Node(T i, Node n) {
            item = i;
            next = n;
        }
    }

    /** Create an empty linked list deque */
    public LinkedListDeque() {
        sentinel = new Node(null, null);
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /** Add an item of type T to the front of the deque. */
    public void addFirst(T item) {
        Node firstNode = sentinel.next;
        firstNode.prev = new Node(item, sentinel);
        sentinel.next = firstNode.prev;
        firstNode.prev.prev = sentinel;
        size = size + 1;
    }

    /** Add an item of type T to the back of the deque. */
    public void addLast(T item) {
        Node lastNode = sentinel.prev;
        lastNode.next = new Node(item, sentinel);
        sentinel.prev = lastNode.next;
        lastNode.next.prev = lastNode;
        size = size + 1;
    }

    /** Remove an item of type T at the front of the deque. */
    public T removeFirst() {
        if (size() == 0) {
            return null;
        }
        Node rmNode = sentinel.next;
        T rmItem = rmNode.item;
        sentinel.next = rmNode.next;
        rmNode.next.prev = sentinel;
        rmNode.item = null;
        rmNode.prev = null;
        rmNode.next = null;
        size = size - 1;
        return rmItem;
    }

    /** Remove an item of type Tat the back of the deque. */
    public T removeLast() {
        if (size() == 0) {
            return null;
        }
        Node rmNode = sentinel.prev;
        T rmItem = rmNode.item;
        sentinel.prev = rmNode.prev;
        rmNode.prev.next = sentinel;
        rmNode.item = null;
        rmNode.prev = null;
        rmNode.next = null;
        size = size - 1;
        return rmItem;
    }

    /** get the item of type T at the index. */
    public T get(int index) {
        if (index < 0) {
            return null;
        }
        int nodeInd = 0;
        for (Node p = sentinel.next; p.item != null; p = p.next) {
            if (nodeInd != index) {
                nodeInd += 1;
            } else {
                return p.item;
            }
        }
        return null;
    }

    /** return size of the Deque. */
    public int size() {
        return size;
    }

    /** helper function of getRecur */
    private T getRecurHelper(int index, int nodeInd, Node p) {
        if (p.item == null) {
            return null;
        }
        if (nodeInd == index) {
            return p.item;
        }
        return  getRecurHelper(index, nodeInd + 1, p.next);
    }

    /** get the item of the index in recursive way. */
    public T getRecursive(int index) {
        Node p = sentinel.next;
        if (index < 0) {
            return null;
        }
        int nodeInd = 0;
        return getRecurHelper(index, nodeInd, p);
    }

    /** return an iterator cause the Deque is iterable. */
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int wizPos;
        LinkedListIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    public void printDeque() {
        for (Node p = sentinel.next; p.item != null; p = p.next) {
            System.out.print(p.item + "");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<?> lld = (LinkedListDeque<?>) o;
        if (lld.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (lld.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }


    private static void main(String[] args) {
        int n = 99;

        LinkedListDeque<Integer> d1 = new LinkedListDeque<>();
        for (int i = 0; i <= n; i++) {
            d1.addLast(i);
        }

        LinkedListDeque<Integer> d2 = new LinkedListDeque<>();
        for (int i = n; i >= 0; i--) {
            d2.addFirst(i);
        }

        d1.printDeque();

        System.out.println(d1.equals(d2));

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            ad1.addLast(i);
        }

        System.out.println((d1.equals(ad1)));
    }
}
