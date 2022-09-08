package com.pluralsight.improperexceptionuse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    /*
    ********************************************************************************
    * REMEMBER: The code in this project demonstrates a WRONG WAY to use exceptions.
    *
    * The techniques in this program are provided as a demonstration for educational
    * purposes. Although I have seen these techniques used in real applications
    * created by other developers, I do not use these techniques and neither should
    * you or anyone else.
    *
    * These code is provided to demonstrate usage scenarios tha should be AVOIDED.
     */
    public static void main(String[] args) {
        int result = 0;
        boolean done = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            while(!done) {
                try {
                    // Each line of the file is processed by calling processFile
                    // Work done within processFile calls another method which then
                    //  throws a specific exception to indicate what operation to
                    //  perform. The code then branches to do that work by handling
                    //  the exception. This is a really BAD technique
                    processFile(reader);
                    done = true;
                } catch (ProcessAddException ex) {
                    result = doAdd(ex);
                } catch (ProcessSubtractException ex) {
                    result = doSubtract(ex);
                } catch (ProcessMultiplyException ex) {
                    result = doMultiply(ex);
                } catch (ProcessDivideException ex) {
                    result = doDivide(ex);
                }

                if(!done)
                    System.out.println(result);
            }
        } catch(FileNotFoundException ex) {
            System.out.println("File not found: " + args[0]);
        } catch(IOException ex) {
            System.out.println("Error reading file - " + ex.getMessage());
        } catch(Exception ex) {
            System.out.println("Error processing file - " + ex.getMessage());
        }
    }

    private static void processFile(BufferedReader reader)
            throws IOException,ProcessAddException, ProcessSubtractException,
            ProcessMultiplyException, ProcessDivideException {
        String inputLine = null;
        int result = 0;
        if((inputLine = reader.readLine()) != null) {
            System.out.print(inputLine + " = ");
            processInput(inputLine);
        }
    }

    private static void processInput(String inputLine)
            throws ProcessAddException, ProcessSubtractException, ProcessMultiplyException, ProcessDivideException {
        String[] parts = inputLine.split(" ");
        MathOperation operation = MathOperation.valueOf(parts[0].toUpperCase());
        int leftVal = valueFromWord(parts[1]);
        int rightVal = valueFromWord(parts[2]);

        // The switch statement throws a specific exception to indicate what operation
        //  to perform. The exception will then exit this method (processInput), it'll
        //  then also cause processFile to be unwound until the code reaches the catch
        //  block in main.
        //
        // This is a bad technique because it undermines the normal behavior of methods
        //  which are called and then exit in a distinct and predictable manner.
        //
        // In addition this code is using the exceptions as a way to carry standard
        //  application data (leftVal and rightVal) out of this method back up to the
        //  main method. Such standard app data should be carried through proper
        //  mechanisms such as method return values or parameters.
        switch(operation) {
            case ADD:
                throw new ProcessAddException(leftVal, rightVal);
            case SUBTRACT:
                throw new ProcessSubtractException(leftVal, rightVal);
            case MULTIPLY:
                throw new ProcessMultiplyException(leftVal, rightVal);
            case DIVIDE:
                throw new ProcessDivideException(leftVal, rightVal);
        }

    }
    private static int doAdd(ProcessAddException ex) {
        int leftVal = ex.getLeftVal();
        int rightVal = ex.getRightVal();

        return leftVal + rightVal;
    }

    private static int doSubtract(ProcessSubtractException ex) {
        int leftVal = ex.getLeftVal();
        int rightVal = ex.getRightVal();

        return leftVal - rightVal;
    }

    private static int doMultiply(ProcessMultiplyException ex) {
        int leftVal = ex.getLeftVal();
        int rightVal = ex.getRightVal();

        return leftVal * rightVal;
    }

    private static int doDivide(ProcessDivideException ex) {
        int leftVal = ex.getLeftVal();
        int rightVal = ex.getRightVal();

        return leftVal / rightVal;
    }

    static int valueFromWord(String word) {
        String[] numberWords = {
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"
        };
        int value = -1;
        for(int index = 0; index < numberWords.length; index++) {
            if(word.equals(numberWords[index])) {
                value = index;
                break;
            }
        }
        if(value == -1d)
            value = Integer.parseInt(word);

        return value;
    }

}
