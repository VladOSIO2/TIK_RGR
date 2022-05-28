package sample.PlotTools;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
            Circle point = new Circle(0.4, Color.BLUE);
            point.setCenterX(axesPane.mapX(x));
            point.setCenterY(axesPane.mapY(y));
            points.add(point);
        }

        setPrefSize(axesPane.getPrefWidth(), axesPane.getPrefHeight());
        getChildren().setAll(axesPane);
        getChildren().addAll(points);
    }

    public PointedPlot(PlotValues plotValues, AxesPane axesPane) {
        List<Circle> points = new ArrayList<>();
        for (Map.Entry<Double, Double> plotPoint : plotValues.getPoints().entrySet()) {
            Circle point = new Circle(0.4, Color.BLUE);
            point.setCenterX(axesPane.mapX(plotPoint.getKey()));
            point.setCenterY(axesPane.mapY(plotPoint.getValue()));
            points.add(point);
        }

        setPrefSize(axesPane.getPrefWidth(), axesPane.getPrefHeight());
        getChildren().setAll(axesPane);
        getChildren().addAll(points);
    }
}
