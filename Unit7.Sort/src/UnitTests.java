import org.junit.Test;

import java.util.Arrays;

import org.junit.Assert;

public class UnitTests {

	@Test
    public void testJavaArraySort()
    {
		int [] input = new int[] {10, 5, 8, -3, 11, 11, 1, -8, 12, 8, 15};
		int [] output = new int[] {-8, -3, 1, 5, 8, 8, 10, 11, 11, 12, 15};
		SortingLab.javaArraySort(input);
		Assert.assertEquals("javaArraySort(input, output): ", true, Arrays.equals(input,  output));
    }
	
	@Test
	public void testInsertionSort() {
		
		
		/* FILL IN UNIT TEST CODE FOR SortingLab.insertionSort() */
		
	}
	
	@Test
	public void testSelectionSort() {
		
		/* FILL IN UNIT TEST CODE FOR SortingLab.insertionSort() */
		
	}
}
