package arhh.wordsearchsolver.view.components;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.text.ParseException;

public class FormattedTextField extends JFormattedTextField {

    private FormattedTextField(AbstractFormatterFactory maskFormatter, int columns, InputVerifier inputVerifier) {
        super(maskFormatter);
        setColumns(columns);
        setInputVerifier(inputVerifier);
    }

    public static FormattedTextField createBoundedNumericalTextField(int min, int max, int columns) {
        FormattedTextField textField;

        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumIntegerDigits(min);
        format.setMaximumIntegerDigits(max);
        NumberFormatter formatter = new NumberFormatter(format);

        InputVerifier verifier = new Verifier();

        textField = new FormattedTextField(new DefaultFormatterFactory(formatter), columns, verifier);

        return textField;
    }

    private static class Verifier extends InputVerifier {
        @Override
        public boolean verify(JComponent jComponent) {
            FormattedTextField inputField = (FormattedTextField) jComponent;
            AbstractFormatter formatter = inputField.getFormatter();

            boolean isValidInput = true;

            if (formatter != null) {
                String parsedInput;
                try {
                    parsedInput = (String) formatter.stringToValue(inputField.getText());
                } catch (ParseException | ClassCastException pe) {
                    parsedInput = null;
                }

                if (parsedInput == null) {
                    isValidInput = false;
                }
            }

            return isValidInput;
        }
    }
}
