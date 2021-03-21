package com.example.wordsearchsolver.main.view;

import javax.swing.*;
import java.awt.*;

public class WordSearchView extends JFrame {
    private WordSearchView() {
        super("Word Search Solver (Java Edition)");
        this.setSize(640, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pnl = new JPanel();
        pnl.setLayout(null);

        JButton b = new JButton("quit");
        b.setBounds(220, 400, 100, 35);
        pnl.add(b);

        JLabel txt = new JLabel("test text...");
        txt.setBounds(220, 100, 100, 35);
        pnl.add(txt);

        this.add(pnl);
        this.setVisible(true);
    }

    public static WordSearchView createWordSearchView() {
        return new WordSearchView();
    }

    public static void main(String[] args) {
        WordSearchView a = createWordSearchView();
    }
}
