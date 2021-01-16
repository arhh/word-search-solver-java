import java.util.Random;

public class Test {


    public static void main(String[] args) {
        final String[] wordsToFind = {"word1", "word2"};
        final int testRow = 4;
        final int testColumn = 4;

        WordSearchModel ws = WordSearchModel.createWordSearch(testRow, testColumn);
        System.out.println(ws);

        boolean testUpdateGridSuccess = testUpdateGrid_lastGridLocationShouldContainCharacterA(ws, testRow, testColumn);
        if (testUpdateGridSuccess) {System.out.println(ws);}
        else {System.out.println("updateGrid() method fail");}

        boolean getCellSuccess = getCell_correctItemReturned(ws, testRow, testColumn);
        if (!getCellSuccess) {System.out.println("getCell_correctItemReturned method fail");}

        boolean getCellUnsuccessful = getCell_outOfBounds(ws, testRow, testColumn);
        if (!getCellUnsuccessful) {System.out.println("getCellUnsuccessful method fail");}
    }

    /*
     * Tests whether updateGrid() is behaving as expected.
     *
     * Requires manual observation as currently relies on string output
     * to verify results.
     */
    private static boolean testUpdateGrid_lastGridLocationShouldContainCharacterA(WordSearchModel ws, int maxRows, int maxColumns) {
        boolean pass = false;
        char testCharacter = 'A';

        pass = ws.updateGrid(maxRows - 1, maxColumns - 1, testCharacter);

        return pass;
    }

    private static boolean getCell_correctItemReturned(WordSearchModel ws, int maxRows, int maxColumns) {
        int testRow = maxRows - 1;
        int testColumn = maxColumns - 1;
        boolean pass = false;
        char testCharacter = 'A';

        if (testCharacter == ws.getCell(testRow, testColumn)) {
            pass = true;
        }

        return pass;
    }

    private static boolean getCell_outOfBounds(WordSearchModel ws, int maxRows, int maxColumns) {
        int testRow = maxRows * 2;
        int testColumn = maxColumns * 2;
        boolean pass = false;
        char testCharacter = 0;

        if (testCharacter == ws.getCell(testRow, testColumn)) {
            pass = true;
        }

        return pass;
    }

    private static boolean findWord_findWordSuccessfully(WordSearchModel ws, int maxRows, int maxColumns) {
        boolean pass = false;
        String wordToFind = "plot";

        // Update the word search with characters, including the word to find
        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxColumns; col++) {
                ws.updateGrid(row, col, ((char) ('a' + new Random().nextInt(26))));
            }
        }

        for (int wordCharIndex = 0; wordCharIndex < wordToFind.length(); wordCharIndex++) {
            ws.updateGrid(wordCharIndex, wordCharIndex, wordToFind.charAt(wordCharIndex));
        }

        int[] wordLocation = ws.findWord(wordToFind);

        if (wordLocation[0] >= 0 & wordLocation[1] >= 0) {
            pass = true;
        }

        return pass;
    }
}
