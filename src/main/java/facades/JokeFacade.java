package facades;

import entities.Joke;
import dto.JokeDTO;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
    
    public List<JokeDTO> getAllJokes() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery query =
                    em.createNamedQuery("Joke.getAllJokes", Joke.class);
            return JokeDTO.listToDTO(query.getResultList());
        } finally {
            em.close();
        }
    } 
    
    public JokeDTO getJokeByID(int id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery query = 
                    em.createNamedQuery("Joke.getByID", Joke.class);
            query.setParameter("id", id);
            JokeDTO joke = new JokeDTO((Joke) query.getSingleResult());
            return joke;
        } finally {
            em.close();
        }
    }
    
    public JokeDTO getRandomJoke() {
        Random random = new Random();
        EntityManager em = getEntityManager();
        try {
            TypedQuery query =
                    em.createNamedQuery("Joke.getAllJokes", Joke.class);
            TypedQuery query2 =
                    em.createNamedQuery("Joke.getCount", Joke.class);
        long count = (long) query2.getSingleResult();
        int randomNumber = random.nextInt((int) count);
        
        query.setFirstResult(randomNumber);
        query.setMaxResults(1);
        return new JokeDTO((Joke) query.getSingleResult());
        } finally {
            em.close();
        }
    }
    
    public void populateJokeTable() {
       Joke j1 = new Joke("What is the best thing about Switzerland?", "I do not know, but the flag is a big plus", "Family-friendly", 5);
       Joke j2 = new Joke("What is a quick way to get rid of a cold?", "Just stand in a corner, it is 90 degrees", "Math joke", 7);
       Joke j3 = new Joke("Have you heard about the new restaurant called Karma?", "There is no menu; you get what you deserve", "Deadpan", 8);
       EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
