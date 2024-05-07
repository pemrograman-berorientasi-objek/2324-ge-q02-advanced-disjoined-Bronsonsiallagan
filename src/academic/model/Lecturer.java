package academic.model;

/**
 * @author 12S22025 - Bronson TM Siallagan
 * @author 12S22026 - Ruben Sianipar 
 */
public class Lecturer extends AcademicPerson {

    // class definition     
    private String intial; 
    private String email;
    private String studyprogram;  
 
    //konstruktor 
    public Lecturer(String id, String name, String intial, String email, String studyprogram) {
        super.id = id;
        super.name = name; 
        this.intial = intial;
        this.email = email;  
        this.studyprogram = studyprogram; 
    }

    public String getId() {   
        return id; 
    }    
 
    public String getName() {
        return name; 
    } 

    public String getInitial() { 
        return intial; 
    }

    public String getEmail() {   
        return email;  
    }  
 
    public String getStudyprogram() { 
        return studyprogram;
    } 
}
      