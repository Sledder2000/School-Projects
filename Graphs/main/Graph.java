package Graphs.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Class definition for a generic (Directed) Graph.
 * The Graph contains a collection of Nodes, each Node contains
 * a collection of references (edges) to their neighboring Nodes.
 * 
 * @param <T> - reference type of Nodes contained in the Graph.
 *            The type T is expected to implement the Comparable interface,
 *            such that Nodes can be compared to each other.<br>
 *            E.g.:
 * 
 *            <pre>
 * Graph&lt;String&gt; g = new Graph&ltString&gt();
 *            </pre>
 * 
 * @see Node
 */
public class Graph<T extends Comparable<T>> {

    /**
     * Private Map keying each Node in the Graph by the hashCode of its data
     * E.g: Given
     * 
     * <pre>
     * Node<String> n = new Node<String>("abc");
     * </pre>
     * 
     * added to the graph,
     * the _nodes map contains a Map.Entry with
     * 
     * <pre>
     * key="abc".hashCode()<br>
     * value=n
     * 
     * <pre>
     * 
     * @see java.lang.Object#hashCode()
     */
    private Map<Integer, Node<T>> _nodes;

    /**
     * Constructs a new Graph as an empty container fit for Nodes of the type T.
     * 
     * @see Graph
     * @see Node
     */
    public Graph() {
        _nodes = new TreeMap<Integer, Node<T>>();
    }

    /**
     * Gets the size of this Graph. The size of the Graph is equal to the number
     * of Nodes it contains.
     * 
     * @return number of Nodes in this Graph.
     */
    public int size() {
        return _nodes.size();
    }

    /**
     * Checks if the state of all the Nodes in the Graph matches a given value.
     * 
     * @param state - the value to check against all Nodes in the Graph.
     * @return true if all the Nodes in the Graph have a state matching the
     *         given value, false otherwise.
     * @see Node#getState()
     */
    public boolean checkState(int state) {
        for (Node<?> n : _nodes.values()) {
            if (state != n.getState()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Adds a new Node to the Graph containing the <i>data</i>. The method
     * throws if the Graph already contains a Node with data having the same
     * hashCode().
     * 
     * @param data - the data reference (of type T) contained in the new Node.
     * @throws RuntimeException if the Graph already contains a Node for the
     *                          given data.
     * @see java.lang.Object#hashCode()
     */
    public void addNode(T data) {
        int nodeHash = data.hashCode();
        if (_nodes.containsKey(nodeHash)) {
            throw new RuntimeException("Ambiguous graph!");
        }

        _nodes.put(nodeHash, new Node<T>(data));
    }

    /**
     * Adds a new directed Edge to the Graph, linking the Nodes containing
     * <i>from</i> and <i>to</i> data. It is expected the two Nodes exist
     * otherwise the method throws an exception.
     * 
     * @param from - Node where the Edge is starting.
     * @param to   - Node where the Edge is ending.
     * @throws RuntimeException if either of the two Nodes are not present in the
     *                          Graph.
     * @see Node
     * @see Graph#removeEdge(Comparable, Comparable)
     */
    public void addEdge(T from, T to) {
        Node<T> fromNode = _nodes.get(from.hashCode());
        Node<T> toNode = _nodes.get(to.hashCode());
        if (fromNode == null || toNode == null) {
            throw new RuntimeException("Node(s) not in the graph!");
        }

        fromNode.addEdge(toNode);
    }

    /**
     * Removes an existent directed Edge from the Graph, if one exists.
     * The Edge to be removed is linking the nodes containing the <i>from</i>
     * and <i>to</i> data references. The two Nodes must exist, otherwise the
     * method throws an exception.
     * 
     * @param from - Node at the starting point of the Edge.
     * @param to   - Node at the ending point of the Edge.
     * @throws RuntimeException if either of the two Nodes are not present in the
     *                          Graph.
     * @see Node
     * @see Graph#addEdge(Comparable, Comparable)
     */
    public void removeEdge(T from, T to) {
        Node<T> fromNode = _nodes.get(from.hashCode());
        Node<T> toNode = _nodes.get(to.hashCode());
        if (fromNode == null || toNode == null) {
            throw new RuntimeException("Node(s) not in the graph!");
        }

        fromNode.removeEdge(toNode);
    }

    /**
     * Removes a Node from the Graph if one exists, along with all
     * its outgoing (egress) and incoming (ingress) edges. If there
     * is no Node hosting the <i>data</i> reference the method does
     * nothing.
     * 
     * @param data - Node to be removed from the Graph.
     */
    public void removeNode(T data) {
        Node<T> Node = _nodes.get(data.hashCode());
        if (Node == null) {
            return;
        }
        _nodes.remove(data.hashCode());
        Map<Integer, Node<T>> _edges = Node.getEdges();
        for (Map.Entry<Integer, Node<T>> entry : _nodes.entrySet()) {
            entry.getValue().removeEdge(Node);
        }
        for (Node<T> otherNode : _edges.values()) {
            Node.removeEdge(otherNode);
        }
    }

    /**
     * Checks if the Graph is undirected.
     * 
     * @return true if Graph is undirected, false otherwise.
     */
    public boolean isUGraph() {
        for (Map.Entry<Integer, Node<T>> entry : _nodes.entrySet()) {
            if (!entry.getValue().isUGraph()) {
                return false;
            }
        }
        return true;
    }

    public void resetState() {
        for (Node<T> n : _nodes.values()) {
            n.reset();
        }
    }

    /**
     * Checks is the Graph is connected.
     * 
     * @return true if the Graph is connected, false otherwise.
     */
    public boolean isConnected() {
        for (Map.Entry<Integer, Node<T>> entry : _nodes.entrySet()) {
            for (Node<T> n : _nodes.values()) {
                n.reset();
            }
            entry.getValue().checkConnected();
            if (!checkState(1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the Graph is Directed Acyclic graph.
     * 
     * @return true if Graph is Directed Acyclic, false otherwise.
     */
    public boolean isDAGraph() {
        if (this.isUGraph()) {
            return false;
        }
        Queue<Node<T>> scheduler = new LinkedList<Node<T>>();
        try {
            scheduler.add(((Node<T>) (_nodes.values().toArray()[0])));
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
        while (!scheduler.isEmpty()) {
            Node<T> node = scheduler.remove();
            node.setState(1);
            Map<Integer, Node<T>> edgeMap = node.getEdges();
            for (Node<T> edge : edgeMap.values()) {
                if (edge.getState() == 1)
                    return false;
                scheduler.add(edge);
            }
        }
        for (Node<T> n : _nodes.values())
            n.reset();
        return true;

    }

    /**
     * Generates the adjacency matrix for this Graph.
     * 
     * @return the adjacency matrix.
     */
    public int[][] getAdjacencyMatrix() {
        int i = 0;
        int[][] arr = new int[_nodes.size()][_nodes.size()];
        for (Map.Entry<Integer, Node<T>> entry : _nodes.entrySet()) {
            int j = 0;
            for (Map.Entry<Integer, Node<T>> nodes : _nodes.entrySet()) {
                if (entry.getValue().hasEdge(nodes.getValue())) {
                    arr[i][j] = 1;
                }
                j++;
            }
            i++;
        }
        return arr;
    }

    public TreeMap<Integer, TreeSet<String>> getOutDegrees() {
        TreeMap<Integer, TreeSet<String>> ret = new TreeMap<Integer, TreeSet<String>>();
        for (int i = 0; i < _nodes.size(); i++) {
            TreeSet<String> nodes = new TreeSet<String>();
            for (Map.Entry<Integer, Node<T>> node : _nodes.entrySet()) {
                if (node.getValue().numEdges() == i) {
                    nodes.add("" + node.getValue().getData());
                }
                if (nodes.size() > 0)
                    ret.put(i, nodes);
            }
        }
        return ret;
    }

    public TreeMap<Integer, TreeSet<String>> getInDegrees() {
        TreeMap<Integer, TreeSet<String>> ret = new TreeMap<Integer, TreeSet<String>>();
        for (int j = 0; j < _nodes.size(); j++) {
            TreeSet<String> nodes = new TreeSet<String>();
            for (Map.Entry<Integer, Node<T>> node : _nodes.entrySet()) {
                int i = 0;
                for (Map.Entry<Integer, Node<T>> node2 : _nodes.entrySet()) {
                    if (node2.getValue().hasEdge(node.getValue())) {
                        i++;
                    }
                }
                if (i == j)
                    nodes.add("" + node.getValue().getData());
            }
            if (nodes.size() > 0)
                ret.put(j, nodes);
        }
        return ret;
    }

    public TreeMap<Integer, TreeSet<String>> topoSort() {
        /*if (!isDAGraph()) {
            return null;
        }*/
        int hieght = 0;
        TreeMap<Integer, TreeSet<String>> ret = new TreeMap<Integer, TreeSet<String>>();
        Map<Integer, Node<T>> myNodes = _nodes;
        for (int i = 0; i < _nodes.size() * 10; i++) {
            TreeMap<Integer, TreeSet<String>> nodes = getInDegrees();
            TreeSet<String> cur = nodes.get(0);
            TreeSet<String> retTreeSet = new TreeSet<String>();
            TreeSet<Integer> removeSet = new TreeSet<Integer>();
            for (Map.Entry<Integer, Node<T>> node : myNodes.entrySet()) {
                for (String str : cur) {
                    if (node.getValue().getData().equals(str)) {
                        retTreeSet.add("" + node.getValue().getData());
                        removeSet.add(node.getKey());
                    }        
                }
            }
            for (int p : removeSet) {
                myNodes.remove(p);
            }
            ret.put(hieght, retTreeSet);
            hieght += 1;
        }
        return ret;
    }

    public int countPartitions() {
        int count = 0;
        for (Map.Entry<Integer, Node<T>> node : _nodes.entrySet()) {
            if (!node.getValue().connected(node.getValue())) {
                count++;
            }
        }
        resetState();
        return count;
    }

    public TreeMap<T, Integer> dijkstra(T data) {
        TreeMap<T, Integer> ret = new TreeMap<T, Integer>();
        Queue<Node<T>> q = new LinkedList<Node<T>>();
        for (Node<T> n : _nodes.values()) {
            if (n.getData().equals(data)) {
                q.add(n);
            }
        }
        while(!q.isEmpty()) {
            Node<T> node = q.remove();
            if (!ret.containsKey(node.getData())) {
                ret.put(node.getData(), node.getState());
            }
            for (Node<T> n : node.getEdges().values()) {
                if (!q.contains(n)) {
                n.setState(node.getState() + 1);
                }
                if (!ret.containsKey(n.getData())) {
                    q.add(n);
                }
            }
        }
        for (Node<T> n : _nodes.values()) {
            if (!ret.containsKey("" + n.getData())) {
                ret.put(n.getData(), -1);
            }
        }
        resetState();
        return ret;
    }

    public boolean eulerianCircuit() {
        for (Node<T> n : _nodes.values()) {
            if (n.getEdges().size() % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Node<T>> eulerianPath(T data) {
        Node<T> node = null;
        for (Node<T> n : _nodes.values()) {
            if (n.getData().equals(data)) {
                node = n;
            }
        }
        ArrayList<Node<T>> paths = new ArrayList<Node<T>>();
        Queue<Node<T>> q = new LinkedList<Node<T>>();
        Map<Node<T>, TreeSet<Node<T>>> m = new TreeMap<Node<T>, TreeSet<Node<T>>>();
        for (Node<T> n : _nodes.values()) {
            TreeSet<Node<T>> edges = new TreeSet<Node<T>>();
            for (Node<T> edge : n.getEdges().values()) {
            edges.add(edge);
            }
            m.put(n, edges);
        }
        q.add(node);
        while (!q.isEmpty()) {
            Node<T> curNode = q.remove();
            for (Node<T> edge : curNode.getEdges().values()) {
                if (m.get(curNode) != null && m.get(curNode).contains(edge)) {
                    TreeSet<Node<T>> t = m.get(curNode);
                    t.remove(edge);
                    q.add(edge);
                    paths.add(edge);
                    m.put(curNode, t);
                    break;
                }
            }
        }
        return paths;
    }
    /**
     * Gives a multi-line String representation of this Graph. Each line in the
     * returned
     * string represent a Node in the graph, followed by its outgoing (egress)
     * Edges.
     * E.g: If the graph contains 3 nodes, A, B an C, where A and B point to each
     * other and
     * both of them point to C, the value returned by toString() will be as follows:
     * 
     * <pre>
     * A > B C
     * B > A C
     * C >
     * </pre>
     * 
     * <u>Note:</u> Each line is a space-separated sequence of token. A Node with no
     * outgoing (egress) edges, like C in the example above still has a line where
     * the ' > ' token is surrounded by the space characters.
     * 
     * @return multi-line String reflecting the content and structure of this Graph.
     */
    @Override
    public String toString() {
        String output = "";
        boolean first = true;
        for (Node<?> n : _nodes.values()) {
            if (!first) {
                output += "\n";
            }
            output += n.toString();
            first = false;
        }

        return output;
    }
}
