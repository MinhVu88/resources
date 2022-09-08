package com.pluralsight.slidesexceptiontypes;

public class Main {

    public static void main(String[] args) {
        multipleCatches_WithException();
        exceptionThrown_CaughtInDifferentMethod();
    }

    private static void multipleCatches_WithException() {
        System.out.println("***************************");
        System.out.println("Try with multiple catch blocks");
        System.out.println();

        int i = 12;
        int j = 2;

        try {
            System.out.println("Performing operation - i = " + i + " j = " + j);
            int result = i / (j - 2);
            System.out.println(result);
        } catch (ArithmeticException ex) {
            System.out.println("Invalid math operation - " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        System.out.println("***************************");
        System.out.println();
    }

    private static void exceptionThrown_CaughtInDifferentMethod() {
        System.out.println("***************************");
        System.out.println("Exception thrown in one method caught in different method");
        System.out.println();

        methodA();

        System.out.println("***************************");
        System.out.println();

    }

    private static void methodA() {
        System.out.println("In methodA");
        try {
            methodB();
        } catch(Exception ex) {
            System.out.println("Catching exception in methodA, "  + ex.getMessage());
        }
    }

    private static void methodB() {
        System.out.println("In methodB");

        methodC();
    }

    private static void methodC() {
        System.out.println("In methodC");

        System.out.println("Causing a divide-by-zero exception within methodC");
        int i = 10 / 0;

    }


}
