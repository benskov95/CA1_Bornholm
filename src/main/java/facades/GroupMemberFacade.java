package facades;

import entities.GroupMember;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class GroupMemberFacade {

    private static GroupMemberFacade instance;
    private static EntityManagerFactory emf;

    private GroupMemberFacade() {

    }

    public static GroupMemberFacade getMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GroupMemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<GroupMember> getAllMembers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery query
                    = em.createQuery("SELECT g FROM GroupMember g", GroupMember.class);
            List<GroupMember> members = query.getResultList();
            return members;
        } finally {
            em.close();
        }
    }

    public GroupMember getMemberById(int id) {
        EntityManager em = getEntityManager();
        try {
            GroupMember member = em.find(GroupMember.class, id);
            return member;
        } finally {
            em.close();
        }
    }

    public GroupMember getMemberByStudentId(String studentId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery query = 
                    em.createQuery("SELECT g FROM GroupMember g WHERE g.studentId = :studentId", GroupMember.class);
           query.setParameter("studentId", studentId);
           GroupMember member = (GroupMember) query.getSingleResult();       
            return member;
        } finally {
            em.close();
        }
    }

    public List<GroupMember> getMemberByName(String name) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT g FROM GroupMember g WHERE g.name LIKE CONCAT('%',:name,'%')");
            query.setParameter("name", name);
            List<GroupMember> members = query.getResultList();
            return members;
        } finally {
            em.close();
        }
    }

    public void createMember(String name, String studentId, String favoriteSeries) {
        EntityManager em = getEntityManager();
        GroupMember member = new GroupMember(name, studentId, favoriteSeries);
        try {
            em.getTransaction().begin();
            em.persist(member);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void populateDB() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new GroupMember("Mari Rødseth Haugen", "mh-823", "Deadwood"));
            em.persist(new GroupMember("Benjamin Skovgaard", "bs-190", "Breaking Bad"));
            em.persist(new GroupMember("Pelle Mathias Rasmussen", "pr-128", "Better Call Saul"));
            em.persist(new GroupMember("Matt Grønnegaard Thomsen", "mt-312", "Black Mirror"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
