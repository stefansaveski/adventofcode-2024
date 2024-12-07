import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordSearch {
    public static void main(String[] args) {
        // Example input grid
        String filePath = "src/input.txt";

        try {
            // Read the file line by line and store it in a list
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            ArrayList<String> gridList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                gridList.add(line);
            }
            reader.close();

            // Convert the list to a String array
            String[] grid = gridList.toArray(new String[0]);

            // Print the grid to verify

            int rowCount = grid.length;
            int colCount = grid[0].length();
            int xMasCount = 0;

            // Traverse the grid
            for (int row = 1; row < rowCount - 1; row++) {
                for (int col = 1; col < colCount - 1; col++) {
                    // Check for "X-MAS" pattern centered at (row, col)
                    if (isXMASPattern(grid, row, col)) {
                        xMasCount++;
                    }
                }
            }

            System.out.println("Number of X-MAS patterns: " + xMasCount);


        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }


    }
    private static boolean isXMASPattern(String[] grid, int row, int col) {
        boolean flag1 = false, flag2 = false;
        if(grid[row].charAt(col) == 'A') {
            if(grid[row - 1].charAt(col - 1) == 'M' && grid[row + 1].charAt(col + 1) == 'S'){
                flag1 = true;
            }
            if(grid[row - 1].charAt(col - 1) == 'S' && grid[row + 1].charAt(col + 1) == 'M')
                flag1 = true;
            if(grid[row - 1].charAt(col + 1) == 'S' && grid[row + 1].charAt(col - 1) == 'M')
                flag2 = true;
            if(grid[row - 1].charAt(col + 1) == 'M' && grid[row + 1].charAt(col - 1) == 'S')
                flag2 = true;
        }
        if(flag1 && flag2)
            return true;
        else
            return false;
    }

    public static int countXMAS(String[] grid) {
        int rows = grid.length;
        int cols = grid[0].length();
        String target = "XMAS";

        // Define the 8 directions
        int[][] directions = {
                {0, 1}, {0, -1},   // Horizontal
                {1, 0}, {-1, 0},   // Vertical
                {1, 1}, {1, -1},   // Diagonal
                {-1, 1}, {-1, -1}
        };

        int count = 0;

        // Iterate over each cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Check all 8 directions
                for (int[] dir : directions) {
                    if (findWord(grid, i, j, dir, target)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    // Check if the word exists starting from (x, y) in the given direction
    public static boolean findWord(String[] grid, int x, int y, int[] dir, String target) {
        int rows = grid.length;
        int cols = grid[0].length();
        int len = target.length();

        for (int k = 0; k < len; k++) {
            int nx = x + k * dir[0];
            int ny = y + k * dir[1];

            // Check bounds
            if (nx < 0 || ny < 0 || nx >= rows || ny >= cols) {
                return false;
            }

            // Check if the character matches
            if (grid[nx].charAt(ny) != target.charAt(k)) {
                return false;
            }
        }

        return true;
    }
}
