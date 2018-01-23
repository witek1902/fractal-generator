package pl.witkowski.fractal.generator.function;

public class MandelbrotFunction extends AbstractFunction implements Function {

    public MandelbrotFunction(int maxCount, boolean shouldSmooth) {
        super(maxCount, shouldSmooth);
    }

    @Override
    public int calc(double zr, double zi, double cr, double ci) {
        double pr = zr * zr, pi = zi * zi;
        double zm = 0.0;
        int count = 0;
        while (pr + pi < 4.0 && count < maxCount) {
            zm = pr + pi;
            zi = 2.0 * zr * zi + ci;
            zr = pr - pi + cr;
            pr = zr * zr;
            pi = zi * zi;
            count++;
        }
        if (count == 0 || count == maxCount) {
            return 0;
        }
        zm += 0.000000001;
        return 256 * count + calcSmoothParam(pr, pi, zm);
    }


}
