package com.example.wordsearchsolver.main.view.components;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class FormattedTextField extends JFormattedTextField {

    private FormattedTextField(MaskFormatter maskFormatter, int columns) {
        super(maskFormatter);
        setColumns(columns);
    }

    public static FormattedTextField createFormattedTextFieldWithMask(String mask, int columns) {
        FormattedTextField textField;
        try {
            textField = new FormattedTextField(new MaskFormatter(mask), columns);
        } catch (java.text.ParseException pe) {
            textField = null;
        }

        return textField;
    }
}
