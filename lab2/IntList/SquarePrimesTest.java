package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimple2() {
        IntList lst = IntList.of(8, 9, 10, 11, 12);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("8 -> 9 -> 10 -> 121 -> 12", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimple3() {
        IntList lst = IntList.of(20, 21, 22, 24, 25);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("20 -> 21 -> 22 -> 24 -> 25", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimesMedium1() {
        IntList lst = IntList.of(37, 38, 41, 43, 47);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1369 -> 38 -> 1681 -> 1849 -> 2209", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesMedium2() {
        IntList lst = IntList.of(2, 3, 5, 6, 13);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 9 -> 25 -> 6 -> 169", lst.toString());
        assertTrue(changed);
    }
}
