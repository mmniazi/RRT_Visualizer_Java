package rrt;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by hp on 2/14/2015.
 */
public class Tree {
    Node root;
    ArrayList<Node> nodeArrayList;

    public Tree(int x, int y) {
        root = new Node(x, y, 0, Double.POSITIVE_INFINITY, null);
        nodeArrayList = new ArrayList<>();
        nodeArrayList.add(root);
    }

    public void add(Node parent, Node child) {
        parent.addChild(child);
        nodeArrayList.add(child);
        child.setParent(parent);
    }

    public void remove(Node node) {
        node.getParent().removeChild(node);
        nodeArrayList.remove(node);
    }

    public boolean contains(Node nodeReq) {
        return nodeArrayList.contains(nodeReq);
    }

    public Node nearestNode(int x, int y) {
        double minDistance = distance(root.getX(), root.getY(), x, y);
        Node nearestNode = root;
        for (Node node : nodeArrayList) {
            double currentDistance = distance(node.getX(), node.getY(), x, y);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                nearestNode = node;
            }
        }
        return nearestNode;
    }

    private double distance(int x1, int y1, int x2, int y2) {
        return sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
    }
}
