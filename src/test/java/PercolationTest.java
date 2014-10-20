import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tiden on 10/20/14.
 */
public class PercolationTest {
    Percolation percolation;

    @Before
    public void init() {
        percolation = new Percolation(4);
    }

    @Test
    public void testOpen() {
        // should be open
        percolation.open(2, 3);
        assertTrue(percolation.isOpen(2, 3));
    }

    @Test
    public void testFull() {
        // should be full
        percolation.open(1,3);
        percolation.open(2,3);
        assertTrue(percolation.isFull(2, 3));
    }

    @Test
    public void testPercolates() {
        // should percolates
        percolation.open(1, 3);
        percolation.open(2, 3);
        percolation.open(3, 3);
        percolation.open(4, 3);
        assertTrue(percolation.percolates());
    }
}
