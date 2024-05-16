package AStar.main;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * The Graph represents a collection of Nodes (each of which is a Point) along with all their connections.
 * A Graph is akin to a road network in the real life, in which street intersections are represented by Nodes,
 * and each street segment is represented by the connections between the nodes.
 */
public class Graph {

    // Map of all the nodes in the graph, indexed by the label of the Point object inside the node
    private Map<String, Node> _nodes;
    // Count of all the queue.add() calls, as a measure of algorithm complexity
    private int _queueAddCount;
    // Distance traveled during the last route calculation as a measure of algorithm optimality
    private double _traveledDistance;
    
    public Graph() {
        _nodes = new TreeMap<String, Node>();
    }
    
    public int size() {
        return _nodes.size();
    }

    public int getQueueAddCount() {
        return _queueAddCount;
    }

    public double getTraveledDistance() {
        return _traveledDistance;
    }

    public void addNode(Point point) {
        Node node = new Node(point);
        String label = node.getLabel();
        if (_nodes.containsKey(label)) {
            throw new RuntimeException("Ambiguous graph!");
        }
        _nodes.put(label, node);
    }

    public Point removeNode(String label) {
        Node node = _nodes.get(label);
        if (node == null) {
            throw new RuntimeException("Node does not exist in graph!");
        }
        for(Node n : _nodes.values()) {
            n.removeNeighbor(node);
        }
        _nodes.remove(node.getLabel());
        return node.getPoint();
    }
    
    public void addEdge(String fromLabel, Object toLabel) {
        Node fromNode = _nodes.get(fromLabel);
        Node toNode = _nodes.get(toLabel);
        if (fromNode == null || toNode == null) {
            throw new RuntimeException("Node(s) not in the graph!");
        }
        fromNode.addNeighbor(toNode);
    }
    
    public void removeEdge(String fromLabel, String toLabel) {
        Node fromNode = _nodes.get(fromLabel);
        Node toNode = _nodes.get(toLabel);
        if (fromNode == null || toNode == null) {
            throw new RuntimeException("Node(s) not in the graph!");
        }
        fromNode.removeNeighbor(toNode);
    }
    
    @Override
    public String toString() {
        String output = "";
        boolean first = true;
        for(Node n : _nodes.values()) {
            if (!first) {
                output += "\n";
            }
            output += n.toString();
            first = false;
        }
        return output;
    }

    private void reset() {
        for (Node n : _nodes.values()) {
            // Reset the state of the graph: sets _previous references inside each node to null.
            n.setState(null);
        }
        _traveledDistance = 0;
        _queueAddCount = 0;
    }

    /**
     * Implements the FirstPath algorithm to calculate a route that starts at {fromLabel} point 
     * and ends at {toLabel} point.The route is returned as a list of strings, each of which is the
     * label of the point visited in the route ordered from start {fromLabel} to target {toLabel}.
     * @param fromLabel - label of the starting point
     * @param toLabel - label of the target point
     * @return - list of point labels in the route, or null if a route does not exist.
     * @throws RuntimeException if {fromLabel} and/or {toLabel} cannot be resolved to nodes in the graph.
     */
    public LinkedList<String> routeFirstPath(String fromLabel, String toLabel) {
        // Get the fromNode and toNode containing the points labeled with fromLabel and toLabel 
        Node nFrom = _nodes.get(fromLabel);
        Node nTo = _nodes.get(toLabel);
        
        reset();
        // Check both nodes exist, otherwise throw exception
        try {
            if (nFrom == null || nTo == null) {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            System.out.println("null bro");
        }
        // Initialize fromNode (start) _previous field to non-null (self) and _distanceSoFar to 0.
        nFrom.setState(nFrom);
        // Create a queue of nodes for all the nodes to be explored and add fromNode to it
        Queue<Node> nQueue = new LinkedList<Node>();
        nQueue.add(nFrom);
        _queueAddCount++;

        boolean routeFound = false;
        // Loop through the queue until either it becomes empty or we extract the toNode (target)
        while (!nQueue.isEmpty() && !routeFound) {
            // Remove the first node from the queue.
            Node curr = nQueue.remove();
            // If the node is the target, we're done, mark that we found the route and break from the loop
            if (curr.getLabel().equals(nTo.getLabel())) {
                routeFound = true;
            }
            // We're not done, so loop through all the neighbors of node and..
            for (Node n : curr.getNeighbors()) {
                // ..If neighbor node had already been visited (it's state is not null), just skip it
                // ..Otherwise mark neighbor with a reference to the current node, upadte its _distanceSoFar and add it to the queue.
                if (n.getState() == null) {
                    n.setState(curr);
                    nQueue.add(n);
                    _queueAddCount++;

                }
            }
        }
        // If toNode was not visited (its state is null), no route cound be found, return null.
        if (nTo.getState() == null) {
            return null;
        }
        // Otherwise, a route was found. Each node, starting from toNode points to the _previous node in the route.
        // Create a link list to capture all the point labels in the route.
        LinkedList<String> path = new LinkedList<String>();
        Node c = nTo;
        // Loop through all nodes, starting from toNode, following the links to previous nodes, until fromNode is reached.
            // Add the label of the point in the node to the result list
        while (c != nFrom) {
            _traveledDistance += c.distance(c.getState().getPoint());
            path.addFirst(c.getLabel());
            c = c.getState();
        }
        _traveledDistance += c.distance(c.getState().getPoint());
        path.addFirst(c.getLabel());
        System.out.println(_traveledDistance);
        // Return the route list:
        return path;
    }

    /**
     * Implements the Dijkstra algorithm to calculate a route that starts at {fromLabel} point 
     * and ends at {toLabel} point.The route is returned as a list of strings, each of which is the
     * label of the point visited in the route ordered from start {fromLabel} to target {toLabel}.
     * @param fromLabel - label of the starting point
     * @param toLabel - label of the target point
     * @return - list of point labels in the route, or null if a route does not exist.
     * @throws RuntimeException if {fromLabel} and/or {toLabel} cannot be resolved to nodes in the graph.
     */
    public LinkedList<String> routeDijkstra(String fromLabel, String toLabel) {
        // Modify the routeFirstPath implementation to verify if the state of a neighboring node
        // improves at each step (rather than skipping the node if it was already visible, like
        // in FirstPath algorithm). If the state improves, re-add it to the queue.
        // _previous points the the previous step in the route (fromNode points to itself)
        // _distanceSoFar should reflect the sum of edge lengths from start to the node
        // Get the fromNode and toNode containing the points labeled with fromLabel and toLabel 
        Node nFrom = _nodes.get(fromLabel);
        Node nTo = _nodes.get(toLabel);
        
        reset();
        // Check both nodes exist, otherwise throw exception
        try {
            if (nFrom == null || nTo == null) {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            System.out.println("null bro");
        }
        // Initialize fromNode (start) _previous field to non-null (self) and _distanceSoFar to 0.
        nFrom.setState(nFrom);
        // Create a queue of nodes for all the nodes to be explored and add fromNode to it
        Queue<Node> nQueue = new LinkedList<Node>();
        nQueue.add(nFrom);
        _queueAddCount++;

        boolean routeFound = false;
        // Loop through the queue until either it becomes empty or we extract the toNode (target)
        while (!nQueue.isEmpty() && !routeFound) {
            // Remove the first node from the queue.
            Node curr = nQueue.remove();
            // If the node is the target, we're done, mark that we found the route and break from the loop
            if (curr.getLabel().equals(nTo.getLabel())) {
                routeFound = true;
                break;
            }
            // We're not done, so loop through all the neighbors of node and..
            for (Node n : curr.getNeighbors()) {
                // ..If neighbor node had already been visited (it's state is not null), just skip it
                // ..Otherwise mark neighbor with a reference to the current node, upadte its _distanceSoFar and add it to the queue.
                if (n.setState(curr)) {
                    nQueue.add(n);
                    _queueAddCount++;
                }
            }
        }
        // If toNode was not visited (its state is null), no route cound be found, return null.
        if (nTo.getState() == null) {
            return null;
        }
        // Otherwise, a route was found. Each node, starting from toNode points to the _previous node in the route.
        // Create a link list to capture all the point labels in the route.
        LinkedList<String> path = new LinkedList<String>();
        Node c = nTo;
        // Loop through all nodes, starting from toNode, following the links to previous nodes, until fromNode is reached.
            // Add the label of the point in the node to the result list
        while (c != nFrom) {
            _traveledDistance += c.distance(c.getState().getPoint());
            path.addFirst(c.getLabel());
            c = c.getState();
        }
        _traveledDistance += c.distance(c.getState().getPoint());
        path.addFirst(c.getLabel());
        System.out.println(_traveledDistance);
        // Return the route list:
        return path;
    }

    /**
     * Implements the A* algorithm to calculate the approximate shortest route from the {fromLabel} point
     * and ending at {toLabel} point.The route is returned as a list of strings, each of which is the
     * label of the point visited in the route ordered from start {fromLabel} to target {toLabel}.
     * @param fromLabel - label of the starting point
     * @param toLabel - label of the target point
     * @return - list of point labels in the route, or null if a route does not exist.
     * @throws RuntimeException if {fromLabel} and/or {toLabel} cannot be resolved to nodes in the graph.
     */
    public LinkedList<String> routeAStar(String fromLabel, String toLabel) {
        // Modify the routeDijkstra implementation to make use of a PriorityQueue<Node> instead of a simple Queue
        // Make sure to update all relevant fields in each of the nodes being explored:
        // _previous points the the previous step in the route (fromNode points to itself)
        // _distanceSoFar should reflect the sum of edge lengths from start to the node
        // _cost should be the sum between _distanceSoFar and the estimate of distance from the node to the target.
        Node nFrom = _nodes.get(fromLabel);
        Node nTo = _nodes.get(toLabel);
        
        reset();
        // Check both nodes exist, otherwise throw exception
        try {
            if (nFrom == null || nTo == null) {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            System.out.println("null bro");
        }
        // Initialize fromNode (start) _previous field to non-null (self) and _distanceSoFar to 0.
        nFrom.setState(nFrom);
        // Create a queue of nodes for all the nodes to be explored and add fromNode to it
        HeapPriorityQueue<Node> nQueue = new HeapPriorityQueue<Node>(Node.class); 
        nQueue.add(nFrom);
        _queueAddCount++;

        boolean routeFound = false;
        // Loop through the queue until either it becomes empty or we extract the toNode (target)
        while (!nQueue.isEmpty() && !routeFound) {
            // Remove the first node from the queue.
            Node curr = nQueue.remove();
            _queueAddCount++;
            // If the node is the target, we're done, mark that we found the route and break from the loop
            if (curr.getLabel().equals(nTo.getLabel())) {
                routeFound = true;
                break;
            }
            Map<Double, Node> m = new TreeMap<Double, Node>();
            // We're not done, so loop through all the neighbors of node and..
            for (Node n : curr.getNeighbors()) {
                // ..If neighbor node had already been visited (it's state is not null), just skip it
                // ..Otherwise mark neighbor with a reference to the current node, upadte its _distanceSoFar and add it to the queue.
                Double nCost = (curr.distance(n.getPoint()) + n.distance(nTo.getPoint()));
                m.put(nCost, n);
            }
            for (Double d : m.keySet()) {
                if (m.get(d).setState(curr)) {
                    nQueue.add(m.get(d));
                }
            }
        }
        // If toNode was not visited (its state is null), no route cound be found, return null.
        if (nTo.getState() == null) {
            return null;
        }
        // Otherwise, a route was found. Each node, starting from toNode points to the _previous node in the route.
        // Create a link list to capture all the point labels in the route.
        LinkedList<String> path = new LinkedList<String>();
        Node c = nTo;
        // Loop through all nodes, starting from toNode, following the links to previous nodes, until fromNode is reached.
            // Add the label of the point in the node to the result list
        while (c != nFrom) {
            _traveledDistance += c.distance(c.getState().getPoint());
            path.addFirst(c.getLabel());
            c = c.getState();
        }
        _traveledDistance += c.distance(c.getState().getPoint());
        path.addFirst(c.getLabel());
        System.out.println(_traveledDistance);
        // Return the route list:
        return path;
    }
}
