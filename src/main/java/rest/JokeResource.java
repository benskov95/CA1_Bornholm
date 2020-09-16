package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.JokeDTO;
import entities.Joke;
import utils.EMF_Creator;
import facades.JokeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("joke")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final JokeFacade FACADE =  JokeFacade.getJokeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
   
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes() {
        try {
            List<Joke> jokes = FACADE.getAllJokes();
            String jsonString = GSON.toJson(JokeDTO.listToDTO(jokes));
            return jsonString;
        } catch (Exception e) {
            return "ERROR: Something went wrong.";
        }
    }
    
    @GET
    @Path("/by_id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeByID(@PathParam("id") int id) {
        try {
            Joke joke = FACADE.getJokeByID(id);
            JokeDTO jokeDTO = new JokeDTO(joke);
            String jsonString = GSON.toJson(jokeDTO);
            return jsonString;
        } catch (Exception e) {
            return "ERROR: The movie with the specified ID (" + id + ") does not exist.";
        }
    }
    
    @GET
    @Path("/random")
    @Produces({MediaType.APPLICATION_JSON})
    public String getRandomJoke() {
        try {
            Joke joke = FACADE.getRandomJoke();
            String jsonString = GSON.toJson(joke);
            return jsonString;
        } catch (Exception e) {
            return "ERROR: Something went wrong.";
        }
    }
    
    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populateJokeTable() {
        try {
            FACADE.populateJokeTable();
            List<Joke> jokes = FACADE.getAllJokes();
            String jsonString = GSON.toJson("Added 3 jokes to DB:\n" + jokes);
            return jsonString;
        } catch (Exception e) {
            return "ERROR: Something went wrong.";
        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
}
