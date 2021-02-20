import com.example.wordsearchsolver.main.WordSearchModel;
import org.junit.Test;
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
}
