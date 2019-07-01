/*
 * The Class MenuScanner is used to aquire and validate input from user calling appropriate methods from Checker class.
 * To navigate through different menus: Main Menu,Course Menu, Student Menu, Assignment Menu,Trainer Menu
 * 
 */
package individualprojectparta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author giagkas
 */
public class MenuScanner {

    private MenuScanner() {
    }

    static Scanner scanner = new Scanner(System.in);

    public static void mainMenu(ArrayList<Student> studentList, ArrayList<Course> courseList,
            ArrayList<Assignment> assignmentList, ArrayList<Trainer> trainerList,
            ArrayList<CourseDetails> courseDetailsList,
            ArrayList<AssignmentsPerStudent> assignmentsPerStudentsList) {

        boolean quit = false;

        while (!quit) {
            Menu.printMainMenu();

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
                    System.out.println("Program termanating............");
                    quit = true;
                    break;
                case 1:
                    courseMenu(courseDetailsList, courseList);
                    break;
                case 2:
                    studentMenu(courseDetailsList, studentList, assignmentsPerStudentsList, courseList);
                    break;
                case 3:
                    assignmentMenu(courseDetailsList, assignmentsPerStudentsList, assignmentList, courseList, studentList);
                    break;
                case 4:
                    trainerMenu(courseDetailsList, trainerList, courseList);
                    break;
                case 5:
                    if (Checker.isCourseListEmpty(courseList)) {
                        break;
                    }
                    studentsToSubmitAssignment(assignmentsPerStudentsList);
                    break;
                default:
                    System.out.println("Invalid action.");
                    break;
            }

        }
    }

    public static Student inputNewStudentData() {
        String firstName = "No input";
        String lastName = "No input";
        LocalDate dateOfBirth = LocalDate.now();
        double tuitionFees = 0.0;

        String input = "";

        boolean quit = false;
        while (!quit) {
            System.out.println("Enter student's info all at once or one at a time?:\n"
                    + "0:\tAll at once.\n"
                    + "1:\tOne at a time\n");

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
                    System.out.println("Enter in order: first name, last name, date of birth(yyyy/mm/dd), tuition fees. Each field separated by comma:");
                    String userInput = scanner.nextLine();
                    String[] inputArray = userInput.split(",");
                    if (inputArray.length != 4) {
                        System.out.println("You do not enter a valid quantity of data!");
                        break;
                    }

                    if (Checker.isStringContainsLettersOnly(inputArray[0])) {
                        firstName = inputArray[0];
                    } else {
                        System.out.println("Invalid name input!");
                        break;
                    }

                    if (Checker.isStringContainsLettersOnly(inputArray[1])) {
                        lastName = inputArray[1];
                    } else {
                        System.out.println("Invalid name input");
                        break;
                    }

                    if (inputArray[2] != null && Checker.dateCheck(inputArray[2])) {
                        dateOfBirth = inputDate(inputArray[2]);
                    } else {
                        System.out.println("Invalid date!");
                        break;
                    }

                    if (inputArray[3].matches("^\\d+(\\.\\d+)?$")) {//regular expression to check double
                        tuitionFees = Double.parseDouble(inputArray[3]);
                    } else {
                        System.out.println("Invalid tuition fee input!");
                        break;
                    }
                    quit = true;
                    break;

                case 1:
                    System.out.println("Enter student's first name:");
                    input = scanner.nextLine();
                    if (Checker.isStringContainsLettersOnly(input)) {
                        firstName = input;
                    } else {
                        System.out.println("Invalid name input!");
                        break;
                    }

                    System.out.println("Enter student's last name:");
                    input = scanner.nextLine();
                    if (Checker.isStringContainsLettersOnly(input)) {
                        lastName = input;
                    } else {
                        System.out.println("Invalid name input!");
                        break;
                    }

                    System.out.println("Enter student's date of Birth (yyyy/mm/dd):");
                    String checkDate = scanner.nextLine();
                    if (Checker.dateCheck(checkDate)) {
                        dateOfBirth = inputDate(checkDate);
                    } else {
                        System.out.println("Invalid date.");
                        break;
                    }

                    System.out.println("Enter tuition fees:");
                    boolean hasNextDouble = scanner.hasNextDouble();
                    if (!hasNextDouble) {
                        System.out.println("Invalid input.");
                        break;
                    }
                    tuitionFees = scanner.nextDouble();
                    scanner.nextLine();
                    if (tuitionFees < 0) {
                        System.out.println("Negative value is invalid input!");
                        break;
                    }

                    quit = true;
                    break;
                default:
                    System.out.println("Invalid action...\n");
                    break;
            }

        }

        Student student = new Student(dateOfBirth, tuitionFees, firstName, lastName);
        return student;
    }

    public static Trainer inputNewTrainerData() {
        String firstName = "No input";
        String lastName = "No input";
        String subject = "No input";
        String input = "";

        boolean quit = false;
        while (!quit) {
            System.out.println("Enter trainer's info all at once or one at a time?:\n"
                    + "0:\tAll at once.\n"
                    + "1:\tOne at a time\n");

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
                    System.out.println("Enter in order: first name, last name, subject. Each field separated by comma:");
                    String userInput = scanner.nextLine();
                    String[] inputArray = userInput.split(",");
                    if (inputArray.length != 3) {
                        System.out.println("You do not enter a valid quantity of data!");
                        break;
                    }

                    if (Checker.isStringContainsLettersOnly(inputArray[0])) {
                        firstName = inputArray[0];
                    } else {
                        System.out.println("Invalid name input!");
                        break;
                    }

                    if (Checker.isStringContainsLettersOnly(inputArray[1])) {
                        lastName = inputArray[1];
                    } else {
                        System.out.println("Invalid name input!");
                        break;
                    }

                    if (inputArray[2] != null && Checker.checkForText(inputArray[2])) {
                        subject = inputArray[2];
                    } else {
                        System.out.println("Invalid subject input!");
                        break;
                    }

                    quit = true;
                    break;
                case 1:
                    System.out.println("Enter trainer's first name:");
                    input = scanner.nextLine();
                    if (Checker.isStringContainsLettersOnly(input)) {
                        firstName = input;
                    } else {
                        System.out.println("Invalid name input!");
                        break;
                    }

                    System.out.println("Enter trainer's last name:");
                    input = scanner.nextLine();
                    if (Checker.isStringContainsLettersOnly(input)) {
                        lastName = input;
                    } else {
                        System.out.println("Invalid name input");
                        break;
                    }

                    System.out.println("Enter subject:");
                    subject = scanner.nextLine();
                    if (!Checker.checkForText(subject)) {
                        System.out.println("Invalid subject input!");
                        break;
                    }

                    quit = true;
                    break;
                default:
                    System.out.println("Invalid action...\n");
                    break;
            }

        }

        Trainer trainer = new Trainer(subject, firstName, lastName);
        return trainer;
    }

    public static Course inputNewCourse() {
        String title = "No input";
        String stream = "No input";
        String type = "No input";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        String input = "";

        boolean quit = false;
        while (!quit) {
            System.out.println("Enter course info all at once or one at a time?:\n"
                    + "0:\tAll at once.\n"
                    + "1:\tOne at a time\n");

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
                    System.out.println("Enter in order: title, stream, type, start-date(yyyy/mm/dd), end-date(yyyy/mm/dd). Each field separated by comma:");
                    String userInput = scanner.nextLine();
                    String[] inputArray = userInput.split(",");
                    if (inputArray.length != 5) {
                        System.out.println("You do not enter a valid quantity of data!");
                        break;
                    }

                    if (inputArray[0] != null && Checker.checkForText(inputArray[0])) {
                        title = inputArray[0];
                    } else {
                        System.out.println("Invalid title input!");
                        break;
                    }
                    if (inputArray[1] != null && Checker.checkForText(inputArray[1])) {
                        stream = inputArray[1];
                    } else {
                        System.out.println("Invalid stream input!");
                        break;
                    }
                    if (inputArray[2] != null && Checker.checkForText(inputArray[2])) {
                        type = inputArray[2];
                    } else {
                        System.out.println("Ivalid type input!");
                        break;
                    }

                    if (inputArray[3] != null && Checker.dateCheck(inputArray[3])) {
                        startDate = inputDate(inputArray[3]);
                    } else {
                        System.out.println("Invalid date!");
                        break;
                    }
                    if (inputArray[4] != null && Checker.dateCheck(inputArray[4])) {
                        endDate = inputDate(inputArray[4]);
                    } else {
                        System.out.println("Invalid date!");
                        break;
                    }
                    quit = true;
                    break;
                case 1:
                    System.out.println("Enter course title:");
                    title = scanner.nextLine();
                    if (!Checker.checkForText(title)) {
                        System.out.println("Invalid title input!");
                        break;
                    }
                    System.out.println("Enter course stream:");
                    stream = scanner.nextLine();
                    if (!Checker.checkForText(stream)) {
                        System.out.println("Invalid stream input!");
                        break;
                    }
                    System.out.println("Enter course type:");
                    type = scanner.nextLine();
                    if (!Checker.checkForText(type)) {
                        System.out.println("Invalid type input!");
                        break;
                    }

                    System.out.println("Enter course start date(yyyy/mm/dd):");
                    input = scanner.nextLine();
                    if (Checker.dateCheck(input)) {
                        startDate = inputDate(input);
                    } else {
                        System.out.println("Invalid date!");
                        break;
                    }

                    System.out.println("Enter course end date(yyyy/mm/dd):");
                    input = scanner.nextLine();

                    if (Checker.dateCheck(input)) {
                        endDate = inputDate(input);
                    } else {
                        System.out.println("Invalid date!");
                        break;
                    }
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid action...\n");
                    break;
            }

        }

        Course course = new Course(title, stream, type, startDate, endDate);
        return course;
    }

    public static Assignment inputNewAssignment() {
        String title = "No input";
        String description = "No input";
        LocalDateTime subDateTime = LocalDateTime.now();
        int oralMark = 0;
        int totalMark = 0;
        String input = "";
        boolean quit = false;
        while (!quit) {
            System.out.println("Enter course info all at once or one at a time?:\n"
                    + "0:\tAll at once.\n"
                    + "1:\tOne at a time\n");
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
                    System.out.println("Enter in order: title, description, submission-date(yyyy/MM/dd/hh/mm/ss). Each field separated by comma:");
                    String userInput = scanner.nextLine();
                    String[] inputArray = userInput.split(",");
                    if (inputArray.length != 3) {
                        System.out.println("You do not enter a valid quantity of data!");
                        break;
                    }

                    if (inputArray[0] != null && Checker.checkForText(inputArray[0])) {
                        title = inputArray[0];
                    } else {
                        System.out.println("Invalid title input!");
                        break;
                    }

                    if (inputArray[1] != null && Checker.checkForText(inputArray[1])) {
                        description = inputArray[1];
                    } else {
                        System.out.println("Invalid description input!");
                        break;
                    }

                    if (inputArray[2] != null && Checker.dateTimeCheck(inputArray[2])) {
                        subDateTime = inputDateTime(inputArray[2]);
                    } else {
                        System.out.println("Invalid date!");
                        break;
                    }

                    quit = true;
                    break;
                case 1:
                    System.out.println("Enter assignment title:");
                    title = scanner.nextLine();
                    if (!Checker.checkForText(title)) {
                        System.out.println("Invalid title input!");
                        break;
                    }
                    System.out.println("Enter assignment description:");
                    description = scanner.nextLine();
                    if (!Checker.checkForText(description)) {
                        System.out.println("Invalid description input!");
                        break;
                    }
                    System.out.println("Enter submission date and time(yyyy/MM/dd/HH/mm/ss):");
                    input = scanner.nextLine();
                    if (Checker.dateTimeCheck(input)) {
                        subDateTime = inputDateTime(input);
                    } else {
                        System.out.println("Invalid date!");
                        break;
                    }

                    quit = true;
                    break;
                default:
                    System.out.println("Invalid action...\n");
                    break;
            }

        }

        Assignment assignment = new Assignment(title, description, subDateTime, oralMark, totalMark);
        return assignment;
    }

    public static Course selectCourse(ArrayList<Course> courseList) {
        int listSize = courseList.size();
        if (listSize == 0) {
            return null;
        }

        while (true) {
            System.out.println("Select course:");
            System.out.println("Button\tTitle\tStream\tType\tStartDate\tEndDate");
            System.out.println("-------------------------------------------------------------");
            for (int i = 0; i < listSize; i++) {
                System.out.println(String.format("%s:\t%s", (i + 1), courseList.get(i).printCourse()));
            }
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            if (userInput >= 1 && userInput <= listSize) {
                return courseList.get(userInput - 1);
            } else {
                System.out.println("Invalid input");
            }
        }

    }

    public static Student selectStudent(ArrayList<Student> studentList) {
        int listSize = studentList.size();
        if (listSize == 0) {
            return null;
        }

        while (true) {
            System.out.println("Select student:");
            System.out.println("Button\tFirstName\tLastName\tDateOfBirth\tTuitionFees");
            System.out.println("-------------------------------------------------------------");
            for (int i = 0; i < listSize; i++) {
                System.out.println(String.format("%s:\t%s", (i + 1), studentList.get(i).printStudent()));;
            }
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            if (userInput >= 1 && userInput <= listSize) {
                return studentList.get(userInput - 1);
            } else {
                System.out.println("Invalid input!");
            }
        }

    }

    public static Trainer selectTrainer(ArrayList<Trainer> trainerList) {
        int listSize = trainerList.size();
        if (listSize == 0) {
            return null;
        }

        while (true) {
            System.out.println("Select trainer:");
            System.out.println("Button\tFirstName\tLastName\tSubject");
            System.out.println("-------------------------------------------------------------");
            for (int i = 0; i < listSize; i++) {
                System.out.println(String.format("%s:\t%s", (i + 1), trainerList.get(i).printTrainer()));;
            }
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            if (userInput >= 1 && userInput <= listSize) {
                return trainerList.get(userInput - 1);
            } else {
                System.out.println("Invalid input!");
            }
        }

    }

    public static Assignment selectAssignment(ArrayList<Assignment> assignmentList) {
        int listSize = assignmentList.size();
        if (listSize == 0) {
            return null;
        }

        while (true) {
            System.out.println("Select assignment:");
            System.out.println("Button\tTitle\tDiscription\tSumbissionDate");
            System.out.println("-------------------------------------------------------------");
            for (int i = 0; i < listSize; i++) {
                System.out.println(String.format("%s:\t%s", (i + 1), assignmentList.get(i).printAssignment()));;
            }
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            if (userInput >= 1 && userInput <= listSize) {
                return assignmentList.get(userInput - 1);
            } else {
                System.out.println("Invalid input!");
            }
        }

    }

    public static void courseMenu(ArrayList<CourseDetails> courseDetailsList, ArrayList<Course> courseList) {
        boolean quit = false;
        Course course;
        while (!quit) {
            Menu.printCourseMenu();
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 0:
                    System.out.println("Returning to main.");
                    quit = true;
                    break;
                case 1:
                    if (Checker.isCourseListEmpty(courseList)) {
                        break;
                    }
                    System.out.println("Title\tStream\tType\tStartDate\tEndDate");
                    System.out.println("-------------------------------------------------------------");
                    for (int i = 0; i < courseList.size(); i++) {
                        System.out.println(courseList.get(i).printCourse());
                    }
                    break;
                case 2:
                    if (Checker.isCourseListEmpty(courseList)) {
                        break;
                    }

                    course = selectCourse(courseList);

                    for (int i = 0; i < courseDetailsList.size(); i++) {
                        if (courseDetailsList.get(i).getCourse().equals(course)) {
                            System.out.println("FirstName\tLastName\tDateOfBirth\tTuitionFees");
                            System.out.println("-------------------------------------------------------------");
                            for (int j = 0; j < courseDetailsList.get(i).getStudents().size(); j++) {
                                System.out.println(courseDetailsList.get(i).getStudents().get(j).printStudent());
                            }
                        }
                    }
                    break;
                case 3:
                    if (Checker.isCourseListEmpty(courseList)) {
                        break;
                    }

                    course = selectCourse(courseList);
                    for (int i = 0; i < courseDetailsList.size(); i++) {
                        if (courseDetailsList.get(i).getCourse().equals(course)) {
                            System.out.println("Title\tDiscription\tSumbissionDate");
                            System.out.println("-------------------------------------------------------------");
                            for (int j = 0; j < courseDetailsList.get(i).getAssignments().size(); j++) {
                                System.out.println(courseDetailsList.get(i).getAssignments().get(j).getTitle() + "\t"
                                        + courseDetailsList.get(i).getAssignments().get(j).getDescription() + "\t"
                                        + courseDetailsList.get(i).getAssignments().get(j).getSubDateTime());
                            }
                            System.out.println("");
                        }
                    }
                    break;
                case 4:
                    if (Checker.isCourseListEmpty(courseList)) {
                        break;
                    }

                    course = selectCourse(courseList);
                    for (int i = 0; i < courseDetailsList.size(); i++) {
                        if (courseDetailsList.get(i).getCourse().equals(course)) {
                            System.out.println("FirstName\tLastName\tSubject");
                            System.out.println("-------------------------------------------------------------");
                            for (int j = 0; j < courseDetailsList.get(i).getTrainers().size(); j++) {
                                System.out.println(courseDetailsList.get(i).getTrainers().get(j).printTrainer());
                            }
                        }
                    }
                    break;

                case 5:
                    Course courseToAdd = inputNewCourse();
                    if (courseToAdd != null && (!Checker.courseExistsInCourseList(courseToAdd, courseList))) {
                        courseList.add(courseToAdd);
                        courseDetailsList.add(new CourseDetails(courseToAdd));
                        System.out.println("New course has been added!");
                    } else {
                        System.out.println("Course already exists. Cannot add!");
                    }

                    break;
                default:
                    System.out.println("Invalid action");
                    break;

            }

        }

    }

    public static void studentMenu(ArrayList<CourseDetails> courseDetailsList, ArrayList<Student> studentList, ArrayList<AssignmentsPerStudent> assignmentsPerStudentList, ArrayList<Course> courseList) {

        if (Checker.isCourseListEmpty(courseList)) {
            return;
        }

        boolean quit = false;

        while (!quit) {
            Menu.printStudentMenu();
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 0:
                    System.out.println("Returning to main.");
                    quit = true;
                    break;
                case 1:
                    System.out.println("FirstName\tLastName\tDateOfBirth\tTuitionFees");
                    System.out.println("-------------------------------------------------------------");
                    for (int i = 0; i < studentList.size(); i++) {
                        System.out.println(studentList.get(i).printStudent());
                    }
                    break;
                case 2:
                    System.out.println("FirstName\tLastName\tDateOfBirth\tTuitionFees");
                    System.out.println("-------------------------------------------------------------");

                    for (int i = 0; i < studentList.size(); i++) {
                        int count = 0;
                        for (AssignmentsPerStudent std : assignmentsPerStudentList) {
                            if (std.getStudent().equals(studentList.get(i))) {
                                count++;
                            }
                        }
                        if (count > 1) {
                            System.out.println(studentList.get(i).printStudent());
                        }
                    }

                    break;

                case 3:
                    Student student = inputNewStudentData();
                    if (student != null && (!Checker.studentExistsInStudentList(student, studentList))) {
                        studentList.add(student);
                        System.out.println("Please add the new student to a course.");
                        Course course = selectCourse(courseList);
                        for (CourseDetails courseDetails : courseDetailsList) {

                            if (courseDetails.getCourse().equals(course)) {

                                if (courseDetails.addStudentToCourse(student)) {
                                    AssignmentsPerStudent aps = new AssignmentsPerStudent(student, course);
                                    assignmentsPerStudentList.add(aps);
                                    for (Assignment assignment : courseDetails.getAssignments()) {
                                        aps.addAssignmentToStudent(assignment);

                                    }
                                }

                            }
                        }

                    } else {
                        System.out.println("Student already exists!");
                    }
                    break;

                case 4:
                    Student studentToAdd = selectStudent(studentList);
                    if (studentToAdd != null) {
                        System.out.println("Please add the student to a course.");
                        Course course = selectCourse(courseList);
                        for (CourseDetails courseDetails : courseDetailsList) {

                            if (courseDetails.getCourse().equals(course)) {

                                if (courseDetails.addStudentToCourse(studentToAdd)) {
                                    AssignmentsPerStudent aps = new AssignmentsPerStudent(studentToAdd, course);
                                    assignmentsPerStudentList.add(aps);
                                    for (Assignment assignment : courseDetails.getAssignments()) {
                                        aps.addAssignmentToStudent(new Assignment(assignment));//deep copy of assignment

                                    }
                                }
                            }
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid action");
                    break;

            }

        }

    }

    public static void trainerMenu(ArrayList<CourseDetails> courseDetailsList, ArrayList<Trainer> trainerList, ArrayList<Course> courseList) {

        if (Checker.isCourseListEmpty(courseList)) {
            return;
        }

        boolean quit = false;

        while (!quit) {
            Menu.printTrainerMenu();
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 0:
                    System.out.println("Returning to main.");
                    quit = true;
                    break;
                case 1:
                    System.out.println("FirstName\tLastName\tSubject");
                    System.out.println("-------------------------------------------------------------");
                    for (int i = 0; i < trainerList.size(); i++) {
                        System.out.println(trainerList.get(i).printTrainer());
                    }
                    break;

                case 2:
                    Trainer trainer = inputNewTrainerData();
                    if (trainer != null && (!Checker.trainerExistsInTrainerList(trainer, trainerList))) {
                        trainerList.add(trainer);
                        System.out.println("Please add the new trainer to a course.");
                        Course course = selectCourse(courseList);
                        for (CourseDetails courseDetails : courseDetailsList) {

                            if (courseDetails.getCourse().equals(course)) {
                                courseDetails.addTrainerToCourse(trainer);

                            }
                        }

                    } else {
                        System.out.println("Trainer already exists!");
                    }
                    break;

                case 3:
                    Trainer trainerToAdd = selectTrainer(trainerList);
                    if (trainerToAdd != null) {
                        System.out.println("Please add the trainer to a course.");
                        Course course = selectCourse(courseList);
                        for (CourseDetails courseDetails : courseDetailsList) {
                            if (courseDetails.getCourse().equals(course)) {
                                courseDetails.addTrainerToCourse(trainerToAdd);

                            }
                        }

                    }
                    break;

                default:
                    System.out.println("Invalid action");
                    break;

            }

        }

    }

    public static void assignmentMenu(ArrayList<CourseDetails> courseDetailsList, ArrayList<AssignmentsPerStudent> assignmentsPerStudentList,
            ArrayList<Assignment> assignmentList, ArrayList<Course> courseList, ArrayList<Student> studentList) {

        if (Checker.isCourseListEmpty(courseList)) {
            return;
        }

        boolean quit = false;

        while (!quit) {
            Menu.printAssignmentMenu();
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 0:
                    System.out.println("Returning to main.");
                    quit = true;
                    break;
                case 1:
                    System.out.println("Title\tDiscription\tSumbissionDate");
                    System.out.println("-------------------------------------------------------------");
                    for (int i = 0; i < assignmentList.size(); i++) {
                        System.out.println(assignmentList.get(i).getTitle() + "\t"
                                + assignmentList.get(i).getDescription() + "\t"
                                + assignmentList.get(i).getSubDateTime());
                    }
                    break;

                case 2:

                    Student student = selectStudent(studentList);
                    if (student != null) {
                        for (AssignmentsPerStudent assignmentPerStudent : assignmentsPerStudentList) {
                            if (assignmentPerStudent.getStudent().equals(student)) {
                                System.out.println(assignmentPerStudent.getCourse());
                                System.out.println("====================================================================");
                                System.out.println("Title\tDescription\tSubmission-Date\tOralMark\tTotalMark");
                                System.out.println("-------------------------------------------------------------");
                                for (Assignment assignment : assignmentPerStudent.getAssignments()) {
                                    System.out.println(assignment.printAssignment());

                                }

                            }
                        }
                    }
                    break;

                case 3:
                    Assignment assignment = inputNewAssignment();
                    if (assignment != null && (!Checker.assignmentExistsInAssignmentList(assignment, assignmentList))) {
                        assignmentList.add(assignment);
                        System.out.println("Please add the new assignment to a course.");
                        Course course = selectCourse(courseList);
                        for (CourseDetails courseDetails : courseDetailsList) {
                            if (courseDetails.getCourse().equals(course)) {
                                courseDetails.addAssignmentToCourse(assignment);
                            }
                        }

                        for (AssignmentsPerStudent assignmentPerStudent : assignmentsPerStudentList) {

                            if (assignmentPerStudent.getCourse().equals(course)) {
                                assignmentPerStudent.addAssignmentToStudent(new Assignment(assignment));//deep copy of assignment

                            }
                        }

                    } else {
                        System.out.println("Assignment already exists!");
                    }
                    break;

                case 4:
                    Student studentToSearch = selectStudent(studentList);
                    Course courseToSearch = selectCourse(courseList);
                    Assignment assignmentToSearch = selectAssignment(assignmentList);

                    for (AssignmentsPerStudent assignmentsPerStudent : assignmentsPerStudentList) {
                        if (assignmentsPerStudent.getStudent().equals(studentToSearch)) {
                            if (assignmentsPerStudent.getCourse().equals(courseToSearch)) {
                                for (Assignment assign : assignmentsPerStudent.getAssignments()) {
                                    if (assign.equals(assignmentToSearch)) {
                                        enterOralMark(assign);
                                        enterTotalMark(assign);

                                    }
                                }
                            }
                        }
                    }

                    break;

                default:
                    System.out.println("Invalid action");
                    break;

            }

        }

    }

    public static void studentsToSubmitAssignment(ArrayList<AssignmentsPerStudent> assignmentsPerStudentsList) {

        if (assignmentsPerStudentsList.isEmpty()) {
            System.out.println("There are no course assignments available.");
            System.out.println("You have to enter assignment first:\n"
                    + "Go to -> Main Menu -> Assignments -> Add assignment");
            return;
        }

        boolean quit = false;
        LocalDate date;
        while (!quit) {
            System.out.println("Enter a date(yyyy/mm/dd) to print a list of students who need to submit one or more assignments on the same calendar week:");
            String checkDate = scanner.nextLine();
            if (Checker.dateCheck(checkDate)) {
                date = inputDate(checkDate);
            } else {
                System.out.println("Invalid date.");
                break;
            }

            Set<Student> studentsToSubmit = new HashSet();
            for (AssignmentsPerStudent assignmentsPerStudent : assignmentsPerStudentsList) {
                boolean hasToSubmit = false;
                for (Assignment assignment : assignmentsPerStudent.getAssignments()) {
                    if (Checker.isWithinSameWeek(date, assignment.getSubDateTime())) {
                        hasToSubmit = true;
                    }
                }
                if (hasToSubmit) {
                    studentsToSubmit.add(assignmentsPerStudent.getStudent());
                }
            }
            System.out.println("FirstName\tLastName\tDateOfBirth\tTuitionFees");
            System.out.println("-------------------------------------------------------------");
            studentsToSubmit.forEach((student) -> System.out.println(student.printStudent()));

            System.out.println("Enter another date?(y/n)");
            if (scanner.nextLine().toLowerCase().equals("y")) {
                quit = false;
            } else {
                quit = true;
            }

        }

    }

    private static void enterOralMark(Assignment assignment) {
        boolean quit = false;
        int mark = 0;
        while (!quit) {
            System.out.println("Enter oral mark. Integer number from 1 to 10:");
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                System.out.println("Invalid value");
                scanner.nextLine();
                continue;
            }

            mark = scanner.nextInt();
            scanner.nextLine();
            if (mark < 1 || mark > 10) {
                System.out.println("Mark out of range!");
                continue;
            }
            quit = true;

        }
        assignment.setOralMark(mark);
    }

    private static void enterTotalMark(Assignment assignment) {
        boolean quit = false;
        int mark = 0;
        while (!quit) {
            System.out.println("Enter total mark. Integer number from 1 to 10:");
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                System.out.println("Invalid value");
                scanner.nextLine();
                continue;
            }

            mark = scanner.nextInt();
            scanner.nextLine();

            if (mark < 1 || mark > 10) {
                System.out.println("Mark out of range!");
                continue;
            }
            quit = true;

        }
        assignment.setTotalMark(mark);
    }

    public static LocalDate inputDate(String userInput) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(userInput, dateFormat);
        return date;
    }

    public static LocalDateTime inputDateTime(String userInput) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH/mm/ss");
        LocalDateTime date = LocalDateTime.parse(userInput, dateFormat);
        return date;
    }
}
