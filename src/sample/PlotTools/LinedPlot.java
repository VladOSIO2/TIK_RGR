package sample.PlotTools;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.Map;
import java.util.function.Function;

public class LinedPlot extends Pane {
    public LinedPlot(
            Function<Double, Double> f,
            double xMin, double xMax, double xInc,
            AxesPane axesPane
    ) {
        getChildren().setAll(axesPane);
        Path path = new Path();
        path.setStroke(Color.BLUE);
        path.setStrokeWidth(0.8);

        for (double x = xMin; x < xMax; x += xInc) {
            double y = f.apply(x);
            if (Double.isNaN(y) || Double.isInfinite(y)) {
                getChildren().add(path);
                path = new Path();
                path.setStroke(Color.BLUE);
                path.setStrokeWidth(0.8);
                continue;
            }
            if (!path.hasProperties()) {
                path.getElements().add(
                        new MoveTo(axesPane.mapX(x), axesPane.mapY(y))
                );
            }
            path.getElements().addAll(
                    new HLineTo(axesPane.mapX(x)),
                    new VLineTo(axesPane.mapY(y))
            );
        }

        setPrefSize(axesPane.getPrefWidth(), axesPane.getPrefHeight());
        getChildren().setAll(path);
    }

    public LinedPlot(PlotValues plotValues, AxesPane axesPane) {
        getChildren().setAll(axesPane);
        Path path = new Path();
        path.setStroke(Color.BLUE);
        path.setStrokeWidth(0.8);

        for (Map.Entry<Double, Double> plotPoint : plotValues.getPoints().entrySet()) {
            double x = plotPoint.getKey();
            double y = plotPoint.getValue();

            if (Double.isNaN(y) || Double.isInfinite(y)) {
                getChildren().add(path);
                path = new Path();
                path.setStroke(Color.BLUE);
                path.setStrokeWidth(0.8);
                continue;
            }
            if (path.getElements().isEmpty()) {
                path.getElements().add(
                        new MoveTo(axesPane.mapX(x), axesPane.mapY(y))
                );
            } else {
                path.getElements().addAll(
                        new HLineTo(axesPane.mapX(x)),
                        new VLineTo(axesPane.mapY(y))
                );
            }
        }
        path.getElements().add(
                new HLineTo(axesPane.mapX(plotValues.getXMax()))
        );
        getChildren().add(path);
    }
}
