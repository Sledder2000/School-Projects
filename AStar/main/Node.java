package AStar.main;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * The Node class represents a Node in a graph of Points. In addition to the  point itself,
 * a Node contains references to the neighboring nodes in the graph, and other internal state information
 * needed to track progress in route calculations.
 * Nodes can be compared to each other based on a specific heuristic applicable to the routing algorithm (i.e. A*)
 */
public class Node implements Comparable<Node> {

    // Point object (data) stored in this node. Each point contains a _label and _x, _y coordinates.
    private Point _point;
    // Map of nodes neighboring this node, indexed by the labels of their _point.
    private Map<String, Node> _neighbors;
    // Internal state of the node: null (if node was not visited) or reference to the previous node that reached to this node.
    private Node _previous;
    // Accumulated distance from the beginning of the route: sum of the lengths of all edges along the route from start to this node.
    private double _distanceSoFar;

    private double _cost;
    
    public Node(Point data) {
        _point = data;
        _neighbors = new HashMap<String, Node>();
        _previous = null;
        _distanceSoFar = 0;
        _cost = 0;
    }

    public double getCost() {
        return _cost;
    }
    
    public Point getPoint() {
        return _point;
    }

    public double distance(Point otherPoint) {
        return _point.distance(otherPoint);
    }


    public String getLabel() {
        return _point.getLabel();
    }
        
    public Node getState() {
        return _previous;
    }

    public boolean setState(Node previous) {
        if (previous == null) {
            _previous = null;
            _distanceSoFar = 0;
            _cost = 0;
            return true;
        } else {
        double distanceSoFar = previous._distanceSoFar + _point.distance(previous._point);
        if (_previous != null && distanceSoFar >= _distanceSoFar) {
            return false;
        } else {
            _previous = previous;
            _distanceSoFar = distanceSoFar;
            return true;
        }
        }
    }

    public boolean setState(Node previous, Node target) {
        _previous = previous;
        _distanceSoFar = previous._distanceSoFar + _point.distance(previous._point);
        _cost = _distanceSoFar + _point.distance(target._point);
        return true;
    }

    public void resetDistance() {
        _distanceSoFar = 0;
        _cost = 0;
    }

    public void addNeighbor(Node otherNode) {
        _neighbors.put(otherNode.getLabel(), otherNode);
    }

    public void removeNeighbor(Node otherNode) {
        _neighbors.remove(otherNode.getLabel());
    }

    public Collection<Node> getNeighbors() {
        return new LinkedList<Node>(_neighbors.values());
    }
    
   @Override
    public String toString() {
        String output = _point.toString() + " > ";
        boolean first = true;
        for(Node n : _neighbors.values()) {
            if (!first) {
                output += " ";
            }
            output += n.getLabel();
            first = false;
        }
        return output;
    }

    @Override
    public int compareTo(Node other) {
        return this._point.compareTo(other._point);
    }

    public int compareToCost(Node other) {
        if (_cost < other._cost) {
            return -1;
        } else if (_cost > other._cost) {
            return 1;
        } else {
            return 0;
        }
    }
}
