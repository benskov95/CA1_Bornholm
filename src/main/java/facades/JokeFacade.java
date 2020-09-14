package facades;

import dto.JokeDTO;
import entities.Joke;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;
    
    private JokeFacade() {}
    
    
    public static JokeFacade getJokeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Joke> getAllJokes() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery query =
                    em.createNamedQuery("Joke.getAllJokes", Joke.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    } 
    
    public Joke getJokeByID(int id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery query = 
                    em.createNamedQuery("Joke.getByID", Joke.class);
            query.setParameter("id", id);
            Joke joke = (Joke) query.getSingleResult();
            return joke;
        } finally {
            em.close();
        }
    }
    
    public Joke getRandomJoke() {
        List<Joke> referenceList = getAllJokes();
        Random random = new Random();
        int randomID = random.nextInt(referenceList.size() + 1); // +1 so there is no 0.
        return getJokeByID(randomID);
    }
    
    
    
    

}
