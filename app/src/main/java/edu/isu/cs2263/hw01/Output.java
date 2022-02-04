package edu.isu.cs2263.hw01;

/**
 * An interface defining what is needed to display the output
 *
 * @author Baylor McElroy
 */
public interface Output {
    void outputStd(String result);
    void outputFile(String result, String location);
}