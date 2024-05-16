package main;

public class OpNode extends RawNode {
    
    public enum OpCode {
        UNKNOWN,
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION,
        POWER,
        MODULO
    }
    
    /**
     * Class fields:
     * Operator code for this node
     */
    private OpCode _operator;
    
    /**
     * Class constructor. Builds a new operator node.
     * @param rawContent - the raw content stored in this node.
     */
    protected OpNode(String rawContent) {
        super(rawContent);
        _operator = OpCode.UNKNOWN;
        // UNKNOWN initial value for this node's operator code
        // since the raw content is not parsed.
    }
    
    /**
     * OpNode factory method, returning a new operator node 
     * for the given raw content or null if the node could not be created.
     * @param rawContent - the raw content stored in this node.
     * @return the new operator node.
     */
    public static OpNode createNode(String rawContent) {
        // Tries to parse the raw content into a OpCode value
        // if successful, creates an OpNode for it and save the operator code value within,
        // otherwise returns null 
        try {
            OpCode oc = OpCode.UNKNOWN;
            switch(rawContent){ 
                case "^":
                    oc = OpCode.POWER;
                    break;
                case "%":
                    oc = OpCode.MODULO;
                    break;
                case "/":
                    oc = OpCode.DIVISION;
                    break;
                case "*":
                    oc = OpCode.MULTIPLICATION;
                    break;
                case "-":
                    oc = OpCode.SUBTRACTION;
                    break;
                case "+":
                    oc = OpCode.ADDITION;
                    break;
                default :
                    break;
            }
            OpNode o = new OpNode(rawContent);
            o._operator = oc;
            return o;
            } catch (Exception e) {
                return null;
            }
    }
    
    /**
     * Gets the node's operator code.
     * @return - the operator code.
     */
    public OpCode getOpCode() {
        // returns the operator code (OpCode)
        return _operator;
    }
    
    /**
     * Evaluates this operator node, returning the result of the evaluation
     * as a new numerical node.
     * @return the result as a new numerical node.
     */
    public NumNode evaluate() {
        // precondition for evaluation is that this (operator) node is surrounded
        // by numerical nodes. Check and throw runtime exceptions if these are not true.
        // Otherwise get the two numerical (operand) nodes, execute the operator and
        // return the result as a new numerical node.
        String word = this.getPrev().getRawContent();
        if ((this.getPrev() instanceof NumNode) && (this.getNext() instanceof NumNode)) {

            NumNode n1 = (NumNode)this.getPrev();
            NumNode n2 = (NumNode)this.getNext();
            RawNode n1Prev = n1.getPrev();
            RawNode n2Next = n2.getNext();
            double count = 0.0;
            switch(this._operator) {
                case POWER:
                    for (int i = 0; i < n2.getNumValue(); i++){
                        count = n1.getNumValue() * n1.getNumValue();
                    }
                    break;  
                case MULTIPLICATION:
                    count = n1.getNumValue() * n2.getNumValue();
                    break;  
                case DIVISION:
                    if (n2.getNumValue() == 0.0) {
                        throw new RuntimeException("Divide by 0");
                        
                    }
                    count = n1.getNumValue() / n2.getNumValue();
                    break;  
                case MODULO:
                    if (n2.getNumValue() == 0.0) {
                        throw new RuntimeException("Modulo by 0");
                    }
                    count = n1.getNumValue() % n2.getNumValue();
                    break;  
                case ADDITION:
                    count = n1.getNumValue() + n2.getNumValue();
                    break;  
                case SUBTRACTION:  
                    count = n1.getNumValue() - n2.getNumValue();
                    break;  
                case UNKNOWN: 
                    throw new RuntimeException("Tried to evaluate UNKOWN");
                        
            }
            NumNode result = NumNode.createNode("" + count);
            if (n1Prev != null) {
                n1Prev._next = result;
                result._prev = n1Prev;
            }

            if (n2Next != null) {
                n2Next._prev = result;
                result._next = n2Next;
            }

            return result;
        } else {
            throw new RuntimeException("not surrounded");
        }
    }

}
