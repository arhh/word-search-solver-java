public class Test {


    public static void main(String[] args) {
        final String[] wordsToFind = {"word1", "word2"};
        final int testRow = 2;
        final int testColumn = 2;

        WordSearchModel ws = WordSearchModel.createWordSearch(testRow, testColumn, wordsToFind);
        System.out.println(ws);

        boolean testUpdateGridSuccess = testUpdateGrid(ws, testRow, testColumn);
        if (testUpdateGridSuccess) {System.out.println(ws);}
        else {System.out.println("updateGrid() method fail");}
    }

    /*
     * Tests whether updateGrid() is behaving as expected.
     *
     * Requires manual observation as currently relies on string output
     * to verify results.
     */
    private static boolean testUpdateGrid(WordSearchModel ws, int maxRows, int maxColumns) {
        boolean pass = false;

        pass = ws.updateGrid(maxRows - 1, maxColumns - 1, 'a');

        return pass;
    }
}
