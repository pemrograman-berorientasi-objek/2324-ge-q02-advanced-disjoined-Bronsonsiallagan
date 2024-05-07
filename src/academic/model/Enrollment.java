package academic.model;

/**
 * @author 12S22025 - Bronson TM Siallagan
 * @author 12S22026 - Ruben Sianipar 
 */
public class Enrollment {
    private String courseID;
    private String studentID;
    private String year; 
    private String semester;
    private String grade; 
    private String sebelum;  
    private String remedial; 
    private Integer  totalnilai; 


    public Enrollment(String courseID, String studentID, String year, String semester, String grade) {
        this.courseID = courseID;
        this.studentID = studentID;
        this.year = year;
        this.semester = semester;  
        this.grade = grade;
        this.sebelum = "";
        this.remedial = null; 
        this.totalnilai = 0;     
         
    }
 
    public String getCourseID() { 
        return courseID;
    }

    public String getStudentID() { 
        return studentID;
    } 

    public String getYear() { 
        return year;  
    }

    public String getSemester() {  
        return semester;
    }

    public String getGrade() {
        return grade; 
    }

    public void setGrade(String grade) { 
        this.grade = grade; 
    } 

    public String getSebelum() {
        return sebelum;
    }
    public void setSebelum(String sebelum) {
       this.sebelum = sebelum;
    }

    public String getRemedial() {
        return remedial;
    }   

    public void setRemedial(String remedial) {
        this.remedial = remedial;
    }

    public int getTotalnilai() {
        return totalnilai;
    }

    public void setTotalnilai() {
        this.totalnilai += 1;  
    }

    public void tukarGrade() {
        String temp = ""; 
        temp = this.grade;
        this.grade = this.sebelum;
        this.sebelum = temp;
    }  

    @Override
    public String toString() {
        return this.courseID + "|" + this.studentID + "|" + this.year + "|" + this.semester + "|" + this.grade;
    }
    public String toRemediString() {
        return this.courseID + "|" + this.studentID + "|" + this.year + "|" + this.semester + "|" + this.grade + "(" +this.sebelum +")";
    } 

 
}
   