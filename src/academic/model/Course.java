package academic.model;



/**
 * @author 12S22026 Ruben Sianipar
 */

public class Course {     
    private String id;
    private String name;
    private int credits; 
    private String grade;
    

    public Course(String id, String name, int credits, String grade) {
        this.id = id;
        this.name = name;
        this.credits = credits; 
        this.grade = grade;
    }
    

    // Tambahkan getter untuk setiap field (id, name, credits, grade)
    public String getId() {
        return id;
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

     @Override
    public String toString() {
        return id + "|" + name + "|" + credits + "|" + grade;
    }
}
 