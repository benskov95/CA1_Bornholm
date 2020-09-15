package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String studentId;
    private String favoriteSeries;

    public Member(String name, String studentId, String favoriteSeries) {
        this.name = name;
        this.studentId = studentId;
        this.favoriteSeries = favoriteSeries;
    }

    public Member() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFavoriteSeries() {
        return favoriteSeries;
    }

    public void setFavoriteSeries(String favoriteSeries) {
        this.favoriteSeries = favoriteSeries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entities.Member[ id=" + id + " ]";
    }
    
}
