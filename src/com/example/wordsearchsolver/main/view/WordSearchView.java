package com.example.wordsearchsolver.main.view;

import com.example.wordsearchsolver.main.model.WordSearchModel;
import com.example.wordsearchsolver.main.view.components.Button;
import com.example.wordsearchsolver.main.view.components.TextField;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordSearchView extends JFrame implements ActionListener {
    private static final String APP_TITLE = "Word Search Solver (Java Edition)";
    private static final String APPLY_SETUP = "APPLY_SETUP";
    private static final String FIND_WORDS = "FIND_WORDS";

    // Application setup UI
    private final JPanel appConfigPanel = new JPanel();
    private final JLabel rowInputLabel = new JLabel("Rows:");
    private final JLabel columnInputLabel = new JLabel("Columns:");
    private final TextField rowInputField = TextField.createTextField(10, "Max 15");
    private final TextField columnInputField = TextField.createTextField(10, "Max 15");
    private final Button applySetupButton = Button.createButton("Apply", APPLY_SETUP, this);

    // Word Search grid rendering
    private final JPanel wordSearchGridPanel = new JPanel();

    // Words to find UI
    private final JPanel wordsToFindPanel = new JPanel();
    private final JLabel wordsToFinalInputLabel = new JLabel("Words to find (separate with whitespace):");
    private final TextField wordsToFindInputField = TextField.createTextField(10);
    private final Button findWordsButton = Button.createButton("Search", FIND_WORDS, this);

    private WordSearchModel model;

    private JTextField[][] gridCells;

    private WordSearchView() {
        super(APP_TITLE);

        // Setup window
        setSize(480, 640);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
//        setResizable(false);
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
            case APPLY_SETUP:
                System.out.println("Will apply setup...");
                handleSetup();
                break;
            case FIND_WORDS:
                System.out.println("Will find words...");
                findWords();
                break;
            default:
                System.out.println("Unknown event object received");
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

            wordSearchGridPanel.setLayout(new GridLayout(rowCount, columnCount, 5, 5));
            fillGridWithCells(wordSearchGridPanel, rowCount, columnCount);
            revalidate();
        }
    }

    private void fillGridWithCells(JPanel wordSearchGridPanel, int rowCount, int columnCount) {
        gridCells = new JTextField[rowCount][columnCount];
        JTextField gridCell;
        for (int rowCounter = 0; rowCounter < rowCount; rowCounter++) {
            for (int columnCounter = 0; columnCounter < columnCount; columnCounter++) {
                gridCell = new JTextField(1);
                gridCells[rowCounter][columnCounter] = gridCell;
                wordSearchGridPanel.add(gridCell);
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
                JTextField matchedWordStartCharacter = gridCells[matchingRow][matchingColumn];
                matchedWordStartCharacter.setBackground(Color.CYAN);
            }
        }
    }
}
