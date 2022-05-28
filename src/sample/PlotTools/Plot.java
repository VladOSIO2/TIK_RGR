package sample.PlotTools;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.function.Function;

public class Plot extends Pane {
    public Plot(
            Function<Double, Double> f,
            double xMin, double xMax, double xInc,
            AxesPane axesPane
    ) {
        Path path = new Path();
        path.setStroke(Color.BLUE);
        path.setStrokeWidth(2);

        double x = xMin;
        double y = f.apply(x);

        path.getElements().add(
                new MoveTo(axesPane.mapX(x), axesPane.mapY(y))
        );
        x += xInc;

        for (; x < xMax; x += xInc) {
            y = f.apply(x);
            if (Double.isNaN(y)) {
                path.getElements().add(
                        new MoveTo(axesPane.mapX(x), axesPane.mapY(y))
                );
            }
            path.getElements().add(
                    new LineTo(axesPane.mapX(x), axesPane.mapY(y))
            );
        }

        setPrefSize(axesPane.getPrefWidth(), axesPane.getPrefHeight());
        getChildren().setAll(axesPane, path);
    }


}
