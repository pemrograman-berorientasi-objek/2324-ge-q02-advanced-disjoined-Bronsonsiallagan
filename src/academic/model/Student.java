package academic.model;

public class Student extends Inheritance {
    private int year;

    public Student(String id, String name, int year, String studyProgram) {
        super(id, name, null, studyProgram); 
        this.year = year;
    }  
    public int getYear() {
        return year;   
    }
    public void setYear(int year) {
        this.year = year;
    }
    public double calculateGPA(String academicYear, String semester) {
       
        return 4.0; 
    }
} 

