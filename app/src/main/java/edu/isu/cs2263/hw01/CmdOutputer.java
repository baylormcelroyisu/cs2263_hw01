package edu.isu.cs2263.hw01;

/**
 * Outputs to the command line
 *
 * @author Baylor McElroy
 */
public class CmdOutputer {

    /**
     * Outputs result to the command line
     * @param result the result of the expression that was evaluated
     */

    public void output(String result) {
        System.out.printf("  -> %s\n", result);
    }
}