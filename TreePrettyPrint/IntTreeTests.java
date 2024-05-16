package TreePrettyPrint;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntTreeTests {


    @Test
    public void testaddValue() throws Exception {
        IntTree tree = new IntTree();
        tree.addValue(10);
        assertEquals(10, tree.overallRoot.data);
        tree.addValue(11);
        assertEquals(11, tree.overallRoot.left.data);
        tree.addValue(9);
        assertEquals(9, tree.overallRoot.right.data);
    }

    @Test
    public void testEmpty() throws Exception {
        IntTree tree = new IntTree();
        assertEquals("", tree.toPreOrderString());
        assertEquals("", tree.toInOrderString());
        assertEquals("", tree.toPostOrderString());
    }

    @Test
    public void testSmallData() throws Exception {
        IntTree tree = new IntTree();
        tree.addValue(10);
        tree.addValue(5);
        tree.addValue(3);
        tree.addValue(8);
        tree.addValue(14);
        tree.addValue(16);
        assertEquals("[10]([5]([3]()())([8]()()))([14]()([16]()()))", tree.toPreOrderString());
        assertEquals("((()[3]())[5](()[8]()))[10](()[14](()[16]()))", tree.toInOrderString());
        assertEquals("((()()[3])(()()[8])[5])(()(()()[16])[14])[10]", tree.toPostOrderString());
    }

    @Test
    public void testBigData() throws Exception {
        IntTree tree = new IntTree();
        tree.addValue(100000);
        tree.addValue(542356346);
        tree.addValue(3345);
        tree.addValue(8345345);
        tree.addValue(1464364343);
        tree.addValue(1);
        assertEquals("[100000]([3345]()())([542356346]([8345345]()())([1464364343]([1]()())()))", tree.toPreOrderString());
        assertEquals("(()[3345]())[100000]((()[8345345]())[542356346]((()[1]())[1464364343]()))", tree.toInOrderString());
        assertEquals("(()()[3345])((()()[8345345])((()()[1])()[1464364343])[542356346])[100000]", tree.toPostOrderString());
    }
}
