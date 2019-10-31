package control;

import control.MembersEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class PeopleController {
    private EntityManagerFactory emf;
    public PeopleController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public void create(MembersEntity person) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
    }
    public void findByName(String personName) {
//        EntityManager em = emf.createEntityManager();
//        Query query = em.createQuery("select email from members");
//        List persons = query.setParameter("name", personName).getResultList();
//        em.close();
//        return persons.isEmpty() ? null : (MembersEntity) persons.get(0);
    }
}