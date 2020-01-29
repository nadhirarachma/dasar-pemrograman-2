package exercise;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolverTest {
	@Test public void testTwoSumOnEmptyArray() {
        assertFalse("Two sum doesn't crash/giving wrong result on empty array", new Solver().isExistTwoSum(new int[]{}, 0));
    }
	
	@Test public void testTwoSumNotFound() {
        assertFalse("Two sum should not found any match", new Solver().isExistTwoSum(new int[] {1, 2, 7}, 10));
    }
	
    @Test public void testTwoSumFoundDifferentValue() {
        assertTrue("Two sum should consider different number with different value", new Solver().isExistTwoSum(new int[]{1, 2, 3}, 4));
    }
    
    @Test public void testTwoSumFoundSameValue() {
        assertTrue("Two sum should consider different number with the same value", new Solver().isExistTwoSum(new int[]{1, 2, 2}, 4));
    }
    
    @Test public void testTwoSumNotConsiderSameNumber() {
        assertFalse("Two sum should not consider the same number twice", new Solver().isExistTwoSum(new int[] {2}, 4));
    }
}
