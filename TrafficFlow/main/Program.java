package TrafficFlow.main;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import drawing.KeyInterceptor.KeyHook;
import TrafficFlow.mapFramework.MapFrame;
import TrafficFlow.mapFramework.MapImage;

public class Program {

    private static MapImage _mapImage;
    private static MapFrame _mapFrame;
    public static  Map<String, Set<String>> routesMap = new HashMap<String, Set<String>>(); 
    private static boolean xPress = false;
    private static String curRoute = "AB";
    private static Map<Integer, Set<Node<String>>> m = new TreeMap<Integer, Set<Node<String>>>();
    /**
     * Lambda method which will be called each time the user
     * is pressing the key 'T'.
     * @param keyEvent - details about the key which was pressed.
     */
    private static KeyHook _onKeyT = (KeyEvent keyEvent) -> {
        String statusText = "Key: '" + keyEvent.getKeyChar() + "'; ";
        statusText += "Routes: " + _mapImage.getRoutes();
        _mapFrame.setStatusMessage(statusText);
    };

    private static KeyHook _onKeynotT = (KeyEvent keyEvent) -> {
        _mapImage.setOverlays("");
        String key = "" + keyEvent.getKeyChar();
        String s = "";
        Set<String> set = routesMap.get(key.toUpperCase());
        ArrayList<String> a = new ArrayList<String>();
        for (String string : set) {
            a.add(string);
        }
        String statusText = "Key: '" + keyEvent.getKeyChar() + "'; ";
        statusText += "Routes: " + set.toString();
        _mapFrame.setStatusMessage(statusText);
        int random = (int) (Math.random() * set.size());
        if (a.size() > 0) {
            s = a.get(random);
        } else {
            s = "";
        }
        curRoute = s;
        _mapImage.setOverlays(s);
        _mapFrame.repaint();
    };

    private static KeyHook _onKeyQ = (KeyEvent keyEvent) -> {
        _mapImage.setOverlays("");
        _mapFrame.repaint();
    };

    private static KeyHook _onKeyW = (KeyEvent keyEvent) -> {
        Set<String> overlaySet = _mapImage.getOverlays();
        Set<String> newSet = new TreeSet<String>();
        for (Integer i : m.keySet()) {
            Set<String> mapSet = new TreeSet<String>();
            for (Node<String> s: m.get(i)) {
                mapSet.add(s.getData());
            }
            if (m.get(i+1) != null && overlaySet.equals(mapSet)) {
                for (Node<String> s: m.get(i + 1)) {
                    newSet.add(s.getData());
                }
                break;
            } else if (m.get(i+1) == null) {
                for (Node<String> s: m.get(1)) {
                    newSet.add(s.getData());
                }
                break;
            }
        }
        for (String str : _mapImage.getOverlays()) {
            if (newSet.contains(str)) {
                newSet.remove(str);
            }
        }
        _mapImage.setOverlays(newSet);
         _mapFrame.repaint();
        /*for (Integer i : m.keySet()) {
            System.out.println("For Each I in Map");
            Set<String> stringSet = new HashSet<String>();
            for (Node<String> s: m.get(i)) {
                stringSet.add(s.getData());
            }
            System.out.println(stringSet.toString());
            _mapImage.setOverlays(stringSet);
            _mapFrame.repaint();
            try {
                System.out.println("Before timout");
                _mapFrame.repaint();
                TimeUnit.SECONDS.sleep(3);
                System.out.println("After Timeout");
            } catch (InterruptedException e) {
                System.out.println("Timout Failed");
                e.printStackTrace();
            }
        }
        String key = "" + keyEvent.getKeyChar();
        String s = "";
        Set<String> set = routesMap.get(key.toUpperCase());
        ArrayList<String> a = new ArrayList<String>();
        for (String string : set) {
            a.add(string);
        }
        String statusText = "Key: '" + keyEvent.getKeyChar() + "'; ";
        statusText += "Routes: " + set.toString();
        _mapFrame.setStatusMessage(statusText);
        int random = (int) (Math.random() * set.size());
        if (a.size() > 0) {
            s = a.get(random);
        } else {
            s = "";
        }
        curRoute = s;
        _mapImage.setOverlays(s);
        _mapFrame.repaint();*/
    };

    private static KeyHook _onKeyX = (KeyEvent keyEvent) -> {
        Graph<String> g = buildGraph();
        if (xPress) {
            _mapImage.setOverlays(curRoute);
            _mapFrame.repaint();
            xPress = false;
        } else {
            xPress = true;
        Set<String> curSet = _mapImage.getOverlays();
        Set<String> newSet = new HashSet<String>();
        //String key = "" + keyEvent.getKeyChar();
        if (curRoute.equals("")) {
            for (Node<String> node : g.get_nodes().values()) {
                newSet.add(node.getData());
            }
        } else {
        for (Node<String> node : g.get_nodes().values()) {
            for (String curNode : curSet) {
                if ((_mapImage.getCollision(node.getData(), curNode) && !node.getData().substring(0, 1).equals(curNode.substring(0, 1))) || node.getData().equals(curNode)) {
                    newSet.add(node.getData());
                }
            }
        }
        }
        System.out.println(newSet.toString());
        _mapImage.setOverlays(newSet);
        _mapFrame.repaint();
    }
    };

    /*public void keys (KeyEvent KeyEvent) {
        String c = "" + KeyEvent.getKeyChar();
        Set<String> set = routesMap.get(c);
        _mapImage.setOverlays(set);
    }*/

    public static Graph<String> buildGraph() {
        Graph<String> g = new Graph<String>();
        for (String str : _mapImage.getRoutes()) {
            String node = str;
            g.addNode(node);
        }
        for (Node<String> node : g.get_nodes().values()) {
            for (Node<String> node2 : g.get_nodes().values()) {
                if (_mapImage.getCollision(node.getData(), node2.getData())) {
                    g.addEdge(node.getData(), node2.getData());
                }
            }
        }
        return g;
    }

    public static Map<Integer, Set<Node<String>>> graphColoring() {
        Graph<String> g = buildGraph();
        Queue<Node<String>> unLabledQueue = new LinkedList<Node<String>>();
        for (Node<String> str : g.get_nodes().values()) {
            unLabledQueue.add(str);
        }
        int label = 1;
        Map<Integer, Set<Node<String>>> m = new TreeMap<Integer, Set<Node<String>>>();
        while (!unLabledQueue.isEmpty()) {
            Set<Node<String>> s = Greedy(label, unLabledQueue);
            m.put(label, s);
            label++;
            for (Node<String> node : s) {
                if (unLabledQueue.contains(node)) {
                    unLabledQueue.remove(node);
                }
            }
        }
        return m;
    }

    public static Set<Node<String>> Greedy(int label, Queue<Node<String>> unLabeledQueue) {
        Set<Node<String>> LabeledSet = new TreeSet<Node<String>>();
        int iterator = unLabeledQueue.size();
        for (int i = 0; i < iterator; i++) {
            //while (!unLabeledQueue.isEmpty())} {
            Node<String> cur = unLabeledQueue.remove();
            if (i == 0) {
                LabeledSet.add(cur);
            } else {
                boolean collides = false;
            for (Node<String> str : LabeledSet) {
                if (_mapImage.getCollision(str.getData(), cur.getData())) {
                    collides = true;
                }               
            }
            if (!collides) {
                cur.setState(label);
                LabeledSet.add(cur);
            } else {
            collides = false;
            unLabeledQueue.add(cur);
            }
            }
        }
        return LabeledSet;

    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        // loads an intersection image file and displays it in a map frame.
        _mapImage = MapImage.load("TrafficFlow/maps/Woodlawn.jpg");
        _mapFrame = new MapFrame(_mapImage);
        m = graphColoring();

        // registers the key T with the method _onKeyT
        _mapFrame.setKeyTypedHook('T', _onKeyT);
        
        
        String alph = "ABCDE";
        for (int i = 0; i < 5; i++) {
            Set<String> routes = new HashSet<String>(); 
            for (String route : _mapImage.getRoutes()) {
                if (route.substring(0, 1).equals(alph.substring(i, i+1))) {
                    routes.add(route);
                }
                routesMap.put(alph.substring(i, i + 1), routes);
            }
        }
        _mapFrame.setKeyTypedHook('A', _onKeynotT);
        _mapFrame.setKeyTypedHook('B', _onKeynotT);
        _mapFrame.setKeyTypedHook('C', _onKeynotT);
        _mapFrame.setKeyTypedHook('D', _onKeynotT);
        _mapFrame.setKeyTypedHook('E', _onKeynotT);
        _mapFrame.setKeyTypedHook('X', _onKeyX);
        _mapFrame.setKeyTypedHook('W', _onKeyW);
        _mapFrame.setKeyTypedHook('Q', _onKeyQ);


        // opens the GUI window
        _mapFrame.open();
        
        // stops, waiting for user action
        _mapFrame.stop();
        
        // close the window and terminate the program
        _mapFrame.close();
    }
}
