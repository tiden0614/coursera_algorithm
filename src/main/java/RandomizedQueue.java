import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by tiden on 10/21/14.
 */
public class RandomizedQueue<T> implements Iterable<T> {
    private E front = null;
    private E last = null;
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(T item) {
        if(item == null) {
            throw new NullPointerException("Cannot enqueue null object");
        }
        E e = new E(item, null, last);
        if(front == null) {
            front = e;
        }
        if(e.prev != null) {
            e.prev.next = e;
        }
        last = e;
        size++;
    }

    /**
     * returns and removes a random object from the queue
     * @return T the dequeued object
     */
    public T dequeue() {
        if(size == 0) {
            throw new NoSuchElementException("Cannot dequeue from an empty queue");
        }
        E pointer = getRandomElement();
        if(pointer.prev != null) {
            pointer.prev.next = pointer.next;
        }
        if(pointer.next != null) {
            pointer.next.prev = pointer.prev;
        }
        if(front == pointer) {
            front = pointer.next;
        }
        if(last == pointer) {
            last = pointer.prev;
        }
        size--;
        return pointer.element;
    }

    /**
     * returns a random object from the queue but does not delete it
     * @return T sample
     */
    public T sample() {
        if(size == 0) {
            throw new NoSuchElementException("Cannot sample from an empty queue");
        }
        return getRandomElement().element;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private T[] objectArray = (T[])(new Object[size]);
            {
                E start = front;
                int i = 0;
                while(start != null) {
                    objectArray[i++] = start.element;
                    start = start.next;
                }
                StdRandom.shuffle(objectArray);
            }

            @Override
            public boolean hasNext() {
                return index < objectArray.length;
            }

            @Override
            public T next() {
                if(!hasNext()) {
                    throw new NoSuchElementException("There is no next");
                }
                return objectArray[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Unsupported remove operation on Randomized Queue");
            }
        };
    }

    private E getRandomElement() {
        int rand = StdRandom.uniform(size);
        E pointer = front;
        for(int i = 0; i < rand; i++) {
            pointer = pointer.next;
        }
        return pointer;
    }

    private class E {
        private T element;
        private E next;
        private E prev;
        private E(T ele, E nex, E pre) {
            element = ele;
            next = nex;
            prev = pre;
        }
    }
}
