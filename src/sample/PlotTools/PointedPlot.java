package sample.PlotTools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PointedPlot extends Pane {
    public PointedPlot(
            Function<Double, Double> f,
            double xMin, double xMax, double xInc,
            AxesPane axesPane
    ) {

        List<Circle> points = new ArrayList<>();
        for (double x = xMin; x < xMax; x += xInc) {
            double y = f.apply(x);
            Circle point = new Circle(1, Color.BLUE);
            point.setCenterX(axesPane.mapX(x));
            point.setCenterY(axesPane.mapY(y));
            points.add(point);
        }

        setPrefSize(axesPane.getPrefWidth(), axesPane.getPrefHeight());
        getChildren().setAll(axesPane);
        getChildren().addAll(points);
    }
}
