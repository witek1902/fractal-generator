package pl.witkowski.fractal.generator.function;

public class FunctionProvider {

    /**
     * Instead IF in this method we should have inject (with Dependency Injection) all implementations of this interface and get proper based on parameter (functionId for example).
     *
     * @param functionId
     * @param maxCount
     * @param shouldSmooth
     * @return
     */
    public static Function get(int functionId, int maxCount, boolean shouldSmooth) {
        return functionId == 1 ? new PhoenixFunction(maxCount, shouldSmooth) : new MandelbrotFunction(maxCount, shouldSmooth);
    }
}
