import com.example.wordsearchsolver.main.WordSearchModel;
import org.junit.BeforeClass;
import org.junit.Test;

import java.security.UnresolvedPermission;
import java.util.Random;

import static org.junit.Assert.*;

public class WordSearchModelTest {
    private static final String LEFT_RIGHT = "plot";
    private static final String RIGHT_LEFT = "couple";
    private static final String UP_DOWN = "board";
    private static final String DOWN_UP = "extern";
    private static final String DOWN_RIGHT = "pebble";
    private static final String DOWN_LEFT = "eraser";
    private static final String UP_RIGHT = "woody";
    private static final String UP_LEFT = "plastic";

    private static final int ROWS = 10;
    private static final int COLUMNS = 15;
    private static WordSearchModel wsm;
    private static Random alphabetPicker;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    @BeforeClass
    public static void populateWordSearch() {
        wsm = WordSearchModel.createWordSearch(ROWS, COLUMNS);
        alphabetPicker = new Random();



        // Randomise the whole word search grid with alphabetic characters
        for (int rowIndex = 0; rowIndex < ROWS; rowIndex++) {
            for (int columnIndex = 0; columnIndex < COLUMNS; columnIndex++) {
                wsm.updateGrid(rowIndex, columnIndex, ALPHABET.charAt(alphabetPicker.nextInt(ALPHABET.length())));
            }
        }

        // Here we choose random start positions for rows/columns to ensure there is no
        // overlap in the placement of words to find.
        for (int charIndex = 0; charIndex < LEFT_RIGHT.length(); charIndex++) {
            wsm.updateGrid(0, charIndex, LEFT_RIGHT.charAt(charIndex));
        }
        for (int charIndex = 0; charIndex < RIGHT_LEFT.length(); charIndex++) {
            wsm.updateGrid(0, COLUMNS - charIndex - 1, RIGHT_LEFT.charAt(charIndex));
        }
        for (int charIndex = 0; charIndex < UP_DOWN.length(); charIndex++) {
            wsm.updateGrid(5 + charIndex, 0, UP_DOWN.charAt(charIndex));
        }
        for (int charIndex = 0; charIndex < DOWN_UP.length(); charIndex++) {
            wsm.updateGrid(ROWS - 1 - charIndex, 1, DOWN_UP.charAt(charIndex));
        }
        for (int charIndex = 0; charIndex < DOWN_RIGHT.length(); charIndex++) {
            wsm.updateGrid(2 + charIndex, 4 + charIndex, DOWN_RIGHT.charAt(charIndex));
        }
        for (int charIndex = 0; charIndex < DOWN_LEFT.length(); charIndex++) {
            wsm.updateGrid(1 + charIndex, COLUMNS - 1 - charIndex, DOWN_LEFT.charAt(charIndex));
        }
        for (int charIndex = 0; charIndex < UP_RIGHT.length(); charIndex++) {
            wsm.updateGrid(8 - charIndex, 9 + charIndex, UP_RIGHT.charAt(charIndex));
        }
        for (int charIndex = 0; charIndex < UP_LEFT.length(); charIndex++) {
            wsm.updateGrid(ROWS - 1 - charIndex, 8 - charIndex, UP_LEFT.charAt(charIndex));
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
        final int[] startCoordinateMatchOnWordSearch = wsm.findWord(LEFT_RIGHT);
        assertArrayEquals(new int[] {0, 0}, startCoordinateMatchOnWordSearch);
    }

    @Test
    public void test_findWordUnsuccessfully() {
        final String wordToFind = "I am not on grid";

        final int[] matchCoordinates = wsm.findWord(wordToFind);

        assertArrayEquals(new int[] {-1, -1}, matchCoordinates);
    }

    @Test
    public void test_findWordRightToLeftHorizontalSuccessfully() {
        final int[] startCoordinateMatchOnWordSearch = wsm.findWord(RIGHT_LEFT);
        assertArrayEquals(new int[] {0, 14}, startCoordinateMatchOnWordSearch);
    }

    @Test
    public void test_findWordDownVerticalSuccessfully() {
        final int[] startCoordinateMatchOnWordSearch = wsm.findWord(UP_DOWN);
        assertArrayEquals(new int[] {5, 0}, startCoordinateMatchOnWordSearch);
    }

    @Test
    public void test_findWordUpVerticalSuccessfully() {
        final int[] startCoordinateMatchOnWordSearch = wsm.findWord(DOWN_UP);
        assertArrayEquals(new int[] {9, 1}, startCoordinateMatchOnWordSearch);
    }

    @Test
    public void test_findWordDownRightDiagonalSuccessfully() {
        final int[] startCoordinateMatchOnWordSearch = wsm.findWord(DOWN_RIGHT);
        assertArrayEquals(new int[] {2, 4}, startCoordinateMatchOnWordSearch);
    }

    @Test
    public void test_findWordDownLeftDiagonalSuccessfully() {
        final int[] startCoordinateMatchOnWordSearch = wsm.findWord(DOWN_LEFT);
        assertArrayEquals(new int[] {1, 14}, startCoordinateMatchOnWordSearch);
    }

    @Test
    public void test_findWordUpRightDiagonalSuccessfully() {
        final int[] startCoordinateMatchOnWordSearch = wsm.findWord(UP_RIGHT);
        assertArrayEquals(new int[]{8, 9}, startCoordinateMatchOnWordSearch);
    }

    @Test
    public void test_findWordUpLeftDiagonalSuccessfully() {
        final int[] startCoordinateMatchOnWordSearch = wsm.findWord(UP_LEFT);
        assertArrayEquals(new int[] {9, 8}, startCoordinateMatchOnWordSearch);
    }
}
