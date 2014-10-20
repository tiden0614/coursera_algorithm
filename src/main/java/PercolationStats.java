/**
 * Created by tiden on 10/20/14.
 */
public class PercolationStats {
    private int t, n;
    private double u, x, conLo, conHi;
    private double[] results;
    private int[] seq;

    public PercolationStats(int N, int T) {
        n = N;
        t = T;
        u = x = conHi = conLo = -2;
        seq = new int[N * N];
        for(int i = 0; i < seq.length; i++) {
            seq[i] = i;
        }
        results = new double[T];
        for(int i = 0; i < results.length; i++) {
            results[i] = getOneThreshold();
        }
    }

    public double mean() {
        if(u == -2) {
            double total = 0;
            for(double r : results) {
                total += r;
            }
            u = total / t;
        }
        return u;
    }

    public double stddev() {
        if(x == -2) {
            double st = 0;
            for(double r : results) {
                st += Math.pow((r - mean()), 2);
            }
            x = st / (t - 1);
        }
        return x;
    }

    public double confidenceLo() {
        if(conLo == -2) {
            conLo = mean() - (1.96 * stddev()) / Math.sqrt(t);
        }
        return conLo;
    }

    public double confidenceHi() {
        if(conHi == -2) {
            conHi = mean() + (1.96 * stddev()) / Math.sqrt(t);
        }
        return conHi;
    }

    private double getOneThreshold() {
        Percolation p = new Percolation(n);
        // FIXME is this random renderer causing the difference between my statistical numbers and the official's?
        StdRandom.shuffle(seq);
        int index = 0;
        while(!p.percolates() && p.getOpens() <= n * n) {
            int randint = seq[index++] + 1;
            int x = (randint / n) + 1;
            int y = randint % n;
            p.open(x, y);
        }
        return ((double) p.getOpens()) / p.getSize();
    }

    public static void main(String[] args) {
        if(args.length < 2) {
            StdOut.println("Usage: java PercolationStats SIZE REPETITION");
            return;
        }
        Integer size = Integer.valueOf(args[args.length - 2]);
        Integer repetition = Integer.valueOf(args[args.length - 1]);
        PercolationStats ps = new PercolationStats(size, repetition);
        StdOut.printf("%-23s = %f\n", "mean", ps.mean());
        StdOut.printf("%-23s = %f\n", "stddev", ps.stddev());
        StdOut.printf("%-23s = %f, %f\n", "95% confidence interval", ps.confidenceLo(), ps.confidenceHi());
    }
}
