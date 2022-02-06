package edu.isu.cs2263.hw01;

import java.io.*;
import java.util.List;

/**
 * Runs the evaluation of the expressions. Can handle two types of input (file and system.in)
 *
 * @author Baylor McELroy
 */
public class Evaluator {
    private final String outputLoc;
    private String fileName;
    private FileOutputer fileOut;

    /**
     * Constructor for a batch/text file reading
     * @param fileName name of input file
     * @param outputLoc location for output of results
     */

    public Evaluator(String fileName, String outputLoc) {
        this.outputLoc = outputLoc;
        this.fileName = fileName;
        if(!outputLoc.equals(":cmd")){
            fileOut = new FileOutputer(outputLoc);
        }
    }

    /**
     * Constructor for command line only input
     *
     * @param outputLoc location for output of results
     */
    public Evaluator(String outputLoc) {
        this.outputLoc = outputLoc;
        if(!outputLoc.equals(":cmd")){
            fileOut = new FileOutputer(outputLoc);
        }
    }

    /**
     * Evaluates expressions from command line infinitely
     */
    public void runEvaluatorCmd() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("> ");
            try{
                String line = br.readLine();
                //optional escape
                if(line.equals("x")){
                    if(!outputLoc.equals(":cmd")){
                        fileOut .closeFile();
                    }
                    System.exit(0);
                }
                //process then output expression
                int result = processExpression(line);
                outPutter(Integer.toString(result), line);
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    /**
     * Evaluates expressions from an input file
     */
    public void runEvaluatorBat() {
      try {
          BufferedReader br = new BufferedReader(new FileReader(fileName));
          String line;
          //evaluate all lines of the input file
          while ((line = br.readLine()) != null) {
              System.out.print("> " + line + "\n");
              int result = processExpression(line);
              outPutter(Integer.toString(result), line);
          }

      }
      catch (FileNotFoundException e) {
          System.out.println("Please enter an absolute file location or a path relative to your directory");
      }
      catch (IOException e){
          e.printStackTrace();
      }
      fileOut.closeFile();
    }

    /**
     * Outputs expressions to the command line and file if appropriate
     * @param result the integer result of the expression
     * @param expr the initial expression to add to the file if necessary
     */

    public void outPutter(String result, String expr){
        //send output to command line
        CmdOutputer out = new CmdOutputer();
        out.output(result);
        //send outputs to the file
        if(!outputLoc.equals(":cmd")){
            fileOut.output(result, expr);
        }
    }




    /**
     * Simple method to process the expressions and return a result
     * @param expr The input string from the user (assumed to be good input)
     * @return The value of the expression
     * @author Isaac D Griffith
     */
    public int processExpression(String expr) {
        int result = 0;
        String[] components = expr.split(" ");
        List<String> operators = List.of("+", "-", "/", "*");
        String operator = "";
        for (String item : components) {
            if (!operators.contains(item)) {
                switch(operator) {
                    case "+" -> result += Integer.parseInt(item);
                    case "-" -> result -= Integer.parseInt(item);
                    case "/" -> result /= Integer.parseInt(item);
                    case "*" -> result *= Integer.parseInt(item);
                    default -> result = Integer.parseInt(item);
                }
            } else {
                operator = item;
            }
        }

        return result;
    }
}