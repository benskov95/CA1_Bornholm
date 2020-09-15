package facades;

import utils.EMF_Creator;
import entities.GroupMember;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class GroupMemberFacadeTest {

    private static EntityManagerFactory emf;
    private static GroupMemberFacade facade;
    private static GroupMember m1 = new GroupMember("Peter Petersen", "pp-333", "Dexters Laboratorium");
    private static GroupMember m2 = new GroupMember("Lars Larsen", "ll-444", "Johnny Bravo");

    public GroupMemberFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = GroupMemberFacade.getMemberFacade(emf);
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
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
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
        List<GroupMember> members = facade.getAllMembers();
        assertEquals(2, members.size());
    }

    @Test
    public void testGetMemberById() {
        int id = m1.getId();
        GroupMember member = facade.getMemberById(id);
        assertEquals(m1.getName(), member.getName());
    }

    @Test
    public void testGetMemberByStudentId() {
        String studentId = m1.getStudentId();
        GroupMember member = facade.getMemberByStudentId(studentId);
        assertEquals(m1.getName(), member.getName());
    }

    @Test
    public void testGetMemberByName() {
        String name = m1.getName();
        List<GroupMember> members = facade.getMemberByName(name);
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
