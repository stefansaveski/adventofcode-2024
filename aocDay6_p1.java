import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Vector;

public class aocDay6_p1 {

    public static int checkMap(Vector<StringBuilder> map, int posI, int posJ){
        String dir = "up";
        int res = 0;
        while (posI >= 0 && posI <= map.size() && posJ >= 0 && posJ <= map.get(0).length()-1){
            //System.out.println(posI + " " + posJ + dir);
            if(dir.equals("up") && posI-1 < 0)
                break;
            if(dir.equals("up") && map.get(posI-1).charAt(posJ) == '#'){
                dir = "right";
            }
            else if(dir == "up"){
                //System.out.println("up");
                posI -= 1;
                map.get(posI).setCharAt(posJ, 'X');
            }
            if(dir.equals("right") && posJ+1 > map.get(0).length())
                break;
            if(dir.equals("right") && map.get(posI).charAt(posJ+1) == '#'){
                dir = "down";
            }
            else if(dir == "right"){
                //System.out.println("right");
                posJ += 1;
                map.get(posI).setCharAt(posJ, 'X');
            }
            if(dir.equals("down") && posI+1 > map.size())
                break;
            if(dir.equals("down") && map.get(posI+1).charAt(posJ) == '#'){
                dir = "left";
            }
            else if(dir == "down"){
                //System.out.println("down");
                posI += 1;
                map.get(posI).setCharAt(posJ, 'X');
            }
            if(dir.equals("left") && posJ-1 < 0)
                break;
            if(dir.equals("left") && map.get(posI).charAt(posJ-1) == '#'){
                dir = "up";
            }
            else if(dir == "left"){
                //System.out.println("left");
                posJ -= 1;
                map.get(posI).setCharAt(posJ, 'X');
            }
        }
        for(int i=0; i<map.size(); i++){
            for(int j=0; j<map.get(0).length(); j++){
                if(map.get(i).charAt(j) == 'X')
                    res++;
            }
        }
        return res;
    }
    public static void main(String args[]){

        try {
            File myObj = new File("src/index");
            Scanner myReader = new Scanner(myObj);
            Vector<StringBuilder> map = new Vector<>();
            while (myReader.hasNextLine()) {
                StringBuilder data = new StringBuilder(myReader.nextLine());
                map.add(data);
            }
            int posI = 0, posJ = 0;
            for(int i=0; i<map.size(); i++){
                for(int j=0; j<map.get(i).length(); j++){
                    if(map.get(i).charAt(j) == '^'){
                        posI = i;
                        posJ = j;
                    }
                }
            }

            System.out.println(checkMap(map, posI, posJ));
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
