package main;

public class RawNode {
    
    /**
     * Class fields:
     * Raw content in this node, as a string.
     * References to the previous and next node, or null respectively if nodes do not exist. 
     */
    private String _rawContent;
    protected RawNode _prev;
    protected RawNode _next;
    /**
     * Class constructor. Builds a new generic raw content node.
     * @param rawContent - the raw content stored in this node.
     */
    public RawNode(String rawContent) {
        // configures / initializes class fields.
        _rawContent = rawContent;
        _prev = null;
        _next = null;
    }
    
    /**
     * Node factory method, returning a new node for the given raw content
     * or null if the node could not be created.
     * @param rawContent - the row content stored in this node.
     * @return the new node.
     */
    public static RawNode createNode(String rawContent) {
        return new RawNode(rawContent);
    }
    
    /**
     * Gets the node's raw content.
     * @return the raw content.
     */
    public String getRawContent() {
        // returns the raw content
        return _rawContent;
    }
    
    /**
     * Gets the previous node.
     * @return the reference to the previous node, or null if none exists.
     */
    public RawNode getPrev() {
        // returns the reference to the previous node
        return _prev;
    }
    
    /**
     * Gets the next node.
     * @return the reference to the next node, or null if none exists.
     */
    public RawNode getNext() {
        // returns the reference to the next node
        return _next;
    }
    
    /**
     * Adds another node right after this node.
     * @param other - the node to be added.
     * @return the node that's been added.
     */
    public RawNode addNext(RawNode other) {
        // code inserting the other node after this node
        if (this._next != null) {
            other._next = this._next;
            other._prev = this;
            this._next = other;
        } else {
            this._next = other;
            if (other != null) {
                other._prev = this;
            }

        }

        /*other._next = this._next;
        this._next = other;
        other._prev = this;
        */return other;
    }
    
    /**
     * Adds another node to the tail of the list.
     * @param other - the node to be added.
     * @return the new tail of the list.
     */
    public RawNode addTail(RawNode other) {
        // code inserting the other node at the very end of
        // the list where this node is part of.
        RawNode temp = this;
        while (temp._next != null) {
            temp = temp._next;
        }
        temp._next = other;
        return other;
    }
}
