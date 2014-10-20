import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by tiden on 10/21/14.
 */
public class DequeTest {

    @Test
    public void testAddFirst() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("first");
        deque.addFirst("second");
        deque.addFirst("third");
        assertEquals(deque.size(), 3);
    }

    @Test
    public void testRemove() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("first");
        deque.addLast("second");
        deque.addLast("third");
        assertEquals(deque.removeFirst(), "first");
        assertEquals(deque.removeLast(), "third");
        assertEquals(deque.size(), 1);
        assertEquals(deque.removeFirst(), "second");
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testIterator() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("first");
        deque.addLast("second");
        deque.addLast("third");
        for(String s : deque) {
            StdOut.println(s);
            assertNotNull(s);
        }
    }
}
