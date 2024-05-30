package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import main.NumNode;
import main.OpNode;
import main.OpNode.OpCode;

public class OpNode_tests {

    public static void main(String[] args) {
       NumNode n1 = NumNode.createNode("4");
        OpNode addition = OpNode.createNode("+");
        NumNode n2 = NumNode.createNode("7");
        n1.addNext(addition);
        addition.addNext(n2);
        System.out.println(n1.getNext());
        System.out.println(n2.getPrev());
        System.out.println(addition.getPrev());
        System.out.println(addition.getNext());
        
        
    }
    @Test
    public void test_createNode() {
        // Create OpNodes for each of the supported operators (i.e. "+",..)
        // and verify they reflect back the the correct operator code (i.e. OpCode.ADDITION)

        OpNode addition = OpNode.createNode("+");
        OpNode subtraction = OpNode.createNode("-");
        OpNode multiplication = OpNode.createNode("*");
        OpNode modulo = OpNode.createNode("%");
        OpNode division = OpNode.createNode("/");
        OpNode power = OpNode.createNode("^");
        OpNode unkown = OpNode.createNode("er346");
        assertEquals(addition.getOpCode(), OpCode.ADDITION);
        assertEquals(subtraction.getOpCode(), OpCode.SUBTRACTION);
        assertEquals(multiplication.getOpCode(), OpCode.MULTIPLICATION);
        assertEquals(modulo.getOpCode(), OpCode.MODULO);
        assertEquals(division.getOpCode(), OpCode.DIVISION);
        assertEquals(power.getOpCode(), OpCode.POWER);
        assertEquals(unkown.getOpCode(), OpCode.UNKNOWN);
    }
    
    @Test
    public void test_evaluate() {
        // Create a valid sequence of NumNode, OpNode, NumNode, chain them in a linked list
        // And verify the result of the evaluation of the operator node is as expected.

        NumNode n1 = NumNode.createNode("4");
        OpNode addition = OpNode.createNode("+");
        NumNode n2 = NumNode.createNode("7");
        n1.addNext(addition);
        addition.addNext(n2);
        assertEquals(addition.evaluate().getNumValue(), 11.0, 0.0); 

        NumNode n3 = NumNode.createNode("4");
        OpNode subtraction = OpNode.createNode("-");
        NumNode n4 = NumNode.createNode("7");
        n3.addNext(subtraction);
        subtraction.addNext(n4);
        assertEquals(subtraction.evaluate().getNumValue(), -3.0, 0.0); 

        NumNode n5 = NumNode.createNode("4");
        OpNode multiplication = OpNode.createNode("*");
        NumNode n6 = NumNode.createNode("7");
        n5.addNext(multiplication);
        multiplication.addNext(n6);
        assertEquals(multiplication.evaluate().getNumValue(), 28.0, 0.0); 

        NumNode n7 = NumNode.createNode("4");
        OpNode division = OpNode.createNode("/");
        NumNode n8 = NumNode.createNode("7");
        n7.addNext(division);
        division.addNext(n8);
        assertEquals(division.evaluate().getNumValue(), 0.57, 0.01); 

        NumNode n9 = NumNode.createNode("4");
        OpNode modulo = OpNode.createNode("%");
        NumNode n10 = NumNode.createNode("7");
        n9.addNext(modulo);
        modulo.addNext(n10);
        assertEquals(modulo.evaluate().getNumValue(), 4.0, 0.0); 

        NumNode n11 = NumNode.createNode("4");
        OpNode unkown = OpNode.createNode("dfg5");
        NumNode n12 = NumNode.createNode("7");
        n11.addNext(unkown);
        unkown.addNext(n12);
        try {
            assertEquals(unkown.evaluate().getNumValue(), 11.0, 0.0); 
            fail();
        } catch (Exception e) {
        }
    }
}
