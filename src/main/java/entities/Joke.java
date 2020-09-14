package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries ({
@NamedQuery(name = "Joke.deleteAllRows", query = "DELETE from Joke"),
@NamedQuery(name = "Joke.getAllJokes", query = "SELECT j from Joke j"),
@NamedQuery(name = "Joke.getByID", query = "SELECT j FROM Joke j WHERE j.id = :id")
})
public class Joke implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id;
    private String joke;
    private String punchLine;
    private String type;
    private int devRating;
    
    public Joke() {
    }

    public Joke(String joke, String punchLine, String type, int devRating) {
        this.joke = joke;
        this.punchLine = punchLine;
        this.type = type;
        this.devRating = devRating;
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getPunchLine() {
        return punchLine;
    }

    public void setPunchLine(String punchLine) {
        this.punchLine = punchLine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDevRating() {
        return devRating;
    }

    public void setDevRating(int devRating) {
        this.devRating = devRating;
    }
    
    
}
