package academic.model;

import java.util.Objects;

/**
 * @author 12S22026 Ruben Sianipar
 * 12S22025 Bronson Siallagan
 */

public class Enrollment {
    private String student;
    private String course;
    private String academicYear;
    private String semester;
    private String grade; 
    private String previousGrade; 
    private boolean isRemedialDone;

    // Constructor for creating an enrollment object with all fields set to the values passed as parameters
    public Enrollment(String s, String c, String aY, String sem ) {
        this.student = s;
        this.course = c;
        this.academicYear = aY; 
        this.semester = sem;
        this.grade = "None";  
        this.isRemedialDone= false;
        
        
    }


     public void setStudent(String student) {
        this.student = student;
    }   

    public boolean isRemedialGrade() {
    return previousGrade != null && isRemedialDone;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setGrade(String grade) {
    if (!this.grade.equals("None") && !isRemedialDone) {
        this.previousGrade = this.grade;
        this.isRemedialDone = true;
    }
    this.grade = grade;
}

    public String getStudent() {
        return student;
    }

    public String getCourse() {
        return course;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getSemester() {
        return semester;
    }

    public String getGrade() {
        return grade;

    }

    

    public String getPreviousGrade() {
        return previousGrade;
    }

    public void setPreviousGrade(String previousGrade) {
        this.previousGrade = previousGrade;
    }

    public void setRemedialDone(boolean isRemedialDone) {
        this.isRemedialDone = isRemedialDone;
    }

    public boolean isRemedialDone() {
        return isRemedialDone;
    }

    public String swapgrade (String grade) {
        String temp = this.previousGrade;
        this.previousGrade = grade;
        return temp;
    }
        
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment enrollment = (Enrollment) o;
        return Objects.equals(student, enrollment.student) &&
               Objects.equals(course, enrollment.course) &&
               Objects.equals(academicYear, enrollment.academicYear) &&
               Objects.equals(semester, enrollment.semester) &&
               Objects.equals(grade, enrollment.grade);
    }

    @Override
    public String toString() {
        return this.course + "|" + this.student + "|" + academicYear + "|" + semester + "|" + grade + "|" + previousGrade;
    }


    

}
