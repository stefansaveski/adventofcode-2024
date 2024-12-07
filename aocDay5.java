import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class aocDay5 {
    public static void main(String[] args) {
        List<String> items = new ArrayList<String>();
        Hashtable<String, List<String>> hashtable = new Hashtable<>();
        boolean flag = true;
        int result = 0;
        try {
            File myObj = new File("src/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                if(data == "")
                    flag = false;
                if(flag){
                    String[] hashinput = data.split("\\|");
                    //System.out.println(hashinput[1]);
                    hashtable.computeIfAbsent(hashinput[0], k -> new ArrayList<>()).add(hashinput[1]);
                }
                else{
                    if(data.equals(""))
                        continue;
                    String[] nums = data.split(",");
                    //System.out.println(nums[0]);
                    boolean correct = true;
                    //System.out.println(hashtable.get(nums[0]));
                    for(int i=0; i<nums.length; i++){
                        List<String> temp = hashtable.get(nums[i]);
                        for(int j=0; j<i; j++){
                            if(temp == null)
                                continue;
                            if(temp.contains(nums[j])){
                                correct = false;
                                String qk = nums[i];
                                nums[i] = nums[j];
                                nums[j] = qk;
                            }
                        }
                    }
                    if(!correct){
                        System.out.println(nums);
                        result += parseInt(nums[nums.length/2]);
                    }
                }
            }
            System.out.println(result);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}