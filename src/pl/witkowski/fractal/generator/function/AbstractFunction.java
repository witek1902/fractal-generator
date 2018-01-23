package pl.witkowski.fractal.generator.function;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractFunction {

    protected final int maxCount;
    protected final boolean shouldSmooth;

    protected int calcSmoothParam(double pr, double pi, double zm) {
        return shouldSmooth ? (int) (255.0 * Math.log(4.0 / zm) / Math.log((pr + pi) / zm)) : 0;
    }
}
