package com.example.wordsearchsolver.main.view;

import com.example.wordsearchsolver.main.model.WordSearchModel;
import com.example.wordsearchsolver.main.view.components.Button;
import com.example.wordsearchsolver.main.view.components.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordSearchView extends JFrame implements ActionListener {
    private static final String APP_TITLE = "Word Search Solver (Java Edition)";

    private static final String APPLY_SETUP_ACTION_COMMAND = "APPLY_SETUP";
    private static final String APPLY_SETUP_BUTTON_TEXT = "Apply";

    private static final String FIND_WORDS_ACTION_COMMAND = "FIND_WORDS";
    private static final String FIND_WORDS_BUTTON_TEXT = "Search";

    private static final String ROW_INPUT_LABEL_TEXT = "Rows:";
    private static final String ROW_INPUT_LABEL_TOOLTIP = "Max 15";
    private static final int ROW_INPUT_COLUMN = 10;
    private static final int COLUMN_INPUT_COLUMN = 10;
    private static final String COLUMN_INPUT_LABEL_TOOLTIP = "Max 15";
    private static final String COLUMN_INPUT_LABEL_TEXT = "Columns:";

    private static final String WORDS_TO_FIND_INPUT_LABEL_TEXT = "Words to find (separate with whitespace):";
    private static final int WORDS_TO_FIND_INPUT_COLUMN = 10;


    // Application setup UI
    private final JPanel appConfigPanel = new JPanel();
    private final JLabel rowInputLabel = new JLabel(ROW_INPUT_LABEL_TEXT);
    private final JLabel columnInputLabel = new JLabel(COLUMN_INPUT_LABEL_TEXT);
    private final TextField rowInputField = TextField.createTextField(ROW_INPUT_COLUMN, ROW_INPUT_LABEL_TOOLTIP);
    private final TextField columnInputField = TextField.createTextField(COLUMN_INPUT_COLUMN, COLUMN_INPUT_LABEL_TOOLTIP);
    private final Button applySetupButton = Button.createButton(APPLY_SETUP_BUTTON_TEXT, APPLY_SETUP_ACTION_COMMAND, this);

    // Word Search grid rendering
    private final JPanel wordSearchGridPanel = new JPanel();

    // Words to find UI
    private final JPanel wordsToFindPanel = new JPanel();
    private final JLabel wordsToFinalInputLabel = new JLabel(WORDS_TO_FIND_INPUT_LABEL_TEXT);
    private final TextField wordsToFindInputField = TextField.createTextField(WORDS_TO_FIND_INPUT_COLUMN);
    private final Button findWordsButton = Button.createButton(FIND_WORDS_BUTTON_TEXT, FIND_WORDS_ACTION_COMMAND, this);

    private WordSearchModel model;

    private TextField[][] gridCells;

    private WordSearchView() {
        super(APP_TITLE);

        // Setup window
        setSize(560, 640);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Application setup UI
        appConfigPanel.add(rowInputLabel);
        appConfigPanel.add(rowInputField);
        appConfigPanel.add(columnInputLabel);
        appConfigPanel.add(columnInputField);
        appConfigPanel.add(applySetupButton);
        appConfigPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(appConfigPanel);

        // Word Search grid rendering
        wordSearchGridPanel.setLayout(new GridBagLayout());
        add(wordSearchGridPanel);

        // Words to find UI
        wordsToFindPanel.add(wordsToFinalInputLabel);
        wordsToFindPanel.add(wordsToFindInputField);
        wordsToFindPanel.add(findWordsButton);
        wordsToFindPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(wordsToFindPanel);

        setVisible(true);
    }

    public static WordSearchView createWordSearchView() {
        return new WordSearchView();
    }

    public static void main(String[] args) {
        WordSearchView a = createWordSearchView();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "APPLY_SETUP":
                handleSetup();
                break;
            case "FIND_WORDS":
                findWords();
                break;
            default:
                System.err.println("Invalid action command: " + actionEvent.getActionCommand());
        }
    }

    private void handleSetup() {
        int rowCount;
        int columnCount;
        try {
            rowCount = Integer.parseInt(rowInputField.getText());
            columnCount = Integer.parseInt(columnInputField.getText());
        } catch (NumberFormatException e) {
            rowCount = 0;
            columnCount = 0;
        }

        if (rowCount > 0 && columnCount > 0 && rowCount <= 15 && columnCount <= 15) {
            model = WordSearchModel.createWordSearch(rowCount, columnCount);

            wordSearchGridPanel.removeAll();
            fillGridWithCells(wordSearchGridPanel, rowCount, columnCount);
            wordSearchGridPanel.revalidate();
            wordSearchGridPanel.repaint();
        }
    }

    private void fillGridWithCells(JPanel wordSearchGridPanel, int rowCount, int columnCount) {
        gridCells = new TextField[rowCount][columnCount];
        GridBagConstraints cellConstraints;
        TextField gridCell;
        for (int rowCounter = 0; rowCounter < rowCount; rowCounter++) {
            for (int columnCounter = 0; columnCounter < columnCount; columnCounter++) {
                gridCell = TextField.createTextField(1);
                gridCell.setHorizontalAlignment(JTextField.CENTER);
                gridCells[rowCounter][columnCounter] = gridCell;

                cellConstraints = new GridBagConstraints();
                cellConstraints.gridx = rowCounter;
                cellConstraints.gridy = columnCounter;
                cellConstraints.ipadx = 15;
                cellConstraints.ipady = 15;
                wordSearchGridPanel.add(gridCell, cellConstraints);
            }
        }
    }

    private void findWords() {
        System.out.println(model.toString());
        for (int rowCounter = 0; rowCounter < gridCells.length; rowCounter++) {
            for (int columnCounter = 0; columnCounter < gridCells[rowCounter].length; columnCounter++) {
                model.updateGrid(rowCounter, columnCounter, gridCells[rowCounter][columnCounter].getText().charAt(0));
            }
        }

        final String[] wordsToFind = wordsToFindInputField.getText().split("\\s");
        for (String word : wordsToFind) {
            int[] matchCoordinates = model.findWord(word);
            int matchingRow = matchCoordinates[0];
            int matchingColumn = matchCoordinates[1];
            if (matchingRow > -1 && matchingColumn > -1) {
                TextField matchedWordStartCharacter = gridCells[matchingRow][matchingColumn];
                matchedWordStartCharacter.setBackground(Color.CYAN);
            }
        }
    }
}
