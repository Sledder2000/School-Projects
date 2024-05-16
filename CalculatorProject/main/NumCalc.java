package main;

import java.util.LinkedList;

import main.OpNode.OpCode;

public class NumCalc {
    
    /**
     * Class fields:
     * TODO: Operators precedence table, as a double array of operator codes (OpNode.OpCode).
     * TODO: Expression list: a double-linked list containing the numbers and operators to be evaluated.
     * TODO: Tracing list: a double-linked list containing raw nodes, each holding a trace frame as a string. 
     */
    private OpNode.OpCode[][] _precedence = {
        {OpNode.OpCode.POWER},
        {OpNode.OpCode.MULTIPLICATION, OpNode.OpCode.DIVISION, OpNode.OpCode.MODULO},
        {OpNode.OpCode.ADDITION, OpNode.OpCode.SUBTRACTION}
    };

    private RawNode _expressionList;
    private RawNode _tracingList;

    /**
     * Class constructor.
     */
    public NumCalc() {
    }
    
    /**
     * Takes the expression strings and builds the internal
     * double-linked list storing the expression nodes.
     * @param exprStrings - components of the expression string 
     */
    private void buildExprList(String[] exprStrings) {
        // For each string in the expression, try creating either
        // a numeric node or an operator node.
        // Whichever succeeds first, is added to the tail of the expression list.
        // If neither nodes could be created, throw a runtime exception.

        for (String string : exprStrings) {
            try {
                NumNode nummy = NumNode.createNode(string);
                if(_expressionList == null){
                    _expressionList = nummy;
                } else {
                _expressionList.addTail(nummy);
                }
            } catch (Exception e) {
                try {
                    OpNode oppy = OpNode.createNode(string);
                    if(_expressionList == null){
                        _expressionList = oppy;
                    } else {
                    _expressionList.addTail(oppy);
                    }
                } catch (Exception t) {
                    throw new RuntimeException("couldn't create a node");
                }
            }
        } 
    }
    
    /**
     * Evaluates the expression stored in the double linked list
     * and returns the final result. 
     * @return - evaluation result.
     */
    private String evalExprList() {
        // For each row of operators in the precedence table,
        // Scan the expression lists and check each operator node.
        // If the operator code is found in the row of operators,
        // evaluate it, and replace the operator node and its operands
        // with the node for the result. After each such reduction of the list
        // add the entire expression list as a frame into the trace frames list.
        // When all loops are done, expression list should contain only one numerical node,
        // which is the final result. Method returns the numerical value, as a string.
        addTraceFrame();
        while (_expressionList._next != null) {
        for (int i = 0; i < _precedence.length; i++) {
            RawNode temp = _expressionList;
            RawNode currant = _expressionList;
            while (temp._next != null) {
                boolean added = false;
                if (temp instanceof OpNode && ((OpNode)temp).getOpCode().equals(OpCode.POWER) && i == 0) {
                    while (currant._next != temp) {
                        currant = currant._next;
                    }
                    currant._next._prev = _expressionList;
                    _expressionList = ((OpNode)(currant)._next).evaluate();
                    if(temp._next._next != null){
                        _expressionList._next = temp._next._next;
                        if (temp._prev._prev != null) {
                            _expressionList._prev = temp._prev._prev;
                        }
                        addTraceFrame();
                        added = true;
                    }
                } else if (temp instanceof OpNode && (((OpNode)temp).getOpCode().equals(OpCode.MODULO) || ((OpNode)temp).getOpCode().equals(OpCode.MULTIPLICATION) || ((OpNode)temp).getOpCode().equals(OpCode.DIVISION)) && i == 1) {
                    while (currant._next != temp) {
                        currant = currant._next;
                    }
                    currant._next._prev = _expressionList;
                    _expressionList = ((OpNode)(currant)._next).evaluate();
                    if(temp._next._next != null){
                        _expressionList._next = temp._next._next;
                        if (temp._prev._prev != null) {
                            _expressionList._prev = temp._prev._prev;
                        }
                        addTraceFrame();
                        added = true;
                    }
                } else if (temp instanceof OpNode && (((OpNode)temp).getOpCode().equals(OpCode.ADDITION) || ((OpNode)temp).getOpCode().equals(OpCode.SUBTRACTION)) && i == 2) {
                    while (currant._next != temp) {
                        currant = currant._next;
                    }
                    currant._next._prev = _expressionList;
                    _expressionList = ((OpNode)(currant)._next).evaluate();
                    if(temp._next._next != null){
                        _expressionList._next = temp._next._next;
                        if (temp._prev._prev != null) {
                            _expressionList._prev = temp._prev._prev;
                        }
                        addTraceFrame();
                        added = true;
                    }
                }
                if (added == false) {
                temp = temp._next;
                } else {
                    break;
                }
            }
        }
    }
        addTraceFrame();
        return _expressionList.getRawContent();
    }
    
    /**
     * Constructs a new trace frame string from the expression list
     * and adds it to the trace list.
     */
    private void addTraceFrame() {
        // Builds a trace frame from the expression list:
        // Goes through the expression list nodes and adds their raw content
        // to a string accumulator, separated by one space character.
        // The resulting string is added as a new raw node to the tail of the tracing list. 

        String ret  = "";
        RawNode temp = _expressionList;
        RawNode temp2 = _tracingList;

        while (temp != null) {
            ret += temp.getRawContent() + " ";
            temp = temp._next;
        }
        RawNode retu = RawNode.createNode(ret);
        if(_tracingList == null){
            _tracingList = retu;
        }  else {
            while (temp2._next != null) {
                temp2 = temp2._next;
            }
            temp2._next = retu;
        }
    }
    
    /**
     * Takes a raw expression and returns the final evaluation result.
     * @param expression as entered by the user.
     * @return - evaluation result.
     */
    public String evaluate(String expression) {
        // Resets the expression list and the tracing list,
        this._expressionList = null;
        this._tracingList = null;
        // Splits the expression string around the " " character, into its individual parts
        String[] parts = expression.split(" ");
        // Builds the expression list,
        buildExprList(parts);
        // Evaluates the expression and returns the result of the evaluation.
        return evalExprList();
    }
    
    /**
     * Gives the evaluation trace of the last evaluated expression,
     * as a multi-line string.
     */
    @Override
    public String toString() {
        // Goes through the tracing list nodes adding the node raw content to
        // an accumulator string, separated by "\n" (each trace frame on a new line)
        // then returns the accumulated result.

        String ret  = "";
        RawNode temp = _tracingList;
        while (temp._next != null) {
            ret += temp.getRawContent() + "\n";
            temp = temp._next;
        }
        return ret;
    }

    public void tracingList () {
        while (_tracingList != null) {
            System.out.println(_tracingList.getRawContent());
            _tracingList = _tracingList._next;
        }
    }

}
