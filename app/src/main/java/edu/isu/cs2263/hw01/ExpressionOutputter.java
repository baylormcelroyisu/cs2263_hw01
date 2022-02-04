package edu.isu.cs2263.hw01;

/**
 * A Simple class to implement the Output Interface, nothing fancy
 *
 * @author Baylor McElroy
 */
public class ExpressionOutputter implements Output {

    @Override
    public void outputStd(String result) {
        System.out.printf("  -> %s\n", result);
    }
    @Override
    public void outputFile(String result, String location) {
    //    File outFile = new File(location);

    }


}