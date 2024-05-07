package academic.model;

/**
 * @author 12S22026 Ruben Sianipar
 */
   
import java.util.Objects;

public class Lecturer {   
    private String id;
    private String name;
    private String initial;
    private String email;
    private String studyProgram;

    public Lecturer(String id, String name, String initial, String email, String studyProgram) {
        this.id = id;
        this.name = name; 
        this.initial = initial;
        this.email = email;
        this.studyProgram = studyProgram;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInitial() {
        return initial;
    }

    public String getEmail() {
        return email;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setId(String id) {
        this.id = id;
    } 
    


@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecturer lecturer = (Lecturer) o;
        return Objects.equals(id, lecturer.id) &&
               Objects.equals(name, lecturer.name) &&
               Objects.equals(initial, lecturer.initial) &&
               Objects.equals(email, lecturer.email) &&
               Objects.equals(studyProgram, lecturer.studyProgram);
    }

@Override
public int hashCode() {
    return Objects.hash(id, name, initial, email, studyProgram);
}
}