package tests;

import static org.junit.Assert.fail;

import org.junit.Test;

import main.RawNode;

public class RawNode_tests {

    @Test
    public void test_createRawNode() {
        // Create a new RawNode
        // and verify the content is as expected
        // and that its links are null.
        RawNode n1 = new RawNode("test");
        if (n1.getNext() != null) {
            fail();
        }
        if (!n1.getRawContent().equals("test")) {
            fail();
        }
    }
    
    @Test
    public void test_addNext() {
        //Create two nodes, n1 and n2, add one next to the other and
        //verify their links are pointing to their respective nodes.
        //Create a third node n3, add it after n1 and verify its links
        //are pointing to n1 and n2 respectively, and that
        //the n1 and n2 links were adjusted accordingly as well.
        RawNode n1 = new RawNode("1");
        RawNode n2 = new RawNode("2");
        RawNode n3 = new RawNode("3");
        n1.addNext(n2);
        if (!n1.getNext().equals(n2)) {
            fail();
        }
        n1.addNext(n3);
        if (!n1.getNext().equals(n3)) {
            fail();
        }
        if (!n3.getNext().equals(n2)) {
            fail();
        }
        if (n2.getNext() != null) {
            fail();
        }
    }
    
    @Test
    public void test_addTail() {
        //Create three nodes, n1, n2 and n3, add them into a three nodes list
        //then create a fourth node n, adding it to the tail of the list pointed by n1.
        //verify n links are pointing to n3 and null, while n3 next link is now pointing to n.

        RawNode n1 = new RawNode("1");
        RawNode n2 = new RawNode("2");
        RawNode n3 = new RawNode("3");
        RawNode n4 = new RawNode("4");
        n1.addNext(n2);
        n2.addNext(n3);
        n1.addTail(n4);
        if (!n3.getNext().equals(n4)) {
            fail();
        }
        if (!n2.getNext().equals(n3)) {
            fail();
        }
    }
}
