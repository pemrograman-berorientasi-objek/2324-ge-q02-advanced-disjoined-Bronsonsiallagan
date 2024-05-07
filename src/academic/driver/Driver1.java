package academic.driver;

import academic.model.Course;
import academic.model.CourseOpening;
import academic.model.Student;
import academic.model.Enrollment;
import academic.model.Lecturer;

import java.util.ArrayList;
import java.util.HashSet; 
import java.util.List;
import java.util.Scanner; 

public class Driver1 {

    private static double convertGrade(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "AB":
                return 3.5;
            case "B":
                return 3.0;
            case "BC":
                return 2.5;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "E":
                return 0.0;
            default:
                return 0.0;
        }
    }

    private static String formatLecturers(List<Lecturer> lecturers) {
        StringBuilder sb = new StringBuilder();
        for (Lecturer lecturer : lecturers) {
            if (sb.length() > 0) {
                sb.append(";");
            }
            sb.append(lecturer.getInitial()).append(" (").append(lecturer.getEmail()).append(")");
        }
        return sb.toString();
    }

    private static double calculateGPA(String studentId, List<Enrollment> enrollments, List<Course> courses) {
        int totalCredits = calculateTotalCredits(studentId, enrollments, courses);
        String[] course = new String[enrollments.size()];
        String[] grade = new String[enrollments.size()];
        int index = 0;

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourse().equals(studentId)) {
                course[index] = enrollment.getStudent();
                grade[index] = enrollment.getGrade();
                index++;
            }
        }

        for (int i = 0; i < index; i++) {
            for (int j = i + 1; j < index; j++) {
                if (course[i].equals(course[j])) {
                    grade[i] = grade[j];
                    course[i] = course[j];
                    course[j] = null;
                    grade[j] = null;
                    index--;
                }
            }
        }

        double totalGradePoints = 0;
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < courses.size(); j++) {
                if (course[i].equals(courses.get(j).getId())) {
                    totalGradePoints += convertGrade(grade[i]) * courses.get(j).getCredits();
                }
            }
        }

        totalGradePoints = totalGradePoints / totalCredits;

        return totalGradePoints;
    }

    private static int calculateTotalCredits(String studentId, List<Enrollment> enrollments, List<Course> courses) {
        int totalCredits = 0;
        String[] course = new String[enrollments.size()];
        int index = 0;

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourse().equals(studentId)) {
                course[index] = enrollment.getStudent();
                index++;
            }
        }

        for (int i = 0; i < index; i++) {
            for (int j = i + 1; j < index; j++) {
                if (course[i].equals(course[j])) {
                    course[i] = course[j];
                    course[j] = null;
                    index--;
                }
            }
        }

        for (int i = 0; i < index; i++) {
            for (Course crs : courses) {
                if (crs.getId().equals(course[i])) {
                    totalCredits += crs.getCredits();
                }
            }
        }

        return totalCredits;
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ArrayList<String> studentDetailsRequests = new ArrayList<>();
        ArrayList<CourseOpening> courseOpenings = new ArrayList<>();

        while (inputScanner.hasNext()) {
            String input = inputScanner.nextLine();

            if (input.equals("---")) {
                break;
            }

            String[] inputParts = input.split("#");

            if (inputParts[0].equals("student-add")) {
                boolean studentExists = false;
                for (Student existingStudent : students) {
                    if (existingStudent.getId().equals(inputParts[1])
                            || existingStudent.getName().equals(inputParts[2])) {
                        studentExists = true;
                        break;
                    }
                }
                if (!studentExists) {
                    Student newStudent = new Student(inputParts[1], inputParts[2], Integer.parseInt(inputParts[3]),
                            inputParts[4]);
                    students.add(newStudent);
                }
            } else if (inputParts[0].equals("enrollment-add")) {
                boolean enrollmentExists = false;
                for (Enrollment existingEnrollment : enrollments) {
                    if (existingEnrollment.getStudent().equals(inputParts[1])
                            && existingEnrollment.getCourse().equals(inputParts[2])
                            && existingEnrollment.getAcademicYear().equals(inputParts[3])
                            && existingEnrollment.getSemester().equals(inputParts[4])) {
                        enrollmentExists = true;
                        break;
                    }
                }
                if (!enrollmentExists) {
                    Enrollment newEnrollment = new Enrollment(inputParts[1], inputParts[2], inputParts[3],
                            inputParts[4]);
                    enrollments.add(newEnrollment);
                }
            } else if (inputParts[0].equals("lecturer-add")) {
                Lecturer newLecturer = new Lecturer(inputParts[1], inputParts[2], inputParts[3], inputParts[4],
                        inputParts[5]);
                if (!lecturers.contains(newLecturer)) {
                    lecturers.add(newLecturer);
                }
            } else if (inputParts[0].equals("course-add")) {
                Course newCourse = new Course(inputParts[1], inputParts[2], Integer.parseInt(inputParts[3]),
                        inputParts[4]);

                courses.add(newCourse);

            } else if (inputParts[0].equals("enrollment-grade")) {
                for (Enrollment enrollment : enrollments) {
                    boolean ada = true;
                    if (enrollment.getGrade().equals("None")) {
                        ada = false;
                    }
                    if (enrollment.getStudent().equals(inputParts[1]) && enrollment.getCourse().equals(inputParts[2])
                            && enrollment.getAcademicYear().equals(inputParts[3])
                            && enrollment.getSemester().equals(inputParts[4]) && ada == false) {
                        enrollment.setGrade(inputParts[5]);
                        break;
                    }
                }
            } else if (inputParts[0].equals("student-details")) {
                studentDetailsRequests.add(input);
                HashSet<String> printedStudents = new HashSet<>();

                if (!printedStudents.contains(inputParts[1])) {
                    for (Student student : students) {
                        if (student.getId().equals(inputParts[1])) {
                            double gpa = calculateGPA(inputParts[1], enrollments, courses);
                            int totalCredits = calculateTotalCredits(inputParts[1], enrollments, courses);
                            System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|"
                                    + student.getStudyProgram() + "|" + String.format("%.2f", gpa) + "|"
                                    + totalCredits);
                            printedStudents.add(inputParts[1]);
                            break;
                        }
                    }
                }
            } else if (inputParts[0].equals("enrollment-remedial")) {
                for (Enrollment enrollment : enrollments) {
                    boolean bisa = false;
                    if (enrollment.getPreviousGrade() == null) {
                        bisa = true;
                    }
                    if (!enrollment.isRemedialGrade()) {
                        if (enrollment.getGrade().equals("None")) {
                            bisa = false;
                            continue;
                        } else {
                            bisa = true;
                        }
                    }
                    if (enrollment.getStudent().equals(inputParts[1]) && enrollment.getCourse().equals(inputParts[2])
                            && enrollment.getAcademicYear().equals(inputParts[3])
                            && enrollment.getSemester().equals(inputParts[4]) && bisa) {
                        if (!enrollment.isRemedialGrade()) {
                            enrollment.setPreviousGrade(enrollment.getGrade());
                            enrollment.setGrade(inputParts[5]);
                            enrollment.setRemedialDone(true);
                            break;
                        } else {
                            // Handling untuk nilai remedial
                            String previousGrade = enrollment.getPreviousGrade();
                            String newGrade = inputParts[5];
                            enrollment.setGrade(previousGrade + "(" + newGrade + ")");
                            break;
                        }
                    }
                }
            } else if (inputParts[0].equals("course-open")) {
                CourseOpening newCourseOpening = new CourseOpening(inputParts[1], inputParts[2], inputParts[3],
                        new ArrayList<>());
                if (!courseOpenings.contains(newCourseOpening)) {
                    courseOpenings.add(newCourseOpening);
                }
                String[] lecturerInitials = inputParts[4].split(",");
                for (String initial : lecturerInitials) {
                    for (Lecturer lecturer : lecturers) {
                        if (lecturer.getInitial().equals(initial)) {
                            newCourseOpening.addLecturer(lecturer);
                            break;
                        }
                    }
                }

            } else if (inputParts[0].equals("course-history")) {
                // Print course history gabungan course-add dan course-open
                for (Course course : courses) {
                    for (CourseOpening courseOpening : courseOpenings) {

                        // Print course history

                            if( courseOpening.getSemester().equals("odd")){

                            
                        System.out.println(course.getId() + "|" + course.getName() + "|" + course.getCredits() + "|"
                                + course.getGrade() + "|" + courseOpening.getAcademicYear() + "|"
                                + courseOpening.getSemester() + "|"
                                + formatLecturers(courseOpening.getLecturers()));
                        for (Enrollment enrollment : enrollments) {
                            if (enrollment.getAcademicYear().equals(courseOpening.getAcademicYear()) && enrollment.getSemester().equals(courseOpening.getSemester())) {
                                if (enrollment.getPreviousGrade() != null) {
                                    System.out.println(enrollment.getStudent() + "|" + enrollment.getCourse() + "|"
                                            + enrollment.getAcademicYear() + "|" + enrollment.getSemester() + "|"
                                            + enrollment.getGrade() + "(" + enrollment.getPreviousGrade() + ")");
                                } else {
                                    System.out.println(enrollment.getStudent() + "|" + enrollment.getCourse() + "|"
                                            + enrollment.getAcademicYear() + "|" + enrollment.getSemester() + "|"
                                            + enrollment.getGrade());
                                }
                            }
                            }
                        }
                     
                    }

                }
                for (Course course : courses) {
                    for (CourseOpening courseOpening : courseOpenings) {

                        // Print course history

                        if (courseOpening.getSemester().equals("even")) {

                            System.out.println(course.getId() + "|" + course.getName() + "|" + course.getCredits() + "|"
                                    + course.getGrade() + "|" + courseOpening.getAcademicYear() + "|"
                                    + courseOpening.getSemester() + "|"
                                    + formatLecturers(courseOpening.getLecturers()));
                            for (Enrollment enrollment : enrollments) {
                                if (enrollment.getAcademicYear().equals(courseOpening.getAcademicYear())
                                        && enrollment.getSemester().equals(courseOpening.getSemester())) {
                                    if (enrollment.getPreviousGrade() != null) {
                                        System.out.println(enrollment.getStudent() + "|" + enrollment.getCourse() + "|"
                                                + enrollment.getAcademicYear() + "|" + enrollment.getSemester() + "|"
                                                + enrollment.getGrade() + "(" + enrollment.getPreviousGrade() + ")");
                                    } else {
                                        System.out.println(enrollment.getStudent() + "|" + enrollment.getCourse() + "|"
                                                + enrollment.getAcademicYear() + "|" + enrollment.getSemester() + "|"
                                                + enrollment.getGrade());
                                    }
                                }
                            }
                        }
                    }

                }
                
 // private static double calculateGPA(String studentId, List<Enrollment> enrollments, List<Course> 
            }else if (inputParts[0].equals("find-the-best-student")) {
                if (inputParts.length >= 3) {
                    String academicYear = inputParts[1];
                    String semester = inputParts[2];
                    double maxGPA = 0;
                    String bestStudent = "";
            
                    // Check if the semester is odd or even
                    boolean isOddSemester = semester.equals("odd");
            
                    // Assuming students is a collection of Student objects
                    for (Student student : students) { 
                        double gpa = 0;
                        // Calculate GPA based on semester
                        if (isOddSemester) {
                            gpa = student.calculateGPA(academicYear, "odd");
                        } else {
                            gpa = student.calculateGPA(academicYear, "even");
                        }
                        if (gpa > maxGPA || (gpa == maxGPA && Integer.parseInt(student.getId().substring(4)) % 2 == 0)) {
                            maxGPA = gpa;
                            bestStudent = student.getId();
                        }
                    } 
                    System.out.println(bestStudent + "|B/A");
                }
            }
            
            
        }
            
        
        // Print lecturers
        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer.getId() + "|" + lecturer.getName() + "|" + lecturer.getInitial() + "|"
                    + lecturer.getEmail() + "|" + lecturer.getStudyProgram());
        }

        // Print courses
        for (Course course : courses) {
            System.out.println(course.getId() + "|" + course.getName() + "|" + course.getCredits() + "|"
                    + course.getGrade());
        }

        // Print students
        for (Student newStudent : students) {
            System.out.println(newStudent.getId() + "|" + newStudent.getName() + "|" + newStudent.getYear() + "|"
                    + newStudent.getStudyProgram());
        }

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getPreviousGrade() != null) {
                System.out.println(enrollment.getStudent() + "|" + enrollment.getCourse() + "|"
                        + enrollment.getAcademicYear() + "|" + enrollment.getSemester() + "|" + enrollment.getGrade()
                        + "(" + enrollment.getPreviousGrade() + ")");
            } else {
                System.out.println(enrollment.getStudent() + "|" + enrollment.getCourse() + "|"
                        + enrollment.getAcademicYear() + "|" + enrollment.getSemester() + "|" + enrollment.getGrade());
            }
        }

        // Close the Scanner
        inputScanner.close();
    }
}
