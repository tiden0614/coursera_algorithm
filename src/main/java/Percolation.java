public class Percolation {
    private int[] store;
    private int[] weight;
    private int size;
    private int opens = 0;
    private int edgeLength;

    public Percolation(int N) {
        edgeLength = N;
        size = N * N;
        store = new int[size + 2];
        weight = new int[size + 2];
        for(int i = 0; i < size + 2; i++) {
            weight[i] = 1;
            store[i] = -1;
        }
        store[0] = 0;
        store[size + 1] = size + 1;
        weight[0] = weight[size + 1] = size + 2;
    }

    public boolean isOpen(int x, int y) {
        return store[getSeq(x, y)] != -1;
    }

    public boolean isFull(int x, int y) {
        return connected(getSeq(x, y), 0);
    }

    public void open(int x, int y) {
        int seq = getSeq(x, y);
        if(seq <= 0 || seq > size || store[seq] != -1) return;
        if(seq > 0 && seq <= edgeLength) {
            // the first row should connect to the top
            store[seq] = 0;
        } else if(seq >= size - 2 && seq <= size) {
            // the last row should connect to the bottom
            store[seq] = size + 1;
        } else {
            // other rows set itself a tree
            store[seq] = seq;
        }
        // connect to nodes around
        probeAndConnect(seq, new int[]{ seq - 1, seq + 1, seq - edgeLength, seq + edgeLength });
        opens++;
    }

    public boolean percolates() {
        return connected(0, size + 1);
    }

    public int getOpens() {
        return opens;
    }

    public int getSize() {
        return size;
    }

    private void probeAndConnect(int seq, int[] targets) {
        for(int target : targets){
            if(target > 0 && target <= size && store[target] != -1) {
                union(seq, target);
            }
        }
    }

    private int getSeq(int x, int y) {
        return (x - 1) * edgeLength + y;
    }

    private int root(int i) {
        int father = store[i];
        while(father != i) {
            store[i] = store[father];
            i = father;
            father = store[father];
        }
        return father;
    }

    private boolean connected(int i, int j) {
        return root(i) == root(j);
    }

    private void union(int i, int j) {
        int r1 = root(i);
        int r2 = root(j);
        if(weight[r1] < weight[r2]) {
            store[r1] = r2;
            weight[r2] += weight[r1];
        } else {
            store[r2] = r1;
            weight[r1] += weight[r2];
        }
    }
}