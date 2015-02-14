package rrt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Tree tree;
    Image background;
    @FXML
    private Pane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tree = new Tree(0, 0);
        background = new Image("rrt/background.png");
        pane.getChildren().add(new ImageView(background));
    }

    @FXML
    private void onStartButton(ActionEvent event) {
        boolean found = false;
        while (!found) {
            Node randomNode = selectRandomNode();
            Node nearestNode = tree.nearestNode(randomNode.getX(), randomNode.getY());

            if (isValidNode(randomNode, nearestNode)) {
                Node intermediateNode;
                while (distanceBetween(nearestNode, randomNode) > 0) {
                    intermediateNode = moveTowardRandNode(nearestNode, randomNode, 10);
                    drawLineSegment(nearestNode, intermediateNode);
                    tree.add(nearestNode, intermediateNode);
                    nearestNode = intermediateNode;
                }
                if (goalReached(randomNode)) {
                    found = true;
                }
            }

        }
    }

    private boolean goalReached(Node node) {
        return background.getPixelReader().getColor(node.getX(), node.getY()).equals(Color.GREEN);
    }

    private void drawLineSegment(Node nearestNode, Node intermediateNode) {
        Line line = new Line(nearestNode.getX(), nearestNode.getY(), intermediateNode.getX(), intermediateNode.getY());
        pane.getChildren().add(line);
    }

    private Node moveTowardRandNode(Node nearestNode, Node randomNode, int edgeLength) {
        int x1 = nearestNode.getX();
        int y1 = nearestNode.getY();
        int x2 = randomNode.getX();
        int y2 = randomNode.getY();

        Node newNode = new Node(x2, y2);

        double d = distanceBetween(nearestNode, randomNode);

        if (d <= edgeLength) {
            newNode.setDistanceFromRoot(nearestNode.getDistanceFromRoot() + d);
            return newNode;
        }

        newNode.setDistanceFromRoot(nearestNode.getDistanceFromRoot() + edgeLength);

        double distFromLine;
        double distFromNearestNode;
        int num;
        double den;

        if (x1 > x2 && y1 > y2) {
            for (int x = x2; x <= x1; x++) {
                for (int y = y2; y <= y1; y++) {

                    num = Math.abs((x - x1) * (y1 - y2) - (y - y1) * (x1 - x2));
                    den = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

                    distFromLine = num / den;
                    distFromNearestNode = Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));

                    if (distFromLine <= 2 && distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                        newNode.setCoordinate(x, y);
                        return newNode;
                    }
                }
            }
        } else if (x1 > x2 && y1 < y2) {
            for (int x = x2; x <= x1; x++) {
                for (int y = y2; y >= y1; y--) {

                    num = Math.abs((x - x1) * (y1 - y2) - (y - y1) * (x1 - x2));
                    den = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

                    distFromLine = num / den;
                    distFromNearestNode = Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));

                    if (distFromLine <= 2 && distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                        newNode.setCoordinate(x, y);
                        return newNode;
                    }
                }
            }
        } else if (x1 < x2 && y1 > y2) {
            for (int x = x2; x >= x1; x--) {
                for (int y = y2; y <= y1; y++) {

                    num = Math.abs((x - x1) * (y1 - y2) - (y - y1) * (x1 - x2));
                    den = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

                    distFromLine = num / den;
                    distFromNearestNode = Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));

                    if (distFromLine <= 2 && distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                        newNode.setCoordinate(x, y);
                        return newNode;
                    }
                }
            }
        } else if (x1 < x2 && y1 < y2) {
            for (int x = x2; x >= x1; x--) {
                for (int y = y2; y >= y1; y--) {

                    num = Math.abs((x - x1) * (y1 - y2) - (y - y1) * (x1 - x2));
                    den = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

                    distFromLine = num / den;
                    distFromNearestNode = Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));

                    if (distFromLine <= 2 && distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                        newNode.setCoordinate(x, y);
                        return newNode;
                    }
                }
            }
        } else if (x1 == x2 && y1 > y2) {
            for (int y = y2; y <= y1; y++) {
                distFromNearestNode = Math.sqrt((y - y1) * (y - y1));

                if (distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                    newNode.setCoordinate(x1, y);
                    return newNode;
                }
            }
        } else if (x1 == x2 && y1 < y2) {
            for (int y = y2; y >= y1; y--) {
                distFromNearestNode = Math.sqrt((y - y1) * (y - y1));

                if (distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                    newNode.setCoordinate(x1, y);
                    return newNode;
                }
            }
        } else if (x1 > x2 && y1 == y2) {
            for (int x = x2; x <= x1; x++) {
                distFromNearestNode = Math.sqrt((x - x1) * (x - x1));

                if (distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                    newNode.setCoordinate(x, y1);
                    return newNode;
                }
            }
        } else if (x1 < x2 && y1 == y2) {
            for (int x = x2; x >= x1; x--) {
                distFromNearestNode = Math.sqrt((x - x1) * (x - x1));

                if (distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                    newNode.setCoordinate(x, y1);
                    return newNode;
                }
            }
        }
        return null;
    }

    private boolean isValidNode(Node randomNode, Node nearestNode) {
        int x1 = nearestNode.getX();
        int y1 = nearestNode.getY();
        int x2 = randomNode.getX();
        int y2 = randomNode.getY();

        if (x1 == x2 && y1 == y2) {
            return false;
        } else if (x1 > x2 && y1 > y2) {
            for (int x = x2; x <= x1; x++) {
                for (int y = y2; y <= y1; y++) {
                    if (((x - x1) / (x1 - x2)) == ((y - y1) / (y1 - y2))
                            && background.getPixelReader().getColor(x, y).equals(Color.BLACK)) {
                        return false;
                    }
                }
            }
        } else if (x1 > x2 && y1 < y2) {
            for (int x = x2; x <= x1; x++) {
                for (int y = y2; y >= y1; y--) {
                    if (((x - x1) / (x1 - x2)) == ((y - y1) / (y1 - y2))
                            && background.getPixelReader().getColor(x, y).equals(Color.BLACK)) {
                        return false;
                    }
                }
            }
        } else if (x1 < x2 && y1 > y2) {
            for (int x = x2; x >= x1; x--) {
                for (int y = y2; y <= y1; y++) {
                    if (((x - x1) / (x1 - x2)) == ((y - y1) / (y1 - y2))
                            && background.getPixelReader().getColor(x, y).equals(Color.BLACK)) {
                        return false;
                    }
                }
            }
        } else if (x1 < x2 && y1 < y2) {
            for (int x = x2; x >= x1; x--) {
                for (int y = y2; y >= y1; y--) {
                    if (((x - x1) / (x1 - x2)) == ((y - y1) / (y1 - y2))
                            && background.getPixelReader().getColor(x, y).equals(Color.BLACK)) {
                        return false;
                    }
                }
            }
        } else if (x1 == x2 && y1 > y2) {
            for (int y = y2; y <= y1; y++) {
                if (background.getPixelReader().getColor(x1, y).equals(Color.BLACK)) {
                    return false;
                }
            }
        } else if (x1 == x2 && y1 < y2) {
            for (int y = y2; y >= y1; y--) {
                if (background.getPixelReader().getColor(x1, y).equals(Color.BLACK)) {
                    return false;
                }
            }
        } else if (x1 > x2 && y1 == y2) {
            for (int x = x2; x <= x1; x++) {
                if (background.getPixelReader().getColor(x, y1).equals(Color.BLACK)) {
                    return false;
                }
            }
        } else if (x1 < x2 && y1 == y2) {
            for (int x = x2; x >= x1; x--) {
                if (background.getPixelReader().getColor(x, y1).equals(Color.BLACK)) {
                    return false;
                }
            }
        }

        return true;
    }

    private Node selectRandomNode() {
        int x = (int) (Math.random() * background.getWidth());
        int y = (int) (Math.random() * background.getHeight());
        Color color = background.getPixelReader().getColor(x, y);

        while (color.equals(Color.BLACK)) {
            x = (int) (Math.random() * background.getWidth());
            y = (int) (Math.random() * background.getHeight());
            color = background.getPixelReader().getColor(x, y);
        }

        return new Node(x, y);
    }

    public double distanceBetween(Node n1, Node n2) {
        int x1 = n1.getX();
        int y1 = n1.getY();
        int x2 = n2.getX();
        int y2 = n2.getY();
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

}
