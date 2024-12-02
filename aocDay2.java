import java.util.*;

public class aocDay2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int res = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                break; // Stop input when an empty line is encountered
            }

            String[] numbers = line.split("\\s+"); // Split the line by whitespace
            Vector<Integer> row = new Vector<>();
            for (String num : numbers) {
                row.add(Integer.parseInt(num)); // Add each number to the row
            }
            boolean equal = false;
            boolean increase = false, decrease = false;
            boolean dif = false;
            for(int i=0; i<row.size()-1; i++){
                if(row.get(i) == row.get(i+1)){
                    //System.out.println(row.get(i) + " " + row.get(i+1));
                    equal = true;
                }
                if(row.get(i) > row.get(i+1))
                    decrease = true;
                if(row.get(i) < row.get(i+1))
                    increase = true;
                if(Math.abs(row.get(i) - row.get(i+1)) > 3){
                    dif = true;
                    //System.out.println(row.get(i) + " " + row.get(i+1));
                }

            }
            if(increase && decrease || dif || equal){
                boolean flag = false;
                for(int i=0; i<row.size(); i++){
                    Vector<Integer> temp = new Vector<>();
                    for(int j=0; j<row.size(); j++){
                        if(i != j)
                            temp.add(row.get(j));
                    }
                    boolean equal1 = false;
                    boolean increase1 = false, decrease1 = false;
                    boolean dif1 = false;
                    for(int k=0; k<temp.size()-1; k++){
                        if(temp.get(k) == temp.get(k+1)){
                            //System.out.println(row.get(i) + " " + row.get(i+1));
                            equal1 = true;
                        }
                        if(temp.get(k) > temp.get(k+1))
                            decrease1 = true;
                        if(temp.get(k) < temp.get(k+1))
                            increase1 = true;
                        if(Math.abs(temp.get(k) - temp.get(k+1)) > 3){
                            dif1 = true;
                            //System.out.println(row.get(i) + " " + row.get(i+1));
                        }
                    }
                    if(increase1 && decrease1 || dif1 || equal1){

                    }
                    else
                        flag = true;
                }
                if(flag)
                    res++;
            }
            else
                res++;
        }
        System.out.println(res);
    }
}