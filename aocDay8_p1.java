import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Stack;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class aocDay8_p1 {
    
    public static void main(String args[]){
        String fileName = "src/input"; // Specify the file name or path
        ArrayList<StringBuilder> arr = new ArrayList<>();
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String input;
            while ((input = reader.readLine()) != null) {
                arr.add(new StringBuilder(input));
            }
            int cnt = 0;
            for(int i=0; i<arr.size(); i++){
                for(int k = 0; k<arr.get(i).length(); k++){
                    if(arr.get(i).charAt(k) != '.'){
                        map.put(arr.get(i).charAt(k), arr.get(i).charAt(k));
                    }
                }
            }
            int answer = 0;
            for(char item : map.keySet()) {
                System.out.println(item);
                while (true) {
                    boolean first = true;
                    char firstChar = 0;
                    int lastRow = 0, lastCol = 0;
                    for (int i = 0; i < arr.size(); i++) {
                        for (int j = 0; j < arr.get(0).length(); j++) {
                            if (arr.get(i).charAt(j) == item && first && arr.get(i).charAt(j) != '$' && arr.get(i).charAt(j) != '#') {
                                firstChar = arr.get(i).charAt(j);
                                lastRow = i;
                                lastCol = j;
                                first = false;
                                continue;
                            }
                            if (arr.get(i).charAt(j) == firstChar) {
                                int difRow, difCol;
                                difRow = Math.abs(i - lastRow);
                                difCol = Math.abs(j - lastCol);
                                System.out.println(difRow + " " + difCol);
                                if (lastCol < j) {
                                    if (lastRow - difRow >= 0 && lastCol - difCol >= 0){
                                        if(arr.get(lastRow - difRow).charAt(lastCol - difCol) != '.' && arr.get(lastRow - difRow).charAt(lastCol - difCol) != '#'){
                                            System.out.println(arr.get(lastRow - difRow).charAt(lastCol - difCol));
                                            answer++;
                                        }
                                        else
                                            arr.get(lastRow - difRow).setCharAt(lastCol - difCol, '#');
                                    }
                                    if (i + difRow < arr.size() && j + difCol < arr.get(0).length()){
                                        if(arr.get(i + difRow).charAt(j + difCol) != '.' && arr.get(i + difRow).charAt(j + difCol) != '#'){
                                            System.out.println(arr.get(i + difRow).charAt(j + difCol));
                                            answer++;
                                        }
                                        else
                                            arr.get(i + difRow).setCharAt(j + difCol, '#');
                                    }
                                } else {
                                    if (lastRow - difRow >= 0 && lastCol + difCol < arr.get(0).length()){
                                        if(arr.get(lastRow - difRow).charAt(lastCol + difCol) != '.' && arr.get(lastRow - difRow).charAt(lastCol + difCol) != '#'){
                                            System.out.println(arr.get(lastRow - difRow).charAt(lastCol + difCol));
                                            answer++;
                                        }
                                        else
                                            arr.get(lastRow - difRow).setCharAt(lastCol + difCol, '#');
                                    }
                                    if (i + difRow < arr.size() && j - difCol >= 0){
                                        if(arr.get(i + difRow).charAt(j - difCol) != '.' && arr.get(i + difRow).charAt(j - difCol) != '#'){
                                            System.out.println(arr.get(i + difRow).charAt(j - difCol));
                                            answer++;
                                        }
                                        else
                                            arr.get(i + difRow).setCharAt(j - difCol, '#');
                                    }
                                }
                            }
                        }
                    }
                    if (first)
                        break;
                    arr.get(lastRow).setCharAt(lastCol, '$');
                }
                for(StringBuilder sb : arr){
                    System.out.println(sb);
                }

            }
            for(int i=0; i<arr.size(); i++){
                for(int k = 0; k<arr.get(i).length(); k++){
                    if(arr.get(i).charAt(k) == '#'){
                        answer++;
                    }
                }
            }
            System.out.println(answer);


        } catch (IOException e) {
            e.printStackTrace(); // Handle potential exceptions (e.g., file not found)
        }
    }
}
