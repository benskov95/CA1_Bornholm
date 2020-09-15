package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery (name = "GroupMember.deleteAllRows", query = "DELETE FROM GroupMember")
public class GroupMember implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String studentId;
    private String favoriteSeries;

    public GroupMember(String name, String studentId, String favoriteSeries) {
        this.name = name;
        this.studentId = studentId;
        this.favoriteSeries = favoriteSeries;
    }

    public GroupMember() {
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
