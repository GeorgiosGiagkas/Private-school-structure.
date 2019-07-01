/*
 * Each course has an object of class CourseDetails that holds data for
 * all students in the course, all assignments for the course and all trainers for the course.
 * 
 */
package individualprojectparta;

import java.util.ArrayList;

/**
 *
 * @author giagkas
 */
public class CourseDetails {

    private Course course;
    private ArrayList<Student> students;
    private ArrayList<Assignment> assignments;
    private ArrayList<Trainer> trainers;

    public CourseDetails(Course course) {
        this.course = course;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
        this.trainers = new ArrayList<>();
    }

    private boolean studentIsInList(Student student) {
        if (students.isEmpty() || student == null) {
            return false;
        }
        for (Student std : students) {
            if (student.equals(std)) {
                return true;
            }
        }
        return false;
    }

    private boolean assignmentIsInList(Assignment assignment) {
        if (assignments.isEmpty() || assignment == null) {
            return false;
        }
        for (Assignment assign : assignments) {
            if (assignment.equals(assign)) {
                return true;
            }
        }
        return false;
    }

    private boolean trainerIsInList(Trainer trainer) {
        if (trainers.isEmpty() || trainer == null) {
            return false;
        }
        for (Trainer trn : trainers) {
            if (trainer.equals(trn)) {
                return true;
            }
        }
        return false;
    }

    public boolean addAssignmentToCourse(Assignment assignment) {
        if (!assignmentIsInList(assignment)) {
            this.assignments.add(assignment);
            System.out.println("Assignment has been added to the course!");
            return true;
        }
        System.out.println("Assignment already exits!");
        return false;
    }

    public boolean addTrainerToCourse(Trainer trainer) {
        if (!trainerIsInList(trainer)) {
            this.trainers.add(trainer);
            System.out.println("Trainer has been added to the course!");
            return true;
        }
        System.out.println("Trainer already exists!");
        return false;
    }

    public boolean addStudentToCourse(Student student) {
        if (!studentIsInList(student)) {
            this.students.add(student);
            System.out.println("Student has been added to the course!");
            return true;
        }
        System.out.println("Student already exists!");
        return false;
    }

    public Course getCourse() {
        return course;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

}
