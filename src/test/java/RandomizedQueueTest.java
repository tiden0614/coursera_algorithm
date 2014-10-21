import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by tiden on 10/21/14.
 */
public class RandomizedQueueTest {
    @Test
    public void testEnqueue() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue("first");
        q.enqueue("second");
        q.enqueue("third");
        assertFalse(q.isEmpty());
    }

    @Test
    public void testSample() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue("first");
        q.enqueue("second");
        q.enqueue("third");
        assertNotNull(q.sample());
        assertNotNull(q.sample());
        assertNotNull(q.sample());
        assertFalse(q.isEmpty());
    }

    @Test
    public void testDeuque() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue("first");
        q.enqueue("second");
        q.enqueue("third");
        assertNotNull(q.dequeue());
        assertNotNull(q.dequeue());
        assertNotNull(q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    public void testIterator() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue("first");
        q.enqueue("second");
        q.enqueue("third");
        for(String s : q) {
            StdOut.println(s);
            assertNotNull(s);
        }
    }
}
