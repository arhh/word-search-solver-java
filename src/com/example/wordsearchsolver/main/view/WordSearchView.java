package com.example.wordsearchsolver.main.view;

import javax.swing.*;
import java.awt.*;

public class WordSearchView extends JFrame {
    private WordSearchView() {
        super("<frame title>");
        this.setSize(320, 240);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        JButton b = new JButton("quit");
        b.setBounds(120, 50, 100, 35);
        p.add(b);
        this.add(p);
        this.setVisible(true);
    }

    public static WordSearchView createWordSearchView() {
        return new WordSearchView();
    }

    public static void main(String[] args) {
        WordSearchView a = createWordSearchView();
    }
}
