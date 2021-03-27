package com.example.wordsearchsolver.main.view;

import javax.swing.*;
import java.awt.*;

public class WordSearchView extends JFrame {
    private static final String APP_TITLE = "Word Search Solver (Java Edition)";

    // Application setup UI
    private final JPanel appConfigPanel = new JPanel();
    private final JLabel rowInputLabel = new JLabel("Rows:");
    private final JLabel columnInputLabel = new JLabel("Columns:");
    private final JTextField rowInputField = new JTextField(10);
    private final JTextField columnInputField = new JTextField(10);
    private final JButton applySetupButton = new JButton("Apply");

    // Word Search grid rendering
    private final JPanel wordSearchGridPanel = new JPanel();
    private final JTextField wordSearchCell = new JTextField(10);

    // Words to find UI
    private final JPanel wordsToFindPanel = new JPanel();
    private final JLabel wordsToFinalInputLabel = new JLabel("Words to find (separate with whitespace):");
    private final JTextField wordsToFindInputField = new JTextField(10);
    private final JButton findWordsButton = new JButton("Search");

    private WordSearchView() {
        super(APP_TITLE);

        // Setup window
        setSize(640, 480);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Application setup UI
        appConfigPanel.add(rowInputLabel);
        appConfigPanel.add(rowInputField);
        appConfigPanel.add(columnInputLabel);
        appConfigPanel.add(columnInputField);
        appConfigPanel.add(applySetupButton);
        add(appConfigPanel);

        // Word Search grid rendering
//        wordSearchCell.setColumns(1);
        wordSearchGridPanel.add(wordSearchCell);
        add(wordSearchGridPanel);

        // Words to find UI
        wordsToFindPanel.add(wordsToFinalInputLabel);
        wordsToFindPanel.add(wordsToFindInputField);
        wordsToFindPanel.add(findWordsButton);
        add(wordsToFindPanel);

        setVisible(true);
    }

    public static WordSearchView createWordSearchView() {
        return new WordSearchView();
    }

    public static void main(String[] args) {
        WordSearchView a = createWordSearchView();
    }
}
