package TreePrettyPrint;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Benji Lelivelt
 */
public class IntTree {
    
    class IntTreeNode {
        protected int data;
        protected IntTreeNode left;
        protected IntTreeNode right;


        public IntTreeNode(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    protected IntTreeNode overallRoot;

    public IntTree() {
        overallRoot = null;
    }

    public static void main(String args[]) throws Exception {
        IntTree tree = new IntTree();
        tree.addValue(12);
        tree.addValue(5);
        tree.addValue(3);
        tree.addValue(1);
        tree.addValue(-2);
        tree.addValue(-7);
        tree.addValue(10);
        tree.addValue(1000);
        System.out.println(tree.toPreOrderString());
        System.out.println(tree.toInOrderString());
        System.out.println(tree.toPostOrderString());
        //System.out.println(tree.SubTreeSize(tree.overallRoot));
        tree.toPrettyPrint(tree.overallRoot);
    }

    public void addValue(int data) throws Exception {
        if (overallRoot == null) {
            overallRoot = new IntTreeNode(data);
        } else {
            IntTreeNode root = new IntTreeNode(data);
            addValue(overallRoot, root, data);
        }
    }
    private void addValue(IntTreeNode overroot, IntTreeNode root, int data) throws Exception {
        if (overroot.right == null && overroot.data > data) {
            overroot.right = root;
            return;
        }
        if (overroot.left == null && overroot.data < data) {
            overroot.left = root;
            return;
        }
        if (data == overroot.data) {
            throw new Exception("Same value");
        }
        if (overroot.left != null) {
            addValue(overroot.left, root, data);
        } else if (overroot.right != null) {
            addValue(overroot.right, root, data);
        }
    }

    public String toPreOrderString() {
        return toPreOrderString(overallRoot);
    }
    private String toPreOrderString(IntTreeNode root) {
        String str = "";
        if (root == null) {
            return "";
        }
        str += "[" + root.data + "]";
        str += "(" + toPreOrderString(root.right) + ")";
        str += "(" + toPreOrderString(root.left) + ")";
        return str;
    }
    public String toInOrderString() {
        return toInOrderString(overallRoot);
    }
    private String toInOrderString(IntTreeNode root) {
        String str = "";
        if (root == null) {
            return "";
        }
        str += "(" + toInOrderString(root.right) + ")";
        str += "[" + root.data + "]";
        str += "(" + toInOrderString(root.left) + ")";
        return str;
    }
    public String toPostOrderString() {
        return toPostOrderString(overallRoot);
    }
    private String toPostOrderString(IntTreeNode root) {
        String str = "";
        if (root == null) {
            return "";
        }
        str += "(" + toPostOrderString(root.right) + ")";
        str += "(" + toPostOrderString(root.left) + ")";
        str += "[" + root.data + "]";
        return str;

    }
    private void toPrettyPrint(IntTreeNode overallRoot) {
        Queue<IntTreeNode> q = new LinkedList<IntTreeNode>();
        q.add(overallRoot);
        toPrettyPrint(q);
    }
    public void toPrettyPrint(Queue<IntTreeNode> q) {
        while (!q.isEmpty()) {
            int size = q.size();
            IntTreeNode node2 = q.peek();
            for (int i = 0; i < size; i++) {
                IntTreeNode node = q.remove();
                if (node != null) {
             if (node.right != null) {
                for (int w = 0; w < SubTreeSize(node.right) - SubTreeSize(node.right.left); w++) {
                    System.out.print(" ");
                }
                if (node.right.left != null) {
                    for (int w2 = 0; w2 < SubTreeSize(node.right.left); w2++) {
                        System.out.print("_");
                    }
                }
            }
            System.out.print("[" + node.data + "]");
            if (node.left != null) {
                if (node.left.right != null) {
                    for (int w2 = 0; w2 < SubTreeSize(node.left.right); w2++) {
                        System.out.print("_");
                    }

                }
            for (int w = 0; w < SubTreeSize(node.left) - SubTreeSize(node.left.right) + 3; w++) {
                System.out.print(" ");
            }
        }
        System.out.print(" ");
        System.out.print(" ");
        System.out.print(" ");
            q.add(node.right);
            q.add(node.left);
        }
    }
    System.out.println();
    if (node2.right != null) {
    for (int w = 0; w < SubTreeSize(node2) - SubTreeSize(node2.left) - 4; w++) {
        System.out.print(" ");
    }

    System.out.print("/");
}
    System.out.println();
    }
    }

    //Prints reverse substree (.right prints left and .left prints right)
    public int SubTreeSize(IntTreeNode root) {
        int numNodes = 0;
        if (root == null) {
            return 0;
        }
        numNodes += 3; 
        numNodes += SubTreeSize(root.left) + SubTreeSize(root.right) + String.valueOf(root.data).length() - 1;
        return numNodes;
    }

    public int HigherParent(int data,  IntTreeNode node, ArrayList<Integer> list) {
        ArrayList<Integer> list2 = list;
        if (node != null) {
            HigherParent(data, node.left, list2);
            list2.add(node.data);
            HigherParent(data, node.right, list2);
        }
        for (int i = 0; i < list2.size() - 1; i++) {
            if (list2.get(i) == data) {
                return String.valueOf(list2.get(i + 1)).length() + 2;
            }
        }
        return 0;
    }
}


