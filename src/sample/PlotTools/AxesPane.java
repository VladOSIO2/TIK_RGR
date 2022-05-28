package sample.PlotTools;

import javafx.beans.binding.Bindings;
import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;

public class AxesPane extends Pane {
    private final NumberAxis xAxis;
    private final NumberAxis yAxis;
    private final double xCenter;
    private final double yCenter;

    public AxesPane(
            int width, int height,
            double xMin, double xMax, double xTickUnit,
            double yMin, double yMax, double yTickUnit
    ) {
        setPrefSize(width, height);
        yCenter = calcPos(yMin, yMax, height);
        xCenter = width - calcPos(xMin, xMax, width);

        xAxis = new NumberAxis(xMin, xMax, xTickUnit);
        xAxis.setMinorTickVisible(false);
        xAxis.setPrefWidth(width);
        if (yCenter < 0.8 * height) {
            xAxis.setSide(Side.BOTTOM);
            xAxis.setLayoutY(yCenter);
        } else {
            xAxis.setSide(Side.TOP);
            xAxis.layoutYProperty().bind(Bindings.subtract(yCenter + 1, xAxis.heightProperty()));
        }

        yAxis = new NumberAxis(yMin, yMax, yTickUnit);
        yAxis.setMinorTickVisible(false);
        yAxis.setPrefHeight(height);
        if (xCenter < 0.8 * width) {
            yAxis.setSide(Side.RIGHT);
            yAxis.setLayoutX(xCenter);
        } else {
            yAxis.setSide(Side.LEFT);
            yAxis.layoutXProperty().bind(Bindings.subtract(xCenter + 1, yAxis.widthProperty()));
        }

        getChildren().setAll(xAxis, yAxis);
    }

    private boolean isZeroInRange(double n1, double n2) {
        return Math.signum(n1) != Math.signum(n2);
    }

    //calculating axis position,
    //to make axes cross in (0; 0) point, if possible
    private double calcPos(double min, double max, int range) {
        if (isZeroInRange(min, max)) {
            return range * (max / Math.abs(min - max));
        }
        return range;
    }

    //mapping x to pane coordinates
    public double mapX(double x) {
        double sx = this.getPrefWidth() /
                (xAxis.getUpperBound() -
                        xAxis.getLowerBound());
        return x * sx + xCenter;
    }

    //mapping y to pane coordinates
    public double mapY(double y) {
        double sy = this.getPrefHeight() /
                (yAxis.getUpperBound() -
                        yAxis.getLowerBound());
        return -y * sy + yCenter;
    }
}