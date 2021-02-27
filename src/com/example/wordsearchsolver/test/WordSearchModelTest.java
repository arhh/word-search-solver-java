import com.example.wordsearchsolver.main.WordSearchModel;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class WordSearchModelTest {
    private static final int ROWS = 10;
    private static final int COLUMNS = 15;
    private static WordSearchModel wsm;
    private static Random alphabetPicker;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    @BeforeClass
    public static void populateWordSearch() {
        wsm = WordSearchModel.createWordSearch(ROWS, COLUMNS);
        alphabetPicker = new Random();

        final String leftRight = "plot";
        final String rightLeft = "couple";
        final String upDown = "board";
        final String downUp = "extern";
        final String downRight = "pebble";
        final String downLeft = "eraser";
        final String upRight = "woody";
        final String upLeft = "plastic";

        // Randomise the whole word search grid with alphabetic characters
        for (int rowIndex = 0; rowIndex < ROWS; rowIndex++) {
            for (int columnIndex = 0; columnIndex < COLUMNS; columnIndex++) {
                wsm.updateGrid(rowIndex, columnIndex, ALPHABET.charAt(alphabetPicker.nextInt(ALPHABET.length())));
            }
        }

        // Here we choose random start positions for rows/columns to ensure there is no
        // overlap in the placement of words to find.
        for (int charIndex = 0; charIndex < leftRight.length(); charIndex++) {
            wsm.updateGrid(0, charIndex, leftRight.charAt(charIndex));
        }
        for (int charIndex = 0; charIndex < rightLeft.length(); charIndex++) {
            wsm.updateGrid(0, COLUMNS - charIndex - 1, rightLeft.charAt(charIndex));
        }
        for (int charIndex = 0; charIndex < upDown.length(); charIndex++) {
            wsm.updateGrid(5 + charIndex, 0, upDown.charAt(charIndex));
        }
        for (int charIndex = 0; charIndex < downUp.length(); charIndex++) {
            wsm.updateGrid(ROWS - 1 - charIndex, 1, downUp.charAt(charIndex));
        }
        for (int charIndex = 0; charIndex < downRight.length(); charIndex++) {
            wsm.updateGrid(2 + charIndex, 4 + charIndex, downRight.charAt(charIndex));
        }
        for (int charIndex = 0; charIndex < downLeft.length(); charIndex++) {
            wsm.updateGrid(1 + charIndex, COLUMNS - 1 - charIndex, downLeft.charAt(charIndex));
        }
        for (int charIndex = 0; charIndex < upRight.length(); charIndex++) {
            wsm.updateGrid(8 - charIndex, 9 + charIndex, upRight.charAt(charIndex));
        }
        for (int charIndex = 0; charIndex < upLeft.length(); charIndex++) {
            wsm.updateGrid(ROWS - 1 - charIndex, 8 - charIndex, upLeft.charAt(charIndex));
        }

        System.out.println(wsm);
    }

    @Test
    public void test_updateCellWithinGridBoundsSuccessfully() {
        final char testChar = 'A';
        final int rowToUpdate = ROWS - 1;
        final int columnToUpdate = COLUMNS - 1;

        final char existingCharAtCoordinates = wsm.getCell(rowToUpdate, columnToUpdate);

        final boolean updatedSuccessfully = wsm.updateGrid(rowToUpdate, columnToUpdate, testChar);
        assertTrue(updatedSuccessfully);
        assertEquals(testChar, wsm.getCell(ROWS - 1, COLUMNS - 1));

        wsm.updateGrid(rowToUpdate, columnToUpdate, existingCharAtCoordinates);
    }

    @Test
    public void test_updateCellOutOfGridBoundsUnsuccessfully() {
        final char testChar = 'A';
        final int rowToUpdate = ROWS;
        final int columnToUpdate = COLUMNS;

        final boolean updatedSuccessfully = wsm.updateGrid(rowToUpdate, columnToUpdate, testChar);
        assertFalse(updatedSuccessfully);
    }

    @Test
    public void test_getCellWithinGridSuccessfully() {
        final char testChar = 'A';
        final int targetRow = ROWS - 1;
        final int targetColumn = COLUMNS - 1;
        wsm.updateGrid(targetRow, targetColumn, testChar);

        final char retrievedCellValue = wsm.getCell(targetRow, targetColumn);
        assertEquals(testChar, retrievedCellValue);
    }

    @Test
    public void test_getCellOutOfGridBoundsUnsuccessfully() {
        final int targetRow = ROWS;
        final int targetColumn = COLUMNS;

        final char retrievedCellValue = wsm.getCell(targetRow, targetColumn);
        assertEquals(0, retrievedCellValue); // int "0" represents unmatched character.
    }

    @Test
    public void test_findWordLeftToRightHorizontalSuccessfully() {
        final String wordToFind = "plot";

        final int[] startCoordinateMatchOnWordSearch = wsm.findWord(wordToFind);

        assertArrayEquals(new int[] {0, 0}, startCoordinateMatchOnWordSearch);
    }

    @Test
    public void test_findWordLeftToRightHorizontalUnsuccessfully() {
        final String wordToFind = "I am not on grid";

        final int[] matchCoordinates = wsm.findWord(wordToFind);

        assertArrayEquals(new int[] {-1, -1}, matchCoordinates);
    }
}
