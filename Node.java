public class Node {


    private int     xPos;
    private int     yPos;
    private char    data;
    private boolean start;
    private boolean end;

    //distance from node to target node
    public int      hValue;
    //movement cost from node to adjacent

    public int      fValue;
    public Node     parent;

    public Node(int yPos, int xPos, char data, boolean start, boolean end) {
        this.data  = data;
        this.yPos  = yPos;
        this.xPos  = xPos;
        this.start = start;
        this.end   = end;
    }

//    public String toString() {
//        return hValue + " " + gValue;
//    }

    public char getData() {
        return data;
    }
}
