package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.PlotTools.AxesPane;
import sample.PlotTools.PointedPlot;

import java.util.Objects;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("РГР_ТІК_Ошовський_В_АС201");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
/*
    @Override
    public void start(final Stage stage) {
        double xMin = -3;
        double xMax = 6;
        AxesPane axesPane = new AxesPane(
                450, 450,
                xMin, xMax, 1,
                -8, 6, 1
        );

        PointedPlot plot = new PointedPlot(
                x -> 1 / x,
                xMin, xMax, 0.001,
                axesPane
        );

        StackPane layout = new StackPane(
                plot
        );
        layout.setPadding(new Insets(20));

        stage.setScene(new Scene(layout));
        stage.show();
    }*/
}