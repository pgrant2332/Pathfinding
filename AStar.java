import java.util.*;
import java.io.*;

public class AStar{

    public static void main(String[] args) {

        Scanner console;
        int height = 0;
        int width = 0;
        try {
            console = new Scanner(new File("map.txt"));

            width = getMapWidth(console);
            height = getMapHeight(console);
        }

        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        //create a node map of file
        Node[][] node = new Node[height][width];
        try {
            Scanner scanMap = new Scanner(new File("map.txt"));

            for(int i = 0; i < height; i++) {

                boolean start = false;
                boolean end = false;
                String s = scanMap.nextLine();

                for(int j = 0; j < width; j++) {

                    char data = s.charAt(j);

                    if(data == 'A')
                      start = true;
                    else if(data == 'O')
                      end = true;

                    node[i][j] = new Node(i, j, data, start, end);
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        //testing output
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
