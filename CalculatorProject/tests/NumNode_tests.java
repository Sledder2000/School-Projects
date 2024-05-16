package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import main.NumNode;

public class NumNode_tests {

    @Test
    public void test_createNode() {
        // Create a few numerical nodes with various correct numerical values given strings
        // and verify they reflect their content correctly as a double value.
        // Try create a numerical node with an invalid string and
        // verify the result is a null reference.

        NumNode a = NumNode.createNode("1.0");
        NumNode b = NumNode.createNode("iuew-");
        NumNode c = NumNode.createNode("-574");
        assertEquals(a.getNumValue(), 1.0, 0.00001);
        assertEquals(b, null);
        assertEquals(c.getNumValue(), -574, 0.00001);
    }

}
