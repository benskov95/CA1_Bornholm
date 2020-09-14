package facades;

import utils.EMF_Creator;
import entities.Joke;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//@Disabled
public class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;
    private static Joke j1;
    private static Joke j2;
    private static Joke j3;

    public JokeFacadeTest() {
    }

    /* moved test joke creation up here because creating new jokes
        for each test method made the getRandom() method not work. */
    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = JokeFacade.getJokeFacade(emf);
       j1 = new Joke("What's the best thing about Switzerland?", "I don't know, but the flag is a big plus", "Family-friendly", 5);
       j2 = new Joke("What's a quick way to get rid of a cold?", "Just stand in a corner, it's 90 degrees", "Math joke", 7);
       j3 = new Joke("Have you heard about the new restaurant called Karma?", "There's no menu; you get what you deserve", "Deadpan", 8);
       
        EntityManager em = emf.createEntityManager();
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

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetAllJokes() {
        int expectedSize = 3;
        List<Joke> jokes = facade.getAllJokes();
        assertEquals(expectedSize, jokes.size());
    }
    
    @Test
    public void testGetJokeByID() {
        Joke joke = facade.getJokeByID(j1.getId());
        assertEquals(j1.getPunchLine(), joke.getPunchLine());
    }
    
    @Test
    public void testGetRandomJoke() {
        Joke joke = facade.getRandomJoke();
        int mustContainSomething = 5;
        assertTrue(joke.getJoke().length() > mustContainSomething);
    }

}
