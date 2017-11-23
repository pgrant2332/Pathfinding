public class Node {


    private int     xPos;
    private int     yPos;
    private char    data;

    //distance from node to target node (heuristic)
    private int     hValue = 0;
    //movement cost from node to adjacent
    private int     gValue = 0;
    //heuristic + movement cost
    private int     fValue = 0;
    private Node    parent = null;

    public Node(int yPos, int xPos, char data) {
        this.data  = data;
        this.yPos  = yPos;
        this.xPos  = xPos;
    }

    public String toString() {
        return hValue + " ";
    }

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

    public int getGValue() {
        return gValue;
    }

    public Node getParent() {
        return parent;
    }

    public int getFValue(){
        return fValue;
    }

    public void setData(char c) {
        this.data = c;
    }

    public void setGValue(int value) {
        this.gValue += value;
    }

    public void setHValue(int value) {
        this.hValue = value;
    }

    public void setParent(Node node) {
        this.parent = node;
    }

    public void setFValue() {
        this.fValue = this.hValue + this.gValue;
    }

}
