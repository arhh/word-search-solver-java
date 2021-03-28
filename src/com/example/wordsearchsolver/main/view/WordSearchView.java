package com.example.wordsearchsolver.main.view;

import com.example.wordsearchsolver.main.view.components.Button;

import javax.swing.*;
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
    private final JTextField rowInputField = new JTextField(10);
    private final JTextField columnInputField = new JTextField(10);
    private final Button applySetupButton = Button.createButton("Apply", APPLY_SETUP, this);

    // Word Search grid rendering
    private final JPanel wordSearchGridPanel = new JPanel();
    private final JTextField wordSearchCell = new JTextField(10);

    // Words to find UI
    private final JPanel wordsToFindPanel = new JPanel();
    private final JLabel wordsToFinalInputLabel = new JLabel("Words to find (separate with whitespace):");
    private final JTextField wordsToFindInputField = new JTextField(10);
    private final Button findWordsButton = Button.createButton("Search", FIND_WORDS, this);

    private WordSearchView() {
        super(APP_TITLE);

        // Setup window
        setSize(640, 480);
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
        wordSearchGridPanel.add(wordSearchCell);
        wordSearchGridPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
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
                break;
            case FIND_WORDS:
                System.out.println("Will find words...");
                break;
            default:
                System.out.println("Unknown event object received");
        }
    }
}
