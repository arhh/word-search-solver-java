import java.lang.reflect.Array;
import java.util.ArrayList;

/*
 * Class representing a word search.
 *
 * A word search is a 2D array of characters called a "grid".
 * The array has rows and columns.
 * Given words (i.e. strings), the word search will identify where on the
 * grid the word exists.
 */
public class WordSearchModel {
    private char[][] grid;

    private WordSearchModel(int rows, int columns) {
        grid = initialiseGrid(rows, columns);
    }

    /*
     * Create an empty grid representing the word search board.
     *
     * A grid is a 2D array of characters.
     *
     * @param rows An int representing the number of rows in the grid
     * @param columns An int representing the number of columns in the grid
     *
     * @return An ArrayList of ArrayLists of Strings representing the grid.
     */
    private char[][] initialiseGrid(int rows, int columns) {
        char[][] newGrid = new char[rows][columns];
        return newGrid;
    }

    public boolean updateGrid(int row, int column, char value) {
        boolean updated = false;

        // Only update the word search if the row-column parameters are within grid range
        if (row < grid.length && column < grid[0].length) {
            grid[row][column] = value;
            updated = true;
        }

        return updated;
    }

    /*
     * Factory method to instantiate a WordSearchModel object.
     *
     * @param rows An int representing the number of rows in the grid
     * @param columns An int representing the number of columns in the grid
     *
     * @returns An instance of WordSearchModel
     */
    public static WordSearchModel createWordSearch(int rows, int columns) {
        WordSearchModel wordsearch = new WordSearchModel(rows, columns);

        return wordsearch;
    }

    /*
     * Get the value in word search at location given by row-coloumn.
     *
     * @param row An int representing the row of the location
     * @param column An int representing the column of the location
     *
     * @returns A char representing the item at the selected location,
     * or 0 if the coordinates are out of bounds.
     */
    public char getCell(int row, int column) {
        char cellItem = 0;

        if (row < grid.length && column < grid[0].length) {
            cellItem = grid[row][column];
        }

        return cellItem;
    }

    /*
     * Return a string representation of this word search.
     *
     * @return A string representing the word search grid.
     */
    @Override
    public String toString() {
        String stringRepr = "";

        // Iterate over each row of grid and print the array representing each
        for (char[] row : grid) {
            stringRepr += "[";
            for (char column : row) {
                if (Character.isLetter(column)) {
                    stringRepr += " " + column + " ";
                }
                else {
                    stringRepr += " " + "." + " ";
                }

            }
            stringRepr += "]\n";
        }

        return stringRepr;
    }

    /*
     * Method to locate the row-column position of a word on the word search
     *
     * This method searches the grid for the specified word and returns the row-column pair
     * where the first letter of the word is located.
     *
     * @param wordToFind A String representing the word to find on the grid
     *
     * @returns An int array representing the location of the first letter of the word on
     * the grid, or -1 if not found.
     */
    public int[] findWord(String wordToFind) {
        int[] wordLocation = {-1, -1};

        wordLocation = findWordLeftRightHorizonal(wordToFind);

        return wordLocation;
    }

    /*
     * Find a word on the word search reading left to right horizontally
     *
     * @param wordToFind A string representing the word to search for
     *
     * @returns An int array representing the location of the first letter of the word on
     * the grid, or -1 if not found.
     */
    private int[] findWordLeftRightHorizonal(String wordToFind) {
        int[] wordLocation = {-1, -1};

        int wordLocationInColumn = -1;
        int rowIndex = 0;
        while (rowIndex < grid.length & wordLocationInColumn == -1) {
            char[] column = grid[rowIndex];

            wordLocationInColumn = findWordLeftRightHorizonalR(wordToFind, 0, column, 0, false);

            if (wordLocationInColumn > -1) {
                wordLocation[0] = rowIndex;
                wordLocation[1] = wordLocationInColumn;
            }
            rowIndex++;
        }
        return wordLocation;
    }

    /*
     * Function to locate a given word in a grid column
     *
     * @param wordToFind A string representing the word to find on the grid
     * @param wordToFindStartIndex An int representing the index of the first character in wordToFind
     * @param column A char array representing the column of the grid to search
     * @param columnStartIndex An int representing the index of the first character in wordToFind
     * @param wordPartiallyFound A boolean indicating if part of the word has been found by a previous call
     * to this method
     *
     * @returns An int representing the location of the first character in the word to find on the grid.
     * -1 if not found.
     */
    private int findWordLeftRightHorizonalR(String wordToFind, int wordToFindStartIndex, char[] column, int columnStartIndex, boolean wordPartiallyFound) {

        // At end of word means successful match
        if (wordToFindStartIndex >= wordToFind.length()) {
            return columnStartIndex - wordToFind.length();
        }

        // No match on current row if at end of column
        else if (columnStartIndex >= column.length) {
            return -1;
        }

        // Increment word index and column index if match found
        else if (wordToFind.charAt(wordToFindStartIndex) == column[columnStartIndex]) {
            return findWordLeftRightHorizonalR(wordToFind, wordToFindStartIndex + 1, column, columnStartIndex + 1, true);
        }

        // Current character in word does not match column character
        else {
            // Increment just the column index and reset word index if successful in last iteration
            if (wordPartiallyFound) {
                return findWordLeftRightHorizonalR(wordToFind, 0, column, columnStartIndex, false);
            }
            // Increment just the column index not successful in last iteration
            else {
                return findWordLeftRightHorizonalR(wordToFind, 0, column, columnStartIndex + 1, false);
            }
        }


    }
}
