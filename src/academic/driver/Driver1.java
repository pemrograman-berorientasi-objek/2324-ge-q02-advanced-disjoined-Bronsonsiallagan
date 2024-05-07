package academic.driver;

import academic.model.AcademicPerson;
import academic.model.Course;
import academic.model.CourseOpening;
import academic.model.Enrollment;
import academic.model.Lecturer;
import academic.model.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set; 

/**
 * @author 12S22025 - Bronson TM Siallagan
 * @author 12S22026 - Ruben Sianipar 
 */
 
public class Driver1 {  
 
    public static void main(String[] _args) {

        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        Set<String> studentIds = new HashSet<>();
        ArrayList<Enrollment> new_Enrollments = new ArrayList<>(); 
        ArrayList<CourseOpening> courseOpenings = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        String str;
        boolean pers = false;

        while (scan.hasNextLine()) { 
            str = scan.nextLine(); 
 
            if (str.equals("---")) {
                break; 
            } else {
                String[] tokens = str.split("#");

                switch (tokens[0]) { 

                    case "student-add":
                        String studentId = tokens[1];
                        if (!studentIds.contains(studentId)) {
                            String programStudy = tokens.length > 4 ? tokens[4] : ""; // Default value if program study is not provided
                            students.add(new Student(tokens[1], tokens[2], tokens[3], programStudy));
                            studentIds.add(studentId);
                        } 
                        break;

                    case "lecturer-add":
                        boolean lecturerExists = false;  
                        for (Lecturer existingLecturer : lecturers) {
                            if (existingLecturer.getId().equals(tokens[1])) {
                                lecturerExists = true; 
                                break;
                            }
                        }
                        if (!lecturerExists) {
                            lecturers.add(new Lecturer(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]));
                        }
                        break; 

                    case "course-add":
                        
                        Course course = new Course(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]);
                        courses.add(course);
                        
                        break;
 
                    case "enrollment-grade": 
                        String courseCode = tokens[1];
                        String studentIdGrade = tokens[2]; 
                        String year = tokens[3];
                        String semester = tokens[4];
                        String grade = tokens[5];
                        for (Enrollment enrollment : enrollments) {
                            if (enrollment.getCourseID().equals(courseCode)
                                    && enrollment.getStudentID().equals(studentIdGrade)
                                    && enrollment.getYear().equals(year)
                                    && enrollment.getSemester().equals(semester)) { 
                                enrollment.setGrade(grade);
                                
                                break;
                            }
                        }

                        for(Enrollment enr : new_Enrollments){
                            if (enr.getCourseID().equals(courseCode)
                                    && enr.getStudentID().equals(studentIdGrade)
                                    && enr.getYear().equals(year)
                                    && enr.getSemester().equals(semester)) {
                                enr.setGrade(grade);
                                break;
                            }
                        }
                        break;   
 
                    case "enrollment-add":
                        enrollments.add(new Enrollment(tokens[1], tokens[2], tokens[3], tokens[4], "None"));
                        new_Enrollments.add(new Enrollment(tokens[1], tokens[2],tokens[3], tokens[4], "None"));
                        break;



                    case "student-details":
                        for(int i = 0; i<enrollments.size();i++){
                            if(enrollments.get(i).getStudentID().equals(tokens[1])){
                                for(int j = i+1; j<enrollments.size();j++){
                                     if(enrollments.get(j).getStudentID().equals(tokens[1])){
                                        if(enrollments.get(i).getCourseID().equals(enrollments.get(j).getCourseID())){
                                            enrollments.remove(i);
                                            
                                        }
                                    }
                                }
                            }
                        }
                        for (Student student : students) {
                            if (student.getId().equals(tokens[1])) {
                                int totalCredits = 0;
                                double totalGrade = 0;
                                for (Enrollment enrollment : enrollments) { 
                                    if (enrollment.getStudentID().equals(tokens[1])) {
                                        for (Course innerCourse : courses) {
                                            if (innerCourse.getCode().equals(enrollment.getCourseID())) {
                                                totalCredits += innerCourse.getCredits();
                                                totalGrade += innerCourse.getCredits() * getGrade(enrollment.getGrade());
                                            }
                                        }
                                    }
                                } 
                                
                                double ipk = totalCredits != 0 ? totalGrade / totalCredits : 0.0;
                                if (ipk == 0.0) { // Jika IPK adalah 0.00, atur jumlah sks menjadi 0 
                                    totalCredits = 0; 

 
                                     
                                }

                                System.out.println(student.getId() + "|" + student.getName() + "|" + student.getAngkatan() + "|" + student.getProdi() + "|" + String.format("%.2f", ipk) + "|" + totalCredits);
                                
                                break;
                            }  
                        } 

                    
                    case "enrollment-remedial":
                        for (Enrollment enr : enrollments) {
                            if (enr.getCourseID().equals(tokens[1]) &&
                                enr.getStudentID().equals(tokens[2]) &&
                                enr.getYear().equals(tokens[3]) &&
                                enr.getSemester().equals(tokens[4])) {
                                if (enr.getGrade().equals("None")) {
                                    break;
                                } else {
                                    if (enr.getTotalnilai() == 0) {   
                                        enr.setSebelum(tokens[5]);
                                        enr.tukarGrade();
                                        enr.setTotalnilai();
                                        // System.out.println("Trssssss");
                                    } else {
                                        String sebelumString = enr.getSebelum();
                                        if (sebelumString.equals("")) {
                                            enr.setRemedial(enr.getGrade() + "(" + tokens[5] + ")");
                                        } else { 
                                            enr.setRemedial(sebelumString + "(" + tokens[5] + ")");
                                        }
                                         
                                    }
                                    break; 
                                }
                            }
                        }
 
                    case "course-open":
                        for (Course crs : courses) {
                            if (crs.getCode().equals(tokens[1])) { 
                                for (Lecturer lecturer : lecturers) {
                                    if (lecturer.getInitial().equals(tokens[4])) {
                                        CourseOpening courseOpening = new CourseOpening(tokens[1], tokens[2], tokens[3], tokens[4]);
                                        courseOpenings.add(courseOpening);  

                                    }
                                }
                            }  
                        }
                        break; 


                        // case "find-the-best-student":
                        //  for(Student student : students){
                        //     if(student.getId().equals(tokens[1])) {
                        //         for(Enrollment enrollment : enrollments ){
                        //             if (enrollment.getStudentID().equals(tokens[2])){
                                          
                        //             }
                        //         }
                        //         System.out.println(courseOpenings.getYear()  + "|" + courseOpenings.getSemester());
                        //     }
                        //  }
                    case "course-history":
                        // Urutkan koleksi courseOpenings berdasarkan semester
                        courseOpenings.sort((c1, c2) -> c2.getSemester().compareTo(c1.getSemester()));

                        for (CourseOpening courseOp : courseOpenings) {
                            // Temukan course yang sesuai dengan course opening
                            Optional<Course> matchedCourse = courses.stream()
                        .filter(crs -> courseOp.getId().equals(crs.getCode()))
                         .findFirst(); 

                        // Jika course ditemukan, set informasi tambahan pada courseOp
                         matchedCourse.ifPresent(crs -> {
                         courseOp.setGrade(crs.getGrade());
                            courseOp.setStdProgram(crs.getName());
                        courseOp.setSks(crs.getCredits());
                         }); 

                        // Temukan lecturer yang sesuai dengan course opening
                        Optional<Lecturer> matchedLecturer = lecturers.stream()
                            .filter(lec -> courseOp.getInitial().equals(lec.getInitial()))
                            .findFirst(); 

                        // Jika lecturer ditemukan, set informasi tambahan pada courseOp
                        matchedLecturer.ifPresent(lec -> courseOp.setLecEmail(lec.getEmail())); 

                        // Tampilkan informasi course opening
                        System.out.println(courseOp);

                        // Tampilkan informasi enrollment yang sesuai dengan course opening
                        enrollments.stream()
                            .filter(enroll -> courseOp.getId().equals(enroll.getCourseID()) &&
                                            courseOp.getYear().equals(enroll.getYear()) && 
                                            courseOp.getSemester().equals(enroll.getSemester()))
                            .forEach(enroll -> {
                                if (enroll.getSebelum().equals("")) {
                                    System.out.println(enroll.toString());
                                } else {
                                    // System.out.println(enroll.toString() + "(" + enroll.getSebelum() + ")");
                                    System.out.println(enroll.toRemediString());
                                }
                            }); 
                        } 
  
                        break;                       
                    }
            }
        } 
        scan.close();

        // Print lecturers
        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer.getId() + "|" + lecturer.getName() + "|" + lecturer.getInitial() + "|" + lecturer.getEmail() + "|" + lecturer.getStudyprogram());
        }
 
 
        if(pers){
            // Print courses
            for (Course course : courses) {
            StringBuilder lecturerString = new StringBuilder();
            for (Lecturer lecturer : course.getLecturers()) {
                lecturerString.append(lecturer.getInitial()).append(" (").append(lecturer.getEmail()).append(");");
            }
            String lecturerList = lecturerString.toString().isEmpty() ? "" : lecturerString.toString().substring(0, lecturerString.length() - 1);
            System.out.println(course.getCode() + "|" + course.getName() + "|" + course.getCredits() + "|" + course.getGrade() + "|" + lecturerList);
        }
        }

        for(Course course : courses) {
            System.out.println(course.getCode() + "|" + course.getName() + "|" + course.getCredits() + "|" + course.getGrade());
        }

        // Print students

        for (Student student : students) {
            System.out.println(student.getId() + "|" + student.getName() + "|" + student.getAngkatan() + "|" + student.getProdi());
        }
  
        // Print enrollments
        for(Enrollment ers : new_Enrollments) {
            if (!ers.getSebelum().equals("")) {
                
                System.out.println(ers.toRemediString());
            } else {
                boolean found = false; 
                for (Enrollment enrs : enrollments) {
                    if (enrs.getCourseID().equals(ers.getCourseID())
                            && enrs.getStudentID().equals(ers.getStudentID())
                            && enrs.getYear().equals(ers.getYear())
                            && enrs.getSemester().equals(ers.getSemester())) {
                        found = true;                         
                        if (enrs.getSebelum().equals("")) {
                            System.out.println(enrs.toString());
                            break;
                        } else {
                            
                            System.out.println(enrs.toRemediString());

                            break;
                        }
                        //break;
                    }
                } 

                if (!found) {
                    System.out.println(ers.toString());
                }
            }
        }
        
                    }
        
    // Method untuk mendapatkan nilai numerik berdasarkan grade huruf
public static double getGrade(String grade) {
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
}  