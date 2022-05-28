package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.PlotTools.AxesPane;
import sample.PlotTools.PointedPlot;

import java.util.function.Function;

public class Controller {
    @FXML private TextField textField_a;
    @FXML private TextField textField_b;
    @FXML private TextField textField_xMin;
    @FXML private TextField textField_xMax;
    @FXML private TextField textField_dx;

    private final StringBuilder errorBuffer = new StringBuilder();

    @FXML
    private void buildPlotCFCA() {
        buildPlot(getFunctionCFCA(5, 5));
    }

    @FXML
    private void buildPlotCFDA() {

    }

    @FXML
    private void buildPlotDFCA() {

    }

    @FXML
    private void buildPlotDFDA() {

    }

    private static void buildPlot(Function<Double, Double> f) {
        double xMin = -3;
        double xMax = 6;
        AxesPane axesPane = new AxesPane(
                450, 450,
                xMin, xMax, 1,
                -8, 6, 1
        );

        PointedPlot plot = new PointedPlot(
                f,
                xMin, xMax, 0.001,
                axesPane
        );

        StackPane layout = new StackPane(
                plot
        );
        layout.setPadding(new Insets(20));
        Stage stage = new Stage();
        stage.setScene(new Scene(layout));
        stage.show();
    }

    private static Function<Double, Double> getFunctionCFCA(double a, double b) {
        return x -> Math.pow(Math.E, Math.abs(Math.sin(3*a*x + b)));
    }

    private static void showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Помилка");
        alert.setContentText(text);
        alert.show();
    }

    private Double getDoubleFromTF(TextField tf, String tfName) {
        Double res = null;
        try {
            res = Double.parseDouble(tf.getText());
        } catch (NumberFormatException e) {
            errorBuffer.append(
                    "Змінна %s: неможливо отримати число з рядку %s"
                            .formatted(tfName, tf.getText())
            );
        }
        return res;
    }
}
