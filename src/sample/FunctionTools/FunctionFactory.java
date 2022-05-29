package sample.FunctionTools;

import java.util.function.Function;

public class FunctionFactory {
    public static Function<Double, Double> getFunction(
            FunctionType type, double a, double b,
            double dy, double absYMax
    ) {
        return switch (type) {
            case CFCA -> getFunctionCFCA(a, b, absYMax);
            case CFDA -> getFunctionCFDA(a, b, absYMax);
            case DFCA -> getFunctionDFCA(a, b, absYMax, dy);
            case DFDA -> getFunctionDFDA(a, b, absYMax, dy);
        };
    }

    private static Function<Double, Double> getFunctionCFCA(double a, double b, double absYMax) {
        return x -> {
            double result = Math.pow(Math.E, Math.abs(Math.sin(3*a*x + b))) *
                    x / Math.pow(Math.sqrt(a*x) + b*x*x, 3);
            return Math.abs(result) > absYMax ? Double.NaN : result;
        };
    }

    private static Function<Double, Double> getFunctionCFDA(double a, double b, double absYMax) {
        return x -> getFunctionCFCA(a, b, absYMax).apply(x);
    }

    private static Function<Double, Double> getFunctionDFCA(double a, double b, double absYMax, double dy) {
        return x -> {
            double xi = getFunctionCFCA(a, b, absYMax).apply(x);
            return Double.isNaN(xi) ? Double.NaN : Math.round(xi / dy) * dy;
        };
    }

    private static Function<Double, Double> getFunctionDFDA(double a, double b, double absYMax, double dy) {
        return x -> {
            double xi = getFunctionCFCA(a, b, absYMax).apply(x);
            return Double.isNaN(xi) ? Double.NaN : Math.round(xi / dy) * dy;
        };
    }


}
