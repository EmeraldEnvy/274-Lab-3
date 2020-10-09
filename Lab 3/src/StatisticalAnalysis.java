/**
 * Alexander Pham
 * September 17, 2019
 * Reads from a file and sorts the integers, then displays an output depending on what the user wants
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class StatisticalAnalysis {
    public static void main(String[] args) {
        ArrayList<Integer> gradeList = populateGrades();
        gradeList = sortList( gradeList );
        while (true) {
            displayMenu();
            System.out.print("What would you like to do? : ");
            int userNumber = CheckInput.getInt();
            //Limits the user to enter a number in the range of the menu
            while ( userNumber<1 || userNumber>6 ){
                System.out.print( "Invalid, try again : " );
                userNumber = CheckInput.getInt();
            }
            //Display grades
            if ( userNumber == 1 ){
                displayGrades(gradeList);
            }
            //Display sum of grades
            if ( userNumber == 2 ){
                double gradesSum = sumGrades( gradeList );
                double averageGrade = gradesSum / gradeList.size();
                System.out.printf( "The average grade is %.4s" , averageGrade);
                System.out.println();
            }
            //Display highest grade
            if ( userNumber == 3 ){
                System.out.println( "The maximum grade is " + gradeList.get( gradeList.size()-1 ) );
            }
            //Display lowest grade
            if ( userNumber == 4 ){
                System.out.println( "The minimum grade is " + gradeList.get(0) );
            }
            //Display the median grade
            if ( userNumber == 5 ){
                double medianGrade = findMedian( gradeList );
                System.out.printf( "The median grade is %.4s" , medianGrade);
                System.out.println();
            }
            //Quit
            if ( userNumber == 6 ){
                System.out.println( "Later loser" );
                break;
            }
        }

    }


    /**
     * Populates the ArrayList
     * @return
     */
    public static ArrayList populateGrades() {
        ArrayList<Integer> gradeList = new ArrayList<Integer>();
        try {
            Scanner read = new Scanner(new File("grades.txt"));
            do {
                int line = read.nextInt();
                gradeList.add(line);
            } while (read.hasNext());
        } catch (FileNotFoundException fnf) {
            System.out.println("File was not found");
        }
        return gradeList;
    }

    /**
     * Displays the grades in rows of 10
     * @param gradeList
     */
    public static void displayGrades( ArrayList <Integer> gradeList){
        for ( int i = 0 ; i < gradeList.size() ; i++ ){
            System.out.print( gradeList.get(i) + " ");
            if ( i % 10 == 9 ){
                System.out.println();
            }
        }
        System.out.print("");
    }

    /**
     * Sorts the list from lowest to highest
     * @param gradeList
     * @return
     */
    public static ArrayList sortList( ArrayList<Integer> gradeList ) {
        //Selection Sort
        for (int i = 0; i < gradeList.size(); i++) {
            int lowest = i;
            for (int j = i + 1; j < gradeList.size(); j++) {
                if (gradeList.get(j) < gradeList.get(lowest)) {
                    lowest = j;
                }
            }
            int swap = gradeList.get(i);
            gradeList.set( i , gradeList.get( lowest ) );
            gradeList.set( lowest , swap );
        }
        return gradeList;
    }

    /**
     * Finds the sum of all the grades
     * @param gradeList
     * @return
     */
    public static int sumGrades( ArrayList<Integer> gradeList){
        int gradesSum = 0;
        for (int i = 0 ; i < gradeList.size() ; i++ ){
            gradesSum = gradesSum + gradeList.get(i);
        }
        return gradesSum;
    }

    /**
     * Finds the median
     * @param gradeList
     * @return
     */
    public static double findMedian( ArrayList<Integer> gradeList){
        double median = -1;
        //Find out if the list contains and odd or even amount of integers
        int listAmount = gradeList.size() % 2;
        //If the array list has an even amount
        if ( listAmount == 0 ){
            double lowerMedian = gradeList.get( (gradeList.size() / 2) - 1 );
            double higherMedian = gradeList.get( gradeList.size() / 2 );
            median = (lowerMedian + higherMedian) / 2;
        }
        //If the array list has an odd amount
        else if ( listAmount == 1 ){
            median = gradeList.get( gradeList.size() / 2 );
        }
        return median;
    }

    /**
     * Displays the menuteam
     */
    public static void displayMenu() {
        System.out.println("-------------------------Menu------------------------");
        System.out.println("1. Display Sorted Grades");
        System.out.println("2. Display Average Grade");
        System.out.println("3. Display Maximum Grade");
        System.out.println("4. Display Minimum Grade");
        System.out.println("5. Display Median Grade");
        System.out.println("6. Quit");
    }
}