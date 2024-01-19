package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items = (T[]) new Object[8];
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    public ArrayDeque(T item) {
        items[3] = item;
        size = 1;
        nextFirst = 2;
        nextLast = 4;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int firstPos = Math.abs(capacity - size) / 2;
        System.arraycopy(items, nextFirst + 1, a, firstPos, size);
        items = a;
        nextFirst = firstPos - 1;
        nextLast = firstPos + size;
    }

    private int arrayInd(int ind) {
        if (nextFirst + 1 + ind >= items.length) {
            return nextFirst + 1 + ind - items.length;
        } else {
            return nextFirst + 1 + ind;
        }
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        size += 1;
        nextFirst -= 1;
        if (nextFirst == -1) {
            resize(size * 2);
        }
    }

    public void addLast(T item) {
        items[nextLast] = item;
        size += 1;
        nextLast += 1;
        if (nextLast == items.length) {
            resize(size * 2);
        }
    }

    private T getFirst() {
        int ind = arrayInd(0);
        return items[ind];
    }

    private T getLast() {
        int ind = arrayInd(size - 1);
        return items[ind];
    }

    public T get(int i) {
        int ind = arrayInd(i);
        return items[ind];
    }

    public int size() {
        return size;
    }

    public  T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst += 1;
        T item = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        shrinkSize();
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast -= 1;
        T item = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        shrinkSize();
        return item;
    }

    private void shrinkSize() {
        if (isEmpty()) {
            resize(8);
        } else if (items.length / 4 > size && size >= 4) {
            resize(size * 2);
        }
    }

    public void printDeque() {
        for (T i : this) {
            System.out.print(i + "");
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        private ArrayDequeIterator() {
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

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<?> ad = (ArrayDeque<?>) o;
        if (ad.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (ad.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

    private void printArray() {
        for (int i = 0; i < items.length; i += 1) {
            System.out.print(items[i] + "");
        }
    }
}
