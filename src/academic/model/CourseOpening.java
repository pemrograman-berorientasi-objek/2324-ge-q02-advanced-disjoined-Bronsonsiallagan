package academic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 12S22026 Ruben Sianipar
 * 12S22025 Bronson Siallagan
 */

public class CourseOpening {     
    private String id; 
    private String academicYear;
    private String semester;
    private List<Lecturer> lecturers = new ArrayList<>();

    public CourseOpening(String id, String academicYear, String semester, List<Lecturer> lecturers) {
        this.id = id;
        this.academicYear = academicYear;
        this.semester = semester;

     
    }
    public void addLecturer(Lecturer lecturer) {
        lecturers.add(lecturer);
    }

    public List<Lecturer> getLecturers() {
    return lecturers;
}

    // Tambahkan getter untuk setiap field (id, name, credits, grade)
    public String getId() {
        return id;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getSemester() {
        return semester;
    }   

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseOpening courseOpening = (CourseOpening) o;
        return id.equals(courseOpening.id) &&
                academicYear.equals(courseOpening.academicYear) &&
                semester.equals(courseOpening.semester);
    }


     
}
 