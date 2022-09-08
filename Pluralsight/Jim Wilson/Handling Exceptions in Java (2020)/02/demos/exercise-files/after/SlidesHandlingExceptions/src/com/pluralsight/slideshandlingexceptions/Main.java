package com.pluralsight.slideshandlingexceptions;

public class Main {

    public static void main(String[] args) {
        simpleTryCatch_NoException();
        simpleTryCatch_WithException();

    }

    private static void simpleTryCatch_NoException() {
        System.out.println("***************************");
        System.out.println("Try/Catch with no exception thrown");
        System.out.println();

        int i = 12;
        int j = 5;

        try {
            System.out.println("Performing operation, i = " + i + ", j = " + j);
            int result = i / (j - 2);
            System.out.println(result);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            //ex.printStackTrace();
        }
        System.out.println("***************************");
        System.out.println();

    }

    private static void simpleTryCatch_WithException() {
        System.out.println("***************************");
        System.out.println("Try/Catch with an exception thrown");
        System.out.println();

        int i = 12;
        int j = 2;

        try {
            System.out.println("Performing operation, i = " + i + ", j = " + j);
            int result = i / (j - 2);
            System.out.println(result);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            //ex.printStackTrace();
        }

        System.out.println("***************************");
        System.out.println();
    }

}
