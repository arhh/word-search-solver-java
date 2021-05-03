package arhh.wordsearchsolver.view.components;

import javax.swing.*;

public class TextField extends JTextField {

    private TextField(int columns, String toolTipText) {
        super();
        setToolTipText(toolTipText);
        if (columns > -1) {
            setColumns(columns);
        }
    }

    public static TextField createTextField(int columns, String toolTipText) {
        return new TextField(columns, toolTipText);
    }

    public static TextField createTextField() {
        return new TextField(-1, null);
    }

    public static TextField createTextField(int columns) {
        return new TextField(columns, null);
    }
}
