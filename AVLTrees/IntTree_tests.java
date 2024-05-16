package AVLTrees;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntTree_tests {

    private IntTree newTree(int... values) {
        IntTree tree = new IntTree();
        for(int data : values) {
            tree.addValue(data);
        }
        return tree;
    }

    private IntTree newTreeAVL(int... values) {
        IntTree tree = new IntTree();
        for(int data : values) {
            tree.addValueAVL(data);
        }
        return tree;
    }

    @Test
    public void test_bstTree() {
        IntTree tree = newTree(1,2,3,4,5,6);
        String expected = 
        "[1]               \n" +
        "   \\              \n" +
        "   [2]            \n" +
        "      \\           \n" +
        "      [3]         \n" +
        "         \\        \n" +
        "         [4]      \n" +
        "            \\     \n" +
        "            [5]   \n" +
        "               \\  \n" +
        "               [6]\n" +
        "                  \n";
        String output = tree.toPrettyPrint();
        //System.out.println(output);
        assertEquals(expected, output);
    }

    @Test
    public void test_bstTreeAVLCreate() {
        IntTree tree = newTreeAVL(19, 28, 21);
        String expected = 
        "    [21]    \n" +
        "   /    \\   \n" +
        "[19]    [28]\n" +
        "            \n";
        String output = tree.toPrettyPrint();
        assertEquals(expected, output);
        System.out.println(output);
    }
    @Test
    public void test_bstTreeAVLRightRight() {
        IntTree tree = newTreeAVL(19);
        System.out.println(tree.toPrettyPrint());
        String expected = 
        "    [20]    \n" +
        "   /    \\   \n" +
        "[19]    [21]\n" +
        "            \n";
        tree.addValueAVL(20); 
        tree.addValueAVL(21); 
        String output = tree.toPrettyPrint();
        assertEquals(expected, output);
        System.out.println(output);
    }
    @Test
    public void test_bstTreeAVLRightLeft() {
        IntTree tree = newTreeAVL(19);
        System.out.println(tree.toPrettyPrint());
        String expected = 
        "    [22]    \n" +
        "   /    \\   \n" +
        "[19]    [27]\n" +
        "            \n";
        tree.addValueAVL(27); 
        tree.addValueAVL(22); 
        String output = tree.toPrettyPrint();
        assertEquals(expected, output);
        System.out.println(output);
    }
    @Test
    public void test_bstTreeAVLLeftRight() {
        IntTree tree = newTreeAVL(19);
        System.out.println(tree.toPrettyPrint());
        String expected = 
        "    [18]    \n" +
        "   /    \\   \n" +
        "[17]    [19]\n" +
        "            \n";
        tree.addValueAVL(17); 
        tree.addValueAVL(18); 
        String output = tree.toPrettyPrint();
        assertEquals(expected, output);
        System.out.println(output);
    }
    @Test
    public void test_bstTreeAVLLeftLeft() {
        IntTree tree = newTreeAVL(19);
        String expected = 
        "   [9]    \n" +
        "  /   \\   \n" +
        "[1]   [19]\n" +
        "          \n";
        tree.addValueAVL(9); 
        tree.addValueAVL(1); 
        String output = tree.toPrettyPrint();
        assertEquals(expected, output);
        System.out.println(output);
    }
    @Test
    public void test_bstTreeAVLBIG() {
        IntTree tree = newTreeAVL(19, 50, 1, 4, 96, 39, 2);
        tree.addValueAVL(10); 
        tree.addValueAVL(340); 
        System.out.println(tree.toPrettyPrint());
        tree.addValueAVL(10000); 
        tree.addValueAVL(9857); 
        System.out.println(tree.toPrettyPrint());
        tree.addValueAVL(238); 
        tree.addValueAVL(2348); 
        System.out.println(tree.toPrettyPrint());
        tree.addValueAVL(5); 
        tree.addValueAVL(3); 
        tree.addValueAVL(8); 
        tree.addValueAVL(9);
        tree.addValueAVL(6); 
        tree.addValueAVL(90);
        System.out.println(tree.toPrettyPrint());
    }
    //@Test
    public void test_bstTreeAVLDelete() {
        IntTree tree = newTreeAVL(19, 18, 16, 15, 14);
        tree.deleteValueAVL(16);
        System.out.println(tree.toPrettyPrint());
    }
}
