package dto;

import entities.Joke;
import java.util.ArrayList;
import java.util.List;

public class JokeDTO {
    
    private String joke;
    private String punchLine;

    public JokeDTO(Joke joke) {
        this.joke = joke.getJoke();
        this.punchLine = joke.getPunchLine();
    }
    
    public static List<JokeDTO> listToDTO(List<Joke> jokes) {
        List<JokeDTO> dtoJokes = new ArrayList<>();
        jokes.forEach(j -> {
            dtoJokes.add(new JokeDTO(j));
        });
        return dtoJokes;
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
    
    
    
}
