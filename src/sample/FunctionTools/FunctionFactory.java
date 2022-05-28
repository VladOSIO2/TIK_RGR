package sample.FunctionTools;

import java.util.function.Function;

public class FunctionFactory {
    public static Function<Double, Double> getFunction(FunctionType type, double a, double b) {
        return switch (type) {
            case CFCA -> getFunctionCFCA(a, b);
            case DFCA -> getFunctionDFCA(a, b);
            case CFDA -> getFunctionCFDA(a, b);
            case DFDA -> getFunctionDFDA(a, b);
        };
    }

    private static Function<Double, Double> getFunctionCFCA(double a, double b) {
        return x -> Math.pow(Math.E, Math.abs(Math.sin(3*a*x + b)));
    }

    private static Function<Double, Double> getFunctionDFDA(double a, double b) {
        return x -> Math.pow(Math.E, Math.abs(Math.sin(3*a*x + b)));
    }
    private static Function<Double, Double> getFunctionCFDA(double a, double b) {
        return x -> Math.pow(Math.E, Math.abs(Math.sin(3*a*x + b)));
    }
    private static Function<Double, Double> getFunctionDFCA(double a, double b) {
        return x -> Math.pow(Math.E, Math.abs(Math.sin(3*a*x + b)));
    }
}
