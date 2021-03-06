package edu.neu.coe.info6205.symbolTable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiFunction;

public class BSTSimple<Key extends Comparable<Key>, Value> implements BSTdetail<Key, Value> {
    @Override
    public Boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public void putAll(Map<Key, Value> map) {
        // CONSIDER optionally randomize the input
        for (Map.Entry<Key, Value> entry : map.entrySet()) put(entry.getKey(), entry.getValue());
    }

    @Override
    public int size() {
        return root != null ? root.count : 0;
    }

    @Override
    public void inOrderTraverse(BiFunction<Key, Value, Void> f) {
        doTraverse(0, root, f);
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    @Override
    public Value put(Key key, Value value) {
        NodeValue nodeValue = put(root, key, value);
        if (root == null) root = nodeValue.node;
        if (nodeValue.value==null) root.count++;
        return nodeValue.value;
    }

//    public void delete(Key key) {
//        root = delete(root, key);
//    }

    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }

    @Override
    public Set<Key> keySet() {
        return null;
    }

    public BSTSimple() {
    }

    public BSTSimple(Map<Key, Value> map) {
        this();
        putAll(map);
    }

    Node root = null;
    
    @Override
    public void delete(Key key) {
        // TODO- Implement this delete method or add your variations of delete.
        root = delete(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;
        int cf = key.compareTo(node.key);
        if (cf < 0) return get(node.smaller, key);
        else if (cf > 0) return get(node.larger, key);
        else return node.value;
    }

    /**
     * Method to put the key/value pair into the subtree whose root is node.
     *
     * @param node  the root of a subtree
     * @param key   the key to insert
     * @param value the value to associate with the key
     * @return a tuple of Node and Value: Node is the
     */
    private NodeValue put(Node node, Key key, Value value) {
        // If node is null, then we return the newly constructed Node, and value=null
        if (node == null) return new NodeValue(new Node(key, value), null);
        int cf = key.compareTo(node.key);
        if (cf == 0) {
            // If keys match, then we return the node and its value
            NodeValue result = new NodeValue(node, node.value);
            node.value = value;
            return result;
        } else if (cf < 0) {
            // if key is less than node's key, we recursively invoke put in the smaller subtree
            NodeValue result = put(node.smaller, key, value);
            if (node.smaller == null)
                node.smaller = result.node;
            if (result.value==null)
                result.node.count++;
            return result;
        } else {
            // if key is greater than node's key, we recursively invoke put in the larger subtree
            NodeValue result = put(node.larger, key, value);
            if (node.larger == null)
                node.larger = result.node;
            if (result.value==null)
                result.node.count++;
            return result;
        }
    }

    private Node delete(Node x, Key key) {
        // TO IMPLEMENT
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.smaller = delete(x.smaller, key);
        else if (cmp > 0) x.larger = delete(x.larger, key);
        else {
            if (x.larger == null) return x.smaller;
            if (x.smaller == null) return x.larger;

            Node t = x;
            x = min(t.larger);
            x.larger = deleteMin(t.larger);
            x.smaller = t.smaller;
        }
        x.count = size(x.smaller) + size(x.larger) + 1;
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.smaller == null) return x.larger;
        x.smaller = deleteMin(x.smaller);
        x.count = 1 + size(x.smaller) + size(x.larger);
        return x;
    }

    private int size(Node x) {
        return x == null ? 0 : x.count;
    }

    private Node min(Node x) {
        if (x == null) throw new RuntimeException("min not implemented for null");
        else if (x.smaller == null) return x;
        else return min(x.smaller);
    }

    /**
     * Do a generic traverse of the binary tree starting with node
     * @param q determines when the function f is invoked ( lt 0: pre, ==0: in, gt 0: post)
     * @param node the node
     * @param f the function to be invoked
     */
    private void doTraverse(int q, Node node, BiFunction<Key, Value, Void> f) {
        if (node == null) return;
        if (q<0) f.apply(node.key, node.value);
        doTraverse(q, node.smaller, f);
        if (q==0) f.apply(node.key, node.value);
        doTraverse(q, node.larger, f);
        if (q>0) f.apply(node.key, node.value);
    }

    private class NodeValue {
        private final Node node;
        private final Value value;

        NodeValue(Node node, Value value) {
            this.node = node;
            this.value = value;
        }

        @Override
        public String toString() {
            return node + "<->" + value;
        }
    }

    class Node {
        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        final Key key;
        Value value;
        Node smaller = null;
        Node larger = null;
        int count = 0;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Node: " + key + ":" + value);
            if (smaller != null) sb.append(", smaller: " + smaller.key);
            if (larger != null) sb.append(", larger: " + larger.key);
            return sb.toString();
        }

    }

    private Node makeNode(Key key, Value value) {
        return new Node(key, value);
    }

    private Node getRoot() {
        return root;
    }

    private void setRoot(Node node) {
        if(root==null){
            root = node;
            root.count++;
        }else
            root = node;
    }
    private void show(Node node, StringBuffer sb, int indent) {
        if (node == null) return;
        for (int i = 0; i < indent; i++) sb.append("  ");
        sb.append(node.key);
        sb.append(": ");
        sb.append(node.value);
        sb.append("\n");
        if (node.smaller != null) {
            for (int i = 0; i <= indent; i++) sb.append("  ");
            sb.append("smaller: ");
            show(node.smaller, sb, indent + 1);
        }
        if (node.larger != null) {
            for (int i = 0; i <= indent; i++) sb.append("  ");
            sb.append("larger: ");
            show(node.larger, sb, indent + 1);
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        show(root, sb, 0);
        return sb.toString();
    }

    private int maxDepth(Node node) 
    { 
        if (node == null) 
                return 0; 
        else
        { 
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.smaller); 
            int rDepth = maxDepth(node.larger); 

            /* use the larger one */
            if (lDepth > rDepth) 
                return (lDepth + 1); 
            else
                return (rDepth + 1); 
        } 
    } 
    
    private int maxDepth() {
    	return maxDepth(root);
    }
    
    private int depth(Node node) {
    	return depth(node,root);
    }
    
    private int depth(Node node, Node key) {
    	if(node == null) return 0;
    	int cmp = node.key.compareTo(key.key);
    	int dep = 0;
    	if(cmp == 0) return 1;
    	else if(cmp > 0) return 1 + depth(node,key.larger);
    	else return 1 + depth(node,key.smaller);
    }
    
    private int leafDepth(Node node) {
    	if(node.smaller == null && node.larger == null) return depth(node);
    	else return 0;
    }
    
    private int sumDepth(Node node) {
    	if(node == null) return 0;
    	return leafDepth(node) + sumDepth(node.smaller) + sumDepth(node.larger);
    }
    
    private int leafCount() {
    	return leafCount(root);
    }
    
    private int leafCount(Node node) {
    	if(node == null) return 0;
    	if(node.smaller != null || node.larger != null)
    		return leafCount(node.smaller) + leafCount(node.larger);
    	else return 1;
    }
    
    private double avgDepth() {
    	return sumDepth(root)/(leafCount()*1.0);
    }
    
    private BSTSimple insDel(BSTSimple bst,int n) {
        int dCount = 0;
        int iCount = 0;
        while(dCount+iCount < 2*n) {
                Random random = new Random();
                int choice = random.nextInt(2);
                if(choice == 0 && dCount < n) {
                        int key = random.nextInt(2*n);
                        bst.delete(key);
                        dCount++;
                }
                if(choice == 1 && iCount < n) {
                        int key = random.nextInt(2*n);
                        bst.put(key, key);
                        iCount ++;
                }
        }
        return bst;
    }

    /* Driver program to test above functions */
    public static void main(String[] args) 
    { 
        int M = 100000;
        System.out.println("Num\tCount\tAverage\tMax\tN^1/2\tlgN");
        for(int N = 100; N < 10000; N+=100)
        {
            Random random = new Random();
            Map<Integer, Integer> hm = new HashMap<>();
            while(hm.size() < N) {
                int t = random.nextInt(2*N);
                hm.put(t, t);
            }  
            BSTSimple<Integer, Integer> bst = new BSTSimple<>(hm);
            bst = bst.insDel(bst, M);
//            int initDepth = bst.maxDepth();
//            int initCount = bst.size();
//            int initAvgDepth = bst.avgDepth();           
            int maxDepth = bst.maxDepth();
            int count = bst.size();
            double avgDepth = bst.avgDepth();
            double square = Math.sqrt(N);
            double lgN = Math.log(N) / Math.log(2);
            System.out.printf("%d\t%d\t%.4f\t%d\t%.4f\t%.4f\n", N, count, avgDepth, maxDepth, square, lgN);
        }   
    }     
}
