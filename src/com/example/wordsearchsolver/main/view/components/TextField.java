package com.example.wordsearchsolver.main.view.components;

import javax.swing.*;

public class TextField extends JTextField {

    private TextField(int columns, String toolTipText) {
        super(columns);
        setToolTipText(toolTipText);
    }

    public static TextField createTextField(int columns, String toolTipText) {
        return new TextField(columns, toolTipText);
    }

    public static TextField createTextField(int columns) {
        return new TextField(columns, null);
    }
}
