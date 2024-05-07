package academic.model;

/**
 * @author 12S22025 - Bronson TM Siallagan
 * @author 12S22026 - Ruben Sianipar 
 */ 
public class Student extends AcademicPerson {
 
    //buat atribut
    private String angkatan;
    private String prodi;   
 
    //buat konstruktor 
    public Student(String id, String name, String angkatan, String prodi){
        super.id = id;
        super.name = name; 
        this.prodi = prodi;  
        this.angkatan = angkatan; 
    }
 
    public String getId(){    
        return id;    
    }  
   
    public String getName(){  
        return name; 
    } 

    public String getAngkatan(){
        return angkatan; 
    }    
 
    public String getProdi(){ 
        return prodi; 
    }  
 
    public void setAngkatan(String angkatan){
        this.angkatan = angkatan;
    }
}
  