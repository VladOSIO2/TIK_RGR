package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.FunctionTools.FunctionFactory;
import sample.FunctionTools.FunctionType;
import sample.PlotTools.AxesPane;
import sample.PlotTools.PlotFactory;
import sample.PlotTools.PlotValues;
import sample.PlotTools.PointedPlot;

public class Controller {
    @FXML private TextField textField_a;
    @FXML private TextField textField_b;
    @FXML private TextField textField_xMin;
    @FXML private TextField textField_xMax;
    @FXML private TextField textField_dx;
    @FXML private TextField textField_dy;
    @FXML private TextField textField_yMax;

    private final StringBuilder errorBuffer = new StringBuilder();



    @FXML
    private void buildPlotCFCA() {
        buildPlot(FunctionType.CFCA);
    }

    @FXML
    private void buildPlotCFDA() {
        buildPlot(FunctionType.CFDA);
    }

    @FXML
    private void buildPlotDFCA() {
        buildPlot(FunctionType.DFCA);
    }

    @FXML
    private void buildPlotDFDA() {
        buildPlot(FunctionType.DFDA);
    }

    private void buildPlot(FunctionType type) {
        Double xMin = getDoubleFromTF(textField_xMin, "xMin");
        Double xMax = getDoubleFromTF(textField_xMax, "xMax");
        Double a = getDoubleFromTF(textField_a, "a");
        Double b = getDoubleFromTF(textField_b, "b");
        Double dx = 0.0D, dy = 0.0D;
        Double absYMax = getDoubleFromTF(textField_yMax, "|yMax|");
        if (xMax != null && xMin != null && xMax < xMin) {
            errorBuffer.append("xMax має бути більшим за xMin\n");
        }
        if (type.equals(FunctionType.CFDA) || type.equals(FunctionType.DFDA)) {
            //для графіків квантованих за часом має бути визначений dx
            dx = getDoubleFromTF(textField_dx, "dx");
            if (dx != null && dx <= 0) {
                errorBuffer.append("dx має бути більшим за 0!\n");
            }
        }
        if (type.equals(FunctionType.DFCA) || type.equals(FunctionType.DFDA)) {
            //для графіків квантованих за аргументом має бути визначений dy
            dy = getDoubleFromTF(textField_dy, "dy");
            if (dy != null && dy <= 0) {
                errorBuffer.append("dy має бути більшим за 0!\n");
            }
        }
        if (!errorBuffer.isEmpty()) {
            showError();
            return;
        }
        if (xMin != null && xMax != null && dy != null) {
            Pane plot = PlotFactory.getPlot(type, a, b, dx, dy, absYMax, xMin, xMax);
            StackPane layout = new StackPane(plot);
            layout.setPadding(new Insets(20));
            Stage stage = new Stage();
            stage.setScene(new Scene(layout));
            stage.show();
        }
    }



    private void showError() {
        if (errorBuffer.isEmpty()) return;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Помилка");
        alert.setContentText(errorBuffer.toString());
        errorBuffer.setLength(0);
        alert.show();
    }

    private Double getDoubleFromTF(TextField tf, String tfName) {
        Double res = null;
        try {
            res = Double.parseDouble(tf.getText());
        } catch (NumberFormatException e) {
            errorBuffer.append(
                    "Змінна %s: неможливо отримати число з рядку %s\n"
                            .formatted(tfName, tf.getText())
            );
        }
        return res;
    }

    private static boolean notNull(Object... os) {
        for (Object o : os) {
            if (o == null) {
                return false;
            }
        }
        return true;
    }
}
