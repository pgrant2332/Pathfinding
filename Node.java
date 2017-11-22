public class Node {


    private int     xPos;
    private int     yPos;
    private char    data;
    private boolean edge; // to make sure we handle edge nodes differently

    //distance from node to target node (heuristic)
    private int     hValue;
    //movement cost from node to adjacent
    private int     gValue;
    //heuristic + movement cost
    private int     fValue;
    private Node    parent;

    public Node(int yPos, int xPos, char data, boolean edge) {
        this.data  = data;
        this.yPos  = yPos;
        this.xPos  = xPos;
        this.edge  = edge;
    }

//    public String toString() {
//        return hValue + " " + gValue;
//    }

    public char getData() {
        return data;
    }

    public int getYPos() {
        return yPos;
    }

    public int getXPos() {
        return xPos;
    }

    public int getHValue(){
        return hValue;
    }

    public Node getParent() {
        return parent;
    }

    public void setHValue(int value) {
        this.hValue = value;
    }

    public void setParent(Node node) {
        this.parent = node;
    }

    public void setFValue(int hValue, int gValue) {
        this.fValue = hValue + gValue;
    }

}
