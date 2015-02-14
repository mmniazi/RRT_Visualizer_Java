package rrt;

import java.util.ArrayList;

public class Node {
    private int x;
    private int y;
    private double distanceFromRoot;
    private double distanceFromGoal;
    private Node parent;
    private ArrayList<Node> children;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
        distanceFromGoal = -1;
        distanceFromRoot = -1;
        parent = null;
        children = new ArrayList<>();
    }

    public Node(int x, int y, double distanceFromRoot, double distanceFromGoal, Node parent){
        this.x = x;
        this.y = y;
        this.distanceFromGoal = distanceFromGoal;
        this.distanceFromRoot = distanceFromRoot;
        this.parent = parent;
        children = new ArrayList<>();
    }

    public void setDistanceFromGoal(double distanceFromGoal) {
        this.distanceFromGoal = distanceFromGoal;
    }

    public void setDistanceFromRoot(double distanceFromRoot) {
        this.distanceFromRoot = distanceFromRoot;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public void removeChild(Node child) {
        children.remove(child);
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public double getDistanceFromGoal() {
        return distanceFromGoal;
    }

    public double getDistanceFromRoot() {
        return distanceFromRoot;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public Node getParent() {
        return parent;
    }
}
