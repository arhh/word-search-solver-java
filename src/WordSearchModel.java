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
    private ArrayList<ArrayList<Character>> grid;
    private String[] wordsToFind;

    private WordSearchModel(int rows, int columns, String[] wordsToFind) {
        this.wordsToFind = wordsToFind;
        grid = createGrid(rows, columns);
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
    private ArrayList<ArrayList<Character>> createGrid(int rows, int columns) {
        ArrayList<ArrayList<Character>> newGrid = new ArrayList<ArrayList<Character>>();
        ArrayList<Character> row;
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            row = new ArrayList<Character>(columns);
            newGrid.add(row);
        }
        return newGrid;
    }

    private boolean updateGrid(int row, int column, char value) {
        boolean updated = false;

        // Only update the word search if the row-column parameters are within grid range
        if (row <= grid.size() && column <= grid.get(1).size()) {
            grid.get(row).set(column, value);
            updated = true;
        }

        return updated;
    }

    /*
     * Factory method to instantiate a WordSearchModel object.
     *
     * @param rows An int representing the number of rows in the grid
     * @param columns An int representing the number of columns in the grid
     * @param wordsToFind An array of Strings representing the words to find on the word search
     *
     * @returns An instance of WordSearchModel
     */
    public WordSearchModel createWordSearch(int rows, int columns, String[] wordsToFind) {
        WordSearchModel wordsearch = new WordSearchModel(rows, columns, wordsToFind);

        return wordsearch;
    }
}
