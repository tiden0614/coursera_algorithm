import java.util.Iterator;

/**
 * Created by tiden on 10/21/14.
 */
public class Subset {
    public static void main(String[] args) {
        if(args.length < 1) {
            StdOut.println("Usage: java Subset k");
            StdOut.println("where k is an integer specifying the number of output elements");
        }
        int k = Integer.valueOf(args[0]);
        String input = StdIn.readLine();
        if(input != null && input != "") {
            RandomizedQueue<String> q = new RandomizedQueue<String>();
            String inputs[] = input.split(" ");
            for(String s : inputs){
                q.enqueue(s);
            }

            if(k > inputs.length) {
                k = inputs.length;
            }
            Iterator<String> iterator = q.iterator();
            for(int i = 0; i < k; i++) {
                StdOut.println(iterator.next());
            }
        }

    }
}
