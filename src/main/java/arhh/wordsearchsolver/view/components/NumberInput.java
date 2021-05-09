package arhh.wordsearchsolver.view.components;

import javax.swing.*;

public class NumberInput extends JSpinner {
    private NumberInput(SpinnerNumberModel numberSpinnerModel) {
        super(numberSpinnerModel);
    }

    public static NumberInput createNumberInput(int initialValue, int minValue, int maxValue, int step) {
        SpinnerNumberModel model = new SpinnerNumberModel(initialValue, minValue, maxValue, step);
        return new NumberInput(model);
    }

    public static NumberInput createNumberInput(int minValue, int maxValue) {
        SpinnerNumberModel model = new SpinnerNumberModel(minValue, minValue, maxValue, 1);
        return new NumberInput(model);
    }
}
