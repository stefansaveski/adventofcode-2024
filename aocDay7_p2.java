import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Stack;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class aocDay7_p2 {

    // Recursive function to generate all combinations
    public static String[] generateAllCombos(long positions) {
        ArrayList<String> result = new ArrayList<>();
        generateCombos("", positions, result);
        return result.toArray(new String[0]); // Convert list to array
    }

    private static void generateCombos(String current, long positionsLeft, ArrayList<String> result) {
        if (positionsLeft == 0) {
            result.add(current); // Add the generated combination to the list
            return;
        }
        for (char symbol : symbols) {
            generateCombos(current + symbol, positionsLeft - 1, result); // Recurse
        }
    }
    static char[] symbols = {'+', '*', '|'}; // Symbols to use
    static long cnt = 0;
    public static long evaluateExpression(String input) {
        // Split the input string by '+' operator first
        String[] additionParts = input.split("\\+");
        long total = 0;

        // Evaluate each part, handling '*' separately
        for (String part : additionParts) {
            total += evaluateMultiplication(part.trim());
        }

        return total;
    }

    public static long evaluateMultiplication(String part) {
        // Split the part by '*' and calculate the product
        String[] factors = part.split("\\*");
        long product = 1;

        for (String factor : factors) {
            product *= parseInt(factor.trim());
        }

        return product;
    }
    public static long evaluateLeftToRight(String expression) {
        // Split the expression into parts: numbers and operators
        String[] tokens = expression.split(" ");

        // Start with the first number
        long result = parseLong(tokens[0]);

        // Iterate through the rest of the tokens
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            long nextNumber = parseLong(tokens[i + 1]);

            // Apply the operator in left-to-right order
            if (operator.equals("+")) {
                result += nextNumber;
            } else if (operator.equals("*")) {
                result *= nextNumber;
            } else if (operator.equals("|")) {
                result = concatNumbers(result, nextNumber);
            }
        }

        return result;
    }

    // Helper method to handle number concatenation
    private static long concatNumbers(long left, long right) {
        String leftStr = String.valueOf(left);
        String rightStr = String.valueOf(right);

        // Concatenate the strings and then convert back to a number
        String concatenated = leftStr + rightStr;
        return Long.parseLong(concatenated);
    }
    public static void main(String args[]){
        String fileName = "src/input"; // Specify the file name or path

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String input;
            long answer = 0;
            while ((input = reader.readLine()) != null) {
                String[] inp = input.split(" ");
                String res = inp[0].substring(0, inp[0].length()-1);
                System.out.println(res);
                String[] nums = new String[inp.length-1];
                for(int i = 1; i<inp.length; i++){
                    nums[i-1] = inp[i];
                }
                for(String out : nums)
                    System.out.println(out);
                int positions = inp.length-2;
                String[] combinations = generateAllCombos(positions);
                for (String combo : combinations) {
                    String temp = "";
                    temp += nums[0];
                    for(int i=0; i<combo.length(); i++){
                        temp += " ";
                        temp += combo.charAt(i);
                        temp += " ";
                        temp += nums[i+1];
                    }
                    System.out.println(temp);
                    long result = evaluateLeftToRight(temp);
                    if(result == parseLong(res)){
                        System.out.println("PINNNGGG!!!-----------------------------------------");
                        answer += parseLong(res);
                        break;
                    }
                    System.out.println("Result: " + result);
                }
                System.out.println();
            }
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace(); // Handle potential exceptions (e.g., file not found)
        }
    }
}
