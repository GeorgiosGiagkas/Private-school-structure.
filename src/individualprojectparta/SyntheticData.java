/*
 * Random Generated synthetic data for Student, Trainer and Assignments. Courses are not random
 */
package individualprojectparta;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author giagkas
 */
public class SyntheticData {

    private SyntheticData() {
    }

    public static void addSyntheticData(ArrayList<Student> studentList, ArrayList<Course> courseList,
            ArrayList<Assignment> assignmentList, ArrayList<Trainer> trainerList,
            ArrayList<CourseDetails> courseDetailsList,
            ArrayList<AssignmentsPerStudent> assignmentsPerStudentsList) {

        //courses
        LocalDate startDate = LocalDate.of(2019, 5, 13);
        LocalDate endDatePart = LocalDate.of(2019, 11, 23);
        LocalDate endDateFull = LocalDate.of(2019, 9, 13);
        Course cb8JavaPart = new Course("CB8", "Java", "part-time", startDate, endDatePart);
        Course cb8JavaFull = new Course("CB8", "Java", "full-time", startDate, endDateFull);
        Course cb8CSharpPart = new Course("CB8", "C#", "part-time", startDate, endDatePart);
        Course cb8CSharpFull = new Course("CB8", "C#", "full-time", startDate, endDateFull);
        //add courses
        courseList.add(cb8JavaPart);
        courseList.add(cb8JavaFull);
        courseList.add(cb8CSharpPart);
        courseList.add(cb8CSharpFull);
        // System.out.println(courseList);

        CourseDetails cd1 = new CourseDetails(cb8JavaPart);
        CourseDetails cd2 = new CourseDetails(cb8JavaFull);
        CourseDetails cd3 = new CourseDetails(cb8CSharpPart);
        CourseDetails cd4 = new CourseDetails(cb8CSharpFull);

        //students  //add courses
        //to cd1
        for (int i = 1; i < 6; i++) {
            Student student = Student.randomStudentGenerator();
            studentList.add(student);
            cd1.addStudentToCourse(student);
        }
        //to cd2
        for (int i = 6; i < 11; i++) {
            Student student = Student.randomStudentGenerator();
            studentList.add(student);
            cd2.addStudentToCourse(student);
        }
        //to cd3
        for (int i = 11; i < 16; i++) {
            Student student = Student.randomStudentGenerator();
            studentList.add(student);
            cd3.addStudentToCourse(student);
        }
        //to cd4
        for (int i = 16; i < 21; i++) {
            Student student = Student.randomStudentGenerator();
            studentList.add(student);
            cd4.addStudentToCourse(student);
        }

        //trainers // add trainers
        //cd1
        for (int i = 1; i < 3; i++) {
            Trainer trainer = Trainer.randomTrainerGenerator();
            trainerList.add(trainer);
            cd1.addTrainerToCourse(trainer);
        }
        //cd2
        for (int i = 3; i < 5; i++) {
            Trainer trainer = Trainer.randomTrainerGenerator();
            trainerList.add(trainer);
            cd2.addTrainerToCourse(trainer);
        }
        //cd3
        for (int i = 5; i < 7; i++) {
            Trainer trainer = Trainer.randomTrainerGenerator();
            trainerList.add(trainer);
            cd3.addTrainerToCourse(trainer);
        }
        //cd4
        for (int i = 7; i < 9; i++) {
            Trainer trainer = Trainer.randomTrainerGenerator();
            trainerList.add(trainer);
            cd4.addTrainerToCourse(trainer);
        }

        //System.out.println(trainerList);
        //assignments
        //cd1      
        for (int i = 1; i < 7; i++) {
            Assignment assignment = Assignment.randomAssignmentGenerator();
            assignmentList.add(assignment);
            cd1.addAssignmentToCourse(assignment);
        }
        //cd2      
        for (int i = 7; i < 13; i++) {
            Assignment assignment = Assignment.randomAssignmentGenerator();
            assignmentList.add(assignment);
            cd2.addAssignmentToCourse(assignment);
        }
        //cd3      
        for (int i = 13; i < 19; i++) {
            Assignment assignment = Assignment.randomAssignmentGenerator();
            assignmentList.add(assignment);
            cd3.addAssignmentToCourse(assignment);
        }
        //cd4      
        for (int i = 19; i < 25; i++) {
            Assignment assignment = Assignment.randomAssignmentGenerator();
            assignmentList.add(assignment);
            cd4.addAssignmentToCourse(assignment);
        }

        courseDetailsList.add(cd1);
        courseDetailsList.add(cd2);
        courseDetailsList.add(cd3);
        courseDetailsList.add(cd4);

        //add assignments to students and set their mark at random
        for (CourseDetails courseDetails : courseDetailsList) {
            for (Student std : courseDetails.getStudents()) {
                AssignmentsPerStudent aps = new AssignmentsPerStudent(std, courseDetails.getCourse());
                for (Assignment assignment : courseDetails.getAssignments()) {
                    Assignment copyOfAssignment = new Assignment(assignment);//a copy of course assignment to student record 
                    copyOfAssignment.setOralMark(1 + (int) (Math.random() * 11));
                    copyOfAssignment.setTotalMark(1 + (int) (Math.random() * 11));
                    aps.addAssignmentToStudent(copyOfAssignment);
                }
                assignmentsPerStudentsList.add(aps);

            }
        }

    }
;

}
