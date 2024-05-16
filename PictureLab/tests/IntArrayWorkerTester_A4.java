import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class IntArrayWorkerTester_A4 {

    /** method to test setMatrix */
    @Test
    public void testSetMatrix() {
        IntArrayWorker worker = new IntArrayWorker();
        int[][] nums = { { 1, 1, 1 }, { 2, 2, 2 } };
        worker.setMatrix(nums);
        nums = worker.getMatrix();

        // matrix should have 2 rows and 3 columns on each row
        Assert.assertEquals(2, nums.length);
        for (int[] row : nums) {
            Assert.assertEquals(3, row.length);
        }

        // first row should contain only 1s, second only 2s.
        Assert.assertArrayEquals(new int[] { 1, 1, 1 }, nums[0]);
        Assert.assertArrayEquals(new int[] { 2, 2, 2 }, nums[1]);

        // print the verified result
        System.out.println("testSetMatrix: matrix has all 1's in first row and all 2's in second");
        worker.print();
    }

    /** Method to test fillPattern1 */
    @Test
    public void testFillPattern1() {
        IntArrayWorker worker = new IntArrayWorker();
        int[][] nums = new int[3][4];
        worker.setMatrix(nums);
        worker.fillPattern1();
        nums = worker.getMatrix();

        // verify the matrix is filled with:
        // 2's on diagonal,
        // 3's to the left of diagonal,
        // and 1's to the right of diagonal
        for (int r = 0; r < nums.length; r++) {
            for (int c = 0; c < nums[r].length; c++) {
                if (r > c) {
                    Assert.assertEquals(3, nums[r][c]);
                } else if (r == c) {
                    Assert.assertEquals(2, nums[r][c]);
                } else {
                    Assert.assertEquals(1, nums[r][c]);
                }
            }
        }

        // print the verified result
        System.out.println("testFillPattern1: matrix has 2's on diagonal, 3's to left, and 1's to right");
        worker.print();
    }

    /** Method to test getCount */
    @Test
    public void testGetCount() {
        IntArrayWorker worker = new IntArrayWorker();
        int[][] nums = new int[3][4];
        worker.setMatrix(nums);
        worker.fillPattern1();
        int count = worker.getCount(1);
        Assert.assertEquals(6, count);
    }

    /** Method to test getTotal */
    @Test
    public void testGetTotal() {
        IntArrayWorker worker = new IntArrayWorker();
        int[][] nums2 = { { 1, 2, 3 }, { 4, 5, 6 } };
        worker.setMatrix(nums2);
        int total = worker.getTotal();
        Assert.assertEquals(21, total);
    }

    /** Method to test getTotalNested */
    @Test
    public void testGetTotalNested() {
        IntArrayWorker worker = new IntArrayWorker();
        int[][] nums2 = { { 1, 2, 3 }, { 4, 5, 6 } };
        worker.setMatrix(nums2);
        int total = worker.getTotalNested();
        Assert.assertEquals(21, total);
    }

    /** Method to test getLargest */
    @Test
    public void testGetLargest() {
        // test when the largest is last
        IntArrayWorker worker = new IntArrayWorker();
        int[][] nums2 = { { 1, 2, 3 }, { 4, 5, 6 } };
        worker.setMatrix(nums2);
        int largest = worker.getLargest();
        Assert.assertEquals(nums2[1][2], largest);

        // test when largest is first
        int[][] nums3 = { { 6, 2, 3 }, { 4, 5, 1 } };
        worker.setMatrix(nums3);
        largest = worker.getLargest();
        Assert.assertEquals(nums3[0][0], largest);

        // test when largest is in the middle
        int[][] nums4 = { { 1, 2, 3 }, { 6, 5, 1 } };
        worker.setMatrix(nums4);
        largest = worker.getLargest();
        Assert.assertEquals(nums4[1][0], largest);

        // test when duplicate largest
        int[][] nums5 = { { 6, 2, 6 }, { 4, 5, 1 } };
        worker.setMatrix(nums5);
        largest = worker.getLargest();
        Assert.assertEquals(6, largest);
    }

    /** Method to test getColTotal */
    @Test
    public void testGetColTotal() {
        IntArrayWorker worker = new IntArrayWorker();
        int[][] nums2 = { { 1, 2, 3 }, { 4, 5, 6 } };
        worker.setMatrix(nums2);
        int total = worker.getColTotal(0);
        Assert.assertEquals(5, total);
        total = worker.getColTotal(1);
        Assert.assertEquals(7, total);
        total = worker.getColTotal(2);
        Assert.assertEquals(9, total);
    }
}
