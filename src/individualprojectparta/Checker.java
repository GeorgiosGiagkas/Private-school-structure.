/*
 * Methods to check user input such as date, and validate if objects exit in a list (see override equals in
 * the four main classes:Assignment,Course,Student,Trainer)
 *
 */
package individualprojectparta;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giagkas
 */
public class Checker {

    private Checker() {
    }

    public static boolean studentExistsInStudentList(Student student, ArrayList<Student> studentList) {
        if (studentList.isEmpty() || student == null) {
            return false;
        }

        for (Student std : studentList) {
            if (student.equals(std)) {
                return true;
            }
        }
        return false;

    }

    public static boolean courseExistsInCourseList(Course course, List<Course> courseList) {
        if (courseList.isEmpty() || course == null) {
            return false;
        }
        for (Course crs : courseList) {
            if (course.equals(crs)) {
                return true;
            }
        }
        return false;

    }

    public static boolean trainerExistsInTrainerList(Trainer trainer, ArrayList<Trainer> trainerList) {
        if (trainerList.isEmpty() || trainer == null) {
            return false;
        }
        for (Trainer trn : trainerList) {
            if (trainer.equals(trn)) {
                return true;
            }
        }
        return false;

    }

    public static boolean assignmentExistsInAssignmentList(Assignment assignment, ArrayList<Assignment> assignmentList) {
        if (assignmentList.isEmpty() || assignment == null) {
            return false;
        }
        for (Assignment assign : assignmentList) {
            if (assignment.equals(assign)) {
                return true;
            }
        }
        return false;

    }

    public static boolean checkForText(String userInput) {
        if (userInput.matches("^([a-zA-Z0-9]+)(.*)")) {
            return true;
        }
        return false;
    }

    private static boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean daysInAMonth(int year, int month, int day) {
        if (month == 1 && (day >= 1 && day <= 31)) {
            return true;
        }
        if (month == 2 && (day >= 1 && day <= 29) && isLeapYear(year)) {
            return true;
        }
        if (month == 2 && (day >= 1 && day <= 28) && (!isLeapYear(year))) {
            return true;
        }
        if (month == 3 && (day >= 1 && day <= 31)) {
            return true;
        }
        if (month == 4 && (day >= 1 && day <= 30)) {
            return true;
        }
        if (month == 5 && (day >= 1 && day <= 31)) {
            return true;
        }
        if (month == 6 && (day >= 1 && day <= 30)) {
            return true;
        }
        if (month == 7 && (day >= 1 && day <= 31)) {
            return true;
        }
        if (month == 8 && (day >= 1 && day <= 31)) {
            return true;
        }
        if (month == 9 && (day >= 1 && day <= 30)) {
            return true;
        }
        if (month == 10 && (day >= 1 && day <= 31)) {
            return true;
        }
        if (month == 11 && (day >= 1 && day <= 30)) {
            return true;
        }
        if (month == 12 && (day >= 1 && day <= 31)) {
            return true;
        }
        return false;
    }

    public static boolean dateCheck(String userInput) {

        String regx = "^\\d{4}/\\d{2}/\\d{2}$";

        if (!userInput.matches(regx)) {
            return false;
        }

        String[] stringSplit = userInput.split("/");

        if (stringSplit.length != 3) {
            return false;
        }

        int year = Integer.parseInt(stringSplit[0]);
        int month = Integer.parseInt(stringSplit[1]);
        int day = Integer.parseInt(stringSplit[2]);

        if (year < 1900 || year > 2200) {
            return false;
        }
        return daysInAMonth(year, month, day);

    }

    public static boolean dateTimeCheck(String userInput) {

        String regx = "^\\d{4}/\\d{2}/\\d{2}/\\d{2}/\\d{2}/\\d{2}$";

        if (!userInput.matches(regx)) {
            return false;
        }

        String[] stringSplit = userInput.split("/");

        if (stringSplit.length != 6) {
            return false;
        }

        int year = Integer.parseInt(stringSplit[0]);
        int month = Integer.parseInt(stringSplit[1]);
        int day = Integer.parseInt(stringSplit[2]);
        int hours = Integer.parseInt(stringSplit[3]);
        int min = Integer.parseInt(stringSplit[4]);
        int sec = Integer.parseInt(stringSplit[5]);

        if (year < 1900 || year > 2200) {
            return false;
        }

        if (!daysInAMonth(year, month, day)) {
            return false;
        }

        if (hours < 0 || hours > 23) {
            return false;
        }
        if (min < 0 || min > 60) {
            return false;
        }
        if (sec < 0 || sec > 60) {
            return false;
        }

        return true;

    }

    public static boolean isStringContainsLettersOnly(String string) {
        return ((string != null)
                && (!string.equals(""))
                && (string.matches("^[a-zA-Z]*$")));
    }

    public static boolean isStringContainsDigitOnly(String string) {
        String regex = "[0-9]+";
        return ((string != null)
                && (!string.equals(""))
                && (string.matches(regex)));
    }

    public static boolean isCourseListEmpty(ArrayList<Course> courseList) {
        if (courseList.isEmpty()) {
            System.out.println("There are no courses available! Please add a course first.\n"
                    + "Go to -> MainMenu -> Courses -> Add course.");
            return true;
        }
        return false;
    }

    public static boolean isWithinSameWeek(LocalDate inputDate, LocalDateTime assignmentDate) {

        //which day of the week?
        DayOfWeek dayOfWeek = DayOfWeek.from(inputDate);

        //convert LocalDateTime to LocalDAte
        LocalDate assignmentLocalDate = assignmentDate.toLocalDate();

        //calclate difference
        long daysBetween = ChronoUnit.DAYS.between(inputDate, assignmentLocalDate);

        if (dayOfWeek.getValue() == 1 && (daysBetween >= 0 && daysBetween <= 4)) {
            return true;
        }
        if (dayOfWeek.getValue() == 2 && (daysBetween >= -1 && daysBetween <= 3)) {
            return true;
        }
        if (dayOfWeek.getValue() == 3 && (daysBetween >= -2 && daysBetween <= 2)) {
            return true;
        }
        if (dayOfWeek.getValue() == 4 && (daysBetween >= -3 && daysBetween <= 1)) {
            return true;
        }
        if (dayOfWeek.getValue() == 5 && (daysBetween >= -4 && daysBetween <= 0)) {
            return true;
        }
        if (dayOfWeek.getValue() == 6 && (daysBetween >= -5 && daysBetween <= -1)) {
            return true;
        }
        if (dayOfWeek.getValue() == 7 && (daysBetween >= -6 && daysBetween <= -2)) {
            return true;
        }

        return false;
    }
}
