/*
 * Individual Project Assignment Part A. Coding Bootcamp 8 . Java Part Time. 
 * Author: Georgios Giagkas
 * Project title: Implementation of a private school structure.
 */
package individualprojectparta;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author giagkas
 */
public class IndividualProjectPartA {

    public static void main(String[] args) {

        ArrayList<Course> listOfCourses = new ArrayList<>();
        ArrayList<Student> listOfStudents = new ArrayList<>();
        ArrayList<Trainer> listOfTrainers = new ArrayList<>();
        ArrayList<Assignment> listOfAssignments = new ArrayList<>();
        ArrayList<CourseDetails> listOfCourseDetails = new ArrayList<>();
        ArrayList<AssignmentsPerStudent> listOfAssignmentsPerStudent = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;//quit---> exit while loop
        boolean proceed = false;//proceed to main menu. (either using synthetic data or not) 

        while (!quit) {
            Menu.printIntro();

            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid action...\n");
                continue;
            }

            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    //quit
                    quit = true;
                    break;
                case 1:
                    //no synthetic data
                    proceed = true;
                    quit = true;
                    break;
                case 2:
                    //use synthetic data
                    SyntheticData.addSyntheticData(listOfStudents, listOfCourses, listOfAssignments, listOfTrainers, listOfCourseDetails, listOfAssignmentsPerStudent);
                    proceed = true;
                    quit = true;
                    break;
                default:
                    System.out.println("Ivalid input!");
                    break;
            }

        }

        if (proceed) {
            //call mainMenu method
            MenuScanner.mainMenu(listOfStudents, listOfCourses, listOfAssignments, listOfTrainers, listOfCourseDetails, listOfAssignmentsPerStudent);
        }

    }

}
