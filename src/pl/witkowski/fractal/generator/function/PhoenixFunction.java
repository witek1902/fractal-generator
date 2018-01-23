package pl.witkowski.fractal.generator.function;

public class PhoenixFunction extends AbstractFunction implements Function {

    public PhoenixFunction(int maxCount, boolean shouldSmooth) {
    super(maxCount, shouldSmooth);
    }

    @Override
    public int calc(double zr, double zi, double cr, double ci) {
        double pr = zr * zr, pi = zi * zi;
        double sr = 0.0, si = 0.0;
        double zm = 0.0;
        int count = 0;
        while (pr + pi < 4.0 && count < maxCount) {
            zm = pr + pi;
            pr = pr - pi + ci * sr + cr;
            pi = 2.0 * zr * zi + ci * si;
            sr = zr;
            si = zi;
            zr = pr;
            zi = pi;
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
