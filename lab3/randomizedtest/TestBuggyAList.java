package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        for (int i = 4; i < 7; i += 1) {
            correct.addLast(i);
            broken.addLast(i);
        }

        assertEquals(correct.size(), broken.size());

        for (int i = 0; i < 3; i+=1) {
            assertEquals(correct.removeLast(), broken.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = correct.size();
                int size2 = broken.size();
                System.out.println("size: " + size + " Size2" + size2);
                assertEquals(size, size2);
            } else if (operationNumber == 2) {
                // getLast
                if (correct.size() > 0 && broken.size() > 0) {
                    int last = correct.getLast();
                    int last2 = broken.getLast();
                    System.out.println("getLast(" + last +")" + " getlast(" + last2 +")");
                    assertEquals(last, last2);
                }
            } else if (operationNumber == 3) {
                //removeLast
                if (correct.size() > 0 && broken.size() > 0) {
                    int last = correct.removeLast();
                    int last2 = broken.removeLast();
                    System.out.println("removeLast(" + last +")" + " removeLast(" + last2 +")");
                    assertEquals(last, last2);
                }
            }
        }
    }
}
