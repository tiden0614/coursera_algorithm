import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by tiden on 10/21/14.
 */
public class Deque<T> implements Iterable<T> {
    private InnerElement front = null;
    private InnerElement last = null;
    private int size = 0;

    public Deque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T t) {
        if(t == null) {
            throw new NullPointerException("Cannot add null object to Deque");
        }
        size++;
        InnerElement e = new InnerElement(t, null, front);
        if(front != null) {
            front.prev = e;
        }
        front = e;
        if(last == null) {
            last = front;
        }
    }

    public void addLast(T t) {
        if(t == null) {
            throw new NullPointerException("Cannot add null object to Deque");
        }
        size++;
        InnerElement e = new InnerElement(t, last, null);
        if(last != null) {
            last.next = e;
        }
        last = e;
        if(front == null) {
            front = last;
        }
    }

    public T removeFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException("Cannot remove object from an empty Deque");
        }
        InnerElement e = front;
        front = e.next;
        size--;
        if(size == 0) {
            front = last = null;
        }
        return e.element;
    }

    public T removeLast() {
        if(isEmpty()) {
            throw new NoSuchElementException("Cannot remove object from an empty Deque");
        }
        InnerElement e = last;
        last = e.prev;
        size--;
        if(size == 0) {
            front = last = null;
        }
        return e.element;
    }



    @Override
    public Iterator<T> iterator() {
        return new InnerIterator(new InnerElement(null, null, front));
    }

    private class InnerElement {
        private T element;
        private InnerElement prev;
        private InnerElement next;

        InnerElement(T ele, InnerElement pre, InnerElement nex) {
            element = ele;
            prev = pre;
            next = nex;
        }
    }

    private class InnerIterator implements Iterator<T> {
        private InnerElement ele;

        InnerIterator(InnerElement e) {
            ele = e;
        }

        @Override
        public boolean hasNext() {
            return ele.next != null;
        }

        @Override
        public T next() {
            if(ele.next == null) {
                throw new NoSuchElementException("Cannot next() when there is no next one");
            }
            ele = ele.next;
            return ele.element;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Deque does not support remove operation on Iterator");
        }
    }
}
