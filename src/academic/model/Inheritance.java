package academic.model;

import java.util.Objects;

public class Inheritance {
    private String id;
    private String name;
    private String email;
    private String studyProgram;

    public Inheritance(String id, String name, String email, String studyProgram) {
        this.id = id;   
        this.name = name;  
        this.email = email;
        this.studyProgram = studyProgram;
    }
    public String getId() { 
        return id;
    } 
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getStudyProgram() {
        return studyProgram;
    }
    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }   

  

@Override
public int hashCode() {
    return Objects.hash(id, name, email, studyProgram);
}
@Override
public boolean equals(Object o){
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Inheritance inheritance = (Inheritance) o;
    return Objects.equals(id, inheritance.id) &&
           Objects.equals(name, inheritance.name) &&
           Objects.equals(email, inheritance.email) &&
           Objects.equals(studyProgram, inheritance.studyProgram);
}

@Override
public String toString() {
    return id + "|" + name + "|" + email + "|" + studyProgram;
}
}


