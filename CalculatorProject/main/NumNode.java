package main;

public class NumNode extends RawNode {
    
    /**
     * Class fields:
     * Numerical content (double) of this node
     */
    private double num;
    /**
     * Class constructor. Builds a new numerical node.
     * @param rawContent - the raw content stored in this node.
     */
    protected NumNode(String rawContent) {
        // Initializes the raw content of this node
        super(rawContent);
    }
    
    /**
     * NumNode factory method, returning a new numerical node 
     * for the given raw content or null if the node could not be created.
     * @param rawContent - the raw content stored in this node.
     * @return the new numerical node.
     */
    public static NumNode createNode(String rawContent) {
        // Tries to parse the raw content into a double value
        // if successful, creates a NumNode for it and save the value within,
        // otherwise returns null 

        try {
        double parsedNum = Double.parseDouble(rawContent);
        NumNode currant = new NumNode(rawContent);
        currant.num = parsedNum;
        return currant;
        } catch (Exception e) {
            throw new RuntimeException("numnode wrong");
        }
    }
    
    /**
     * Gets the node's numerical value.
     * @return
     */
    public double getNumValue() {
        // returns the numerical (double) content
        return num;
    }
}
