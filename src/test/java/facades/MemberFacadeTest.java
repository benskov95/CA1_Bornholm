package facades;

import utils.EMF_Creator;
import entities.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MemberFacadeTest {

    private static EntityManagerFactory emf;
    private static MemberFacade facade;
    private static Member m1 = new Member("Peter Petersen", "pp-333", "Dexters Laboratorium");
    private static Member m2 = new Member("Lars Larsen", "ll-444", "Johnny Bravo");

    public MemberFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MemberFacade.getMemberFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Member.deleteAllRows").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetAllMembers() {
        List<Member> members = facade.getAllMembers();
        assertEquals(2, members.size());
    }

    @Test
    public void testGetMemberById() {
        int id = m1.getId();
        Member member = facade.getMemberById(id);
        assertEquals(m1.getName(), member.getName());
    }

    @Test
    public void testGetMemberByStudentId() {
        String studentId = m1.getStudentId();
        Member member = facade.getMemberByStudentId(studentId);
        assertEquals(m1.getName(), member.getName());
    }

    @Test
    public void testGetMemberByName() {
        String name = m1.getName();
        List<Member> members = facade.getMemberByName(name);
        assertEquals(name, members.get(0).getName());
    }

    @Test
    public void testCreateMember() {
        String name = "Mads Madsen";
        String studentId = "mm-555";
        String favoriteSeries = "Borgen";
        facade.createMember(name, studentId, favoriteSeries);
        assertEquals(3, facade.getAllMembers().size());
    }
}
