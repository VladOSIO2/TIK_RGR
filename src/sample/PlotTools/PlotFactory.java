package sample.PlotTools;

import javafx.scene.layout.Pane;
import sample.FunctionTools.FunctionFactory;
import sample.FunctionTools.FunctionType;

public class PlotFactory {
    public static Pane getPlot(
            FunctionType type, Double a, Double b,
            Double dx, Double dy, Double absYMax,
            Double xMin, Double xMax
    ) {
        PlotValues plotValues;
        switch (type) {
            case CFDA, DFDA -> {
                plotValues = new PlotValues(
                        FunctionFactory.getFunction(type, a, b, dy, absYMax),
                        xMin, xMax, dx
                );
                return getDiscreteArgPlot(plotValues, dy);
            }
            case CFCA, DFCA -> {
                plotValues = new PlotValues(
                        FunctionFactory.getFunction(type, a, b, dy, absYMax),
                        xMin, xMax, 0.0005
                );
                return getContinuousArgPlot(plotValues, dy);
            }
        }
        return null;
    }

    private static Pane getContinuousArgPlot(PlotValues plotValues, Double dy) {
        AxesPane axesPane = new AxesPane(
                450, 450,
                plotValues.getXMin(), plotValues.getXMax(), 1,
                plotValues.getYMin(), plotValues.getYMax(),
                dy == 0.0D ? plotValues.getYDiff() / 15 : dy
        );

        return new PointedPlot(plotValues, axesPane);
    }

    private static Pane getDiscreteArgPlot(PlotValues plotValues, Double dy) {
        AxesPane axesPane = new AxesPane(
                450, 450,
                plotValues.getXMin(), plotValues.getXMax(), 1,
                plotValues.getYMin(), plotValues.getYMax(),
                dy == 0.0D ? plotValues.getYDiff() / 15 : dy
        );
        return new LinedPlot(plotValues, axesPane);
    }
}
