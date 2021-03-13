package com.example.wordsearchsolver.main;

/*
 * Class representing a word search.
 *
 * A word search is a 2D array of characters called a "grid".
 * The array has rows and columns.
 * Given words (i.e. strings), the word search will identify where on the
 * grid the word exists.
 */
public class WordSearchModel {
    private final char[][] grid;

    private WordSearchModel(int rows, int columns) {
        grid = initialiseGrid(rows, columns);
    }

    // Create a blank word search grid with client-specified rows and columns
    private char[][] initialiseGrid(int rows, int columns) {
        char[][] newGrid = new char[rows][columns];
        return newGrid;
    }

    /*
     * Update a cell in the word search grid.
     *
     * @param row An int representing the row containing the cell to update
     * @param column An int representing the column containing the cell to update
     * @param value A char representing the new value for the cell to update
     *
     * @returns A boolean which is true if the cell was updated successfully
     */
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
     * Get the value in word search at location given by row-column.
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

        if (matchCoordinates[0] == -1 || matchCoordinates[1] == -1) {
            matchCoordinates = findDownwards(wordToFind);
        }

        if (matchCoordinates[0] == -1 || matchCoordinates[1] == -1) {
            matchCoordinates = findUpwards(wordToFind);
        }

        if (matchCoordinates[0] == -1 || matchCoordinates[1] == -1) {
            matchCoordinates = findRightToLeftUpDiagonal(wordToFind);
        }

        if (matchCoordinates[0] == -1 || matchCoordinates[1] == -1) {
            matchCoordinates = findRightToLeftDownDiagonal(wordToFind);
        }

        if (matchCoordinates[0] == -1 || matchCoordinates[1] == -1) {
            matchCoordinates = findLeftToRightUpDiagonal(wordToFind);
        }

        if (matchCoordinates[0] == -1 || matchCoordinates[1] == -1) {
            matchCoordinates = findLeftToRightDownDiagonal(wordToFind);
        }

        return matchCoordinates;
    }

    // Return the first character coordinates for a word found in left to right direction
    private int[] findLeftToRightHorizontal(String wordToFind) {
        int matchingRowCoordinate = -1;
        int matchingColumnCoordinate = -1;

        for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
            String row = String.valueOf(grid[rowIndex]);
            matchingColumnCoordinate = row.indexOf(wordToFind);

            if (matchingColumnCoordinate != -1) {
                matchingRowCoordinate = rowIndex;
                break;
            }
        }

        return new int[] {matchingRowCoordinate, matchingColumnCoordinate};
    }

    // Search for start coordinates of a specified word on the word search from lower left to upper right diagonally
    private int[] findLeftToRightUpDiagonal(String wordToFind, int rowIndex, int columnIndex) {

        final int[] matchCoordinates = matchStringGivenDiagonal(rowIndex, columnIndex, wordToFind);

        // return the coordinates if reached end of word search or found a match
        if ((matchCoordinates[0] > -1 && matchCoordinates[1] > -1) || (rowIndex >= grid.length - 1 && columnIndex >= grid[0].length - 1)) {
            return matchCoordinates;
        } else if (rowIndex >= grid.length - 1) {
            return findLeftToRightUpDiagonal(wordToFind, rowIndex, columnIndex + 1);
        } else {
            // No match and neither row nor column index has reached the end
            return findLeftToRightUpDiagonal(wordToFind, rowIndex + 1, columnIndex);
        }
    }

    // Find a word on the word search in right-to-left downwards direction
    private int[] findRightToLeftDownDiagonal(String wordToFind) {
        return findRightToLeftDownDiagonal(wordToFind, 0, 0);
    }

    private int[] findRightToLeftDownDiagonal(String wordToFind, int rowIndex, int columnIndex) {

        final int[] matchCoordinates = matchStringGivenDiagonalReverse(rowIndex, columnIndex, wordToFind);

        // return the coordinates if reached end of word search or found a match
        if ((matchCoordinates[0] > -1 && matchCoordinates[1] > -1) || (rowIndex >= grid.length - 1 && columnIndex >= grid[0].length - 1)) {
            return matchCoordinates;
        } else if (rowIndex >= grid.length - 1) {
            return findRightToLeftDownDiagonal(wordToFind, rowIndex, columnIndex + 1);
        } else {
            // No match and neither row nor column index has reached the end
            return findRightToLeftDownDiagonal(wordToFind, rowIndex + 1, columnIndex);
        }
    }

    // Find a word on the word search in left-to-right up direction
    private int[] findLeftToRightUpDiagonal(String wordToFind) {
        return findLeftToRightUpDiagonal(wordToFind, 0, 0);
    }

    // Find a word on the word search in left-to-right down direction
    private int[] findLeftToRightDownDiagonal(String wordToFind) {
        return findLeftToRightDownDiagonal(wordToFind, 0, grid[0].length - 1);
    }

    private int[] findLeftToRightDownDiagonal(String wordToFind, int rowIndex, int columnIndex) {

        final int[] matchCoordinates = matchStringGivenDiagonalDown(rowIndex, columnIndex, wordToFind);

        // return the coordinates if reached end of word search or found a match
        if ((matchCoordinates[0] > -1 && matchCoordinates[1] > -1) || (rowIndex >= grid.length - 1 && columnIndex <= 0)) {
            return matchCoordinates;
        } else if (columnIndex <= 0) {
            return findLeftToRightDownDiagonal(wordToFind, rowIndex + 1, columnIndex);
        } else {
            // No match and neither row nor column index has reached the end
            return findLeftToRightDownDiagonal(wordToFind, rowIndex, columnIndex - 1);
        }
    }

    // Find a word on the word search in right-to-left up direction
    private int[] findRightToLeftUpDiagonal(String wordToFind) {
        return findRightToLeftUpDiagonal(wordToFind, 0, grid[0].length - 1);
    }

    private int[] findRightToLeftUpDiagonal(String wordToFind, int rowIndex, int columnIndex) {

        final int[] matchCoordinates = matchStringGivenDiagonalDownReverse(rowIndex, columnIndex, wordToFind);

        // return the coordinates if reached end of word search or found a match
        if ((matchCoordinates[0] > -1 && matchCoordinates[1] > -1) || (rowIndex >= grid.length - 1 && columnIndex <= 0)) {
            return matchCoordinates;
        } else if (columnIndex <= 0) {
            return findRightToLeftUpDiagonal(wordToFind, rowIndex + 1, columnIndex);
        } else {
            // No match and neither row nor column index has reached the end
            return findRightToLeftUpDiagonal(wordToFind, rowIndex, columnIndex - 1);
        }
    }

    // Build a string from a diagonal series of word search coordinates left to right upwards
    // and find start coordinates of word in the string.
    private int[] matchStringGivenDiagonal(int rowIndex, int columnIndex, String wordToFind) {
        int matchingRowCoordinate = -1;
        int matchingColumnCoordinate = -1;
        String stringFromDiagonal = "";

        int currentRowIndex = rowIndex;
        int currentColumnIndex = columnIndex;
        while (currentRowIndex >= 0 && (currentColumnIndex < grid[0].length)) {
            stringFromDiagonal += getCell(currentRowIndex, currentColumnIndex);
            currentRowIndex--;
            currentColumnIndex++;
        }

        final int wordStartInString = stringFromDiagonal.indexOf(wordToFind);

        if (wordStartInString > -1) {
            matchingRowCoordinate = rowIndex - wordStartInString;
            matchingColumnCoordinate = columnIndex + wordStartInString;
        }

        return new int[] {matchingRowCoordinate, matchingColumnCoordinate};
    }

    // Build a string from a diagonal series of word search coordinates left to right upwards
    // and find start coordinates of word in the reversed string.
    private int[] matchStringGivenDiagonalReverse(int rowIndex, int columnIndex, String wordToFind) {
        int matchingRowCoordinate = -1;
        int matchingColumnCoordinate = -1;
        String stringFromDiagonal = "";

        int currentRowIndex = rowIndex;
        int currentColumnIndex = columnIndex;
        while (currentRowIndex >= 0 && (currentColumnIndex < grid[0].length)) {
            stringFromDiagonal += getCell(currentRowIndex, currentColumnIndex);
            currentRowIndex--;
            currentColumnIndex++;
        }

        int wordStartInString = reverse(stringFromDiagonal).indexOf(wordToFind);

        if (wordStartInString > -1) {
            wordStartInString = stringFromDiagonal.length() - 1 - wordStartInString;
            matchingRowCoordinate = rowIndex - wordStartInString;
            matchingColumnCoordinate = columnIndex + wordStartInString;
        }

        return new int[] {matchingRowCoordinate, matchingColumnCoordinate};
    }

    // Build a string from a diagonal series of word search coordinates left to right downwards
    // and find start coordinates of word in the string.
    private int[] matchStringGivenDiagonalDown(int rowIndex, int columnIndex, String wordToFind) {
        int matchingRowCoordinate = -1;
        int matchingColumnCoordinate = -1;
        String stringFromDiagonal = "";

        int currentRowIndex = rowIndex;
        int currentColumnIndex = columnIndex;
        while (currentRowIndex < grid.length && (currentColumnIndex < grid[0].length)) {
            stringFromDiagonal += getCell(currentRowIndex, currentColumnIndex);
            currentRowIndex++;
            currentColumnIndex++;
        }

        final int wordStartInString = stringFromDiagonal.indexOf(wordToFind);

        if (wordStartInString > -1) {
            matchingRowCoordinate = rowIndex + wordStartInString;
            matchingColumnCoordinate = columnIndex + wordStartInString;
        }

        return new int[] {matchingRowCoordinate, matchingColumnCoordinate};
    }

    // Build a string from a diagonal series of word search coordinates left to right downwards
    // and find start coordinates of word in the string.
    private int[] matchStringGivenDiagonalDownReverse(int rowIndex, int columnIndex, String wordToFind) {
        int matchingRowCoordinate = -1;
        int matchingColumnCoordinate = -1;
        String stringFromDiagonal = "";

        int currentRowIndex = rowIndex;
        int currentColumnIndex = columnIndex;
        while (currentRowIndex < grid.length && (currentColumnIndex < grid[0].length)) {
            stringFromDiagonal += getCell(currentRowIndex, currentColumnIndex);
            currentRowIndex++;
            currentColumnIndex++;
        }

        int wordStartInString = reverse(stringFromDiagonal).indexOf(wordToFind);

        if (wordStartInString > -1) {
            wordStartInString = stringFromDiagonal.length() - 1 - wordStartInString;
            matchingRowCoordinate = rowIndex + wordStartInString;
            matchingColumnCoordinate = columnIndex + wordStartInString;
        }

        return new int[] {matchingRowCoordinate, matchingColumnCoordinate};
    }

    // Search for start coordinates of a specified word on the word search from right to left
    // horizontally
    private int[] findRightToLeftHorizontal(String wordToFind) {
        int matchingRowCoordinate = -1;
        int matchingColumnCoordinate = -1;

        for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
            String row = String.valueOf(reverse(grid[rowIndex]));
            matchingColumnCoordinate = (row.contains(wordToFind)) ? (row.length() - row.indexOf(wordToFind) - 1) : -1;

            if (matchingColumnCoordinate != -1) {
                matchingRowCoordinate = rowIndex;
                break;
            }
        }

        return new int[] {matchingRowCoordinate, matchingColumnCoordinate};
    }

    // Search for start coordinates of a specified word on the word search from top to bottom.
    private int[] findDownwards(String wordToFind) {
        int matchingRowCoordinate = -1;
        int matchingColumnCoordinate = -1;

        for (int columnIndex = 0; columnIndex < grid[0].length; columnIndex++) {
            final String column = buildStringFromRows(columnIndex);
            matchingRowCoordinate = column.indexOf(wordToFind);

            if (matchingRowCoordinate != -1) {
                matchingColumnCoordinate = columnIndex;
                break;
            }
        }

        return new int[] {matchingRowCoordinate, matchingColumnCoordinate};
    }

    // Search for start coordinates of a specified word on the word search from top to bottom
    private int[] findUpwards(String wordToFind) {
        int matchingRowCoordinate = -1;
        int matchingColumnCoordinate = -1;

        for (int columnIndex = 0; columnIndex < grid[0].length; columnIndex++) {
            final String column = buildStringFromRows(columnIndex);
            matchingRowCoordinate = reverse(column).contains(wordToFind) ? grid.length - reverse(column).indexOf(wordToFind) - 1 : -1;

            if (matchingRowCoordinate != -1) {
                matchingColumnCoordinate = columnIndex;
                break;
            }
        }

        return new int[] {matchingRowCoordinate, matchingColumnCoordinate};
    }

    // Concatenate an element from a sequence of arrays into a single array.
    private String buildStringFromRows(int columnIndex) {
        String columnString = "";

        for (char[] row : grid) {
            columnString += row[columnIndex];
        }

        return columnString;
    }

    // Utility method to reverse a character array.
    private char[] reverse(char[] charArray) {
        char[] reversedArray = new char[charArray.length];

        for (int i = 0; i < reversedArray.length; i++) {
            reversedArray[i] = charArray[charArray.length - 1 - i];
        }
        return reversedArray;
    }

    // Utility method to reverse a string
    private String reverse(String str) {
        StringBuilder reversedString = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(str.length() - i - 1);
            reversedString.append(c);
        }
        return reversedString.toString();
    }

}

