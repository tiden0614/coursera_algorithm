import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tiden on 10/20/14.
 */
public class PercolationStatsTest {
    PercolationStats ps;

    @Before
    public void init() {
        ps = new PercolationStats(200, 100);
    }

    @Test
    public void testStddev() {
        StdOut.println("stddev: " + ps.stddev());
        assertTrue(ps.stddev() > 0);
    }

    @Test
    public void testMean() {
        StdOut.println("mean: " + ps.mean());
        assertTrue(ps.mean() > 0);
    }
}
