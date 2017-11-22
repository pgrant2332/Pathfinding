import java.util.*;
import java.io.*;

public class AStar{

    public static void main(String[] args) {

        Scanner console;
        int height = 0;
        int width  = 0;
        try {
            console = new Scanner(new File("map.txt"));

            width = getMapWidth(console);
            height = getMapHeight(console);
        }

        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        //create a node 2d array to represent map from input file
        Node[][] node = new Node[height][width];
        //making sure to track start and end positions
        int  nodeStartInY = 0;
        int  nodeStartInX = 0;
        int  nodeEndInY   = 0;
        int  nodeEndInX   = 0;
        Node startNode = null;
        Node endNode   = null;
        try {
            Scanner scanMap = new Scanner(new File("map.txt"));

            for(int i = 0; i < height; i++) {

                String s = scanMap.nextLine();

                for(int j = 0; j < width; j++) {

                    char data = s.charAt(j);
                    boolean edge = false;

                    if(data == 'A') {
                        nodeStartInX = j;
                        nodeStartInY = i;
                    }
                    else if(data == 'O') {
                        nodeEndInX = j;
                        nodeEndInY = i;
                    }

                    if(i == 0 || i == height - 1 ||
                       j == 0 || j == width  - 1)
                        edge = true;
                    node[i][j] = new Node(i*10, j*10, data, edge);
                }
            }
            startNode = node[nodeStartInY][nodeStartInX];
            endNode   = node[nodeEndInY][nodeEndInX];
        }
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        //need to get all heuristics for nodes
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++) {
                int hx = node[i][j].getXPos() - node[nodeEndInY][nodeEndInX].getXPos();
                int hy = node[i][j].getYPos() - node[nodeEndInY][nodeEndInX].getYPos();
                node[i][j].setHValue((int) Math.sqrt(hx*hx+hy*hy));
            }
        }

        //create open and closed lists
        //assign parent to surrounding nodes
        List<Node> openList   = new ArrayList<Node>();
        List<Node> closedList = new ArrayList<Node>();
        Node currentNode = startNode;

        while(currentNode != startNode){


        }

        //testing to see if nodes were creatd correctly
        try {
            FileOutputStream out = new FileOutputStream("output.txt");
                for(int i = 0; i < height; i++) {
                    for(int j = 0; j < width; j++) {
                        out.write(node[i][j].getData());
                    }
                out.write('\n');
                }
        }
        catch (IOException e) {
            System.exit(1);
        }
        //testing heuristics of each node
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(i == nodeStartInY && j == nodeStartInX) {
                    System.out.print(" A  ");
                    continue;
                }
                System.out.printf("%4d", node[i][j].getHValue());
            }
            System.out.println();
        }

    }

    public static int getMapWidth(Scanner console) {
        String s = console.nextLine();
        return s.length();

    }

    public static int getMapHeight(Scanner console) {
        //starting at 1 since we've already read a line to get width
        int i = 1;
        while(console.hasNextLine()) {
            console.nextLine();
            i++;
        }

        return i;
    }
}
