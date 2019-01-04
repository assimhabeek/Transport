package bda1.utils;

import javafx.scene.control.TextField;

public class ValidateInput {

    public static void floatValidator(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) textField.setText(oldValue);
        });
    }

    public static void intValidator(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,10}")) textField.setText(oldValue);
        });
    }

}
