package edu.isu.cs2263.hw01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Outputs the expression and result to a text file
 *
 * @author Baylor McElroy
 */

public class FileOutputer {

    private boolean first;
    private final String outputLoc;
    private FileWriter fw;

    /**
     * Constructor that initializes the output location and creates a text file if necessary
     *
     * @param outputLoc
     */

    public FileOutputer(String outputLoc){
        first = true;
        this.outputLoc = outputLoc;
        try{
            //create file if it doesnt exits
            File outFile = new File(outputLoc);
            if(outFile.createNewFile()){
                //tells user the file had to be created
                System.out.println("File created: " + outFile.getName());
            }
            else{
                //inform user that output has been found
                System.out.println(outFile.getName() + "file found");
            }
            fw = new FileWriter(outputLoc);
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Adds the expression and result to the file
     * @param result result of the input expression
     * @param expr the input expression
     */
    public void output(String result, String expr) {
        try {
            //add to file
            fw.append(expr).append("\n  > + ").append(result).append("\n");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Closes the open output file
     */
    public void closeFile(){
        try{
            fw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
