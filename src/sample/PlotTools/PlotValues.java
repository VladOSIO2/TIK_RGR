package sample.PlotTools;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
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
        this.points = new TreeMap<>();
        this.xMin = xMin;
        this.xMax = xMax;
        this.dx = dx;
        this.yMin = 0;
        this.yMax = 0;

        for(double x = xMin; x < xMax; x += dx) {
            double y = f.apply(x);
            if (yMin > y) yMin = y;
            if (yMax < y) yMax = y;
            points.put(x, y);
        }
    }

    public double getXMin() {
        return xMin;
    }

    public double getXMax() {
        return xMax;
    }

    public double getDx() {
        return dx;
    }

    public double getYMin() {
        return yMin;
    }

    public double getYMax() {
        return yMax;
    }

    public Map<Double, Double> getPoints() {
        return new TreeMap<>(points);
    }
}
