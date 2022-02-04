package edu.isu.cs2263.hw01;

import java.io.*;
import java.util.List;

/**
 * A simple app to explore some aspects of Java
 *
 * @author Isaac D Griffith
 */
public class Evaluator {

    /**
     * Main method which handles the main execution loop
     */
    public static void runEvaluatorCmd() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Output out = new ExpressionOutputter();
        while (true) {
            System.out.print("> ");
            try{
                String line = br.readLine();
                int result = processExpression(line);
                out.outputStd(Integer.toString(result));
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    public static void runEvaluatorBat(String fileName) {
      try {
          BufferedReader br = new BufferedReader(new FileReader(fileName));

          Output out = new ExpressionOutputter();
          String line;

          while ((line = br.readLine()) != null) {
              System.out.print("> " + line + "\n");
              int result = processExpression(line);
              out.outputStd(Integer.toString(result));
          }
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
    }

    /**
     * Simple method to process the expressions and return a result
     * @param expr The input string from the user (assumed to be good input)
     * @return The value of the expression
     */
    public static int processExpression(String expr) {
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