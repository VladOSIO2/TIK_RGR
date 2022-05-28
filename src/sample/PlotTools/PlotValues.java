package sample.PlotTools;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PlotValues {
    private Map<Double, Double> points;
    private double xMin;
    private double xMax;
    private double dx;
    private double yMin;
    private double yMax;

    public PlotValues(
            Function<Double, Double> f,
            double xMin, double xMax,
            double dx
    ) {
        this.points = new HashMap<>();
        this.xMin = xMin;
        this.xMax = xMax;
        this.dx = dx;
    }

    public double getyMin() {
        return yMin;
    }

    public double getyMax() {
        return yMax;
    }
}
