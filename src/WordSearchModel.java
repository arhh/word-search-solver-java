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
     * the grid, or {-1, -1} if not found.
     */
    public int[] findWord(String wordToFind) {
        int[] matchCoordinates = findLeftToRightHorizontal(wordToFind);

        if (matchCoordinates[0] == -1 || matchCoordinates[1] == -1) {
            matchCoordinates = findRightToLeftHorizontal(wordToFind);
        }

        return matchCoordinates;
    }

    /*
     * Search for start coordinates of a specified word on the word search from left to right
     * horizontally.
     *
     * @param wordToFind A String representing the word to find
     *
     * @returns An int array representing the location of the first letter of the word on
     * the grid, or {-1, -1} if not found.
     */
    private int[] findLeftToRightHorizontal(String wordToFind) {
        int matchingRowCoordinate = -1;
        int matchingColumnCoordinate = -1;

        for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
            // TODO: Make grid an fully fledged array of string rows
            // However, for going all directions (where building an array each time would be needed
            // may be better to use valueOf for consistency.
            String row = String.valueOf(grid[rowIndex]);
            matchingColumnCoordinate = row.indexOf(wordToFind);

            if (matchingColumnCoordinate != -1) {
                matchingRowCoordinate = rowIndex;
                break;
            }
        }

        return new int[] {matchingRowCoordinate, matchingColumnCoordinate};
    }

    /*
     * Search for start coordinates of a specified word on the word search from right to left
     * horizontally.
     *
     * @param wordToFind A String representing the word to find
     *
     * @returns An int array representing the location of the first letter of the word on
     * the grid, or {-1, -1} if not found.
     */
    private int[] findRightToLeftHorizontal(String wordToFind) {
        int matchingRowCoordinate = -1;
        int matchingColumnCoordinate = -1;

        for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
            String row = String.valueOf(reverse(grid[rowIndex]));
            System.out.println(row);
            matchingColumnCoordinate = (row.contains(wordToFind)) ? (row.length() - row.indexOf(wordToFind) - 1) : -1;

            if (matchingColumnCoordinate != -1) {
                matchingRowCoordinate = rowIndex;
                break;
            }
        }

        return new int[] {matchingRowCoordinate, matchingColumnCoordinate};
    }

    /*
     * Utility method to reverse a character array.
     *
     * @param charArray A character array to reverse.
     *
     * @returns An character array identical to that passed as argument but with elements
     * in reverse order.
     */
    private char[] reverse(char[] charArray) {
        char[] reversedArray = new char[charArray.length];

        for (int i = 0; i < reversedArray.length; i++) {
            reversedArray[i] = charArray[charArray.length - 1 - i];
        }
        return reversedArray;
    }

}
