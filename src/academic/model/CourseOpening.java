package academic.model;
/**
 * @author 12S22025 - Bronson TM Siallagan
 * @author 12S22026 - Ruben Sianipar 
 */public class CourseOpening {
    private String id;
    private String stdProgram;
    private int sks;
    private String grade;
    private String year;
    private String semester;
    private String  initial;
    private String lecEmail;

    
    public CourseOpening(String id, String year, String semester,  String initial) {
        this.id = id;
        this.year = year;
        this.semester = semester;
        this.initial = initial;
        this.lecEmail = "";  
        this.stdProgram = "";
        this.sks = 0;
        this.grade = "";
        
    } 
 
    public String getId() {
        return id; 
    }

    public String getYear() { 
        return year;
    }

    public String getSemester() {
        return semester;
    }

    public String getInitial() {
        return initial; 
    }

    // set lecturer email
    public void setLecEmail(String lecEmail) {
        this.lecEmail = lecEmail;  
    } 
    // set grade
    public void setGrade(String grade) { 
        this.grade = grade;
    } 
   
    // set student program
    public void setStdProgram(String stdProgram) {
        this.stdProgram = stdProgram;
    }

    // set sks 
    public void setSks(int sks) {
        this.sks = sks; 
    }

    // toString 12S1101|Dasar Sistem Informasi|3|D|2020/2021|odd|IUS (iustisia.simbolon@del.ac.id)
    public String toString() {
        return id + "|" + stdProgram + "|" + sks + "|" + grade + "|" + year + "|" + semester + "|" + initial + " (" + lecEmail+ ")";
    }    

}    