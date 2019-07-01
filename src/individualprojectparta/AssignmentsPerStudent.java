/*
 * Keeps the student's grades per course.
 * Each student has a different object of Class AssignmentsPerStudent that holds data for
 * assignments for a particular course.
 * assignments ArrayList holds a copy of assignment object added to the corresponding courseDetails Object
 */
package individualprojectparta;

import java.util.ArrayList;

/**
 *
 * @author giagkas
 */
public class AssignmentsPerStudent {

    private Student student;
    private Course course;
    private ArrayList<Assignment> assignments;

    public AssignmentsPerStudent(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.assignments = new ArrayList<>();
    }

    private boolean assignementIsInList(Assignment assignment) {
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

    public boolean addAssignmentToStudent(Assignment assignment) {
        if (!assignementIsInList(assignment)) {
            assignments.add(assignment);
            return true;
        }
        return false;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

}
