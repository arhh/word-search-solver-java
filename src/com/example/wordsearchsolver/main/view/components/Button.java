package com.example.wordsearchsolver.main.view.components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Button extends JButton {
    private Button(String text, String actionCommand, ActionListener actionListenerObject) {
        super(text);
        setActionCommand(actionCommand);
        addActionListener(actionListenerObject);
    }

    public static Button createButton(String text, String actionCommand, ActionListener actionListenerObject) {
        return new Button(text, actionCommand, actionListenerObject);
    }
}
