package academic.model;


/**
 * @author 12S22025 - Bronson TM Siallagan
 * @author 12S22026 - Ruben Sianipar 
 */
import java.util.ArrayList; 

public class Course extends AcademicPerson{

    private int credits;
    private String grade;
    private ArrayList<Lecturer> lecturers;

    public Course(String code, String _name, int credits, String grade) {
        super.id = code;
        super.name = _name; 
        this.credits = credits; 
        this.grade = grade;  
  
    } 

    public String getCode() {   
        return super.id; 
    }   
 
    public String getName() {
        return name; 
    }  

    public int getCredits() {
        return credits;   
    } 

    public String getGrade() {
        return grade;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }
} 
     