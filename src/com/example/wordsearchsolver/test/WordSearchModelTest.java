import com.example.wordsearchsolver.main.WordSearchModel;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class WordSearchModelTest {
    private static final int ROWS = 10;
    private static final int COLUMNS = 10;

    private WordSearchModel wsm;

    @Test
    public void test_updateCellWithinGridBoundsSuccessfully() {
        final char testChar = 'A';
        final int rowToUpdate = ROWS - 1;
        final int columnToUpdate = COLUMNS - 1;
        wsm = WordSearchModel.createWordSearch(ROWS, COLUMNS);

        final boolean updatedSuccessfully = wsm.updateGrid(rowToUpdate, columnToUpdate, testChar);
        assertTrue(updatedSuccessfully);
        assertEquals(testChar, wsm.getCell(ROWS - 1, COLUMNS - 1));
    }

    @Test
    public void test_updateCellOutOfGridBoundsUnsuccessfully() {
        final char testChar = 'A';
        final int rowToUpdate = ROWS;
        final int columnToUpdate = COLUMNS;
        wsm = WordSearchModel.createWordSearch(ROWS, COLUMNS);

        final boolean updatedSuccessfully = wsm.updateGrid(rowToUpdate, columnToUpdate, testChar);
        assertFalse(updatedSuccessfully);
    }

    @Test
    public void test_getCellWithinGridSuccessfully() {
        final char testChar = 'A';
        final int targetRow = ROWS - 1;
        final int targetColumn = COLUMNS - 1;
        wsm = WordSearchModel.createWordSearch(ROWS, COLUMNS);
        wsm.updateGrid(targetRow, targetColumn, testChar);

        final char retrievedCellValue = wsm.getCell(targetRow, targetColumn);
        assertEquals(testChar, retrievedCellValue);
    }

    @Test
    public void test_getCellWithinGridUnsuccessfully() {
        final int targetRow = ROWS - 1;
        final int targetColumn = COLUMNS - 1;
        wsm = WordSearchModel.createWordSearch(ROWS, COLUMNS);

        final char retrievedCellValue = wsm.getCell(targetRow, targetColumn);
        assertEquals(0, retrievedCellValue);
    }

    @Test
    public void test_getCellOutOfGridBoundsUnsuccessfully() {
        final char testChar = 'A';
        final int targetRow = ROWS;
        final int targetColumn = COLUMNS;
        wsm = WordSearchModel.createWordSearch(ROWS, COLUMNS);

        final char retrievedCellValue = wsm.getCell(targetRow, targetColumn);
        assertEquals(0, retrievedCellValue);
    }

    // Test that we can find a word, regardless of direction
    @Test
    public void test_findWordSuccessfully() {
        final String wordToFind = "plot";
        final Random charGenerator = new Random();
        wsm = WordSearchModel.createWordSearch(ROWS, COLUMNS);

        // Randomise the word search grid with alphabetic characters
        for (int rowIndex = 0; rowIndex < ROWS; rowIndex++) {
            for (int columnIndex = 0; columnIndex < COLUMNS; columnIndex++) {
                wsm.updateGrid(rowIndex, columnIndex, (char) charGenerator.nextInt(26));
            }
        }

        // Insert wordToFind into grid. Here we only care that the findWord method
        // works, no regard to how it finds a word.
        for (int i = 0; i < wordToFind.length(); i++) {
            wsm.updateGrid(0, i, wordToFind.charAt(i));
        }

        final int[] startCoordinateMatchOnWordSearch = wsm.findWord(wordToFind);

        assertArrayEquals(new int[] {0, 0}, startCoordinateMatchOnWordSearch);
    }

    // Test that we can find a word, regardless of direction
    @Test
    public void test_findWordUnsuccessfully() {
        final String wordToFind = "plot";
        final Random charGenerator = new Random();
        wsm = WordSearchModel.createWordSearch(ROWS, COLUMNS);

        // Randomise the word search grid with alphabetic characters
        for (int rowIndex = 0; rowIndex < ROWS; rowIndex++) {
            for (int columnIndex = 0; columnIndex < COLUMNS; columnIndex++) {
                wsm.updateGrid(rowIndex, columnIndex, (char) charGenerator.nextInt(26));
            }
        }

        final int[] startCoordinateMatchOnWordSearch = wsm.findWord(wordToFind);

        assertArrayEquals(new int[] {-1, -1}, startCoordinateMatchOnWordSearch);
    }
}
