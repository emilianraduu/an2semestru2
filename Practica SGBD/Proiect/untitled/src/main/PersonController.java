package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PersonController {
    private EntityManagerFactory emf;
    private static MembersEntity loggedUser;
    private static String loginInfo = "login-info.txt";

    public PersonController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public MembersEntity getLoggedUser(){
        return loggedUser;
    }

    public void create(MembersEntity person) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
    }

    public MembersEntity findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from MembersEntity t where t.email like :email");
        MembersEntity persons = (MembersEntity) query.setParameter("email", email).getSingleResult();
        em.close();
        return persons;
    }

    public Boolean checkUser(String email, String passwd) {
        MembersEntity temp = findByEmail(email);
        boolean passwordMatch = PasswordUtils.verifyUserPassword(passwd, temp.getPasswd(), temp.getSalt());

        if (passwordMatch) {
            try {
                File file;
                String content = temp.getEmail() + " " + temp.getSalt();
                file = new File(loginInfo);
                FileOutputStream fop = new FileOutputStream(file);
                if (!file.exists()) {
                    file.createNewFile();
                }
                byte[] contentInBytes = content.getBytes();
                fop.write(contentInBytes);
                fop.flush();
                fop.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            loggedUser = temp;
            return true;
        } else return false;
    }

    public void addFriend(MembersEntity id1, MembersEntity id2) {
        EntityManager em = emf.createEntityManager();
        FriendsEntity f = new FriendsEntity(id1, id2);
        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
        em.close();
    }

    public void removeFriend(MembersEntity id1, MembersEntity id2) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from FriendsEntity t where t.idMember1 like :id and t.idMember2 like :id2");
        query.setParameter("id", id1.getIdMember());
        query.setParameter("id2", id2.getIdMember());
        FriendsEntity f = (FriendsEntity) query.getSingleResult();
        em.getTransaction().begin();
        em.remove(f);
        em.getTransaction().commit();
        em.close();
    }

    public List<FriendsEntity> getAllFriendsByEntity(MembersEntity membersEntity) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from FriendsEntity t where t.idMember1 like :member");
        List<FriendsEntity> persons = query.setParameter("member", membersEntity.getIdMember()).getResultList();
        em.close();
        return persons;
    }

    public List<FriendsEntity> getAllFriendsByMail(String email) {
        EntityManager em = emf.createEntityManager();
        long s = findByEmail(email).getIdMember();
        Query query = em.createQuery("select t from FriendsEntity t where t.idMember1 like :email");
        List persons = query.setParameter("email", s).getResultList();
        em.close();
        return (List<FriendsEntity>) persons;
    }

    public List<MembersEntity> findPeopleByName(String fname, String lname) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from MembersEntity t where t.fname like :fname and t.lname like :lname");
        List persons = query.setParameter("fname", fname).setParameter("lname", lname).getResultList();
        em.close();
        return (List<MembersEntity>) persons;
    }

    public String getLoginInfo() {
        return loginInfo;
    }
}
