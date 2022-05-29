package sample.PlotTools;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

public class PlotValues {
    private final Map<Double, Double> points;
    private final double xMin;
    private final double xMax;
    private final double xInc;
    private double yMin;
    private double yMax;

    public PlotValues(
            Function<Double, Double> f,
            double xMin, double xMax,
            double xInc
    ) {
        this.points = new TreeMap<>();
        this.xMin = xMin;
        this.xMax = xMax;
        this.xInc = xInc;
        this.yMin = 0;
        this.yMax = 0;

        for(double x = xMin; x < xMax; x += xInc) {
            Double y = f.apply(x);
            if (y != null) {
                if (yMin > y) yMin = y;
                if (yMax < y) yMax = y;
                points.put(x, y);
            }
        }
    }

    public double getXMin() {
        return xMin;
    }

    public double getXMax() {
        return xMax;
    }

    public double getXInc() {
        return xInc;
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

    public double getYDiff() {
        return Math.abs(yMax - yMin);
    }
}
