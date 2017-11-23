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

                    if(data == 'A') {
                        nodeStartInX = j;
                        nodeStartInY = i;
                    }
                    else if(data == 'O') {
                        nodeEndInX = j;
                        nodeEndInY = i;
                    }
                    node[i][j] = new Node(i*10, j*10, data);
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
                int hx = node[i][j].getXPos() - endNode.getXPos();
                int hy = node[i][j].getYPos() - endNode.getYPos();
                node[i][j].setHValue((int) Math.sqrt(hx*hx+hy*hy));
            }
        }

        //create open and closed lists
        //assign parent to surrounding nodes
        List<Node> openList    = new ArrayList<Node>();
        List<Node> closedList  = new ArrayList<Node>();
        Node       currentNode = startNode;

/*      for(Node nodes : openList) {

            System.out.println(nodes.getHValue());

        }
*/        //sorts list based on hvalues

/*        System.out.println();

        for(Node nodes : openList) {
            System.out.println(nodes.getHValue());
        }

        System.out.println();
*/
        //A Star implementation
        while(currentNode != endNode){
            //getting all parent nodes
            for(int k = 0; k < 8; k++) {
                int i = currentNode.getYPos() / 10;
                int j = currentNode.getXPos() / 10;
                Node neighbor = null;

                switch(k) {
                    case 0 :
                        if(i - 1 < 0 || j - 1 < 0)
                            break;
                        neighbor = node[i-1][j-1];
                        neighbor.setGValue(14);
                        break;

                    case 1 :
                        if(i - 1 < 0)
                            break;
                        neighbor = node[i-1][j];
                        neighbor.setGValue(10);
                        break;

                    case 2 :
                        if((i - 1) < 0 || (j + 1) == width)
                            break;
                        neighbor = node[i-1][j+1];
                        neighbor.setGValue(14);
                        break;

                    case 3 :
                        if((j + 1) == width)
                            break;
                        neighbor = node[i][j+1];
                        neighbor.setGValue(10);
                        break;

                    case 4 :
                        if((i + 1) == height || (j + 1) == width)
                            break;
                        neighbor = node[i+1][j+1];
                        neighbor.setGValue(14);
                        break;

                    case 5 :
                        if((i + 1) == height)
                            break;
                        neighbor = node[i+1][j];
                        neighbor.setGValue(10);
                        break;

                    case 6 :
                        if((i + 1) == height || (j - 1) < 0)
                            break;
                        neighbor = node[i+1][j-1];
                        neighbor.setGValue(14);
                        break;

                    case 7 :
                        if((j - 1) < 0)
                            break;
                        neighbor = node[i][j-1];
                        neighbor.setGValue(10);
                        break;

                }

                if(neighbor == null || neighbor == startNode || neighbor.getData() == 'W')
                    continue;

                //check lists
                if(closedList.contains(neighbor));

                else if(neighbor.getParent() == null) {
                    neighbor.setParent(currentNode);
                    neighbor.setFValue();
                    neighbor.setData('e');
                    openList.add(neighbor);
                }
                //LEFT HERE
                /* else {
                    if (neighbor.getGValue() < currentNode.getGValue()) {
                        neighbor.setParent(currentNode.getParent());
                        Node tmp = currentNode.getParent();
                        currentNode.setParent(tmp.getParent());
                    }
                }*/

                if(neighbor == endNode) {
                    currentNode = endNode;
                }
            }

/*          for(Node nodes : openList) {

                System.out.println(nodes.getFValue());

            }
            //sorts list based on F values

            System.out.println();

*/          Collections.sort(openList, (n1, n2) -> n1.getFValue() - n2.getFValue());
/*            for(Node nodes : openList) {
                System.out.println(nodes.getFValue());
            }

            System.out.println();
*/          if(currentNode == endNode)
                break;

            currentNode = openList.get(0);
            closedList.add(currentNode);
            openList.remove(0);
        }
        //backtracking;
        while(currentNode.getParent() != startNode) {
            currentNode = currentNode.getParent();
            currentNode.setData('-');
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
        /*
        //testing heuristics of each node
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(i == nodeStartInY && j == nodeStartInX) {
                    System.out.print(" A  ");
                    continue;
                }
                System.out.printf("%4d", node[i][j].getFValue());
            }
            System.out.println();
        }
        */

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
