package facades;

import entities.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    private MemberFacade() {

    }

    public static MemberFacade getMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Member> getAllMembers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery query
                    = em.createQuery("SELECT m FROM Member m", Member.class);
            List<Member> members = query.getResultList();
            return members;
        } finally {
            em.close();
        }
    }

    public Member getMemberById(int id) {
        EntityManager em = getEntityManager();
        try {
            Member member = em.find(Member.class, id);
            return member;
        } finally {
            em.close();
        }
    }

    public Member getMemberByStudentId(String studentId) {
        EntityManager em = getEntityManager();
        try {
            Member member = em.find(Member.class, studentId);
            return member;
        } finally {
            em.close();
        }
    }

    public List<Member> getMemberByName(String name) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT m FROM Member m WHERE m.name LIKE CONCAT('%',:name,'%')");
            query.setParameter("name", name);
            List<Member> members = query.getResultList();
            return members;
        } finally {
            em.close();
        }
    }

    public void createMember(String name, String studentId, String favoriteSeries) {
        EntityManager em = getEntityManager();
        Member member = new Member(name, studentId, favoriteSeries);
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
            em.persist(new Member("Mari Rødseth Haugen", "mh-823", "Deadwood"));
            em.persist(new Member("Benjamin Skovgaard", "bs-190", "Breaking Bad"));
            em.persist(new Member("Pelle Mathias Rasmussen", "pr-128", "Better Call Saul"));
            em.persist(new Member("Matt Grønnegaard Thomsen", "mt-312", "Black Mirror"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
